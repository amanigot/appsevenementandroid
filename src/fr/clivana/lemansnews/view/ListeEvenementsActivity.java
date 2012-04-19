package fr.clivana.lemansnews.view;

import com.google.android.apps.analytics.GoogleAnalyticsTracker;

import fr.clivana.lemansnews.R;
import fr.clivana.lemansnews.controller.ListeEvenementsController;
import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class ListeEvenementsActivity extends Activity{
	
	Button boutonActualiser;
	Button boutonRetour, boutonNews, boutonEvenement, boutonALaUne;
	Button boutonInfo;
	Button boutonFavoris;
	TextView titreApplication;
	ListView listeEvents;
	ListeEvenementsController listeEvenementsController;
	GoogleAnalyticsTracker tracker;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listeevenement);
		
		tracker=GoogleAnalyticsTracker.getInstance();
		tracker.trackPageView("/ListeEvenement");
		
		boutonActualiser=(Button)findViewById(R.id.buttonActualiser);
		boutonRetour=(Button)findViewById(R.id.buttonRetour);
		titreApplication=(TextView)findViewById(R.id.textViewTitreApplication);
		listeEvents=(ListView)findViewById(R.id.listViewEvenements);
		boutonNews=(Button)findViewById(R.id.buttonNews);
		boutonEvenement=(Button)findViewById(R.id.buttonEvents);
		boutonALaUne=(Button)findViewById(R.id.buttonALaUne);
		boutonFavoris = (Button)findViewById(R.id.buttonFavoris);
		boutonInfo=(Button)findViewById(R.id.buttonInfo);
		
		listeEvenementsController= new ListeEvenementsController(this);
		
		boutonNews.setVisibility(View.GONE);
		boutonALaUne.setVisibility(View.GONE);
		boutonFavoris.setVisibility(View.GONE);
		boutonRetour.setVisibility(View.VISIBLE);
		boutonInfo.setVisibility(View.INVISIBLE);
		
		boutonActualiser.setOnClickListener(listeEvenementsController);
		boutonRetour.setOnClickListener(listeEvenementsController);
		boutonEvenement.setOnClickListener(listeEvenementsController);
		
		titreApplication.setText(listeEvenementsController.initTitre());
		Typeface tfRoman = Typeface.createFromAsset(getAssets(), "fonts/helveticaroman.otf");
		
		titreApplication.setTypeface(tfRoman);
		
		initAdapters();
		
		listeEvents.setOnItemClickListener(listeEvenementsController);
	}

	public void initAdapters() {
		
		listeEvents.setAdapter(listeEvenementsController.initAdapter());
		
	}

}
