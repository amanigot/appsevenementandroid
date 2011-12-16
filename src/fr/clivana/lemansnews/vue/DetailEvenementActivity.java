package fr.clivana.lemansnews.vue;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import fr.clivana.lemansnews.R;
import fr.clivana.lemansnews.controller.DetailEventsController;

public class DetailEvenementActivity extends Activity {

	
	TextView titreApplication;
	TextView titreEvenement;
	TextView dateEvenement;
	TextView lieuEvenement;
	ImageView imageEvenement;
	ImageView partager;
	WebView detailEvenement;
	Button boutonRetour;
	DetailEventsController detailEventsController;
	int idEvenement;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
        setContentView(R.layout.visuelevent);
        
        Bundle extras = getIntent().getExtras();
		if(extras!=null){
			idEvenement=extras.getInt("event");
		}else{
			idEvenement=savedInstanceState.getInt("event");
		}
		
		detailEventsController = new DetailEventsController(this, idEvenement);
		
		titreApplication=(TextView)findViewById(R.id.textViewTitreApplication);
		titreEvenement=(TextView)findViewById(R.id.textViewTitreEvents);
		dateEvenement=(TextView)findViewById(R.id.textViewDateEvent);
		lieuEvenement=(TextView)findViewById(R.id.textViewLieuEvent);
		imageEvenement=(ImageView)findViewById(R.id.imageViewEvents);
		partager=(ImageView)findViewById(R.id.imageViewPartager);
		detailEvenement=(WebView)findViewById(R.id.descriptionEvents);
		boutonRetour=(Button)findViewById(R.id.buttonRetour);
		
		titreApplication.setText(detailEventsController.initTitre());
		titreEvenement.setText(detailEventsController.getTitreEvenement());
		dateEvenement.setText(detailEventsController.getDateEvenement());
		lieuEvenement.setText(detailEventsController.getLieuEvenement());
		detailEvenement.loadData(detailEventsController.getDescriptionEvenement(), "text/html", null);
		
		partager.setOnClickListener(detailEventsController);
		boutonRetour.setOnClickListener(detailEventsController);
	}
	
}
