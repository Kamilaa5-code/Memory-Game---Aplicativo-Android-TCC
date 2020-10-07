package com.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Toast;

/**
 * Created by kamila on 14/01/2016.
 * Classe que exibe a tela com as informações sobre o jogo.
 */
public class SobreActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Método responsável por inicializar a classe,
        // definindo o layout da activity.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sobre);
    }
}