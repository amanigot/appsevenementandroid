package fr.clivana.lemansnews.utils.database;

public class NomsSQL {

	public static final String BASE_NOM = "dblemansnews.db";
	public static final int BASE_VERSION = 1;
	
// noms des tables
	public static final String TABLE_CATEGORIE = "Categorie";
	public static final String TABLE_ARTICLE = "Article";
	public static final String TABLE_EVENEMENT = "Evenement";
	
// structure de la table categorie
	public static final String COLONNE_CATEGORIE_ID = "id";
	public static final String COLONNE_CATEGORIE_NOM = "nom";
	public static final String COLONNE_CATEGORIE_NOMIMAGE = "nom_image";
	public static final String COLONNE_CATEGORIE_COUNT = "count";
	public static final String COLONNE_CATEGORIE_TOTAL = "total";
	public static final String COLONNE_CATEGORIE_SUPPRIMABLE = "supprimable";
	public static final String COLONNE_CATEGORIE_SELECTION = "selection";
	public static final String COLONNE_CATEGORIE_DATEACCES = "date";
	
	public static final int RANG_CATEGORIE_ID = 0;
	public static final int RANG_CATEGORIE_NOM = 1;
	public static final int RANG_CATEGORIE_NOMIMAGE = 2;
	public static final int RANG_CATEGORIE_COUNT = 3;
	public static final int RANG_CATEGORIE_TOTAL = 4;
	public static final int RANG_CATEGORIE_SUPPRIMABLE = 5;
	public static final int RANG_CATEGORIE_SELECTION = 6;
	public static final int RANG_CATEGORIE_DATEACCES = 7;
	
// structure de la table article
	public static final String COLONNE_ARTICLE_ID = "id";
	public static final String COLONNE_ARTICLE_TITRE = "titre";
	public static final String COLONNE_ARTICLE_CONTENU = "contenu";
	public static final String COLONNE_ARTICLE_ACCROCHE = "accroche";
	public static final String COLONNE_ARTICLE_DATEPARUTION = "date_parution";
	public static final String COLONNE_ARTICLE_AUTEUR = "auteur";
	public static final String COLONNE_ARTICLE_URLARTICLE = "url_article";
	public static final String COLONNE_ARTICLE_NOMIMAGE = "nom_image";
	public static final String COLONNE_ARTICLE_NOMIMAGEMOBILE = "nom_image_mobile";
	public static final String COLONNE_ARTICLE_NOMMINIATURE = "nom_miniature";
	public static final String COLONNE_ARTICLE_MOTSCLEFS = "mots_clefs";
	public static final String COLONNE_ARTICLE_NOTIFICATION = "notification";
	public static final String COLONNE_ARTICLE_FAVORIS = "favoris";

	public static final int RANG_ARTICLE_ID = 0;
	public static final int RANG_ARTICLE_TITRE = 1;
	public static final int RANG_ARTICLE_CONTENU = 2;
	public static final int RANG_ARTICLE_ACCROCHE = 3;
	public static final int RANG_ARTICLE_DATEPARUTION = 4;
	public static final int RANG_ARTICLE_AUTEUR = 5;
	public static final int RANG_ARTICLE_URLARTICLE = 6;
	public static final int RANG_ARTICLE_NOMIMAGE = 7;
	public static final int RANG_ARTICLE_NOMIMAGEMOBILE = 8;
	public static final int RANG_ARTICLE_NOMMINIATURE = 9;
	public static final int RANG_ARTICLE_MOTSCLEFS = 10;
	public static final int RANG_ARTICLE_NOTIFICATION = 11;
	public static final int RANG_ARTICLE_FAVORIS = 12;
	
// structure de la table evenement
	public static final String COLONNE_EVENEMENT_ID = "id";
	public static final String COLONNE_EVENEMENT_TITRE = "titre";
	public static final String COLONNE_EVENEMENT_CONTENU = "contenu";
	public static final String COLONNE_EVENEMENT_ACCROCHE = "accroche";
	public static final String COLONNE_EVENEMENT_AUTEUR = "auteur";
	public static final String COLONNE_EVENEMENT_LIEU = "lieu";
	public static final String COLONNE_EVENEMENT_URLEVENEMENT = "url_evenement";
	public static final String COLONNE_EVENEMENT_DATEHEURE = "dateheure";
	public static final String COLONNE_EVENEMENT_DATETRI = "datetri";
	public static final String COLONNE_EVENEMENT_DATEENREGISTREMENT = "date_enregistrement";
	public static final String COLONNE_EVENEMENT_NOMIMAGE = "nom_image";
	public static final String COLONNE_EVENEMENT_NOMIMAGEMOBILE = "nom_image_mobile";
	public static final String COLONNE_EVENEMENT_NOMMINIATURE = "nom_miniature";
	public static final String COLONNE_EVENEMENT_MOTSCLEFS = "mots_clefs";
	public static final String COLONNE_EVENEMENT_NOTIFICATION = "notification";
    public static final String COLONNE_EVENEMENT_FAVORIS = "favoris";

