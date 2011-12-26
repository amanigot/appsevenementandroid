package fr.clivana.lemansnews.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import fr.clivana.lemansnews.entity.Article;
import fr.clivana.lemansnews.entity.Categorie;
import fr.clivana.lemansnews.utils.Params;
import fr.clivana.lemansnews.utils.database.DatabaseLeMansNews;
import fr.clivana.lemansnews.utils.database.NomsSQL;

public class NewsDAO {

	private SQLiteDatabase dbClivana;
	private DatabaseLeMansNews dbClivanaHelper;
	
// constructeur
	public NewsDAO(Context context) {
		super();
		dbClivanaHelper = new DatabaseLeMansNews(context, NomsSQL.BASE_NOM, null, NomsSQL.BASE_VERSION);
	}
	
// open et close database
	private SQLiteDatabase open(){
		dbClivana = dbClivanaHelper.getWritableDatabase();
		return dbClivana;
	}
	private void close(){
		dbClivana.close();
	}
	
// insert un article
	public void insertNews(Article article){
		open();
		dbClivana.insert(NomsSQL.TABLE_ARTICLE, null, articleToContentValues(article));
		close();
	}
	
//suppression un article
	public boolean removeNews(Article article){
		open();
		boolean del = dbClivana.delete(
				NomsSQL.TABLE_ARTICLE, 
				NomsSQL.COLONNE_ARTICLE_ID + " = " + article.getId(), 
				null) > 0;
		close();
		return del;
	}
	
// update un article
	public boolean updateNews(Article article){
		open();
		boolean upd = dbClivana.update(
				NomsSQL.TABLE_ARTICLE, 
				articleToContentValues(article), 
				NomsSQL.COLONNE_ARTICLE_ID + " = " + article.getId(), 
				null) > 0;
		close();
		return upd;
	}
	
// retourne un article à l'aide de son identifiant
	public Article getArticle(long id){
		open();
		Cursor c = dbClivana.query(
				NomsSQL.TABLE_ARTICLE, 
				null, 
				NomsSQL.COLONNE_ARTICLE_ID + " = " + id, 
				null, null, null, null);
		if (c.getCount()==0){
			return null;
		}
		c.moveToFirst();
		Article article = cursorToArticle(c);
		c.close();
		close();
		return article;
	}
	
// récuperation d'un liste d'article triée par date (décroissante) et limitée à Params.QTE_MAX_ARTICLES articles
	public List<Article> getAllArticles(){
		open();
		Cursor c = dbClivana.query(
				NomsSQL.TABLE_ARTICLE, 
				null, null, null, null, null, 
                NomsSQL.COLONNE_ARTICLE_DATEPARUTION + " DESC", 
				Params.QTE_MAX_ARTICLES+"");
		return cursorToArticleTab(c);
	}
	
// récuperation d'un liste d'article selon son mot clé, triée par date (décroissante) et limitée à Params.QTE_MAX_ARTICLES articles
	public List<Article> getArticlesWithMotsclefs(String motClef){
		open();
		Cursor c;
		if (motClef.equals("all")){
			return getAllArticles();
		}else{
			c = dbClivana.query(
					NomsSQL.TABLE_ARTICLE, 
					null, 
					NomsSQL.COLONNE_ARTICLE_MOTSCLEFS + " LIKE '%" + motClef + "%'", 
					null, null, null, NomsSQL.COLONNE_ARTICLE_DATEPARUTION + " DESC", 
					Params.QTE_MAX_ARTICLES + "");
			return cursorToArticleTab(c);
		}
	}
	
// retourne la liste d'article selon le mot clé de la catégorie
	public List<Article> getArticlesFromCategorie(Categorie categorie){
		return getArticlesWithMotsclefs(categorie.getNom());
	}
	
// liste des articles favoris
	public List<Article> getFavoriteArticles(){
		open();
		Cursor c;
		c = dbClivana.query(
					NomsSQL.TABLE_ARTICLE, 
					null, 
					NomsSQL.COLONNE_ARTICLE_FAVORIS + " = 1", 
					null, null, null, NomsSQL.COLONNE_ARTICLE_DATEPARUTION + " DESC", 
					null);
			return cursorToArticleTab(c);
	}
	
// Mise à jour des articles avec une liste d'articles
	public void setArticles(List<Article> articles){
		Iterator<Article> iter = articles.iterator();
		Article article;
		while(iter.hasNext()){
			article = iter.next();
			Article oldArticle = getArticle(article.getId());
			if (oldArticle == null){
				insertNews(article);
			}else{
				article.setFavoris(oldArticle.isFavoris());
				updateNews(article);
			}
		}
	}
	
// conversion du cursor en liste d'articles
	public List<Article> cursorToArticleTab(Cursor c){
		List<Article> arrayArticles= new ArrayList<Article>();
		Article article;
		if (c.getCount()==0){
			arrayArticles.add(Article.noNewsFound());
		}else{
			while(c.moveToNext()){
				article = cursorToArticle(c);
				arrayArticles.add(article);
			};
		}
		c.close();
		close();
		return arrayArticles;
	}
	
// conversion du cursor en article
	public Article cursorToArticle(Cursor c){
		boolean notif = c.getInt(NomsSQL.RANG_ARTICLE_NOTIFICATION) == 1;
		boolean favoris = c.getInt(NomsSQL.RANG_ARTICLE_FAVORIS) == 1;
		
		Article retArticle = new Article(
				c.getLong(NomsSQL.RANG_ARTICLE_ID), 
				c.getString(NomsSQL.RANG_ARTICLE_TITRE), 
				c.getString(NomsSQL.RANG_ARTICLE_CONTENU), 
				c.getString(NomsSQL.RANG_ARTICLE_ACCROCHE), 
				c.getString(NomsSQL.RANG_ARTICLE_DATEPARUTION), 
				c.getString(NomsSQL.RANG_ARTICLE_AUTEUR), 
				c.getString(NomsSQL.RANG_ARTICLE_URLARTICLE), 
				c.getString(NomsSQL.RANG_ARTICLE_NOMIMAGE), 
				c.getString(NomsSQL.RANG_ARTICLE_NOMIMAGEMOBILE), 
				c.getString(NomsSQL.RANG_ARTICLE_NOMMINIATURE), 
				c.getString(NomsSQL.RANG_ARTICLE_MOTSCLEFS), 
				notif,
				favoris);
		return retArticle;
	}

// création d'un contentValues (map clé valeur avec clé = nom de colonne et valeur = attributs de l'article)
	private ContentValues articleToContentValues(Article article){
		ContentValues contentValues = new ContentValues();
		contentValues.put(NomsSQL.COLONNE_ARTICLE_ID , article.getId());
		contentValues.put(NomsSQL.COLONNE_ARTICLE_TITRE , article.getTitre());
		contentValues.put(NomsSQL.COLONNE_ARTICLE_CONTENU , article.getArticle());
		contentValues.put(NomsSQL.COLONNE_ARTICLE_ACCROCHE , article.getAccroche());
		contentValues.put(NomsSQL.COLONNE_ARTICLE_DATEPARUTION , article.getDateParution());
		contentValues.put(NomsSQL.COLONNE_ARTICLE_AUTEUR , article.getAuteur());
		contentValues.put(NomsSQL.COLONNE_ARTICLE_URLARTICLE , article.getUrlArticle());
		contentValues.put(NomsSQL.COLONNE_ARTICLE_NOMIMAGE , article.getUrlImage());
		contentValues.put(NomsSQL.COLONNE_ARTICLE_NOMIMAGEMOBILE ,article.getUrlImageMobile());
		contentValues.put(NomsSQL.COLONNE_ARTICLE_NOMMINIATURE , article.getUrlMiniature());
		contentValues.put(NomsSQL.COLONNE_ARTICLE_MOTSCLEFS , article.getMotsClefs());
		contentValues.put(NomsSQL.COLONNE_ARTICLE_NOTIFICATION , article.isNotification());
		contentValues.put(NomsSQL.COLONNE_ARTICLE_FAVORIS , article.isFavoris());
		return contentValues;
	}
}
