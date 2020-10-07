package com.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by kamila on 04/01/2016.
 * Classe de abertura do Jogo.
 * Esta classe tem por objetivo,
 * apresentar uma imagem de abertura do jogo.
 * Esta classe é executada apenas durante o tempo de execução determinado.
 */

public class Splash extends Activity {

    private static int SPLASH_TIME_OUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Método responsável por inicializar a classe,
        // definindo o layout da activity.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // Verifica se a versão do android é maior ou igual a lollipop.
            // Caso seja maior, abre a splash e seta o layout.
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        }

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                // Abre a main activity e finaliza a splash screean
                Intent i = new Intent(Splash.this, MainActivity.class);
                startActivity(i);
                finish();

            }
        }, SPLASH_TIME_OUT);
    }
}
