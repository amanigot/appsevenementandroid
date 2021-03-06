package fr.clivana.lemansnews.view;


import fr.clivana.lemansnews.R;
import fr.clivana.lemansnews.controller.SplashController;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

public class SplashActivity extends Activity{
	
	TextView version;
	TextView load;
	ProgressBar progress;
	SplashController controller;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        
        version = (TextView)findViewById(R.id.textViewVersion);
        load = (TextView)findViewById(R.id.textViewProgress);
        progress = (ProgressBar)findViewById(R.id.progressBarSplash);
        
        
        //Envoie dans le controlleur des vues pour la gestion des données
        controller = new SplashController(this, load, progress);
        
        version.setText(controller.getVersion());
        
        controller.execute();
        
	}
}
