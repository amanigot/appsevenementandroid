package fr.clivana.lemansnews.controller;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.google.android.apps.analytics.GoogleAnalyticsTracker;

import fr.clivana.lemansnews.dao.EventsDAO;
import fr.clivana.lemansnews.entity.Evenement;
import fr.clivana.lemansnews.view.DetailEvenementActivity;

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

	public void initAdapter(ListView listeEvenementsFavoris) {
		evenements=eventsDao.getFavoriteEvents();
		if(evenements.size()==1 && evenements.get(0).getId()==0){
			listeEvenementsFavoris.setVisibility(View.GONE);
		}else{
			eventsAdapter=new ListEvenementsAdapter(context, evenements);
			listeEvenementsFavoris.setAdapter(eventsAdapter);
		}
		
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
		tracker.trackEvent("Favoris-Evenement", "clic", "detail-"+evenements.get(position).getId()+"-"+evenements.get(position).getTitre(), 1);
		Intent evenementIntent = new Intent(context, DetailEvenementActivity.class);
		evenementIntent.putExtra("event", evenements.get(position).getId());
		context.startActivity(evenementIntent);
		
	}

}
