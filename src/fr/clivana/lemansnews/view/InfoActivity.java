package fr.clivana.lemansnews.view;

import com.google.android.apps.analytics.GoogleAnalyticsTracker;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import fr.clivana.lemansnews.R;
import fr.clivana.lemansnews.controller.InfoController;

public class InfoActivity extends Activity {

	Button boutonRetour, boutonNews, boutonEvenement, boutonALaUne;
	Button boutonInfo, boutonActualiser;
	Button boutonFavoris;
	TextView titreApplication;
	InfoController infoController;
	GoogleAnalyticsTracker tracker;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
        setContentView(R.layout.info);
        
        tracker=GoogleAnalyticsTracker.getInstance();
		tracker.trackPageView("/Infos");
        
        boutonRetour=(Button)findViewById(R.id.buttonRetour);
        boutonNews=(Button)findViewById(R.id.buttonNews);
		boutonEvenement=(Button)findViewById(R.id.buttonEvents);
		boutonALaUne=(Button)findViewById(R.id.buttonALaUne);
        titreApplication=(TextView)findViewById(R.id.textViewTitreApplication);
        boutonFavoris = (Button)findViewById(R.id.buttonFavoris);
        boutonInfo=(Button)findViewById(R.id.buttonInfo);
        boutonActualiser=(Button)findViewById(R.id.buttonActualiser);
        
        infoController= new InfoController(this);
        
        boutonEvenement.setVisibility(View.GONE);
        boutonNews.setVisibility(View.GONE);
        boutonFavoris.setVisibility(View.GONE);
        boutonRetour.setVisibility(View.VISIBLE);
        boutonInfo.setVisibility(View.INVISIBLE);
        boutonActualiser.setVisibility(View.INVISIBLE);
        
        boutonALaUne.setBackgroundResource(R.drawable.btninfoxml);
        boutonALaUne.setOnClickListener(infoController);
        boutonRetour.setOnClickListener(infoController);
        titreApplication.setText(infoController.initTitre());
        Typeface tfRoman = Typeface.createFromAsset(getAssets(), "fonts/helveticaroman.otf");
		titreApplication.setTypeface(tfRoman);
	}
}
