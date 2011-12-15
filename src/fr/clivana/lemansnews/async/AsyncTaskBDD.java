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
		return null;
	}
	
	@Override
	protected void onPostExecute(Void params){
		if(news.size()>0){
			for (int i = 0; i < news.size(); i++) {
				newsDao.insertNews(news.get(i));
			}
		}
		if(!events.get(0).equals(Evenement.noEvents())&&!events.get(0).equals(Evenement.noEventFound())){
			for (int j = 0; j < events.size(); j++) {
				eventsDao.insertNews(events.get(j));
			}
		}
	}
}
