package fr.clivana.lemansnews.dao;

import java.util.ArrayList;
import java.util.Date;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import fr.clivana.lemansnews.entity.Article;
import fr.clivana.lemansnews.utils.database.DatabaseLeMansNews;

public class NewsDAO {

	private static final int BASE_VERSION = 1;
	private static final String BASE_NOM = "dbclivana.db";
	
	private static final String TABLE_NEWS = "news";
	
	private static final String COLONNE_ID = "id";
	private static final int COLONNE_ID_ID = 0;
	private static final String COLONNE_TITRE = "titre";
	private static final int COLONNE_TITRE_ID = 1;
	private static final String COLONNE_ARTICLE = "article";
	private static final int COLONNE_ARTICLE_ID = 2;
	private static final String COLONNE_ACCROCHE = "accroche";
	private static final int COLONNE_ACCROCHE_ID = 3;
	private static final String COLONNE_DATE_PARUTION = "date_parution";
	private static final int COLONNE_DATE_PARUTION_ID = 4;
	private static final String COLONNE_AUTEUR = "auteur";
	private static final int COLONNE_AUTEUR_ID = 5;
	private static final String COLONNE_URL_ARTICLE = "url_article";
	private static final int COLONNE_URL_ARTICLE_ID = 6;
	private static final String COLONNE_URL_IMAGE = "url_image";
	private static final int COLONNE_URL_IMAGE_ID = 7;
	private static final String COLONNE_URL_IMAGE_MOBILE = "url_image_mobile";
	private static final int COLONNE_URL_IMAGE_MOBILE_ID = 8;
	private static final String COLONNE_URL_MINIATURE = "url_miniature";
	private static final int COLONNE_URL_MINIATURE_ID = 9;
	private static final String COLONNE_MOTS_CLEFS = "mots_clefs";
	private static final int COLONNE_MOTS_CLEFS_ID = 10;
	private static final String COLONNE_NOTIFICATION = "notification";
	private static final int COLONNE_NOTIFICATION_ID = 11;
	
	private SQLiteDatabase dbClivana;
	
