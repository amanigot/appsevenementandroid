package fr.clivana.lemansnews.async;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.AsyncTask;
import fr.clivana.lemansnews.dao.CategoriesDAO;
import fr.clivana.lemansnews.dao.EventsDAO;
import fr.clivana.lemansnews.dao.NewsDAO;
import fr.clivana.lemansnews.entity.Article;
import fr.clivana.lemansnews.entity.Evenement;

public class AsyncTaskBDD extends AsyncTask<Void, Void, Void>{
	
	private Context context;
	EventsDAO eventsDao;// = new DatabaseClivanaEventsDAO(context);
	NewsDAO newsDao;// = new DatabaseClivanaNewsDAO(context);
	CategoriesDAO categoriesDao;
	List<Article> news = new ArrayList<Article>();
	List<Evenement> events= new ArrayList<Evenement>();
	
	public AsyncTaskBDD(Context context) {
		super();
		this.context = context;
		newsDao = new NewsDAO(this.context);
		eventsDao = new EventsDAO(this.context);
		categoriesDao = new CategoriesDAO(context);
	}
	
	@Override
	protected void onPreExecute() {
		
	}
	
	@Override
	protected Void doInBackground(Void... params) {
//		Reseau.webService(Reseau.URL_LIST_EVENTS+"all/1");
//		Reseau.webService(Reseau.URL_LIST_NEWS+"all/1");
//		Reseau.webService(Reseau.URL_LIST_KEYWORDS);
		return null;
	}
	
	@Override
	protected void onPostExecute(Void params){
	}
}
