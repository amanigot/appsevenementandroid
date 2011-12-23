package fr.clivana.lemansnews.controller;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;

import com.google.android.apps.analytics.GoogleAnalyticsTracker;

import fr.clivana.lemansnews.dao.EventsDAO;
import fr.clivana.lemansnews.entity.Evenement;
import fr.clivana.lemansnews.vue.DetailEvenementActivity;

public class ListeEvenementsFavorisController implements OnItemClickListener {

	Context context;
	List<Evenement> evenements;
	EventsDAO eventsDao;
	ListEvenementsAdapter eventsAdapter;
	GoogleAnalyticsTracker tracker;
	
	public ListeEvenementsFavorisController(Context context) {
		this.context=context;
		eventsDao=new EventsDAO(this.context);
		tracker=GoogleAnalyticsTracker.getInstance();
	}

	public ListAdapter initAdapter() {
		evenements=eventsDao.getFavoriteEvents();
		
		eventsAdapter=new ListEvenementsAdapter(context, evenements);
		return eventsAdapter;
		
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
		tracker.trackEvent("Favoris-Evenement", "clic", "detail-"+evenements.get(position).getId()+"-"+evenements.get(position).getTitre(), 1);
		Intent evenementIntent = new Intent(context, DetailEvenementActivity.class);
		evenementIntent.putExtra("event", evenements.get(position).getId());
		context.startActivity(evenementIntent);
		
	}

}