	public static final int RANG_EVENEMENT_ID = 0;
	public static final int RANG_EVENEMENT_TITRE = 1;
	public static final int RANG_EVENEMENT_CONTENU = 2;
	public static final int RANG_EVENEMENT_ACCROCHE = 3;
	public static final int RANG_EVENEMENT_AUTEUR = 4;
	public static final int RANG_EVENEMENT_LIEU = 5;
	public static final int RANG_EVENEMENT_URLEVENEMENT = 6;
	public static final int RANG_EVENEMENT_DATEHEURE = 7;
	public static final int RANG_EVENEMENT_DATETRI = 8;
	public static final int RANG_EVENEMENT_DATEENREGISTREMENT = 9;
	public static final int RANG_EVENEMENT_NOMIMAGE = 10;
	public static final int RANG_EVENEMENT_NOMIMAGEMOBILE = 11;
	public static final int RANG_EVENEMENT_NOMMINIATURE = 12;
	public static final int RANG_EVENEMENT_MOTSCLEFS = 13;
	public static final int RANG_EVENEMENT_NOTIFICATION = 14;
    public static final int RANG_EVENEMENT_FAVORIS = 15;

// création table article
	public static final String QUERY_CREATE_TABLE_ARTICLE = "create table if not exists "
			+ TABLE_ARTICLE + " (" + COLONNE_ARTICLE_ID + " integer primary key , " 
								+ COLONNE_ARTICLE_TITRE + " text not null, "
								+ COLONNE_ARTICLE_CONTENU + " varchar(10000), "
								+ COLONNE_ARTICLE_ACCROCHE + " varchar(10000), "
								+ COLONNE_ARTICLE_DATEPARUTION + " long, "
								+ COLONNE_ARTICLE_AUTEUR + " text, "
								+ COLONNE_ARTICLE_URLARTICLE + " text, "
								+ COLONNE_ARTICLE_NOMIMAGE + " text, "
								+ COLONNE_ARTICLE_NOMIMAGEMOBILE + " text, "
								+ COLONNE_ARTICLE_NOMMINIATURE + " text, "
								+ COLONNE_ARTICLE_MOTSCLEFS + " text, "
								+ COLONNE_ARTICLE_NOTIFICATION + " boolean, "
								+ COLONNE_ARTICLE_FAVORIS + " boolean "+");";
	
// création table evenement
	public static final String QUERY_CREATE_TABLE_EVENEMENT = "create table if not exists "
			+ TABLE_EVENEMENT + " (" + COLONNE_EVENEMENT_ID + " integer primary key , " 
								+ COLONNE_EVENEMENT_TITRE + " text not null, "
								+ COLONNE_EVENEMENT_CONTENU + " varchar(10000), "
								+ COLONNE_EVENEMENT_ACCROCHE + " varchar(10000), "
								+ COLONNE_EVENEMENT_AUTEUR + " text, "
								+ COLONNE_EVENEMENT_LIEU + " text, "
								+ COLONNE_EVENEMENT_URLEVENEMENT+ " text, "
								+ COLONNE_EVENEMENT_DATEHEURE + " text, "
								+ COLONNE_EVENEMENT_DATETRI + " text, "
								+ COLONNE_EVENEMENT_DATEENREGISTREMENT + " text, "
								+ COLONNE_EVENEMENT_NOMIMAGE + " text, "
								+ COLONNE_EVENEMENT_NOMIMAGEMOBILE + " text, "
								+ COLONNE_EVENEMENT_NOMMINIATURE + " text, "
								+ COLONNE_EVENEMENT_MOTSCLEFS + " text, "
								+ COLONNE_EVENEMENT_NOTIFICATION + " boolean, "
								+ COLONNE_EVENEMENT_FAVORIS + " boolean "+");";
	
// création table categorie
	public static final String QUERY_CREATE_TABLE_CATEGORIE = "create table if not exists "
			+ TABLE_CATEGORIE + " (" + COLONNE_CATEGORIE_ID + " integer primary key, "
			+ COLONNE_CATEGORIE_NOM + " text not null, " 
			+ COLONNE_CATEGORIE_NOMIMAGE + " text, "
			+ COLONNE_CATEGORIE_COUNT +" integer, "
			+ COLONNE_CATEGORIE_TOTAL +" integer, "
			+ COLONNE_CATEGORIE_SUPPRIMABLE + " boolean, "
			+ COLONNE_CATEGORIE_SELECTION + " boolean, "
			+ COLONNE_CATEGORIE_DATEACCES + " text," + ");";
		
// requetes de mise à jour V2 
	public static final String QUERY_ALTERTABLE_V1_V2 = "";
	
// requetes de mise à jour V3 
	public static final String QUERY_ALTERTABLE_V2_V3 = "";
	
// requetes de mise à jour V4 
	public static final String QUERY_ALTERTABLE_V3_V4 = "";
	
	
}
