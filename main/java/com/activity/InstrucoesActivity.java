package com.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Toast;

/**
 * Created by kamila on 13/01/2016.
 * Classe que exibe a tela com as instruções de como jogar.
 */
public class InstrucoesActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Método responsável por inicializar a classe,
        // definindo o layout da activity.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instrucoes);

    }
}
