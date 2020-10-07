package com.banco;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;

/**
 * Created by kamila on 19/02/2016.
 * Esta classe recebe o banco de dados e através de seus métodos,
 * facilita a manipulação destes.
 * Além disso, ela proporciona operações de abrir e fechar o banco de dados.
*/

public class DBAdapter {
	private SQLiteDatabase database;
	private DBRecorde dbRecorde;
	private final Context nContext;

	public DBAdapter(Context context) {
		nContext = context;
		dbRecorde = new DBRecorde(context);
	}

	// metodo para inserir os dados no banco. Recebe o nome, os cliks e o tempo
	public void insereRecorde(String nome, int clicks, long tempo) {
		// Método utilizado para inserção de dados no banco.
		// Este método recebe o nome, os clicks e o tempo que o usuário
		// levou para finalizar o jogo.
		// insere os dados num ContentValue, associando ao nome do campo na tabela
		// Os dados são inseridos primeiramente no ContentValue e
		// associados ao nome dos campos na tabela.
		// Finalmente são inseridos no banco de dados.
		ContentValues values = new ContentValues();
		values.put("NOME", nome);
		values.put("CLICKS", clicks);
		values.put("TEMPO", tempo);
		// Para a inserção são passados os valores contidos no Content
		// e o nome do banco de dados.
		database.insert("recorde", null, values);
	}

	public ArrayList<TableRow> getRecorde() {
		// Método utilizado para busca dos dados do banco.
		// Este método utiliza os nomes das colunas do banco de dados
		// e através dos mesmos realiza uma busca por todos os resultados,
		// grardando estes dados no Cursor.
		// Os dados são ordenas por clicks e posteriormente pelo tempo e
		// armazenados separadamente.
		String[] colunas = { "nome", "clicks", "tempo" };
		Cursor c = database.query("recorde", colunas, null, null, null, null, "clicks, tempo");

		int iNome = c.getColumnIndex("NOME");
		int iClicks = c.getColumnIndex("CLICKS");
		int iTempo = c.getColumnIndex("TEMPO");

		ArrayList<TableRow> linhas = new ArrayList<TableRow>();
		// Após a busca e i armazenamento dos dados, os mesmos são inseridos em um array de tablerow.
		// Este tem por objetivo exibir os dados posteriormente aos usuários.

		TableRow tr;
		TextView tv;

		if (c.equals(null))
			linhas = null;
			// verifica se existe algum registro de banco de dados atribuido ao Cursor.
			// Caso não exista, a tabela será exibida sem dados aos usuários.

		// for que passa por cada linha do cursor
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			// Caso existam registros atribuidos ao Cursor,
			// é realizada a leitura de todos os dados pertencentes ao Cursor.
			// Assim posteriormente estes serão adicionados à tabela para exibição.

			long segundos = c.getLong(iTempo) % 60;
			long minutos = c.getLong(iTempo) / 60;
			String formatarSegundos = "";
			String formatarMinutos = "";
			// Busca o tempo do Intent que foi passado e calcula os minutos e segundos



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
			// Verifica se os minutos ou segundos são iguais a 0 e acrescenta mais um 0

			tr = new TableRow(nContext);
			tr.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
			// É criada uma linha na tabela para que possam ser serados os dados.
			// Nesta linha também são setadas as dimensoes da mesma.
			// Após isso são criadas as textview para a exibição dos dados.
			// Além disso, na linha são setadas as cores em que as inforações são exibidas
			// e informações contidas no cursor para que a linha seja populada.
			// Ao final, as linhas são adicionadas no array.

			tv = new TextView(nContext);
			tv.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT, 10));
			tv.setWidth(80);
			tv.setTextColor(Color.parseColor("#ffffff"));
			tv.setText(c.getString(iNome));
			tr.addView(tv);

			tv = new TextView(nContext);
			tv.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT, 1));
			tv.setTextColor(Color.parseColor("#ffffff"));
			tv.setText(c.getString(iClicks));
			tr.addView(tv);

			tv = new TextView(nContext);
			tv.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT, 10));
			tv.setTextColor(Color.parseColor("#ffffff"));
			tv.setText(formatarMinutos + ":" + formatarSegundos);
			tr.addView(tv);

			linhas.add(tr);
		}

		return linhas;
	}

	public DBAdapter open() throws SQLException {
		// Método utilizado para abrir a conexão com o banco de dados e
		// assim receber os dados.
		dbRecorde = new DBRecorde(nContext);
		database = dbRecorde.getWritableDatabase();
		return this;

	}

	public void close() {
		// Método utilizado para fechar a conexão com o banco de dados e
		// assim não permitir mais consultas e inserções.
		dbRecorde.close();
	}

}
