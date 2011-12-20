package fr.clivana.lemansnews.vue;


import fr.clivana.lemansnews.R;
import fr.clivana.lemansnews.controller.GridNewsController;
import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

public class GridNewsActivity extends Activity{
	
	GridNewsController controller;
	Button boutonRetour, boutonNews, boutonEvenement, boutonALaUne;
	Button boutonActualiser;
	Button boutonInfo;
	Button boutonFavoris;
	GridView gridViewNews;
	TextView titreApplication;
	String bundle;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gridnews);
		
		Bundle extras = getIntent().getExtras();
		if(extras!=null){
			bundle=extras.getString("categorie");
		}else{
			bundle=savedInstanceState.getString("categorie");
		}
		controller = new GridNewsController(this, bundle);
		
		gridViewNews=(GridView)findViewById(R.id.gridViewNews);
		boutonActualiser=(Button)findViewById(R.id.buttonActualiser);
		boutonRetour=(Button)findViewById(R.id.buttonRetour);
		boutonNews=(Button)findViewById(R.id.buttonNews);
		boutonEvenement=(Button)findViewById(R.id.buttonEvents);
		boutonALaUne=(Button)findViewById(R.id.buttonALaUne);
		titreApplication=(TextView)findViewById(R.id.textViewTitreApplication);
		boutonFavoris = (Button)findViewById(R.id.buttonFavoris);
		
		initAdapters();
		
		gridViewNews.setOnItemClickListener(controller);
		boutonActualiser.setOnClickListener(controller);
		
		boutonEvenement.setVisibility(View.GONE);
		boutonALaUne.setVisibility(View.GONE);
		boutonFavoris.setVisibility(View.GONE);
		boutonRetour.setVisibility(View.VISIBLE);
		
		boutonNews.setOnClickListener(controller);
		boutonRetour.setOnClickListener(controller);
		
		titreApplication.setText(controller.initTitre());
		Typeface tfRoman = Typeface.createFromAsset(getAssets(), "fonts/helveticaroman.otf");
		titreApplication.setTypeface(tfRoman);
	}

	public void initAdapters() {
		
		gridViewNews.setAdapter(controller.initGridNewsAdapter());
		
	}
	
	
}