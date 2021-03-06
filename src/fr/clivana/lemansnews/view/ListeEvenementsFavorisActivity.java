package fr.clivana.lemansnews.view;

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
		setList();
	}

	private void setList() {
		listeEvenementsFavoris=(ListView)findViewById(R.id.ListeEvenementsFavoris);
		
		controller.initAdapter(listeEvenementsFavoris);
		listeEvenementsFavoris.setOnItemClickListener(controller);
	}

	@Override
	protected void onResume() {
		super.onResume();
		setList();
	}
}
