package fr.clivana.lemansnews.controller;

import com.google.android.apps.analytics.GoogleAnalyticsTracker;

import fr.clivana.lemansnews.R;
import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;

public class InfoController implements OnClickListener {

	Context context;
	GoogleAnalyticsTracker tracker;
	
	public InfoController(Context c) {
		// TODO Auto-generated constructor stub
		context=c;
		tracker = GoogleAnalyticsTracker.getInstance();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId()==R.id.buttonRetour || v.getId()==R.id.buttonALaUne){
			tracker.trackEvent("Infos", "clic", "Retour a l'accueil", 1);
			((Activity) context).finish();
		}
	}

	public CharSequence initTitre() {
		// TODO Auto-generated method stub
		return "Informations";
	}

}
