package fr.clivana.lemansnews.async;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ProgressBar;
import android.widget.TextView;
import fr.clivana.lemansnews.dao.CategoriesDAO;
import fr.clivana.lemansnews.dao.EventsDAO;
import fr.clivana.lemansnews.dao.NewsDAO;
import fr.clivana.lemansnews.entity.Article;
import fr.clivana.lemansnews.entity.Evenement;
import fr.clivana.lemansnews.utils.Reseau;

public class AsyncTaskBDD extends AsyncTask<Void, String, Void>{
	
	private Context context;
	EventsDAO eventsDao;// = new DatabaseClivanaEventsDAO(context);
	NewsDAO newsDao;// = new DatabaseClivanaNewsDAO(context);
	CategoriesDAO categoriesDao;
	List<Article> news = new ArrayList<Article>();
	List<Evenement> events= new ArrayList<Evenement>();
	TextView loading;
	ProgressBar progression;
	
	public AsyncTaskBDD(Context context, TextView load, ProgressBar progress) {
		super();
		this.context = context;
		newsDao = new NewsDAO(this.context);
		eventsDao = new EventsDAO(this.context);
		categoriesDao = new CategoriesDAO(context);
		loading=load;
		progression = progress;
	}
	
	@Override
	protected void onPreExecute() {
		
	}
	
	@Override
	protected Void doInBackground(Void... params) {
		publishProgress("Chargement des événements..."); //inscrit sur la textview chargement des events
//		Reseau.webService(Reseau.URL_LIST_EVENTS+"all/1");
		publishProgress("Chargement des actualités..."); //inscrit sur la textview chargement des actus
//		Reseau.webService(Reseau.URL_LIST_NEWS+"all/1");
		publishProgress("Chargement..."); //inscrit sur la textview chargement
//		Reseau.webService(Reseau.URL_LIST_KEYWORDS);
		return null;
	}
	
	@Override
	protected void onProgressUpdate(String... values) {
		//Attention : ici, values n'est pas du type String mais String Array (String[]) !
		super.onProgressUpdate(values);
		loading.setText(values[0]);
	}
	
	@Override
	protected void onPostExecute(Void params){
		//Intent VuePrincipale = 
	}
}
