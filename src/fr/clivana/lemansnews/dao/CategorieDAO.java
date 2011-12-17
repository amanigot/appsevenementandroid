package fr.clivana.lemansnews.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import fr.clivana.lemansnews.utils.database.DatabaseLeMansNews;
import fr.clivana.lemansnews.utils.database.NomsSQL;

public class CategorieDAO {

	private SQLiteDatabase dbClivana;
	private DatabaseLeMansNews dbClivanaHelper;
	
	public CategorieDAO(Context context) {
		super();
		dbClivanaHelper = new DatabaseLeMansNews(context, NomsSQL.BASE_NOM, null, NomsSQL.BASE_VERSION);
	}
	
	public SQLiteDatabase open(){
		dbClivana = dbClivanaHelper.getWritableDatabase();
		return dbClivana;
	}
	
	public void close(){
		dbClivana.close();
	}
	
//	public SQLiteDatabase getBaseDonnees(){
//		return dbClivana;
//	}
	
	
	
}
