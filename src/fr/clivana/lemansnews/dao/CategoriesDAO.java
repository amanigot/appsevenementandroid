package fr.clivana.lemansnews.dao;

import java.util.List;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import fr.clivana.lemansnews.entity.Categorie;
import fr.clivana.lemansnews.utils.database.DatabaseLeMansNews;
import fr.clivana.lemansnews.utils.database.NomsSQL;

public class CategoriesDAO {
	
	private SQLiteDatabase dbClivana;
	private DatabaseLeMansNews dbClivanaHelper;
	
	public CategoriesDAO(Context context) {
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

	public List<Categorie> getSelectedCategories() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
