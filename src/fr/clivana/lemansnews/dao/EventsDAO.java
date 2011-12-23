package fr.clivana.lemansnews.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import fr.clivana.lemansnews.entity.Evenement;
import fr.clivana.lemansnews.utils.Formatage;
import fr.clivana.lemansnews.utils.Params;
import fr.clivana.lemansnews.utils.database.DatabaseLeMansNews;
import fr.clivana.lemansnews.utils.database.NomsSQL;

public class EventsDAO {

	
	private SQLiteDatabase dbClivana;
	private DatabaseLeMansNews dbClivanaHelper;

// constructeur
	public EventsDAO(Context context) {
		super();
		dbClivanaHelper = new DatabaseLeMansNews(context, NomsSQL.BASE_NOM, null, NomsSQL.BASE_VERSION);
	}
	
// open et close database
	public SQLiteDatabase open(){
		dbClivana = dbClivanaHelper.getWritableDatabase();
		return dbClivana;
	}
	public void close(){
		dbClivana.close();
	}
	
// insertion d'un evenement
	public void insertEvent(Evenement event){
		open();
		dbClivana.insert(NomsSQL.TABLE_EVENEMENT, null, eventToContentValues(event));
		close();
	}
	
// suppression
	public boolean removeEvent(Evenement event){
		open();
		boolean del = dbClivana.delete(
				NomsSQL.TABLE_EVENEMENT, 
				NomsSQL.COLONNE_EVENEMENT_ID + " = " + event.getId(), 
				null) > 0;
		close();
		return del;
	}
	
// update
	public boolean updateEvent(Evenement event){
		open();
		boolean upd = dbClivana.update(
				NomsSQL.TABLE_EVENEMENT, 
				eventToContentValues(event), 
				NomsSQL.COLONNE_EVENEMENT_ID + " = " + event.getId(), 
				null) > 0;
		Log.w("update","done");
		close();
		return upd;
	}
	
// recherche event avec son ID
	public Evenement getEvenement(long id){
		open();
		Cursor c = dbClivana.query(
				NomsSQL.TABLE_EVENEMENT, 
				null, 
				NomsSQL.COLONNE_EVENEMENT_ID + " = " + id, 
				null, null, null, null);
		if (c.getCount()==0){
			return null;
		}else{
			c.moveToFirst();
			Evenement event = cursorToEvent(c);
			c.close();
			close();
			return event;
		}
	}
	
// liste evenement, tri par date croissante, date >= datejour, limit QTE_MAX_EVENTS
	public List<Evenement> getAllEvents(){
		open();
		Cursor c = dbClivana.query(
				NomsSQL.TABLE_EVENEMENT,
				null,
				null,//NomsSQL.COLONNE_EVENEMENT_DATETRI +" >= '" + dateTri + "'",
				null, null, null,
				NomsSQL.COLONNE_EVENEMENT_DATETRI,
				Params.QTE_MAX_EVENEMENTS + "");
		List<Evenement> evenements = cursorToEventTab(c);
		
		return evenements;
	}
	
	public List<Evenement> getFavoriteEvents(){
		open();
		Cursor c = dbClivana.query(
				NomsSQL.TABLE_EVENEMENT,
				null,
				NomsSQL.COLONNE_EVENEMENT_FAVORIS + " = 1",
				null, null, null,
				NomsSQL.COLONNE_EVENEMENT_DATETRI,
				Params.QTE_MAX_EVENEMENTS + "");
		Log.w("getfavevents", "done");
		return cursorToEventTab(c);
	}
	
	public void setEvents(List<Evenement> events){
		Iterator<Evenement> iter = events.iterator();
		Evenement event;
		while(iter.hasNext()){
			event = iter.next();
			Evenement oldEvent = getEvenement(event.getId());
			if (oldEvent == null){
				insertEvent(event);
			}else{
				event.setFavoris(oldEvent.isFavoris());
				updateEvent(event);
			}
		}

	}
	private List<Evenement> cursorToEventTab(Cursor c){
		String dateTri = Formatage.datePourTriEvenement(new Date());
		List<Evenement> events = new ArrayList<Evenement>();
		Evenement event;
		
		if (c.getCount()==0){			
			events.add(Evenement.noEvents());
		}else{
			while(c.moveToNext()){
				event = cursorToEvent(c);
				if (event.getDateTri().compareTo(dateTri)<0){
					Log.w("event", "inferieur");
				}else{
					Log.w("event", "suppérieur");
					events.add(event);
				}
			};
		}
		if (events.size()==0){			
			events.add(Evenement.noEvents());
		}
		c.close();
		close();
		return events;
	}
	
	private Evenement cursorToEvent(Cursor c){
		boolean notif = c.getInt(NomsSQL.RANG_EVENEMENT_NOTIFICATION) == 1;
		boolean favoris = c.getInt(NomsSQL.RANG_EVENEMENT_FAVORIS) == 1;
		Evenement retEvent = new Evenement(
				c.getLong(NomsSQL.RANG_EVENEMENT_ID), 
				c.getString(NomsSQL.RANG_EVENEMENT_TITRE), 
				c.getString(NomsSQL.RANG_EVENEMENT_CONTENU), 
				c.getString(NomsSQL.RANG_EVENEMENT_ACCROCHE), 
				c.getString(NomsSQL.RANG_EVENEMENT_AUTEUR), 
				c.getString(NomsSQL.RANG_EVENEMENT_LIEU), 
				c.getString(NomsSQL.RANG_EVENEMENT_URLEVENEMENT), 
				c.getString(NomsSQL.RANG_EVENEMENT_DATEHEURE), 
				c.getString(NomsSQL.RANG_EVENEMENT_DATETRI), 
				c.getString(NomsSQL.RANG_EVENEMENT_DATEENREGISTREMENT), 
				c.getString(NomsSQL.RANG_EVENEMENT_NOMIMAGE), 
				c.getString(NomsSQL.RANG_EVENEMENT_NOMIMAGEMOBILE), 
				c.getString(NomsSQL.RANG_EVENEMENT_NOMMINIATURE), 
				c.getString(NomsSQL.RANG_EVENEMENT_MOTSCLEFS), 
				notif,
				favoris);
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
		paramEvent.put(NomsSQL.COLONNE_EVENEMENT_DATETRI, event.getDateTri());
		paramEvent.put(NomsSQL.COLONNE_EVENEMENT_DATEENREGISTREMENT, event.getDateEnregistrement());
		paramEvent.put(NomsSQL.COLONNE_EVENEMENT_NOMIMAGE , event.getNomImage());
		paramEvent.put(NomsSQL.COLONNE_EVENEMENT_NOMIMAGEMOBILE , event.getNomImageMobile());
		paramEvent.put(NomsSQL.COLONNE_EVENEMENT_NOMMINIATURE , event.getNomImageMiniature());
		paramEvent.put(NomsSQL.COLONNE_EVENEMENT_MOTSCLEFS , event.getMotsClefs());
		paramEvent.put(NomsSQL.COLONNE_EVENEMENT_NOTIFICATION , event.isNotification());
		paramEvent.put(NomsSQL.COLONNE_EVENEMENT_FAVORIS , event.isFavoris());
		return paramEvent;
	}
}