	private DatabaseLeMansNews dbClivanaHelper;
	private Context context;
	public NewsDAO(Context context) {
		super();
		this.context=context;
		dbClivanaHelper = new DatabaseLeMansNews(this.context, BASE_NOM, null, BASE_VERSION);
		
		
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
	
	public long insertNews(Article article){
		ContentValues newArticle = new ContentValues();
		newArticle.put(COLONNE_ID , article.getId());
		newArticle.put(COLONNE_TITRE , article.getTitre());
		newArticle.put(COLONNE_ARTICLE , article.getArticle());
		newArticle.put(COLONNE_ACCROCHE , article.getAccroche());
		newArticle.put(COLONNE_DATE_PARUTION , article.getDateParution().getTime());
		newArticle.put(COLONNE_AUTEUR , article.getAuteur());
		newArticle.put(COLONNE_URL_ARTICLE , article.getUrlArticle());
		newArticle.put(COLONNE_URL_IMAGE , article.getUrlImage());
		newArticle.put(COLONNE_URL_IMAGE_MOBILE ,article.getUrlImageMobile());
		newArticle.put(COLONNE_URL_MINIATURE , article.getUrlMiniature());
		newArticle.put(COLONNE_MOTS_CLEFS , article.getMotsClefs());
		newArticle.put(COLONNE_NOTIFICATION , article.isNotification());
		open();
		long retour = dbClivana.insert(TABLE_NEWS, null, newArticle);
		close();
		return retour;
	}
	
	public boolean removeNews(long id){
		open();
		boolean del = dbClivana.delete(TABLE_NEWS, COLONNE_ID + "='" + id + "'", null) > 0;
		close();
		return del;
	}
	
	public boolean updateNews(Article article){
		ContentValues updArticle = new ContentValues();
		
		updArticle.put(COLONNE_TITRE , article.getTitre());
		updArticle.put(COLONNE_ARTICLE , article.getArticle());
		updArticle.put(COLONNE_ACCROCHE , article.getAccroche());
		updArticle.put(COLONNE_DATE_PARUTION , article.getDateParution().getTime());
		updArticle.put(COLONNE_AUTEUR , article.getAuteur());
		updArticle.put(COLONNE_URL_ARTICLE , article.getUrlArticle());
		updArticle.put(COLONNE_URL_IMAGE , article.getUrlImage());
		updArticle.put(COLONNE_URL_IMAGE_MOBILE , article.getUrlImageMobile());
		updArticle.put(COLONNE_URL_MINIATURE , article.getUrlMiniature());
		updArticle.put(COLONNE_MOTS_CLEFS , article.getMotsClefs());
		updArticle.put(COLONNE_NOTIFICATION , article.isNotification());
		String where = "id" + "='" + article.getId() +"'";
		open();
		boolean upd = dbClivana.update(TABLE_NEWS, updArticle, where, null) > 0;
		close();
		return upd;
	}
	
	public Article getArticle(long id){
		open();
		Cursor c = dbClivana.query(TABLE_NEWS, new String[]{COLONNE_ID, COLONNE_TITRE, COLONNE_ARTICLE, COLONNE_ACCROCHE, COLONNE_DATE_PARUTION, COLONNE_AUTEUR, COLONNE_URL_ARTICLE, COLONNE_URL_IMAGE, COLONNE_URL_IMAGE_MOBILE, COLONNE_URL_MINIATURE, COLONNE_MOTS_CLEFS, COLONNE_NOTIFICATION}, COLONNE_ID + " = '" + id + "'", null, null, null, null);
		return cursorToArticle(c);
	}
	
	public Article cursorToArticle(Cursor c){
		if (c.getCount()==0){
			
			return null;
		}
		
		c.moveToFirst();
		boolean notif = c.getInt(COLONNE_NOTIFICATION_ID) == 1;
		Article retArticle = new Article(
				c.getLong(COLONNE_ID_ID), 
				c.getString(COLONNE_TITRE_ID), 
				c.getString(COLONNE_ARTICLE_ID), 
				c.getString(COLONNE_ACCROCHE_ID), 
				new Date(c.getLong(COLONNE_DATE_PARUTION_ID)), 
				c.getString(COLONNE_AUTEUR_ID), 
				c.getString(COLONNE_URL_ARTICLE_ID), 
				c.getString(COLONNE_URL_IMAGE_ID), 
				c.getString(COLONNE_URL_IMAGE_MOBILE_ID), 
				c.getString(COLONNE_URL_MINIATURE_ID), 
				c.getString(COLONNE_MOTS_CLEFS_ID), 
				notif);
		c.close();
		close();
		return retArticle;
	}
	
	public ArrayList<Article> getAllArticle(){
		open();
		Cursor c = dbClivana.query(TABLE_NEWS, new String[]{COLONNE_ID, COLONNE_TITRE, COLONNE_ARTICLE, COLONNE_ACCROCHE, COLONNE_DATE_PARUTION, COLONNE_AUTEUR, COLONNE_URL_ARTICLE, COLONNE_URL_IMAGE, COLONNE_URL_IMAGE_MOBILE, COLONNE_URL_MINIATURE, COLONNE_MOTS_CLEFS, COLONNE_NOTIFICATION}, null, null, null, null, null);
		return cursorToArticleTab(c);
	}
	
	public ArrayList<Article> cursorToArticleTab(Cursor c){
		ArrayList<Article> arrayArticles= new ArrayList<Article>();
		Article article;
		
		if (c.getCount()==0){
			arrayArticles.add(Article.noNewsFound());
		}else{
		
	 
			while(c.moveToNext()){
				boolean notif = c.getInt(COLONNE_NOTIFICATION_ID) == 1;
				article = new Article(
						c.getLong(COLONNE_ID_ID), 
						c.getString(COLONNE_TITRE_ID), 
						c.getString(COLONNE_ARTICLE_ID), 
						c.getString(COLONNE_ACCROCHE_ID), 
						new Date(c.getLong(COLONNE_DATE_PARUTION_ID)), 
						c.getString(COLONNE_AUTEUR_ID), 
						c.getString(COLONNE_URL_ARTICLE_ID), 
						c.getString(COLONNE_URL_IMAGE_ID), 
						c.getString(COLONNE_URL_IMAGE_MOBILE_ID), 
						c.getString(COLONNE_URL_MINIATURE_ID), 
						c.getString(COLONNE_MOTS_CLEFS_ID), 
						notif);
				
				arrayArticles.add(article);
				
				
			};
		}
		c.close();
		close();
		
		return arrayArticles;
	}
}
