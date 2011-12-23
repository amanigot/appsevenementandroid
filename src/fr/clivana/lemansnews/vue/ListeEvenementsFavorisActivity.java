package fr.clivana.lemansnews.vue;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import fr.clivana.lemansnews.R;
import fr.clivana.lemansnews.controller.ListeEvenementsFavorisController;

public class ListeEvenementsFavorisActivity extends Activity {
	
	ListView listeEvenementsFavoris;
	ListeEvenementsFavorisController controller;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listeevenementsfavoris);
		controller = new ListeEvenementsFavorisController(this);
		listeEvenementsFavoris=(ListView)findViewById(R.id.ListeEvenementsFavoris);
		
		listeEvenementsFavoris.setAdapter(controller.initAdapter());
		listeEvenementsFavoris.setOnItemClickListener(controller);
	}

}
