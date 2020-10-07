package com.banco;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by kamila on 11/01/2016.
 * Classe que cria o banco de dados.
 * Nela estão as instruções de SQL que serão executadas
 * e através destas instruções o banco de dados é criado.
 * Isto ocorre na instalação do aplicativo.
*/
public class DBRecorde extends SQLiteOpenHelper {
	
	public DBRecorde(Context context) {
		// Cria o banco de dados, com o nome recorde.db.
		super(context, "recorde.db", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// SQL executado na instalçao do aplicativo.
		// Através do mesmo, a tabela Recorde é criada com seus campos.
		db.execSQL("CREATE TABLE RECORDE (NOME TEXT, CLICKS INTEGER, TEMPO LONG)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Classe chamada quando o banco de dados precisa de atualização.
		// Este método é utilizado para que possam ser realizadas
		// quaisquer alterações no banco de dados.
	}

}
