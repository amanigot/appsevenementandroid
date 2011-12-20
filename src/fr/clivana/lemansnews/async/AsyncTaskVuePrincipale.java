package fr.clivana.lemansnews.async;

import java.util.List;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Gallery;
import android.widget.GridView;
import fr.clivana.lemansnews.controller.GalleryAdapter;
import fr.clivana.lemansnews.controller.GridNewsAdapter;
import fr.clivana.lemansnews.dao.EventsDAO;
import fr.clivana.lemansnews.dao.NewsDAO;
import fr.clivana.lemansnews.entity.Article;
import fr.clivana.lemansnews.entity.Evenement;

public class AsyncTaskVuePrincipale extends AsyncTask<Void, Void, Void> {
	
	NewsDAO newsDao;
	EventsDAO eventsDao;
	Context context;
	ProgressDialog progress;
	GalleryAdapter galleryAdapter;
	GridNewsAdapter newsAdapter;
	List<Evenement> evenements;
	List<Article> articles;
	Gallery galleryEvents;
	GridView gridViewNewsPrincipale;
	
	public AsyncTaskVuePrincipale(Context context) {
		super();
		this.context = context;
		newsDao = new NewsDAO(this.context);
		eventsDao = new EventsDAO(this.context);
		progress = new ProgressDialog(this.context);
//		galleryEvents = (Gallery)findViewById(R.id.galleryEvents);
//		gridViewNewsPrincipale = (GridView)findViewById(R.id.gridViewNewsPrincipal);
		
	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		progress.show();
	}
	
	@Override
	protected Void doInBackground(Void... params) {
		//Reseau.webservice();
		//Reseau.webservice();
		return null;
	}
	
	@Override
	protected void onPostExecute(Void result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		//evenements=eventsDao.getAllEvents();
		//articles=newsDao.getAllArticles();
		galleryAdapter=new GalleryAdapter(this.context, evenements);
		newsAdapter= new GridNewsAdapter(this.context, articles);
		galleryEvents.setAdapter(galleryAdapter);
		gridViewNewsPrincipale.setAdapter(newsAdapter);
		progress.dismiss();
	}
	
}
