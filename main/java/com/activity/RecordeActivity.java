package com.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.LinearLayout.LayoutParams;

import com.banco.DBAdapter;

import java.util.ArrayList;

/**
 * Created by Kamila Oliveira on 17/02/2016.
 *  Classe que exibe a tela com os recordes.
 *  Esta classe busca os dados do banco de dados da aplicação e os exibe em tela.
 *  Como na classe DBAdapter criamos um método que retorna um array de linhas da coluna,
 *  nessa tela será chamado esse método para que
 *  as informações sejam inseridas em suas respectivas linhas.
 */
public class RecordeActivity extends Activity{


    @Override
    public void onCreate(Bundle savedInstanceState) {
        // Método responsável por inicializar a classe,
        // definindo o layout da activity.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recorde);

        DBAdapter info = new DBAdapter(this);
        // Instancia o DBAdapter para que seja possivel realizar a busca dos recordes

        TableLayout tl = (TableLayout) findViewById(R.id.tl1);
        // Define a TableLayout e relaciona a mesma ao layout.

        info.open();
        ArrayList<TableRow> data = info.getRecorde();
        info.close();
        //Abre o banco de dados, retorna um array com os dados do banco e fecha o banco de dados

        TableLayout.LayoutParams layoutParams = new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        // Seta o Layout para as linhas da tabela.

        for (TableRow tr : data) {
            // Adiciona todas as linhas do array recebido com os dados do banco na tabela,
            // uma por vez.
            tl.addView(tr, layoutParams);
        }
    }

}
