package fr.clivana.lemansnews.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import fr.clivana.lemansnews.entity.Categorie;
import fr.clivana.lemansnews.utils.database.DatabaseLeMansNews;
import fr.clivana.lemansnews.utils.database.NomsSQL;

public class CategoriesDAO {
	
	private SQLiteDatabase dbClivana;
	private DatabaseLeMansNews dbClivanaHelper;
	private Context context;
	
// constructeur
	public CategoriesDAO(Context context) {
		super();
		this.context = context;
		dbClivanaHelper = new DatabaseLeMansNews(this.context, NomsSQL.BASE_NOM, null, NomsSQL.BASE_VERSION);
	}
	
//open close
	public SQLiteDatabase open(){
		dbClivana = dbClivanaHelper.getWritableDatabase();
		return dbClivana;
	}
	public void close(){
		dbClivana.close();
	}

// insert un article
	public void insertCategorie(Categorie categorie){
		open();
		if(!categorie.isSupprimable()){
			categorie.setSelected(true);
		}
		categorie.setDateConsult("00000000000000");
		dbClivana.insert(NomsSQL.TABLE_CATEGORIE, null, categorieToContentValues(categorie));
		close();
	}
	
//suppression un article
	public boolean removeCategorie(Categorie categorie){
		open();
		boolean del = dbClivana.delete(
				NomsSQL.TABLE_CATEGORIE, 
				NomsSQL.COLONNE_CATEGORIE_ID + " = " + categorie.getId(), 
				null) > 0;
		close();
		return del;
	}
	public void deleteCategorie(long id){
		open();
		dbClivana.delete(
				NomsSQL.TABLE_CATEGORIE, 
				NomsSQL.COLONNE_CATEGORIE_ID + " = " + id, 
				null);
		close();
	}
	
// update un article
	public boolean updateCategorie(Categorie categorie){
		open();
		if(!categorie.isSupprimable()){
			categorie.setSelected(true);
		}
		boolean upd = dbClivana.update(
				NomsSQL.TABLE_CATEGORIE, 
				categorieToContentValues(categorie), 
				NomsSQL.COLONNE_CATEGORIE_ID + " = " + categorie.getId(), 
				null) > 0;
		close();
		return upd;
	}
	
// retourne un article à l'aide de son identifiant
	public Categorie getCategorie(long id){
		open();
		Cursor c = dbClivana.query(
				NomsSQL.TABLE_CATEGORIE, 
				null, 
				NomsSQL.COLONNE_CATEGORIE_ID + " = " + id, 
				null, null, null, null);
		if (c.getCount()==0){
			return null;
		}
		c.moveToFirst();
		Categorie categorie = cursorToCategorie(c);
		c.close();
		close();
		return categorie;
	}
	
	public Categorie getCategorie(String nom){
		open();
		Cursor c = dbClivana.query(
				NomsSQL.TABLE_CATEGORIE, 
				null, 
				NomsSQL.COLONNE_CATEGORIE_NOM + " = '" + nom + "'", 
				null, null, null, null);
		if (c.getCount()==0){
			return null;
		}
		c.moveToFirst();
		Categorie categorie = cursorToCategorie(c);
		c.close();
		close();
		return categorie;
	}
	
// récuperation d'un liste d'article triée par date (décroissante) et limitée à Params.QTE_MAX_ARTICLES articles
	public List<Categorie> getAllCategories(){
		open();
		Cursor c = dbClivana.query(
				NomsSQL.TABLE_CATEGORIE, 
				null, null, null, null, null, 
                NomsSQL.COLONNE_CATEGORIE_NOM, 
				null);
		return cursorToCategorieTab(c);
	}
	
	public List<Categorie> getSelectedCategories() {
		open();
		Cursor c = dbClivana.query(
				NomsSQL.TABLE_CATEGORIE, 
				null, 
				NomsSQL.COLONNE_CATEGORIE_SELECTION + " = 1", 
				null, null, null, 
                NomsSQL.COLONNE_CATEGORIE_NOM, 
				null);
		return cursorToCategorieTab(c);
	}
	
