package fr.clivana.lemansnews.async;

import java.util.Date;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import fr.clivana.lemansnews.utils.Formatage;
import fr.clivana.lemansnews.utils.reseau.Reseau;
import fr.clivana.lemansnews.view.VuePrincipaleActivity;


public class AsyncTaskPullTorefreshVuPrincipale extends
		AsyncTask<Void, Void, Void> {
	
	
	Context context;
	private Date dateMAJ;
	
	
	
	public AsyncTaskPullTorefreshVuPrincipale(Context context) {
		super();
		this.context = context;
		
	}

	@Override
	protected void onPreExecute() {
		dateMAJ = new Date();
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
		editor.putString("date", Formatage.dateEnTexteAvecHeure(dateMAJ));
		editor.putString("datePlay", Formatage.datePourPlay(dateMAJ));
		editor.commit();
		((VuePrincipaleActivity) context).setDate();
		//((VuePrincipaleActivity) context).refreshVisuActivity();
		
		
		
	}
	
}