package fr.clivana.lemansnews.vue;

import fr.clivana.lemansnews.R;
import fr.clivana.lemansnews.controller.VuePrincipaleController;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
	Button boutonFavoris;
	CategoriesDialog dialog;
	String[] items={"Facebook", "Twitter", "Mail", "SMS", "Google+"};
	
	
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
		boutonFavoris = (Button)findViewById(R.id.buttonFavoris);
		
		titreApplication.setBackgroundResource(R.drawable.titreapplication);
		
		controller.miseEnPageRomanLight(titreActualite);
		controller.miseEnPageRomanLight(titreSuite);
		
		initAdapters();
		
		boutonALaUne.setPressed(true);
        boutonALaUne.setClickable(false);
        
        boutonNews.setOnClickListener(controller);
        boutonActualiser.setOnClickListener(controller);
        boutonEvents.setOnClickListener(controller);
        boutonInfo.setOnClickListener(controller);
        boutonFavoris.setOnClickListener(controller);
        
        galleryEvents.setOnItemClickListener(controller);
        gridViewNewsPrincipale.setOnItemClickListener(controller);
	}
	
	public void onResume(){
		super.onResume();
		boutonALaUne.setPressed(true);
        boutonALaUne.setClickable(false);
	}
	
	public void initAdapters(){
		//galleryEvents.setAdapter(controller.initGalleryAdapter());
		//gridViewNewsPrincipale.setAdapter(controller.initNewsAdapter());
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		
		MenuInflater inflater = new MenuInflater(this);
		inflater.inflate(R.menu.optionmenu, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()){
		case R.id.share:
			dialog=new CategoriesDialog(this, "Partager l'application", "", "", "Annuler", items, -1, 3);
			dialog.addInfos("Application Le Mans News & Evénements","");
			dialog.getBuilder().show();
			break;
			
		case R.id.actualiseroption:
			controller.Actualisation();
			break;
		}
		return super.onOptionsItemSelected(item);
	}
}
