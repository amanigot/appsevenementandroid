package fr.clivana.lemansnews.async;

import java.util.Date;

import com.markupartist.android.widget.PullToRefreshListView;

import fr.clivana.lemansnews.R;
import fr.clivana.lemansnews.utils.Formatage;
import fr.clivana.lemansnews.utils.reseau.Reseau;
import fr.clivana.lemansnews.view.VuePrincipaleActivity;
import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;


public class AsyncTaskPullTorefreshVuPrincipale extends
		AsyncTask<Void, Void, Void> {
	
	
	Context context;
	
	
	
	
	public AsyncTaskPullTorefreshVuPrincipale(Context context) {
		super();
		this.context = context;
		
	}

	@Override
	protected void onPreExecute() {
		
		super.onPreExecute();
		
		
	}
	
	@Override
	protected Void doInBackground(Void... params) {
		
		Reseau.majArticles(context, "all", 0, 0);
		
		//Reseau.majCategories(context);
		return null;
	}
	
	@Override
	protected void onPostExecute(Void result) {
		
		super.onPostExecute(result);
		
		((VuePrincipaleActivity) context).initAdapters();
		Editor editor=context.getSharedPreferences("prefs", 0).edit();
		editor.putString("date", Formatage.dateEnTexteAvecHeure(new Date()));
		editor.commit();
		((VuePrincipaleActivity) context).setDate();
		((VuePrincipaleActivity) context).refreshVisuActivity();
		
		
		
	}
	
}