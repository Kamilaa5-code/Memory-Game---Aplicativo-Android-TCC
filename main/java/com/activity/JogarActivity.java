package com.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.banco.DBAdapter;
import com.memory.Carta;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by kamila on 06/01/2016.
 * Esta classe é a mais importante, pois é nela que esta o jogo.
 * Ela embaralha as cartas, vira-as e desvira-as, além de compara-las se são iguais,
 * verifica se o jogo chegou ao fim e calcula o tempo e a quantidade de clicks.
 * calcula o tempo e a quantidade de clicks, etc.
 */
public class JogarActivity extends Activity {
    private static int ROW_COUNT = -1;
    private static int COL_COUNT = -1;
    private int x, y;
    private int fim = 0;
    private int[][] cards;
    private int turns = 0;
    private TableLayout mainTable;
    private UpdateCardsHandler handler;
    private Context context;
    private Drawable backImage;
    private List<Drawable> images;
    private SharedPreferences SP;
    private Carta firstCard = null;
    private Carta seconedCard;
    private ButtonListener buttonListener;
    private Chronometer chrono;
    private String nivel;
    private String nome;
    private static Object lock = new Object();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // Método responsável por inicializar a classe,
        // definindo o layout da activity.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogar);

        nome = getIntent().getStringExtra("Nome").toString();
        //Chamada das preferences
        SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        //carrega a lista de imagens
        loadImages();
        //imagem da carta quando esta virada para baixo
        backImage = getResources().getDrawable(R.drawable.carta);
        // transforma as imagens em botões, para que possam ser viradas as cartas do game
        buttonListener = new ButtonListener();
        //Define a tabela de layout declarada no xml
        mainTable = (TableLayout) findViewById(R.id.TableJogo);
        //pega o contexto da maintable
        context = mainTable.getContext();
        //coloca na string nivel, o valor da preferencia nivel
        nivel = SP.getString("nivel", "1");

        //verifica a string nivel e define a quant. de cartas presentes nas linhas e colunas
        switch (nivel) {
            //se nivel for igual a 1, passa os valores de x = 4 e y = 4
            case "1":
                mainTable.setPadding(95, 95, 95, 95);
                x = 4;
                y = 4;
                break;
            case "2":
                mainTable.setPadding(60, 60, 60, 60);
                x = 4;
                y = 6;
                break;
            case "3":
                mainTable.setPadding(15, 15, 15, 15);
                x = 6;
                y = 6;
                break;
            default:
                return;
        }
        //chama a classe new game passando a quantidade de cartas das linhas e colunas
        newGame(x, y);

