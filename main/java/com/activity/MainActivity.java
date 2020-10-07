package com.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.app.AlertDialog;
import android.view.View;

/**
 * Created by kamila on 05/01/2016.
 * Esta é a principal classe do sistema.
 * Através desta classe, o sistema permite o acesso a todas as demais classes do sistema.
 */

public class MainActivity extends Activity {

private AlertDialog alerta;
private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Método responsável por inicializar a classe,
        // definindo o layout da activity.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    public void onClick(View view) {
        // Este método contém as opções do menu, definidas por botões.
        // Estes botões são acessados através do id definido no layout da página.
        // A partir deste método, o usuário acessa todas as activitys do sistema.
        switch (view.getId()) {
            case R.id.jogar:
                intent = new Intent(this, NovoActivity.class);
                startActivity(intent);
                break;
            case R.id.instrucoes:
                intent = new Intent(this, InstrucoesActivity.class);
                startActivity(intent);
                break;
            case R.id.recorde:
                intent = new Intent(this, RecordeActivity.class);
                startActivity(intent);
                break;
            case R.id.configuraoes:
                intent = new Intent(this, ConfiguracoesActivity.class);
                startActivity(intent);
                break;
            case R.id.sobre:
                intent = new Intent(this, SobreActivity.class);
                startActivity(intent);
                break;
            case R.id.sair:
                fechar();
                break;
        }
    }

    private void fechar() {
        // Este método exibe um alerta caso o usuário tenha selecionado a opção fechar do menu.
        // Este alerta permite ao uauário escolher se deseja realmente encerrar o jogo ou não.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.alerta);
        builder.setInverseBackgroundForced(true);
        builder.setMessage(R.string.msgFechar);
        builder.setPositiveButton(R.string.btNao,
            new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                }
            });
        builder.setNegativeButton(R.string.btSim,
            new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    finish();
                }
            });
        alerta = builder.create();
        alerta.show();
    }
}
