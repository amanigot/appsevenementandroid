package fr.clivana.lemansnews.utils;

import java.io.InputStream;

import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpParams;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class Reseau {

	public final static String URL_LIST_NEWS = "http://www.clivana.com/m/news/list/"; // /m/news/{motClef}/{numPage}
	public final static String URL_COUNT_NEWS = "http://www.clivana.com/m/news/count/"; // /m/news/count/{motClef}/{date}
	public final static String URL_LIST_EVENTS = "http://www.clivana.com/m/events/list/"; // /m/news/{motClef}/{numPage}
	public final static String URL_COUNT_EVENTS = "http://www.clivana.com/m/events/count/"; // /m/news/count/{motClef}/{date}
	public final static String URL_LIST_KEYWORDS = "http://www.clivana.com/m/news/listMotClef";// route OK
	public final static String URL_COUNT_NEWS_IMAGE = "http://www.clivana.com/m/news/countImage/";// /m/news/countImage/{motclef}
	public final static String URL_COUNT_EVENTS_IMAGE = "http://www.clivana.com/m/events/countImage/";// /m/events/countImage/{something}
	
	public static boolean verifReseau(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		if (netInfo != null && netInfo.isConnectedOrConnecting()){
			return true;
		}
		return false;
	}
	
	public static InputStream webService(String url){
		Log.w("Recherche", "debut "+url);
		HttpParams params = new BasicHttpParams();
		params.setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
		DefaultHttpClient client = new DefaultHttpClient(params);
		HttpGet httpGet = new HttpGet(url);
		InputStream result = null;
		try{
		HttpResponse execute = client.execute(httpGet);
		result = execute.getEntity().getContent();
		}catch (Exception e){
			
		}
		Log.w("Recherche", "fin "+url);
		return result;
	}
}
