package fr.clivana.lemansnews.dao;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import fr.clivana.lemansnews.entity.Evenement;
import fr.clivana.lemansnews.utils.database.DatabaseLeMansNews;

public class EventsDAO {

	private static final int BASE_VERSION = 1;
	private static final String BASE_NOM = "dbclivana.db";
	
	private static final String TABLE_EVENTS = "events";
	
	private static final String COLONNE_ID = "id";
	private static final int COLONNE_ID_ID = 0;
	private static final String COLONNE_TITRE = "titre";
	private static final int COLONNE_TITRE_ID = 1;
	private static final String COLONNE_DETAIL_EVENEMENT = "detail_evenement";
	private static final int COLONNE_DETAIL_EVENEMENT_ID = 2;
	private static final String COLONNE_ACCROCHE = "accroche";
	private static final int COLONNE_ACCROCHE_ID = 3;
	private static final String COLONNE_AUTEUR = "auteur";
	private static final int COLONNE_AUTEUR_ID = 4;
	private static final String COLONNE_LIEU = "lieu";
	private static final int COLONNE_LIEU_ID = 5;
	private static final String COLONNE_URL_EVENEMENT = "url_evenement";
	private static final int COLONNE_URL_EVENEMENT_ID = 6;
	private static final String COLONNE_DATE_HEURE_EVENEMENT = "date_heure_evenement";
	private static final int COLONNE_DATE_HEURE_EVENEMENT_ID = 7;
	private static final String COLONNE_DATE_ENREGISTREMENT = "date_enregistrement";
	private static final int COLONNE_DATE_ENREGISTREMENT_ID = 8;
	private static final String COLONNE_NOM_IMAGE = "nom_image";
	private static final int COLONNE_NOM_IMAGE_ID = 9;
	private static final String COLONNE_NOM_IMAGE_MOBILE = "nom_image_mobile";
	private static final int COLONNE_NOM_IMAGE_MOBILE_ID = 10;
	private static final String COLONNE_NOM_IMAGE_MINIATURE = "nom_image_miniature";
	private static final int COLONNE_NOM_IMAGE_MINIATURE_ID = 11;
	private static final String COLONNE_MOTS_CLEFS = "mots_clefs";
	private static final int COLONNE_MOTS_CLEFS_ID = 12;
	private static final String COLONNE_NOTIFICATION = "notification";
	private static final int COLONNE_NOTIFICATION_ID = 13;
	
	private SQLiteDatabase dbClivana;
	private DatabaseLeMansNews dbClivanaHelper;
	
