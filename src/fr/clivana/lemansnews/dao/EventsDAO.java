package fr.clivana.lemansnews.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import fr.clivana.lemansnews.entity.Evenement;
import fr.clivana.lemansnews.utils.database.DatabaseLeMansNews;
import fr.clivana.lemansnews.utils.database.NomsSQL;

public class EventsDAO {

	
	private SQLiteDatabase dbClivana;
	private DatabaseLeMansNews dbClivanaHelper;
	
	public EventsDAO(Context context) {
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
	
	public long insertEvent(Evenement event){
		open();
		long retour = dbClivana.insert(NomsSQL.TABLE_EVENEMENT, null, eventToContentValues(event));
		close();
		return retour;
	}
	
	public boolean removeEvent(Evenement event){
		open();
		boolean del = dbClivana.delete(NomsSQL.TABLE_EVENEMENT, NomsSQL.COLONNE_EVENEMENT_ID + "= " + event.getId(), null) > 0;
		close();
		return del;
	}
	
	public boolean updateEvent(Evenement event){
		open();
		boolean upd = dbClivana.update(NomsSQL.TABLE_EVENEMENT, eventToContentValues(event), NomsSQL.COLONNE_EVENEMENT_ID + "= " + event.getId(), null) > 0;
		close();
		return upd;
	}
	
	public Evenement getArticle(long id){
		open();
		Cursor c = dbClivana.query(NomsSQL.TABLE_EVENEMENT, null, NomsSQL.COLONNE_EVENEMENT_ID + "= " + id, null, null, null, null);
		if (c.getCount()==0){
			return null;
		}else{
			c.moveToFirst();
			return cursorToEvent(c);
		}
	}
	
	public List<Evenement> getAllEvents(){
		open();
		Cursor c = dbClivana.query(NomsSQL.TABLE_EVENEMENT, null, null, null, null, null, null);
		return cursorToEventTab(c);
	}
	
	private List<Evenement> cursorToEventTab(Cursor c){
		List<Evenement> events = new ArrayList<Evenement>();
		Evenement event;
		
		if (c.getCount()==0){			
			events.add(Evenement.noEvents());
		}else{
			while(c.moveToNext()){
				event = cursorToEvent(c);		
				events.add(event);		
			};
		}
		c.close();
		close();
		return events;
	}
	
	private Evenement cursorToEvent(Cursor c){
//		c.moveToFirst();
		boolean notif = c.getInt(NomsSQL.RANG_EVENEMENT_NOTIFICATION) == 1;
		Evenement retEvent = new Evenement(
				c.getLong(NomsSQL.RANG_EVENEMENT_ID), 
				c.getString(NomsSQL.RANG_EVENEMENT_TITRE), 
				c.getString(NomsSQL.RANG_EVENEMENT_CONTENU), 
				c.getString(NomsSQL.RANG_EVENEMENT_ACCROCHE), 
				c.getString(NomsSQL.RANG_EVENEMENT_AUTEUR), 
				c.getString(NomsSQL.RANG_EVENEMENT_LIEU), 
				c.getString(NomsSQL.RANG_EVENEMENT_URLEVENEMENT), 
				c.getString(NomsSQL.RANG_EVENEMENT_DATEHEURE), 
				c.getString(NomsSQL.RANG_EVENEMENT_DATEENREGISTREMENT), 
				c.getString(NomsSQL.RANG_EVENEMENT_NOMIMAGE), 
				c.getString(NomsSQL.RANG_EVENEMENT_NOMIMAGEMOBILE), 
				c.getString(NomsSQL.RANG_EVENEMENT_NOMMINIATURE), 
				c.getString(NomsSQL.RANG_EVENEMENT_MOTSCLEFS), 
				notif);
		c.close();
		close();
		return retEvent;
	}
	
	private ContentValues eventToContentValues(Evenement event){
		ContentValues paramEvent = new ContentValues();
		paramEvent.put(NomsSQL.COLONNE_EVENEMENT_ID , event.getId());
		paramEvent.put(NomsSQL.COLONNE_EVENEMENT_TITRE , event.getTitre());
		paramEvent.put(NomsSQL.COLONNE_EVENEMENT_CONTENU , event.getDetailEvenement());
		paramEvent.put(NomsSQL.COLONNE_EVENEMENT_ACCROCHE , event.getAccroche());
		paramEvent.put(NomsSQL.COLONNE_EVENEMENT_AUTEUR , event.getAuteur());
		paramEvent.put(NomsSQL.COLONNE_EVENEMENT_LIEU, event.getLieu());
		paramEvent.put(NomsSQL.COLONNE_EVENEMENT_URLEVENEMENT , event.getUrlEvenement());
		paramEvent.put(NomsSQL.COLONNE_EVENEMENT_DATEHEURE, event.getDateHeureEvenement());
		paramEvent.put(NomsSQL.COLONNE_EVENEMENT_DATEENREGISTREMENT, event.getDateEnregistrement());
		paramEvent.put(NomsSQL.COLONNE_EVENEMENT_NOMIMAGE , event.getNomImage());
		paramEvent.put(NomsSQL.COLONNE_EVENEMENT_NOMIMAGEMOBILE , event.getNomImageMobile());
		paramEvent.put(NomsSQL.COLONNE_EVENEMENT_NOMMINIATURE , event.getNomImageMiniature());
		paramEvent.put(NomsSQL.COLONNE_EVENEMENT_MOTSCLEFS , event.getMotsClefs());
		paramEvent.put(NomsSQL.COLONNE_EVENEMENT_NOTIFICATION , event.isNotification());
		return paramEvent;
	}
}
