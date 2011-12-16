package fr.clivana.lemansnews.vue;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import fr.clivana.lemansnews.R;
import fr.clivana.lemansnews.controller.InfoController;

public class InfoActivity extends Activity {

	Button boutonRetour;
	TextView titreApplication;
	InfoController infoController;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
        setContentView(R.layout.info);
        boutonRetour=(Button)findViewById(R.id.buttonRetour);
        titreApplication=(TextView)findViewById(R.id.textViewTitreApplication);
        
        infoController= new InfoController(this);
        
        boutonRetour.setOnClickListener(infoController);
        titreApplication.setText(infoController.initTitre());
	}
}
