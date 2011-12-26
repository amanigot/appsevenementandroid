package fr.clivana.lemansnews.controller;

import com.google.android.apps.analytics.GoogleAnalyticsTracker;

import fr.clivana.lemansnews.R;
import fr.clivana.lemansnews.async.AsyncTaskImage;
import fr.clivana.lemansnews.dao.EventsDAO;
import fr.clivana.lemansnews.entity.Evenement;
import fr.clivana.lemansnews.vue.CategoriesDialog;
import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class DetailEventsController implements OnClickListener {

	Context context;
	EventsDAO eventsDao;
	Evenement evenement;
	String[] items={"Facebook", "Twitter", "Mail", "SMS", "Google+"};
	CategoriesDialog dialog;
	GoogleAnalyticsTracker tracker;
	Button bouton;
	AsyncTaskImage asyncTask;
	
	public DetailEventsController(Context c, long idEvenement) {
		this.context=c;
		eventsDao=new EventsDAO(context);
		evenement=eventsDao.getEvenement(idEvenement);
		tracker = GoogleAnalyticsTracker.getInstance();
	}

	public CharSequence initTitre() {
		return "Evénement";
	}

	public CharSequence getTitreEvenement() {
		return evenement.getTitre();
	}

	public CharSequence getDateEvenement() {
		return evenement.getDateHeureEvenement();
	}

	public CharSequence getLieuEvenement() {
		return evenement.getLieu();
	}

	@Override
	public void onClick(View v) {
		if(v.getId()==R.id.buttonRetour || v.getId()==R.id.buttonEvents){
			tracker.trackEvent("Detail d'un evenement", "clic", "Précédent", 1);
			((Activity) context).finish();
		}
		if(v.getId()==R.id.imageViewPartager){
			tracker.trackEvent("Detail d'un evenement", "clic", "Partager-"+evenement.getId()+"-"+evenement.getTitre(), 1);
			dialog=new CategoriesDialog(context, "Partager", "", "", "Annuler", items, -1, 3);
			dialog.addInfos(evenement.getTitre(), evenement.getDetailEvenement());
			dialog.getBuilder().show();
		}
		if(v.getId()==R.id.buttonInfo){
			if(!evenement.isFavoris()){
				tracker.trackEvent("Detail d'un evenement", "clic", "Favoris-"+evenement.getId()+"-"+evenement.getTitre(), 1);
				evenement.setFavoris(true);
				eventsDao.updateEvent(evenement);
				setFavButton();
				Toast.makeText(context, "Mis en favoris", Toast.LENGTH_SHORT).show();
			}else{
				tracker.trackEvent("Detail d'un evenement", "clic", "Non-Favoris-"+evenement.getId()+"-"+evenement.getTitre(), 1);
				evenement.setFavoris(false);
				eventsDao.updateEvent(evenement);
				setFavButton();
				Toast.makeText(context, "Retiré des favoris", Toast.LENGTH_SHORT).show();
			}
		}
	}

	public String getDescriptionEvenement() {
		return evenement.getDetailEvenement();
	}

	public void initFavButton(Button bouton) {
		this.bouton=bouton;
		
	}
	
	public void setFavButton(){
		if(evenement.isFavoris()){
			bouton.setBackgroundResource( R.drawable.btnmenuhautfavoris);
		}else{
			bouton.setBackgroundResource( R.drawable.btnmenuhautfavorisoff);
		}
	}

	public void setImage(ImageView imageEvenement) {
		asyncTask = new AsyncTaskImage(evenement.getNomImageMobile(), imageEvenement, R.drawable.illustaucuneimage480);
		asyncTask.execute();
	}

}
