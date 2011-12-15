package fr.clivana.lemansnews.dao;

import java.util.ArrayList;
import java.util.List;

import fr.clivana.lemansnews.entity.Categorie;
import fr.clivana.lemansnews.utils.DatabaseLeMansNews;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class CategoriesDAO {
	private static final int BASE_VERSION = 1;
	private static final String BASE_NOM = "dbclivana.db";
	
	private static final String TABLE_MAJ_NEWS = "miseajournews";
	private static final String TABLE_MAJ_EVENTS = "miseajourevents";
	
	private static final String COLONNE_ID = "id";
	private static final int COLONNE_ID_ID = 0;
	private static final String COLONNE_MOTCLEF = "motclef";
	private static final int COLONNE_MOTCLEF_ID = 1;
	private static final String COLONNE_DATE = "date";
	private static final int COLONNE_DATE_ID = 2;
	private static final String COLONNE_COUNT = "count";
	private static final int COLONNE_COUNT_ID = 3;
	private static final String COLONNE_SELECTED = "selected";
	private static final int COLONNE_SELECTED_ID = 4;
	
	private SQLiteDatabase dbClivana;
	
	private DatabaseLeMansNews dbClivanaHelper;
	private Context context;
	public CategoriesDAO(Context ctx) {
		dbClivanaHelper = new DatabaseLeMansNews(ctx, BASE_NOM, null, BASE_VERSION);
		context=ctx;
	}
	
	public SQLiteDatabase open(){
		dbClivana = dbClivanaHelper.getWritableDatabase();
		return dbClivana;
	}
	
	public void close(){
		dbClivana.close();
	}
	
	public SQLiteDatabase getBaseDonnees(){
		return dbClivana;
	}

	public List<Categorie> getSelectedCategories() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
