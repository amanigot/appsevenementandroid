package fr.clivana.lemansnews.utils.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import fr.clivana.lemansnews.utils.database.NomsSQL;

public class DatabaseLeMansNews extends SQLiteOpenHelper{
	
	public DatabaseLeMansNews(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

// creation des tables
		db.execSQL(NomsSQL.QUERY_CREATE_TABLE_ARTICLE);
		db.execSQL(NomsSQL.QUERY_CREATE_TABLE_EVENEMENT);
		db.execSQL(NomsSQL.QUERY_CREATE_TABLE_CATEGORIE);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

//		if (oldVersion < newVersion){
//			
//		}
	}
	
	

}
