package fr.clivana.lemansnews.vue;

import com.google.android.apps.analytics.GoogleAnalyticsTracker;

import fr.clivana.lemansnews.R;
import fr.clivana.lemansnews.controller.CategoriesController;
import android.app.Activity;
import android.app.Dialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

public class CategoriesActivity extends Activity{

	CategoriesController controller;
	Button ajouterCategorie;
	Button boutonRetour, boutonNews, boutonEvenement, boutonALaUne;
	Button boutonActualiser;
	Button boutonInfo;
	Button boutonFavoris;
	GridView categories;
	TextView titreApplication;
	GoogleAnalyticsTracker tracker; 
	
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
        setContentView(R.layout.categorie);
        
        tracker=GoogleAnalyticsTracker.getInstance();
    	tracker.trackPageView("/Categories");
        
        ajouterCategorie=(Button)findViewById(R.id.buttonAjouterCategorie);
        boutonRetour=(Button)findViewById(R.id.buttonRetour);
        boutonNews=(Button)findViewById(R.id.buttonNews);
		boutonEvenement=(Button)findViewById(R.id.buttonEvents);
		boutonALaUne=(Button)findViewById(R.id.buttonALaUne);
		boutonInfo=(Button)findViewById(R.id.buttonInfo);
        boutonActualiser=(Button)findViewById(R.id.buttonActualiser);
        categories=(GridView)findViewById(R.id.gridViewCategorie);
        boutonFavoris = (Button)findViewById(R.id.buttonFavoris);
        titreApplication=(TextView)findViewById(R.id.textViewTitreApplication);
        
        //creation du controller
        controller=new CategoriesController(this);
        
        //titre en haut de la page
        titreApplication.setText(controller.initTitre());
        Typeface tfRoman = Typeface.createFromAsset(getAssets(), "fonts/helveticaroman.otf");
		titreApplication.setTypeface(tfRoman);

		boutonInfo.setBackgroundResource(R.drawable.icomenuhautaide);
		
        //ici on gère la visibilité des boutons du menu du bas
        boutonEvenement.setVisibility(View.GONE);
		boutonALaUne.setVisibility(View.GONE);
		boutonRetour.setVisibility(View.VISIBLE);
		boutonFavoris.setVisibility(View.GONE);
		
		//listeners
		boutonInfo.setOnClickListener(controller);
		boutonNews.setOnClickListener(controller);
        ajouterCategorie.setOnClickListener(controller);
        boutonActualiser.setOnClickListener(controller);
        boutonRetour.setOnClickListener(controller);
        
        initAdapters();
        
        //listeners de la gridnews
        categories.setOnItemClickListener(controller);
        categories.setOnItemLongClickListener(controller);
        
        
	}

	public void initAdapters() {
		//Ici sont tous les setAdapter
		categories.setAdapter(controller.initCategorieAdapter());
		
	}
	
	
}
