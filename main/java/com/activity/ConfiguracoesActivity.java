package com.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

/**
 * Created by kamila on 06/01/2016.
 * Esta classe tem por objetivo definir quais as preferencias do usuário.
 * Através desta o sistema sabe qual o tema e o nível do jogo que o usuário deseja jogar.
 */
public class ConfiguracoesActivity extends PreferenceActivity {

    private SharedPreferences SP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Método responsável por inicializar a classe,
        // definindo o layout da activity e
        // o arquivo que contém as preferencias da activity.
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
        this.setContentView(R.layout.activity_configuracoes);

    }
}