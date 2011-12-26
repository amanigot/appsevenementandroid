package fr.clivana.lemansnews.controller;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;

import com.google.android.apps.analytics.GoogleAnalyticsTracker;

import fr.clivana.lemansnews.dao.EventsDAO;
import fr.clivana.lemansnews.dao.NewsDAO;
import fr.clivana.lemansnews.entity.Article;
import fr.clivana.lemansnews.entity.Evenement;

public class FavorisController implements OnClickListener {

	Context context;
	NewsDAO newsDao;
	EventsDAO eventsDao;
	ListEvenementsAdapter eventsAdapter;
	ListNewsAdapter newsAdapter;
	List<Article> articles;
	List<Evenement> evenements;
	GoogleAnalyticsTracker tracker;
	
	public FavorisController(Context c) {
		context=c;
		newsDao=new NewsDAO(context);
		eventsDao=new EventsDAO(context);
		tracker = GoogleAnalyticsTracker.getInstance();
	}

	public CharSequence initTitre() {
		return "Vos favoris";
	}

	@Override
	public void onClick(View v) {
		((Activity)context).finish();
	}

}
