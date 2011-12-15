package fr.clivana.lemansnews.utils.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseLeMansNews extends SQLiteOpenHelper{

	private static final String TABLE_CATEGORIES = "categories";
	private static final String TABLE_NEWS = "news";
	private static final String TABLE_EVENTS = "events";
	
	private static final String COLONNE_ID = "id";
	private static final String COLONNE_MOTCLEF = "motclef";
	private static final String COLONNE_DATE = "date";
	private static final String COLONNE_COUNT = "count";
	private static final String COLONNE_SELECTED = "selected";
	
	private static final String COLONNE_TITRE = "titre";
	private static final String COLONNE_ARTICLE = "article";
	private static final String COLONNE_ACCROCHE = "accroche";
	private static final String COLONNE_DATE_PARUTION = "date_parution";
	private static final String COLONNE_AUTEUR = "auteur";
	private static final String COLONNE_URL_ARTICLE = "url_article";
	private static final String COLONNE_URL_IMAGE = "url_image";
	private static final String COLONNE_URL_IMAGE_MOBILE = "url_image_mobile";
	private static final String COLONNE_URL_MINIATURE = "url_miniature";
	private static final String COLONNE_MOTS_CLEFS = "mots_clefs";
	private static final String COLONNE_NOTIFICATION = "notification";
	
	private static final String COLONNE_DETAIL_EVENEMENT = "detail_evenement";
	private static final String COLONNE_LIEU = "lieu";
	private static final String COLONNE_URL_EVENEMENT = "url_evenement";
	private static final String COLONNE_DATE_HEURE_EVENEMENT = "date_heure_evenement";
	private static final String COLONNE_DATE_ENREGISTREMENT = "date_enregistrement";
	private static final String COLONNE_NOM_IMAGE = "nom_image";
	private static final String COLONNE_NOM_IMAGE_MOBILE = "nom_image_mobile";
	private static final String COLONNE_NOM_IMAGE_MINIATURE = "nom_image_miniature";
	
	private static final String CREATE_QUERY_NEWS = "create table if not exists "
			+ TABLE_NEWS + " (" + COLONNE_ID + " integer primary key , " 
								+ COLONNE_TITRE + " text not null, "
								+ COLONNE_ARTICLE + " varchar(10000), "
								+ COLONNE_ACCROCHE + " varchar(10000), "
								+ COLONNE_DATE_PARUTION+ " long, "
								+ COLONNE_AUTEUR+ " text, "
								+ COLONNE_URL_ARTICLE+ " text, "
								+ COLONNE_URL_IMAGE + " text, "
								+ COLONNE_URL_IMAGE_MOBILE + " text, "
								+ COLONNE_URL_MINIATURE + " text, "
								+ COLONNE_MOTS_CLEFS + " text,"
								+ COLONNE_NOTIFICATION + " boolean "+");";
	
	private static final String CREATE_QUERY_EVENTS = "create table if not exists "
			+ TABLE_EVENTS + " (" + COLONNE_ID + " integer primary key , " 
								+ COLONNE_TITRE + " text not null, "
								+ COLONNE_DETAIL_EVENEMENT + " varchar(10000), "
								+ COLONNE_ACCROCHE + " varchar(10000), "
								+ COLONNE_AUTEUR+ " text, "
								+ COLONNE_LIEU + " text, "
								+ COLONNE_URL_EVENEMENT+ " text, "
								+ COLONNE_DATE_HEURE_EVENEMENT + " text, "
								+ COLONNE_DATE_ENREGISTREMENT + " text, "
								+ COLONNE_NOM_IMAGE + " text, "
								+ COLONNE_NOM_IMAGE_MOBILE + " text, "
								+ COLONNE_NOM_IMAGE_MINIATURE + " text, "
								+ COLONNE_MOTS_CLEFS + " text,"
								+ COLONNE_NOTIFICATION + " boolean "+");";
	
	private static final String CREATE_QUERY_MAJ_NEWS = "create table if not exists "
			+ TABLE_CATEGORIES + " (" + COLONNE_ID 
			+ " integer primary key autoincrement, " + COLONNE_MOTCLEF
			+ " text not null, " + COLONNE_DATE + " long,"
			+ COLONNE_COUNT +" integer,"
			+ COLONNE_SELECTED + " boolean"+");";
	
	private static final String INSERT_QUERY_ALL = "insert into " + TABLE_CATEGORIES + " (" 
			+ COLONNE_MOTCLEF + ", " + COLONNE_DATE + ", " + COLONNE_COUNT + ") values ( 'all', 0, 0 );";
	private static final String INSERT_QUERY_APPLE = "insert into " + TABLE_CATEGORIES + " (" 
			+ COLONNE_MOTCLEF + ", " + COLONNE_DATE + ", " + COLONNE_COUNT + ") values ( 'Apple', 0, 0 );";
	private static final String INSERT_QUERY_ANDROID = "insert into " + TABLE_CATEGORIES + " (" 
			+ COLONNE_MOTCLEF + ", " + COLONNE_DATE + ", " + COLONNE_COUNT + ") values ( 'Android', 0, 0 );";
	private static final String INSERT_QUERY_EVENTS = "insert into " + TABLE_CATEGORIES + " (" 
			+ COLONNE_MOTCLEF + ", " + COLONNE_DATE + ", " + COLONNE_COUNT + ") values ( 'Events', 0, 0 );";
	private static final String INSERT_QUERY_CLIVANA = "insert into " + TABLE_CATEGORIES + " (" 
			+ COLONNE_MOTCLEF + ", " + COLONNE_DATE + ", " + COLONNE_COUNT + ") values ( 'Clivana', 0, 0 );";
	
	public DatabaseLeMansNews(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(CREATE_QUERY_MAJ_NEWS);
		db.execSQL(CREATE_QUERY_NEWS);
		db.execSQL(CREATE_QUERY_EVENTS);
		
		db.execSQL(INSERT_QUERY_ALL);
		db.execSQL(INSERT_QUERY_ANDROID);
		db.execSQL(INSERT_QUERY_APPLE);
		db.execSQL(INSERT_QUERY_EVENTS);
		db.execSQL(INSERT_QUERY_CLIVANA);
	
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	
	

}
