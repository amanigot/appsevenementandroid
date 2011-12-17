package fr.clivana.lemansnews.controller;

import java.util.List;

import fr.clivana.lemansnews.R;
import fr.clivana.lemansnews.dao.EventsDAO;
import fr.clivana.lemansnews.entity.Evenement;
import fr.clivana.lemansnews.vue.DetailEvenementActivity;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;

public class ListeEvenementsController implements OnClickListener, OnItemClickListener {

	Context context;
	EventsDAO eventsDao;
	ListEvenementsAdapter adapter;
	List<Evenement> evenements;
	
	public ListeEvenementsController(Context c) {
		// TODO Auto-generated constructor stub
		context=c;
		eventsDao= new EventsDAO(context);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId()==R.id.buttonActualiser){
			
		}
		if(v.getId()==R.id.buttonRetour){
			((Activity) context).finish();
		}
	}

	public CharSequence initTitre() {
		// TODO Auto-generated method stub
		return "Evénements";
	}

	public ListAdapter initAdapter() {
		evenements=eventsDao.getAllEvents();
		if(evenements==null){
			
		}
		adapter=new ListEvenementsAdapter(context, evenements);
		return adapter;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
		// TODO Auto-generated method stub
		Intent evenementIntent = new Intent(context, DetailEvenementActivity.class);
		evenementIntent.putExtra("event", evenements.get(position).getId());
		context.startActivity(evenementIntent);
	}

}