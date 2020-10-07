package com.memory;

import android.content.Context;
import android.widget.Button;

/**
 * Created by kamila on 11/01/2016.
 *  Essa classe representa uma carta.
 *  Cada instância de carta possui alguns atributos,
 *  que representam a posição em que ela está
 *  e o valor que vai ter na hora em que ela for um botão.
 */

public class Carta {

    public int x;
    public int y;
    public Button button;


    public Carta(Button button, int x,int y) {

        this.x = x;
        this.y=y;
        this.button=button;

    }

}
