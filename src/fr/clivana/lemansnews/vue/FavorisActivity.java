package fr.clivana.lemansnews.vue;

import android.app.TabActivity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import fr.clivana.lemansnews.R;
import fr.clivana.lemansnews.controller.FavorisController;

public class FavorisActivity extends TabActivity {

	ListView listViewNews, listViewEvents;
	TabHost mTabHost;
	FavorisController controller;
	TextView titreApplication;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.favoris);
		mTabHost = getTabHost();
	    listViewEvents=(ListView)findViewById(R.id.listViewEvenementsFav);
	    listViewNews=(ListView)findViewById(R.id.listViewNews);
	    controller=new FavorisController(this);
	    titreApplication=(TextView)findViewById(R.id.textViewTitreApplication);
	    titreApplication.setText(controller.initTitre());
	    Typeface tfRoman = Typeface.createFromAsset(getAssets(), "fonts/helveticaroman.otf");
		titreApplication.setTypeface(tfRoman);
	    
	    initAdapters();
	    listViewNews.setOnItemClickListener(controller);
	    listViewEvents.setOnItemClickListener(controller);
	    
	    mTabHost.addTab(mTabHost.newTabSpec("tab_test2").setIndicator("", getResources().getDrawable(R.drawable.btnfavnewsxml)).setContent(R.id.listViewNews));
	    mTabHost.addTab(mTabHost.newTabSpec("tab_test3").setIndicator("", getResources().getDrawable(R.drawable.btnfaveventsxml)).setContent(R.id.listViewEvenementsFav));
	    
	    mTabHost.setCurrentTab(0);
	}

	private void initAdapters() {
		
		listViewNews.setAdapter(controller.initListNewsAdapter());
		listViewEvents.setAdapter(controller.initListEventsAdapter());
		
	}
}
