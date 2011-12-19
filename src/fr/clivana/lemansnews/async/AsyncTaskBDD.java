package fr.clivana.lemansnews.async;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;
import android.widget.TextView;
import fr.clivana.lemansnews.R;
import fr.clivana.lemansnews.dao.CategoriesDAO;
import fr.clivana.lemansnews.dao.EventsDAO;
import fr.clivana.lemansnews.dao.NewsDAO;
import fr.clivana.lemansnews.entity.Article;
import fr.clivana.lemansnews.entity.Evenement;
import fr.clivana.lemansnews.vue.VuePrincipaleActivity;

public class AsyncTaskBDD extends AsyncTask<Void, String, Void>{
	
	private Context context;
	private EventsDAO eventsDao;// = new DatabaseClivanaEventsDAO(context);
	private NewsDAO newsDao;// = new DatabaseClivanaNewsDAO(context);
	private CategoriesDAO categoriesDao;
	private List<Article> news = new ArrayList<Article>();
	private List<Evenement> events= new ArrayList<Evenement>();
	private TextView loading;
	private ProgressBar progression;
	
	public AsyncTaskBDD(Context context, TextView load, ProgressBar progress) {
		super();
		this.context = context;
		this.newsDao = new NewsDAO(this.context);
		this.eventsDao = new EventsDAO(this.context);
		this.categoriesDao = new CategoriesDAO(context);
		this.loading=load;
		this.progression = progress;
	}
	
	@Override
	protected void onPreExecute() {
		progression.setIndeterminate(true);
		//progression.startAnimation( AnimationUtils.loadAnimation(context, R.anim.rotateindefinitely));;
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

		Intent VuePrincipale = new Intent(context, VuePrincipaleActivity.class);
		context.startActivity(VuePrincipale);
		((Activity) context).finish();

	}
}