        long tempo = (SystemClock.elapsedRealtime() - chrono.getBase()) / 1000;
        ((TextView) findViewById(R.id.jogador)).setText("Jogador: " + nome);

    }

    private void newGame(int r, int c) {
        // declara o row_count e col+count com os valores passados de x e y
        ROW_COUNT = r;
        COL_COUNT = c;

        //define a variavel cards com os arrays inteiros de COL_COUNT e ROW_COUNT
        cards = new int[COL_COUNT][ROW_COUNT];
        //chama a classe UpdateCardsHandler para executar a comparação em outro processo
        handler = new UpdateCardsHandler();

        //for responsável por criar a view da tabela de acordo com o row_count
        for (int y = 0; y < ROW_COUNT; y++) {
            //adiciona na tabla o retorno do método createRow
            mainTable.addView(createRow(y));
        }
        //chama o método loadCards
        loadCards();

        // inicia o contador de tempo
        chrono = ((Chronometer) findViewById(R.id.chronometer));
        chrono.setBase(SystemClock.elapsedRealtime());
        chrono.start();


    }

    private void loadImages() {
        //cria a lista de imagens
        images = new ArrayList<Drawable>();
        //coloca na string tema, o valor referente ao tema selecionado
        String temas = SP.getString("temas", "1");
        //verifica a string tema e define o tema das cartas
        switch (temas) {

            case "1":
                //carrega as imagens na lista de imagens
                images.add(getResources().getDrawable(R.drawable.animais01));
                images.add(getResources().getDrawable(R.drawable.animais02));
                images.add(getResources().getDrawable(R.drawable.animais03));
                images.add(getResources().getDrawable(R.drawable.animais04));
                images.add(getResources().getDrawable(R.drawable.animais05));
                images.add(getResources().getDrawable(R.drawable.animais06));
                images.add(getResources().getDrawable(R.drawable.animais07));
                images.add(getResources().getDrawable(R.drawable.animais08));
                images.add(getResources().getDrawable(R.drawable.animais09));
                images.add(getResources().getDrawable(R.drawable.animais10));
                images.add(getResources().getDrawable(R.drawable.animais11));
                images.add(getResources().getDrawable(R.drawable.animais12));
                images.add(getResources().getDrawable(R.drawable.animais13));
                images.add(getResources().getDrawable(R.drawable.animais14));
                images.add(getResources().getDrawable(R.drawable.animais15));
                images.add(getResources().getDrawable(R.drawable.animais16));
                images.add(getResources().getDrawable(R.drawable.animais17));
                images.add(getResources().getDrawable(R.drawable.animais18));
                break;

            case "2":
                //carrega as imagens na lista de imagens
                images.add(getResources().getDrawable(R.drawable.frutas01));
                images.add(getResources().getDrawable(R.drawable.frutas02));
                images.add(getResources().getDrawable(R.drawable.frutas03));
                images.add(getResources().getDrawable(R.drawable.frutas04));
                images.add(getResources().getDrawable(R.drawable.frutas05));
                images.add(getResources().getDrawable(R.drawable.frutas06));
                images.add(getResources().getDrawable(R.drawable.frutas07));
                images.add(getResources().getDrawable(R.drawable.frutas08));
                images.add(getResources().getDrawable(R.drawable.frutas09));
                images.add(getResources().getDrawable(R.drawable.frutas10));
                images.add(getResources().getDrawable(R.drawable.frutas11));
                images.add(getResources().getDrawable(R.drawable.frutas12));
                images.add(getResources().getDrawable(R.drawable.frutas13));
                images.add(getResources().getDrawable(R.drawable.frutas14));
                images.add(getResources().getDrawable(R.drawable.frutas15));
                images.add(getResources().getDrawable(R.drawable.frutas16));
                images.add(getResources().getDrawable(R.drawable.frutas17));
                images.add(getResources().getDrawable(R.drawable.frutas18));
                break;
            case "3":
                //carrega as imagens na lista de imagens
                images.add(getResources().getDrawable(R.drawable.numeros01));
                images.add(getResources().getDrawable(R.drawable.numeros02));
                images.add(getResources().getDrawable(R.drawable.numeros03));
                images.add(getResources().getDrawable(R.drawable.numeros04));
                images.add(getResources().getDrawable(R.drawable.numeros05));
                images.add(getResources().getDrawable(R.drawable.numeros06));
                images.add(getResources().getDrawable(R.drawable.numeros07));
                images.add(getResources().getDrawable(R.drawable.numeros08));
                images.add(getResources().getDrawable(R.drawable.numeros09));
                images.add(getResources().getDrawable(R.drawable.numeros10));
                images.add(getResources().getDrawable(R.drawable.numeros11));
                images.add(getResources().getDrawable(R.drawable.numeros12));
                images.add(getResources().getDrawable(R.drawable.numeros13));
                images.add(getResources().getDrawable(R.drawable.numeros14));
                images.add(getResources().getDrawable(R.drawable.numeros15));
                images.add(getResources().getDrawable(R.drawable.numeros16));
                images.add(getResources().getDrawable(R.drawable.numeros17));
                images.add(getResources().getDrawable(R.drawable.numeros18));
                break;
        }

    }

    private void loadCards() {
        try {
            //define o numero de cartas que serão carregadas
            int size = ROW_COUNT * COL_COUNT;
            //cria um array para salvar as cartas que serão utilizadas
            ArrayList<Integer> list = new ArrayList<Integer>();
            //adiciona na lista a quantidade de sizes
            for (int i = 0; i < size; i++) {

                list.add(new Integer(i));
            }
            Collections.shuffle(list);
            //for para embaralhar os cards. Este pega o size-1 e vai diminuindo 1
            for (int i = size - 1; i >= 0; i--) {
                //define o t = 0
                int t = 0;

                // remove o t da lista para que o mesmo não se repita
                t = list.remove(t).intValue();
                //adiciona o t em uma linha e coluna especifica
                cards[i % COL_COUNT][i / COL_COUNT] = t % (size / 2);



            }
        } catch (Exception e) {
            Log.e("loadCards()", e + "");
        }

    }

    private TableRow createRow(int y) {
        //Adiciona a tabela row
        TableRow row = new TableRow(context);
        //Cria um for de acordo com o col_count
        for (int x = 0; x < COL_COUNT; x++) {
            //adiciona na row os botões
            row.addView(createImageButton(x, y));
        }
        //retorna a tabela
        return row;
    }

    private View createImageButton(int x, int y) {
        //cria o button e define as caracteristicas do mesmo
        Button button = new Button(context);
        button.setBackgroundDrawable(backImage);
        button.setHeight(85);
        button.setWidth(85);
        button.setId(100 * x + y);
        button.setPadding(2, 2, 2, 2);
        //ao clicar chama o metodo buttonListener
        button.setOnClickListener(buttonListener);

        //retorna o button
        return button;
    }

    class ButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            //sincroniza o objeto lock
            synchronized (lock) {
                //verifica se nenhum dos cards são vazios
                if (firstCard != null && seconedCard != null) {
                    return;
                }
                int id = v.getId();
                int x = id / 100;
                int y = id % 100;
                turnCard((Button) v, x, y);
            }

        }

        private void turnCard(Button button, int x, int y) {
            button.setBackgroundDrawable(images.get(cards[x][y]));

            if (firstCard == null) {
                firstCard = new Carta(button, x, y);
            } else {

                if (firstCard.x == x && firstCard.y == y) {
                    return; //the user pressed the same card
                }

                seconedCard = new Carta(button, x, y);

                turns++;
                ((TextView) findViewById(R.id.turns)).setText("Jogadas: "+turns);

                TimerTask tt = new TimerTask() {

                    @Override
                    public void run() {
                        try {
                            synchronized (lock) {
                                handler.sendEmptyMessage(0);
                            }
                        } catch (Exception e) {
                            Log.e("E1", e.getMessage());
                        }
                    }
                };

                Timer t = new Timer(false);
                t.schedule(tt, 1300);

            }


        }

    }

    class UpdateCardsHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            // sincroniza o objeto lock
            synchronized (lock) {
                //chama o metodo checkCards para verificar se os cards são pares
                checkCards();
            }
        }

        public void checkCards() {
            //verifica se a primeira carta selecionada é igual a segunda carta selecionada
            if (cards[seconedCard.x][seconedCard.y] == cards[firstCard.x][firstCard.y]) {
                //se for igual, o botão da primeira e segunda carta ficam invisiveis
                firstCard.button.setVisibility(View.VISIBLE);
                seconedCard.button.setVisibility(View.VISIBLE);
                verificaFim();
            } else {
                //Se não forem iguais, as cartas são desviradas novamente
                seconedCard.button.setBackgroundDrawable(backImage);
                firstCard.button.setBackgroundDrawable(backImage);

            }
            //a primeira e a segunda tarta tornam-se nulas novamente
            firstCard = null;
            seconedCard = null;

        }

    }

    public void verificaFim() {

        if(((nivel.equals("1"))&&(fim == 7)) ||
           ((nivel.equals("2"))&&(fim == 11)) ||
           ((nivel.equals("3"))&&(fim == 17))){
            chrono.stop(); // para o cronometro

            // divide o tempo em milisegundos por 1000 para encontrar os segundos
            long tempo = (SystemClock.elapsedRealtime() - chrono.getBase()) / 1000;

            DBAdapter db = new DBAdapter(this);
            db.open();
            db.insereRecorde(nome, turns, tempo);
            db.close();

            Intent intent = new Intent(JogarActivity.this, FimActivity.class);
            intent.putExtra("clicks", turns);
            intent.putExtra("nome", nome);
            intent.putExtra("tempo", tempo);
            startActivity(intent);
            finish();

        }
        fim ++;
    }

}