	public List<Categorie> getNonSelectedCategories() {
		open();
		Cursor c = dbClivana.query(
				NomsSQL.TABLE_CATEGORIE, 
				null, 
				NomsSQL.COLONNE_CATEGORIE_SELECTION + " = 0", 
				null, null, null, 
                NomsSQL.COLONNE_CATEGORIE_NOM, 
				null);
		return cursorToCategorieTab(c);
	}
	
// Mise à jour des articles avec une liste d'articles venant du reseau
	public void setCategories(List<Categorie> categories){
		Iterator<Categorie> iter = categories.iterator();
		Categorie categorie;
		while(iter.hasNext()){
			categorie = iter.next();
			setCategorie(categorie);
		}
	}
	
	public void setCategorie(Categorie categorie){
		Categorie oldCategorie = getCategorie(categorie.getId());
		if (oldCategorie == null){
			NewsDAO newsDAO = new NewsDAO(context);
			categorie.setCount(newsDAO.countArticlesWithMotsclefsFromDate(categorie.getNom(), categorie.getDateConsult()));
			insertCategorie(categorie);
		}else{
			categorie.setSelected(oldCategorie.isSelected());
			categorie.setDateConsult(oldCategorie.getDateConsult());
			NewsDAO newsDAO = new NewsDAO(context);
			categorie.setCount(newsDAO.countArticlesWithMotsclefsFromDate(categorie.getNom(), categorie.getDateConsult()));
			updateCategorie(categorie);
		}
		Log.w("countCategorie", categorie.getCount()+" "+categorie.getNom());
	}
	
// conversion du cursor en liste d'articles
	public List<Categorie> cursorToCategorieTab(Cursor c){
		List<Categorie> arrayCategories= new ArrayList<Categorie>();
		Categorie categorie;
		if (c.getCount()==0){
			// aucun comportement n'est gérer ici si une liste de categorie est vide
		}else{
			while(c.moveToNext()){
				categorie = cursorToCategorie(c);
				arrayCategories.add(categorie);
			};
		}
		c.close();
		close();
		return arrayCategories;
	}
	
// conversion du cursor en article
	public Categorie cursorToCategorie(Cursor c){
		boolean supprimable = c.getInt(NomsSQL.RANG_CATEGORIE_SUPPRIMABLE) == 1;
		boolean selection = c.getInt(NomsSQL.RANG_CATEGORIE_SELECTION) == 1;
		Categorie categorie = new Categorie(
				c.getLong(NomsSQL.RANG_CATEGORIE_ID), 
				c.getString(NomsSQL.RANG_CATEGORIE_NOM), 
				c.getString(NomsSQL.RANG_CATEGORIE_NOMIMAGE), 
				c.getInt(NomsSQL.RANG_CATEGORIE_COUNT), 
				c.getInt(NomsSQL.RANG_CATEGORIE_TOTAL), 
				supprimable, 
				selection, 
				c.getString(NomsSQL.RANG_CATEGORIE_DATEACCES));
		return categorie;
	}

// création d'un contentValues (map clé valeur avec clé = nom de colonne et valeur = attributs de l'article)
	private ContentValues categorieToContentValues(Categorie categorie){
		ContentValues contentValues = new ContentValues();
		contentValues.put(NomsSQL.COLONNE_CATEGORIE_ID , categorie.getId());
		contentValues.put(NomsSQL.COLONNE_CATEGORIE_NOM , categorie.getNom());
		contentValues.put(NomsSQL.COLONNE_CATEGORIE_NOMIMAGE , categorie.getNomImage());
		contentValues.put(NomsSQL.COLONNE_CATEGORIE_COUNT , categorie.getCount());
		contentValues.put(NomsSQL.COLONNE_CATEGORIE_TOTAL , categorie.getTotal());
		contentValues.put(NomsSQL.COLONNE_CATEGORIE_SUPPRIMABLE , categorie.isSupprimable());
		contentValues.put(NomsSQL.COLONNE_CATEGORIE_SELECTION , categorie.isSelected());
		contentValues.put(NomsSQL.COLONNE_CATEGORIE_DATEACCES , categorie.getDateConsult());
		return contentValues;
	}
	
}