	public EventsDAO(Context context) {
		super();
		dbClivanaHelper = new DatabaseLeMansNews(context, BASE_NOM, null, BASE_VERSION);
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
	
	public long insertNews(Evenement article){
		ContentValues newArticle = new ContentValues();
		newArticle.put(COLONNE_ID , article.getId());
		newArticle.put(COLONNE_TITRE , article.getTitre());
		newArticle.put(COLONNE_DETAIL_EVENEMENT , article.getDetailEvenement());
		newArticle.put(COLONNE_ACCROCHE , article.getAccroche());
		newArticle.put(COLONNE_AUTEUR , article.getAuteur());
		newArticle.put(COLONNE_LIEU, article.getLieu());
		newArticle.put(COLONNE_URL_EVENEMENT , article.getUrlEvenement());
		newArticle.put(COLONNE_DATE_HEURE_EVENEMENT, article.getDateHeureEvenement());
		newArticle.put(COLONNE_DATE_ENREGISTREMENT, article.getDateEnregistrement());
		newArticle.put(COLONNE_NOM_IMAGE , article.getNomImage());
		newArticle.put(COLONNE_NOM_IMAGE_MOBILE , article.getNomImageMobile());
		newArticle.put(COLONNE_NOM_IMAGE_MINIATURE , article.getNomImageMiniature());
		newArticle.put(COLONNE_MOTS_CLEFS , article.getMotsClefs());
		newArticle.put(COLONNE_NOTIFICATION , article.isNotification());
		open();
		long retour = dbClivana.insert(TABLE_EVENTS, null, newArticle);
		close();
		return retour;
	}
	
	public boolean removeNews(long id){
		open();
		boolean del = dbClivana.delete(TABLE_EVENTS, COLONNE_ID + "='" + id + "'", null) > 0;
		close();
		return del;
	}
	
	public boolean updateNews(Evenement article){
		ContentValues updArticle = new ContentValues();
		
		updArticle.put(COLONNE_TITRE , article.getTitre());
		updArticle.put(COLONNE_DETAIL_EVENEMENT , article.getDetailEvenement());
		updArticle.put(COLONNE_ACCROCHE , article.getAccroche());
		updArticle.put(COLONNE_AUTEUR , article.getAuteur());
		updArticle.put(COLONNE_LIEU, article.getLieu());
		updArticle.put(COLONNE_URL_EVENEMENT , article.getUrlEvenement());
		updArticle.put(COLONNE_DATE_HEURE_EVENEMENT, article.getDateHeureEvenement());
		updArticle.put(COLONNE_DATE_ENREGISTREMENT, article.getDateEnregistrement());
		updArticle.put(COLONNE_NOM_IMAGE , article.getNomImage());
		updArticle.put(COLONNE_NOM_IMAGE_MOBILE , article.getNomImageMobile());
		updArticle.put(COLONNE_NOM_IMAGE_MINIATURE , article.getNomImageMiniature());
		updArticle.put(COLONNE_MOTS_CLEFS , article.getMotsClefs());
		updArticle.put(COLONNE_NOTIFICATION , article.isNotification());
		String where = "id" + "='" + article.getId() +"'";
		open();
		boolean upd = dbClivana.update(TABLE_EVENTS, updArticle, where, null) > 0;
		close();
		return upd;
	}
	
	public Evenement getArticle(long id){
		open();
		Cursor c = dbClivana.query(TABLE_EVENTS, new String[]{COLONNE_ID, COLONNE_TITRE, COLONNE_DETAIL_EVENEMENT, COLONNE_ACCROCHE, COLONNE_AUTEUR, COLONNE_LIEU, COLONNE_URL_EVENEMENT,COLONNE_DATE_HEURE_EVENEMENT, COLONNE_DATE_ENREGISTREMENT, COLONNE_NOM_IMAGE, COLONNE_NOM_IMAGE_MOBILE, COLONNE_NOM_IMAGE_MINIATURE, COLONNE_MOTS_CLEFS, COLONNE_NOTIFICATION}, COLONNE_ID + " = '" + id + "'", null, null, null, null);
		return cursorToEvent(c);
	}
	
	public Evenement cursorToEvent(Cursor c){
		if (c.getCount()==0){
			
			return null;
		}
		
		c.moveToFirst();
		boolean notif = c.getInt(COLONNE_NOTIFICATION_ID) == 1;
		Evenement retEvent = new Evenement(
				c.getLong(COLONNE_ID_ID), 
				c.getString(COLONNE_TITRE_ID), 
				c.getString(COLONNE_DETAIL_EVENEMENT_ID), 
				c.getString(COLONNE_ACCROCHE_ID), 
				c.getString(COLONNE_AUTEUR_ID), 
				c.getString(COLONNE_LIEU_ID), 
				c.getString(COLONNE_URL_EVENEMENT_ID), 
				c.getString(COLONNE_DATE_HEURE_EVENEMENT_ID), 
				c.getString(COLONNE_DATE_ENREGISTREMENT_ID), 
				c.getString(COLONNE_NOM_IMAGE_ID), 
				c.getString(COLONNE_NOM_IMAGE_MOBILE_ID), 
				c.getString(COLONNE_NOM_IMAGE_MINIATURE_ID), 
				c.getString(COLONNE_MOTS_CLEFS_ID), 
				notif);
		c.close();
		close();
		return retEvent;
	}
	
	public ArrayList<Evenement> getAllEvents(){
		open();
		Cursor c = dbClivana.query(TABLE_EVENTS, new String[]{COLONNE_ID, COLONNE_TITRE, COLONNE_DETAIL_EVENEMENT, COLONNE_ACCROCHE, COLONNE_AUTEUR, COLONNE_LIEU, COLONNE_URL_EVENEMENT,COLONNE_DATE_HEURE_EVENEMENT, COLONNE_DATE_ENREGISTREMENT, COLONNE_NOM_IMAGE, COLONNE_NOM_IMAGE_MOBILE, COLONNE_NOM_IMAGE_MINIATURE, COLONNE_MOTS_CLEFS, COLONNE_NOTIFICATION}, null, null, null, null, null);
		return cursorToEventTab(c);
	}
	
	public ArrayList<Evenement> cursorToEventTab(Cursor c){
		ArrayList<Evenement> arrayEvents = new ArrayList<Evenement>();
		Evenement event;
		
		if (c.getCount()==0){			
			arrayEvents.add(Evenement.noEvents());
		}else{
		
		while(c.moveToNext()){
			boolean notif = c.getInt(COLONNE_NOTIFICATION_ID) == 1;
			event = new Evenement(
					c.getLong(COLONNE_ID_ID), 
					c.getString(COLONNE_TITRE_ID), 
					c.getString(COLONNE_DETAIL_EVENEMENT_ID), 
					c.getString(COLONNE_ACCROCHE_ID), 
					c.getString(COLONNE_AUTEUR_ID), 
					c.getString(COLONNE_LIEU_ID), 
					c.getString(COLONNE_URL_EVENEMENT_ID), 
					c.getString(COLONNE_DATE_HEURE_EVENEMENT_ID), 
					c.getString(COLONNE_DATE_ENREGISTREMENT_ID), 
					c.getString(COLONNE_NOM_IMAGE_ID), 
					c.getString(COLONNE_NOM_IMAGE_MOBILE_ID), 
					c.getString(COLONNE_NOM_IMAGE_MINIATURE_ID), 
					c.getString(COLONNE_MOTS_CLEFS_ID), 
					notif);
			
			arrayEvents.add(event);
			
			
		};
		}
		c.close();
		close();
		
		return arrayEvents;
	}
}
