package fr.clivana.lemansnews.vue;


import fr.clivana.lemansnews.R;
import fr.clivana.lemansnews.controller.GridNewsController;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

public class GridNewsActivity extends Activity{
	
	GridNewsController controller;
	Button boutonRetour;
	Button boutonActualiser;
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
		titreApplication=(TextView)findViewById(R.id.textViewTitreApplication);
		
		gridViewNews.setAdapter(controller.initGridNewsAdapter());
		gridViewNews.setOnItemClickListener(controller);
		boutonActualiser.setOnClickListener(controller);
		boutonRetour.setOnClickListener(controller);
		titreApplication.setText(controller.initTitre());
	}
	
}