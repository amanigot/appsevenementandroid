package fr.clivana.lemansnews.vue;


import fr.clivana.lemansnews.R;
import fr.clivana.lemansnews.controller.GridNewsController;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.GridView;

public class GridNewsActivity extends Activity{
	
	GridNewsController controller;
	Button boutonRetour;
	Button boutonActualiser;
	GridView gridViewNews;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gridnews);
		controller = new GridNewsController(this);
		
		gridViewNews=(GridView)findViewById(R.id.gridViewNews);
		boutonActualiser=(Button)findViewById(R.id.buttonActualiser);
		boutonRetour=(Button)findViewById(R.id.buttonRetour);
		
		gridViewNews.setAdapter(controller.initGridNewsAdapter());
		boutonActualiser.setOnClickListener(controller);
		boutonRetour.setOnClickListener(controller);
	}
	
}
