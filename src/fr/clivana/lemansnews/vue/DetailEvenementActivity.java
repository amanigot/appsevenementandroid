package fr.clivana.lemansnews.vue;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.apps.analytics.GoogleAnalyticsTracker;

import fr.clivana.lemansnews.R;
import fr.clivana.lemansnews.controller.DetailEventsController;
import fr.clivana.lemansnews.utils.facebook.FacebookFunctions;

public class DetailEvenementActivity extends Activity {

	
	TextView titreApplication;
	TextView titreEvenement;
	TextView dateEvenement;
	TextView lieuEvenement;
	ImageView imageEvenement;
	ImageView partager;
	TextView date, lieu;
	WebView detailEvenement;
	Button boutonRetour, boutonNews, boutonEvenement, boutonALaUne;
	Button boutonInfo, boutonActualiser;
	Button boutonFavoris;
	DetailEventsController detailEventsController;
	Typeface tfRoman, tfLight;
	long idEvenement;
	GoogleAnalyticsTracker tracker;
	
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
        setContentView(R.layout.visuelevent);
        
        Bundle extras = getIntent().getExtras();
		if(extras!=null){
			idEvenement=extras.getLong("event");
		}else{
			idEvenement=savedInstanceState.getLong("event");
		}
		
		detailEventsController = new DetailEventsController(this, idEvenement);
		
		tracker=GoogleAnalyticsTracker.getInstance();
		tracker.trackPageView("/Evenement/"+idEvenement+"/"+detailEventsController.getTitreEvenement());
		
		titreApplication=(TextView)findViewById(R.id.textViewTitreApplication);
		titreEvenement=(TextView)findViewById(R.id.textViewTitreEvents);
		dateEvenement=(TextView)findViewById(R.id.textViewDateEvent);
		lieuEvenement=(TextView)findViewById(R.id.textViewLieuEvent);
		imageEvenement=(ImageView)findViewById(R.id.imageViewEvents);
		partager=(ImageView)findViewById(R.id.imageViewPartager);
		detailEvenement=(WebView)findViewById(R.id.descriptionEvents);
		boutonRetour=(Button)findViewById(R.id.buttonRetour);
		boutonNews=(Button)findViewById(R.id.buttonNews);
		boutonEvenement=(Button)findViewById(R.id.buttonEvents);
		boutonALaUne=(Button)findViewById(R.id.buttonALaUne);
		boutonFavoris = (Button)findViewById(R.id.buttonFavoris);
		boutonInfo=(Button)findViewById(R.id.buttonInfo);
		boutonActualiser=(Button)findViewById(R.id.buttonActualiser);
		date=(TextView)findViewById(R.id.textViewDate);
		lieu=(TextView)findViewById(R.id.textViewLieu);
		
		//initialise le contenu des vues
		titreApplication.setText(detailEventsController.initTitre());
		tfRoman = Typeface.createFromAsset(getAssets(), "fonts/helveticaroman.otf");
		tfLight = Typeface.createFromAsset(getAssets(), "fonts/helveticalight.otf");
		titreApplication.setTypeface(tfRoman);
		titreEvenement.setText(detailEventsController.getTitreEvenement());
		dateEvenement.setText(detailEventsController.getDateEvenement());
		lieuEvenement.setText(detailEventsController.getLieuEvenement());
		titreEvenement.setTypeface(tfLight);
		dateEvenement.setTypeface(tfLight);
		lieuEvenement.setTypeface(tfLight);
		date.setTypeface(tfLight);
		lieu.setTypeface(tfLight);
        String header = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>";
		detailEvenement.loadDataWithBaseURL(null,header+detailEventsController.getDescriptionEvenement(), "text/html", "UTF-8",null);
		
		 //ici on gère la visibilité des boutons du menu du bas
		boutonNews.setVisibility(View.GONE);
		boutonALaUne.setVisibility(View.GONE);
		boutonFavoris.setVisibility(View.GONE);
		boutonRetour.setVisibility(View.VISIBLE);
		boutonActualiser.setVisibility(View.INVISIBLE);
		
		detailEventsController.initFavButton(boutonInfo);
		detailEventsController.setFavButton();
		
		detailEventsController.setImage(imageEvenement);
		
		boutonInfo.setOnClickListener(detailEventsController);
		
		partager.setOnClickListener(detailEventsController);
		boutonRetour.setOnClickListener(detailEventsController);
		boutonEvenement.setOnClickListener(detailEventsController);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(requestCode==FacebookFunctions.FACEBOOK_REQUEST_CODE){
			FacebookFunctions.handleLoginResult(resultCode, data);
		}
	}
	
}
