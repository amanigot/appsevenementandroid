package fr.clivana.lemansnews.controller;

import fr.clivana.lemansnews.R;
import fr.clivana.lemansnews.dao.EventsDAO;
import fr.clivana.lemansnews.entity.Evenement;
import fr.clivana.lemansnews.vue.CategoriesDialog;
import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;

public class DetailEventsController implements OnClickListener {

	Context context;
	EventsDAO eventsDao;
	Evenement evenement;
	String[] items={"Facebook", "Twitter", "Mail", "SMS", "Google+"};
	CategoriesDialog dialog;
	
	public DetailEventsController(Context c, int idEvenement) {
		context=c;
		eventsDao=new EventsDAO(context);
		evenement=eventsDao.getEvenement(idEvenement);
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
			dialog=new CategoriesDialog(context, "Partager", null, null, "Annuler", items, -1, 3);
			dialog.addInfos(evenement.getTitre(), evenement.getDetailEvenement());
			dialog.show();
		}
		if(v.getId()==R.id.buttonInfo){
			//evenement.setFavoris(true);
			eventsDao.updateEvent(evenement);
		}
	}

	public String getDescriptionEvenement() {
		// TODO Auto-generated method stub
		return evenement.getDetailEvenement();
	}

}
