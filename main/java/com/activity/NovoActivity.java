package com.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by kamila on 11/01/2016.
 * Classe que inicia antes de um novo jogo.
 * Nesta o usuário deve informar o nome, para que apartir deste, sejam exibidos os recordes.
 */
public class NovoActivity extends Activity {

    private Intent intent;
    private AlertDialog alerta;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // Método responsável por inicializar a classe,
        // definindo o layout da activity.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo);

        // Define os campos e relaciona os mesmos ao layout.
        Button Jogar = (Button) findViewById(R.id.jogar);
        final EditText nome = (EditText) findViewById(R.id.nome);

        Jogar.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // Caso o usuário selecione a opção jogar,
                // o sistema faz uma verificação para saber
                // se o mesmo realmente informou o campo nome.
                // Caso o usuário não tenha informado o campo,
                // o sistema irá exibir um alerta informando que
                // o campo é de preenchimento obrigatório e impedindo-o de prosseguir.
                // Caso o usuário tenha informado o campo,
                // o sistema irá posseguir executando a classe JogoActivity.

                if(nome.getText().toString().trim().equals("") || nome.getText().toString().trim().equals("null")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(NovoActivity.this);
                    builder.setTitle(R.string.alerta);
                    builder.setInverseBackgroundForced(true);
                    builder.setMessage(R.string.valida);
                    builder.setNeutralButton(R.string.btOk,
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                }
                            });
                    alerta = builder.create();
                    alerta.show();
                }else{
                    intent = new Intent(NovoActivity.this, JogarActivity.class);
                    intent.putExtra("Nome", nome.getText().toString());
                    startActivity(intent);
                    finish();
                }

            }
        });
    }
}