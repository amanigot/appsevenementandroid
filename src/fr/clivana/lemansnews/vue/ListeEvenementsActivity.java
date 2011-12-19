package fr.clivana.lemansnews.vue;

import fr.clivana.lemansnews.R;
import fr.clivana.lemansnews.controller.ListeEvenementsController;
import android.app.Activity;
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
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listeevenement);
		
		boutonActualiser=(Button)findViewById(R.id.buttonActualiser);
		boutonRetour=(Button)findViewById(R.id.buttonRetour);
		titreApplication=(TextView)findViewById(R.id.textViewTitreApplication);
		listeEvents=(ListView)findViewById(R.id.listViewEvenements);
		boutonNews=(Button)findViewById(R.id.buttonNews);
		boutonEvenement=(Button)findViewById(R.id.buttonEvents);
		boutonALaUne=(Button)findViewById(R.id.buttonALaUne);
		boutonFavoris = (Button)findViewById(R.id.buttonFavoris);
		
		listeEvenementsController= new ListeEvenementsController(this);
		
		boutonNews.setVisibility(View.GONE);
		boutonALaUne.setVisibility(View.GONE);
		boutonFavoris.setVisibility(View.GONE);
		boutonRetour.setVisibility(View.VISIBLE);
		
		boutonActualiser.setOnClickListener(listeEvenementsController);
		boutonRetour.setOnClickListener(listeEvenementsController);
		boutonEvenement.setOnClickListener(listeEvenementsController);
		
		titreApplication.setText(listeEvenementsController.initTitre());
		listeEvents.setAdapter(listeEvenementsController.initAdapter());
		listeEvents.setOnItemClickListener(listeEvenementsController);
	}

}
