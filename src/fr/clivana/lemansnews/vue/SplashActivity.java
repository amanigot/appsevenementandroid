package fr.clivana.lemansnews.vue;


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
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        SplashController controller = new SplashController(this);
        controller.execute();
        
	}
}
