package fr.clivana.lemansnews.utils.reseau;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
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
import fr.clivana.lemansnews.entity.Categorie;
import fr.clivana.lemansnews.utils.Formatage;
import fr.clivana.lemansnews.utils.Params;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.util.Log;

public class Reseau {

	private final static String URL_LIST_NEWS = "/m/news/list/"; // /m/news/list/{motClef}/{numPage}/qte
	private final static String URL_LIST_EVENTS = "/m/events/list/all/"; // /m/events/list/{motClef}/{numPage}/qte
	private final static String URL_LIST_CATEGORIES = "/m/categories/list"; // pas de parametres
	private final static String URL_COUNT_CATEGORIES = "/m/categories/count/";// /m/categories/count/{nom}/{date}
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
		Object manager = context.getSystemService(Context.TELEPHONY_SERVICE);
		return ((TelephonyManager) manager).getNetworkType() < 3;	
	}
	
// requete web
	public static InputStream requeteWeb(String url){
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
		return result;
	}
	
// recuperation des articles sur le reseau et mise à jour BDD
	public static void majArticles(Context context, String motClef, int numPage, int quantite){
		int max;
		if (isSlow(context)){
			max = Params.QTE_MAX_ARTICLES_SLOW;
		}else{
			max = Params.QTE_MAX_ARTICLES;
		}
		if (quantite == 0 || quantite > max){
			quantite = max;
		}
		if (motClef.equals("") || motClef.equals(" ")){
			motClef = "all";
		}
		if (numPage == 0){
			numPage = 1;
		}
		String url = Params.BASE_SERVEUR + URL_LIST_NEWS + Formatage.suppressionEspace(motClef) + "/" + numPage + "/" + quantite;
		DeSerializer<ListArticles> deserialize = new DeSerializer<ListArticles>();
		ListArticles listArticle = new ListArticles();
		listArticle = deserialize.deJson(requeteWeb(url),listArticle);
		if (listArticle != null && listArticle.size() != 0){
			NewsDAO newsDao = new NewsDAO(context);
			newsDao.setArticles(listArticle);
		}else{
			Log.w("Reseau","liste Articles vide pour : " + motClef + "-" + numPage + "-" + quantite);
		}
	}
	
// recuperation des evenements sur le reseau et mise à jour BDD
	public static void majEvenements(Context context, int numPage, int quantite){
		int max;
		if (isSlow(context)){
			max = Params.QTE_MAX_EVENEMENTS_SLOW;
		}else{
			max = Params.QTE_MAX_EVENEMENTS;
		}
		if (quantite == 0 || quantite > max){
			quantite = max;
		}
		if (numPage == 0){
			numPage = 1;
		}
		String url = Params.BASE_SERVEUR + URL_LIST_EVENTS + numPage + "/" + quantite;
		DeSerializer<ListEvents> deserialize = new DeSerializer<ListEvents>();
		ListEvents listEvents = new ListEvents();
		listEvents = deserialize.deJson(requeteWeb(url),listEvents);
		if (listEvents != null && listEvents.size() != 0){
			EventsDAO eventsDao = new EventsDAO(context);
			eventsDao.setEvents(listEvents);
		}else{
			Log.w("Reseau","liste Evenements vide pour : all-" + numPage + "-" + quantite);
		}
	}
	
// recuperation de toutes les categories
	public static void majCategories(Context context){
		String url = Params.BASE_SERVEUR + URL_LIST_CATEGORIES;
		DeSerializer<ListCategorie> deserialize = new DeSerializer<ListCategorie>();
		ListCategorie listCategorie = new ListCategorie();
		listCategorie = deserialize.deJson(requeteWeb(url), listCategorie);
		if (listCategorie != null && listCategorie.size() != 0){
			CategoriesDAO categorieDao = new CategoriesDAO(context);
			categorieDao.setCategories(listCategorie);
		}else{
			Log.e("Reseau", "la liste des categories n'a pas pu être chargée");
		}
		comptageAllCategorie(context);
	}
	
//récuperation d'une categorie avec son comptage
	public static void countCategorie(Context context, String nomCategorie, String dateDernierClick){
		if (nomCategorie.equals("")|| nomCategorie.equals(" ")){
			nomCategorie = "all";
		}
		String url = Params.BASE_SERVEUR + URL_COUNT_CATEGORIES + Formatage.suppressionEspace(nomCategorie) + "/" + dateDernierClick;
		DeSerializer<Categorie> deserialize = new DeSerializer<Categorie>();
		Categorie categorie = new Categorie();
		categorie = deserialize.deJson(requeteWeb(url), categorie);
		if (categorie != null){
			CategoriesDAO categorieDao = new CategoriesDAO(context);
			categorieDao.setCategorie(categorie);
		}else{
			Log.e("Reseau", "la categorie " + nomCategorie +" n'a pas pu être chargée");
		}
		if (categorie.getCount() != 0){
			majArticles(context, categorie.getNom(), 0, 0);
		}
	}
	
	private static void comptageAllCategorie(Context context){
		CategoriesDAO dao = new CategoriesDAO(context);
		List<Categorie> categories = dao.getSelectedCategories();
		Iterator<Categorie> iter = categories.iterator();
		Categorie categorie;
		while (iter.hasNext()){
			categorie = iter.next();
			countCategorie(context, categorie.getNom(), categorie.getDateConsult());
		}
	}
	public static Bitmap chargementImage(String nom){
		return BitmapFactory.decodeStream(requeteWeb(Params.BASE_SERVEUR + URL_IMAGES + nom));
	}
}
