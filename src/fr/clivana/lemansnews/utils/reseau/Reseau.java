package fr.clivana.lemansnews.utils.reseau;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpParams;

import fr.clivana.lemansnews.dao.CategoriesDAO;
import fr.clivana.lemansnews.dao.EventsDAO;
import fr.clivana.lemansnews.dao.NewsDAO;
import fr.clivana.lemansnews.entity.Article;
import fr.clivana.lemansnews.entity.Categorie;
import fr.clivana.lemansnews.entity.Evenement;
import fr.clivana.lemansnews.utils.Params;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class Reseau {

	public final static String URL_LIST_NEWS = "/m/news/list/"; // /m/news/{motClef}/{numPage}
	public final static String URL_LIST_EVENTS = "/m/events/list/"; // /m/news/{motClef}/{numPage}
	public final static String URL_LIST_CATEGORIES = "/m/categories/list"; // pas de parametres
	public final static String URL_COUNT_CATEGORIES = "/m/categories/count/";// /m/categories/count/{nom}/{date}
	public final static String URL_IMAGES = "/data/images/"; // /data/images/{nom}
	
// verification du reseau
	public static boolean verifReseau(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		if (netInfo != null && netInfo.isConnectedOrConnecting()){
			return true;
		}
		return false;
	}
	
// verification de la qualité du reseau
	public static boolean isSlow(Context context){
		/* TODO à implementer*/
		return false;
	}
	
// requete web
	public static InputStream requeteWeb(String url){
		Log.d("Recherche", "debut "+url);
		HttpParams params = new BasicHttpParams();
		params.setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
		DefaultHttpClient client = new DefaultHttpClient(params);
		HttpGet httpGet = new HttpGet(url);
		InputStream result = null;
			HttpResponse execute;
			try {
				execute = client.execute(httpGet);
				result = execute.getEntity().getContent();
			} catch (ClientProtocolException e) {
				Log.e("erreur Protocol Reseau", e.getMessage());
			} catch (IOException e) {
				Log.e("erreur IO Reseau", e.getMessage());
			}
		Log.d("Recherche", "fin "+url);
		return result;
	}
	
// recuperation des articles sur le reseau et mise à jour BDD
	public static void majArticles(Context context, String motClef, int numPage, int quantite){
		if (quantite == 0 || quantite > Params.QTE_MAX_ARTICLES){
			quantite = Params.QTE_MAX_ARTICLES;
		}
		if (motClef.equals("") || motClef.equals(" ")){
			motClef = "all";
		}
		if (numPage == 0){
			numPage = 1;
		}
		String url = Params.BASE_SERVEUR + URL_LIST_NEWS + motClef + "/" + numPage + "/" + quantite;
		DeSerializer<ListArticles> deserialize = new DeSerializer<ListArticles>();
		List<Article> listArticle = deserialize.deJson(requeteWeb(url));
		if (listArticle != null && listArticle.size() != 0){
			NewsDAO newsDao = new NewsDAO(context);
			newsDao.setArticles(listArticle);
		}else{
			Log.w("Reseau","liste Articles vide pour : " + motClef + " - " + numPage + " - " + quantite);
		}
	}
	
// recuperation des evenements sur le reseau et mise à jour BDD
	public static void majEvenements(Context context, int numPage, int quantite){
		if (quantite == 0 || quantite > Params.QTE_MAX_EVENEMENTS){
			quantite = Params.QTE_MAX_EVENEMENTS;
		}
		if (numPage == 0){
			numPage = 1;
		}
		String motClef = "all";
		String url = Params.BASE_SERVEUR + URL_LIST_EVENTS + motClef + "/" + numPage + "/" + quantite;
		DeSerializer<ListEvents> deserialize = new DeSerializer<ListEvents>();
		List<Evenement> listEvents = deserialize.deJson(requeteWeb(url));
		if (listEvents != null && listEvents.size() != 0){
			EventsDAO eventsDao = new EventsDAO(context);
			eventsDao.setEvents(listEvents);
		}else{
			Log.w("Reseau","liste Evenements vide pour : " + motClef + " - " + numPage + " - " + quantite);
		}
	}
	
// recuperation de toutes les categories
	public static void majCategories(Context context){
		String url = Params.BASE_SERVEUR + URL_LIST_CATEGORIES;
		DeSerializer<ListCategorie> deserialize = new DeSerializer<ListCategorie>();
		List<Categorie> listCategorie = deserialize.deJson(requeteWeb(url));
		if (listCategorie != null && listCategorie.size() != 0){
			CategoriesDAO categorieDao = new CategoriesDAO(context);
			categorieDao.setCategories(listCategorie);
		}else{
			Log.e("Reseau", "la liste des categories n''a pas pu être chargée");
		}
	}
	
//récuperation d'une categorie avec son comptage
	public static void countCategorie(Context context, String nomCategorie, long dateDernierClick){
		if (nomCategorie.equals("")|| nomCategorie.equals(" ")){
			nomCategorie = "all";
		}
	}
}
