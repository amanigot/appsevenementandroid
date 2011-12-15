package fr.clivana.lemansnews.vue;

import fr.clivana.lemansnews.R;
import fr.clivana.lemansnews.controller.VuePrincipaleController;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.GridView;
import android.widget.TextView;

public class VuePrincipaleActivity extends Activity{

	VuePrincipaleController controller;
	TextView titreApplication;
	TextView titreActualite;
	TextView titreSuite;
	Gallery galleryEvents;
	GridView gridViewNewsPrincipale;
	Button boutonALaUne;
	Button boutonNews;
	Button boutonEvents;
	Button boutonInfo;
	Button boutonActualiser;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		controller = new VuePrincipaleController(this);
		titreApplication =(TextView)findViewById(R.id.textViewTitreApplication);
		titreActualite = (TextView)findViewById(R.id.titreActualite);
		titreSuite = (TextView)findViewById(R.id.titreActualiteSuite);
		galleryEvents = (Gallery)findViewById(R.id.galleryEvents);
		gridViewNewsPrincipale = (GridView)findViewById(R.id.gridViewNewsPrincipal);
		boutonALaUne = (Button)findViewById(R.id.buttonALaUne);
		boutonNews = (Button)findViewById(R.id.buttonNews);
		boutonEvents = (Button)findViewById(R.id.buttonEvents);
		boutonInfo = (Button)findViewById(R.id.buttonInfo);
		boutonActualiser = (Button)findViewById(R.id.buttonActualiser);
		
		controller.miseEnPageRoman(titreApplication);
		controller.miseEnPageRomanLight(titreActualite);
		controller.miseEnPageRomanLight(titreSuite);
		
		//galleryEvents.setAdapter(controller.initGalleryAdapter());
		//gridViewNewsPrincipale.setAdapter(controller.initNewsAdapter());
		
		boutonALaUne.setPressed(true);
        boutonALaUne.setClickable(false);
        
        boutonNews.setOnClickListener(controller);
        boutonActualiser.setOnClickListener(controller);
        boutonEvents.setOnClickListener(controller);
        boutonInfo.setOnClickListener(controller);
	}
	
	public void onResume(){
		super.onResume();
		boutonALaUne.setPressed(true);
        boutonALaUne.setClickable(false);
	}
}
