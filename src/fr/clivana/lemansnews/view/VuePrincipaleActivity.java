package fr.clivana.lemansnews.view;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.android.Facebook;
import com.google.android.apps.analytics.GoogleAnalyticsTracker;

import fr.clivana.lemansnews.R;
import fr.clivana.lemansnews.controller.GalleryOneByOne;
import fr.clivana.lemansnews.controller.VuePrincipaleController;
import fr.clivana.lemansnews.utils.facebook.FacebookFunctions;

//pullToReefresh
import com.markupartist.android.widget.PullToRefreshListView;
import com.markupartist.android.widget.PullToRefreshListView.OnRefreshListener;

public class VuePrincipaleActivity extends Activity{

	
	//Un lien sur le controlleur qui gère les évènements
	VuePrincipaleController vuePrincipaleController;
	
	//les attributs de classe pour la vue
	TextView titreApplication;
	TextView titreActualite;
	TextView titreSuite;
	TextView titreEvenement;
	TextView derniereMaj;	
	//Lien de la gallerie en haut et au milieu
	GalleryOneByOne galleryEvents;
	PullToRefreshListView pullToRefreshListView;
	//Les boutons de la vue Principale
	Button boutonALaUne;
	Button boutonNews;
	Button boutonEvents;
	Button boutonInfo;
	Button boutonActualiser;
	Button boutonFavoris;
	CategoriesDialog dialog;
	//Tableau de données
	String[] items={"Facebook", "Mail", "SMS", "Google+"};
	//lien sur l'api de google analytics
	GoogleAnalyticsTracker tracker;
	//Lien sur l'api de facebook
	Facebook facebook;
	//Une image
	ImageView suivant;
	ImageView precedent;
	
	public final static String AUTH = "authentication";
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		//désiarilisation de la vue dans l'activity
		setContentView(R.layout.main);
		//Appel de la méthode pour s'enregistrer sur google analytics 
		register();
		//Apper de l'api de google analytics
		tracker = GoogleAnalyticsTracker.getInstance();
		tracker.startNewSession("UA-27914218-1",1, this);
		tracker.setAnonymizeIp(true);
		//page tracker via google analytics
		tracker.trackPageView("/index");
		
		//On récupère depuis le fichier main.xml la vue
		galleryEvents = (GalleryOneByOne)findViewById(R.id.galleryEvents);
		vuePrincipaleController = new VuePrincipaleController(this, galleryEvents);
		titreApplication =(TextView)findViewById(R.id.textViewTitreApplication);
		titreActualite = (TextView)findViewById(R.id.titreActualite);
		titreSuite = (TextView)findViewById(R.id.titreActualiteSuite);
		derniereMaj = (TextView)findViewById(R.id.textViewDateMAJ);
		pullToRefreshListView = (PullToRefreshListView)findViewById(R.id.pullToRefreshListView);
		boutonALaUne = (Button)findViewById(R.id.buttonALaUne);
		boutonNews = (Button)findViewById(R.id.buttonNews);
		boutonEvents = (Button)findViewById(R.id.buttonEvents);
		boutonInfo = (Button)findViewById(R.id.buttonInfo);
		boutonActualiser = (Button)findViewById(R.id.buttonActualiser);
		boutonFavoris = (Button)findViewById(R.id.buttonFavoris);
		//désrialisation des boutons
		
		
		//Un background dans la barre de titre
		titreApplication.setBackgroundResource(R.drawable.titreapplication);
		
		//modification de la police pour la vue principale
		vuePrincipaleController.miseEnPageRomanLight(titreActualite);
		vuePrincipaleController.miseEnPageRomanLight(titreSuite);
		
		setDate();
		initAdapters();
		
		
		//bouton selection 
		boutonALaUne.setPressed(true);
        boutonALaUne.setClickable(false);
        
        //Ajout des listeners sur les boutons et envoie au controleur
        boutonNews.setOnClickListener(vuePrincipaleController);
        boutonActualiser.setOnClickListener(vuePrincipaleController);
        boutonEvents.setOnClickListener(vuePrincipaleController);
        boutonInfo.setOnClickListener(vuePrincipaleController);
        boutonFavoris.setOnClickListener(vuePrincipaleController);
        
        //Ajout des évenements sur la gallerie
        galleryEvents.setOnItemClickListener(vuePrincipaleController);
        pullToRefreshListView.setOnItemClickListener(vuePrincipaleController);
        
        //Ajout de l'événements du pull to refresh
        
        pullToRefreshListView.setOnRefreshListener(vuePrincipaleController);
        
        
        
	}
	
	public void onResume(){
		super.onResume();
		boutonALaUne.setPressed(true);
        boutonALaUne.setClickable(false);
	}
	
	public void initAdapters(){
		
		//Envoie de l'evenements issue de la gallerie de la vue principale
		galleryEvents.setAdapter(vuePrincipaleController.initGalleryAdapter());
		pullToRefreshListView.setAdapter(vuePrincipaleController.initNewsAdapter());
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		//Initialisation du layout
		MenuInflater inflater = new MenuInflater(this);
		inflater.inflate(R.menu.optionmenu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	//méthode qui permet de gérer les options clic droit sur la vue principal
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()){
		case R.id.share:
			tracker.trackEvent("Accueil", "option", "partage application", 1);
			dialog=new CategoriesDialog(this, "Partager l'application", "", "", "Annuler", items, -1, 3);
			dialog.addInfos("Application Le Mans News & Evénements", "https://market.android.com/details?id=fr.clivana.lemansnews", "logoLeMans");
			dialog.getBuilder().show();
			break;
			
		case R.id.actualiseroption:
			tracker.trackEvent("Accueil", "option", "actualisation", 1);
			vuePrincipaleController.Actualisation();
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	protected void onDestroy() {
		tracker.stopSession();
		super.onDestroy();
	}

	public void setDate() {
		derniereMaj.setText("Actualisé le : "+getSharedPreferences("prefs", 0).getString("date", ""));
		
	}
	//facebook retour de la requête envoyée a facebook
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(requestCode==FacebookFunctions.FACEBOOK_REQUEST_CODE){
			FacebookFunctions.handleLoginResult(resultCode, data);
		}
	}
	
	public void register() {
		Intent intent = new Intent("com.google.android.c2dm.intent.REGISTER");
		intent.putExtra("app",PendingIntent.getBroadcast(this, 0, new Intent(), 0));
		intent.putExtra("sender", "clivanadev@gmail.com");
		startService(intent);
	}
	
	public void showRegistrationId() {
		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(this);
		String string = prefs.getString(AUTH, "n/a");
		Toast.makeText(this, string, Toast.LENGTH_LONG).show();
		Log.d("C2DM RegId", string);

	}
	//Méthode pour rafraichir le pulltoRefresh au niveau visuel
	public void refreshVisuActivity (){
		
		pullToRefreshListView.onRefreshComplete();
		
	}
}
