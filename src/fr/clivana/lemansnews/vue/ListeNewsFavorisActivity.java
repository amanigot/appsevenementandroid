package fr.clivana.lemansnews.vue;


import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import fr.clivana.lemansnews.R;
import fr.clivana.lemansnews.controller.ListeNewsFavorisController;

public class ListeNewsFavorisActivity extends Activity {

	
	ListView listeNewsFavoris;
	ListeNewsFavorisController controller;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listenewsfavoris);
		listeNewsFavoris=(ListView)findViewById(R.id.ListeNewsFavoris);
		setList();
	}

	private void setList() {
		controller= new ListeNewsFavorisController(this);
		
		listeNewsFavoris.setAdapter(controller.initAdapter());
		listeNewsFavoris.setOnItemClickListener(controller);
	}
	
	@Override
	protected void onResume() {
		
		super.onResume();
		setList();
	}
}
