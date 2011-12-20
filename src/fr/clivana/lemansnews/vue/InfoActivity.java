package fr.clivana.lemansnews.vue;

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
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
        setContentView(R.layout.info);
        boutonRetour=(Button)findViewById(R.id.buttonRetour);
        boutonNews=(Button)findViewById(R.id.buttonNews);
		boutonEvenement=(Button)findViewById(R.id.buttonEvents);
		boutonALaUne=(Button)findViewById(R.id.buttonALaUne);
        titreApplication=(TextView)findViewById(R.id.textViewTitreApplication);
        boutonFavoris = (Button)findViewById(R.id.buttonFavoris);
        
        infoController= new InfoController(this);
        
        boutonEvenement.setVisibility(View.GONE);
        boutonNews.setVisibility(View.GONE);
        boutonFavoris.setVisibility(View.GONE);
        boutonRetour.setVisibility(View.VISIBLE);
        boutonInfo.setVisibility(View.INVISIBLE);
        
        boutonALaUne.setOnClickListener(infoController);
        boutonRetour.setOnClickListener(infoController);
        titreApplication.setText(infoController.initTitre());
        Typeface tfRoman = Typeface.createFromAsset(getAssets(), "fonts/helveticaroman.otf");
		titreApplication.setTypeface(tfRoman);
	}
}
