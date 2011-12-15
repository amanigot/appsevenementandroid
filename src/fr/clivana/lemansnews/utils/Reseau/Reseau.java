package fr.clivana.lemansnews.utils.Reseau;

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

import fr.clivana.lemansnews.entity.Article;
import fr.clivana.lemansnews.entity.Evenement;
import fr.clivana.lemansnews.utils.Params;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class Reseau {

	public final static String URL_LIST_NEWS = "/m/news/list/"; // /m/news/{motClef}/{numPage}
	public final static String URL_COUNT_NEWS = "/m/news/count/"; // /m/news/count/{motClef}/{date}
	public final static String URL_LIST_EVENTS = "/m/events/list/"; // /m/news/{motClef}/{numPage}
	public final static String URL_COUNT_EVENTS = "/m/events/count/"; // /m/news/count/{motClef}/{date}
	public final static String URL_LIST_KEYWORDS = "/m/news/listMotClef";// route OK
	public final static String URL_COUNT_NEWS_IMAGE = "/m/news/countImage/";// /m/news/countImage/{motclef}
	public final static String URL_COUNT_EVENTS_IMAGE = "/m/events/countImage/";// /m/events/countImage/{something}
	
	public static boolean verifReseau(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		if (netInfo != null && netInfo.isConnectedOrConnecting()){
			return true;
		}
		return false;
	}
	
	public static InputStream webService(String url){
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
	
	public static List<Article> listArticlesServeur(String motClef, int numPage, int quantite){
		
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
		List<Article> listArticle = deserialize.deJson(webService(url));
		return listArticle;
	}
	
	public static List<Evenement> listEvenementsServeur(String motClef, int numPage, int quantite){
		if (quantite == 0 || quantite > Params.QTE_MAX_EVENEMENTS){
			quantite = Params.QTE_MAX_EVENEMENTS;
		}
		if (motClef.equals("") || motClef.equals(" ")){
			motClef = "all";
		}
		if (numPage == 0){
			numPage = 1;
		}
		String url = Params.BASE_SERVEUR + URL_LIST_EVENTS + motClef + "/" + numPage + "/" + quantite;
		DeSerializer<ListEvents> deserialize = new DeSerializer<ListEvents>();
		List<Evenement> listEvents = deserialize.deJson(webService(url));
		return listEvents;
	}
}
