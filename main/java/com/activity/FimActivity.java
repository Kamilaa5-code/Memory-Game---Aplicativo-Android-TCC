package com.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Kamila Oliveira on 17/02/2016.
 * Esta Classe é executada após o término de um jogo.
 * Nela são exibidos o nome do jogador, a quantidade de clicks
 * e o tempo que o usuário levou para finalizar o jogo.
 */
public class FimActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // Método responsável por inicializar a classe,
        // definindo o layout da activity.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fim);

        // Define os campos e relaciona os mesmos ao layout.
        TextView tempo = (TextView) findViewById(R.id.tempo);
        TextView nome = (TextView) findViewById(R.id.nome);
        TextView cronometro = (TextView) findViewById(R.id.cronometro);
        Button recordes = (Button) findViewById(R.id.recordes);
        Button novoJogo = (Button) findViewById(R.id.jogar);

        // Busca o tempo do Intent que foi passado e calcula os minutos e segundos
        long segundos = getIntent().getLongExtra("tempo", 0) % 60;
        long minutos = getIntent().getLongExtra("tempo", 0) / 60;
        String formatarSegundos = "";
        String formatarMinutos = "";

        // Verifica se os minutos ou segundos são iguais a 0 e acrescenta mais um 0
        if(segundos < 10){
            formatarSegundos = String.format("0%s", segundos);
        }else {
            formatarSegundos = String.format("%s", segundos);
        }
        if (minutos < 10){
            formatarMinutos = String.format("0%s", minutos);
        }else {
            formatarMinutos = String.format("%s", minutos);
        }

        // Define os valores dos campos a serem apresentados em tela.
        tempo.append("Jogadas: "+(String.valueOf(getIntent().getIntExtra("clicks", 0))));
        nome.append("Jogador: "+(String.valueOf(getIntent().getStringExtra("nome"))));
        cronometro.append("Tempo: "+(formatarMinutos + ":" + formatarSegundos));

        recordes.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Define a ação a ser executada caso o usuário selecione a opção definida.
                startActivity(new Intent(FimActivity.this, RecordeActivity.class));
                finish();
            }
        });

        novoJogo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Define a ação a ser executada caso o usuário selecione a opção definida.
                startActivity(new Intent(FimActivity.this, NovoActivity.class));
                finish();
            }
        });

    }

}
