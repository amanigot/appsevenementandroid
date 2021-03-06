package fr.clivana.lemansnews.view;

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
import fr.clivana.lemansnews.controller.DetailNewsController;
import fr.clivana.lemansnews.utils.facebook.FacebookFunctions;

public class DetailNewsActivity extends Activity{
	
	TextView titreApplication;
	TextView titreNews;
	TextView dateAuteur;
	ImageView imageNews;
	ImageView partager;
	WebView detailNews;
	Button boutonRetour, boutonNews, boutonEvenement, boutonALaUne;
	Button boutonInfo, boutonActualiser;
	Button boutonFavoris;
	DetailNewsController detailNewsController;
	long idArticle;
	String categorie;
	Typeface tfRoman, tfLight;
	GoogleAnalyticsTracker tracker;
	
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
        setContentView(R.layout.visuelnews);
        
        Bundle extras = getIntent().getExtras();
		if(extras!=null){
			idArticle=extras.getLong("article");
			categorie=extras.getString("categorie");
		}else{
			idArticle=savedInstanceState.getInt("article");
			categorie=savedInstanceState.getString("categorie");
		}
        detailNewsController=new DetailNewsController(this, idArticle, categorie);
        
        tracker=GoogleAnalyticsTracker.getInstance();
    	tracker.trackPageView("/Article/"+idArticle+"/"+detailNewsController.getTitreNews());
        
        titreApplication=(TextView)findViewById(R.id.textViewTitreApplication);
        titreNews=(TextView)findViewById(R.id.textViewTitreNews);
        dateAuteur=(TextView)findViewById(R.id.textViewDateNews);
        imageNews=(ImageView)findViewById(R.id.imageViewNewsEvents);
        detailNews=(WebView)findViewById(R.id.descriptionNews);
        partager=(ImageView)findViewById(R.id.imageViewPartager);
        
        
        //Bouton en bas
        boutonRetour=(Button)findViewById(R.id.buttonRetour);
        
        boutonNews=(Button)findViewById(R.id.buttonNews);
		boutonEvenement=(Button)findViewById(R.id.buttonEvents);
		boutonALaUne=(Button)findViewById(R.id.buttonALaUne);
		boutonFavoris = (Button)findViewById(R.id.buttonFavoris);
		boutonInfo=(Button)findViewById(R.id.buttonInfo);
		
		//bouton en haut
		boutonActualiser=(Button)findViewById(R.id.buttonActualiser);
		
        detailNewsController.setImage(imageNews);
        
        boutonInfo.setOnClickListener(detailNewsController);
        partager.setOnClickListener(detailNewsController);
        boutonRetour.setOnClickListener(detailNewsController);
        boutonNews.setOnClickListener(detailNewsController);
        
        
        
        boutonEvenement.setVisibility(View.GONE);
		boutonALaUne.setVisibility(View.GONE);
		boutonFavoris.setVisibility(View.GONE);
		boutonRetour.setVisibility(View.VISIBLE);
		boutonActualiser.setVisibility(View.INVISIBLE);
		
		detailNewsController.initFavButton(boutonInfo);
		detailNewsController.setFavButton();
		
        titreApplication.setText(detailNewsController.initTitre());
        //titreApplication.setBackgroundResource(R.drawable.bgtitreapplication);

        
        
        tfRoman = Typeface.createFromAsset(getAssets(), "fonts/helveticaroman.otf");
        tfLight = Typeface.createFromAsset(getAssets(), "fonts/helveticalight.otf");
		titreApplication.setTypeface(tfRoman);
        titreNews.setText(detailNewsController.getTitreNews().toString().toUpperCase());
        titreNews.setTypeface(tfLight);
       
        dateAuteur.setText(detailNewsController.initDateAuteur());
        dateAuteur.setTypeface(tfLight);
        
        String header = "<?xml version=\"1.0\" encoding=\"utf-8\" ?>";
        detailNews.loadDataWithBaseURL(null,header+detailNewsController.getDescription(), "text/html", "utf-8",null);

       
       
        
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(requestCode==FacebookFunctions.FACEBOOK_REQUEST_CODE){
			FacebookFunctions.handleLoginResult(resultCode, data);
		}
	}
	
}
