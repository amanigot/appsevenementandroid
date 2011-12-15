package fr.clivana.lemansnews.utils.database;

public class NomsTablesColonnesQuery {

	public static final String BASE_NOM = "dblemansnews.db";
	public static final int BASE_VERSION = 1;
	
	// noms des tables
	public static final String TABLE_CATEGORIE = "Categorie";
	public static final String TABLE_ARTICLE = "Article";
	public static final String TABLE_EVENEMENT = "Evenement";
	
	// structure de la table categorie
	public static final String COLONNE_ID = "id";
	public static final String COLONNE_MOTCLEF = "motclef";
	public static final String COLONNE_DATE = "date";
	public static final String COLONNE_COUNT = "count";
	public static final String COLONNE_SELECTED = "selected";
	
	public static final String COLONNE_TITRE = "titre";
	public static final String COLONNE_ARTICLE = "article";
	public static final String COLONNE_ACCROCHE = "accroche";
	public static final String COLONNE_DATE_PARUTION = "date_parution";
	public static final String COLONNE_AUTEUR = "auteur";
	public static final String COLONNE_URL_ARTICLE = "url_article";
	public static final String COLONNE_URL_IMAGE = "url_image";
	public static final String COLONNE_URL_IMAGE_MOBILE = "url_image_mobile";
	public static final String COLONNE_URL_MINIATURE = "url_miniature";
	public static final String COLONNE_MOTS_CLEFS = "mots_clefs";
	public static final String COLONNE_NOTIFICATION = "notification";
	
	public static final String COLONNE_DETAIL_EVENEMENT = "detail_evenement";
	public static final String COLONNE_LIEU = "lieu";
	public static final String COLONNE_URL_EVENEMENT = "url_evenement";
	public static final String COLONNE_DATE_HEURE_EVENEMENT = "date_heure_evenement";
	public static final String COLONNE_DATE_ENREGISTREMENT = "date_enregistrement";
	public static final String COLONNE_NOM_IMAGE = "nom_image";
	public static final String COLONNE_NOM_IMAGE_MOBILE = "nom_image_mobile";
	public static final String COLONNE_NOM_IMAGE_MINIATURE = "nom_image_miniature";
	
	public static final String CREATE_QUERY_NEWS = "create table if not exists "
			+ TABLE_ARTICLE + " (" + COLONNE_ID + " integer primary key , " 
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
	
	public static final String CREATE_QUERY_EVENTS = "create table if not exists "
			+ TABLE_EVENEMENT + " (" + COLONNE_ID + " integer primary key , " 
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
	
	public static final String CREATE_QUERY_MAJ_NEWS = "create table if not exists "
			+ TABLE_CATEGORIE + " (" + COLONNE_ID 
			+ " integer primary key autoincrement, " + COLONNE_MOTCLEF
			+ " text not null, " + COLONNE_DATE + " long,"
			+ COLONNE_COUNT +" integer,"
			+ COLONNE_SELECTED + " boolean"+");";
	
	public static final String INSERT_QUERY_ALL = "insert into " + TABLE_CATEGORIE + " (" 
			+ COLONNE_MOTCLEF + ", " + COLONNE_DATE + ", " + COLONNE_COUNT + ") values ( 'all', 0, 0 );";
	public static final String INSERT_QUERY_APPLE = "insert into " + TABLE_CATEGORIE + " (" 
			+ COLONNE_MOTCLEF + ", " + COLONNE_DATE + ", " + COLONNE_COUNT + ") values ( 'Apple', 0, 0 );";
	public static final String INSERT_QUERY_ANDROID = "insert into " + TABLE_CATEGORIE + " (" 
			+ COLONNE_MOTCLEF + ", " + COLONNE_DATE + ", " + COLONNE_COUNT + ") values ( 'Android', 0, 0 );";
	public static final String INSERT_QUERY_EVENTS = "insert into " + TABLE_CATEGORIE + " (" 
			+ COLONNE_MOTCLEF + ", " + COLONNE_DATE + ", " + COLONNE_COUNT + ") values ( 'Events', 0, 0 );";
	public static final String INSERT_QUERY_CLIVANA = "insert into " + TABLE_CATEGORIE + " (" 
			+ COLONNE_MOTCLEF + ", " + COLONNE_DATE + ", " + COLONNE_COUNT + ") values ( 'Clivana', 0, 0 );";
	
	
}
