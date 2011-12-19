package fr.clivana.lemansnews.vue;

import android.app.TabActivity;
import android.os.Bundle;
import android.widget.TabHost;
import fr.clivana.lemansnews.R;

public class TabNavActivity extends TabActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tabnav);
		TabHost mTabHost = getTabHost();
	    
	    mTabHost.addTab(mTabHost.newTabSpec("tab_test1").setIndicator("", getResources().getDrawable(R.drawable.btnalaunexml)).setContent(R.id.textview1));
	    mTabHost.addTab(mTabHost.newTabSpec("tab_test2").setIndicator("", getResources().getDrawable(R.drawable.btnalaunexml)).setContent(R.id.textview2));
	    mTabHost.addTab(mTabHost.newTabSpec("tab_test3").setIndicator("", getResources().getDrawable(R.drawable.btnalaunexml)).setContent(R.id.textview3));
	    
	    mTabHost.setCurrentTab(0);
	}
}
