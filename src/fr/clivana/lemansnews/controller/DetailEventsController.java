package fr.clivana.lemansnews.controller;

import fr.clivana.lemansnews.R;
import fr.clivana.lemansnews.dao.EventsDAO;
import fr.clivana.lemansnews.entity.Evenement;
import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;

public class DetailEventsController implements OnClickListener {

	Context context;
	EventsDAO eventsDao;
	Evenement evenement;
	
	public DetailEventsController(Context c, int idEvenement) {
		context=c;
		eventsDao=new EventsDAO(context);
		evenement=eventsDao.getArticle(idEvenement);
	}

	public CharSequence initTitre() {
		// TODO Auto-generated method stub
		return "Evénement";
	}

	public CharSequence getTitreEvenement() {
		// TODO Auto-generated method stub
		return evenement.getTitre();
	}

	public CharSequence getDateEvenement() {
		// TODO Auto-generated method stub
		return evenement.getDateHeureEvenement();
	}

	public CharSequence getLieuEvenement() {
		// TODO Auto-generated method stub
		return evenement.getLieu();
	}

	@Override
	public void onClick(View v) {
		if(v.getId()==R.id.buttonRetour){
			((Activity) context).finish();
		}
		if(v.getId()==R.id.imageViewPartager){
			
		}
	}

	public String getDescriptionEvenement() {
		// TODO Auto-generated method stub
		return evenement.getDetailEvenement();
	}

}
