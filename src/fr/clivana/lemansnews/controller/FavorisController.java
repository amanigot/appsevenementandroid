package fr.clivana.lemansnews.controller;

import java.util.List;

import com.google.android.apps.analytics.GoogleAnalyticsTracker;

import fr.clivana.lemansnews.R;
import fr.clivana.lemansnews.dao.EventsDAO;
import fr.clivana.lemansnews.dao.NewsDAO;
import fr.clivana.lemansnews.entity.Article;
import fr.clivana.lemansnews.entity.Evenement;
import fr.clivana.lemansnews.vue.DetailEvenementActivity;
import fr.clivana.lemansnews.vue.DetailNewsActivity;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;

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
		// TODO Auto-generated method stub
		return "Vos favoris";
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		((Activity)context).finish();
	}

}
