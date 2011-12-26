package fr.clivana.lemansnews.async;

import java.util.Date;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import fr.clivana.lemansnews.utils.Formatage;
import fr.clivana.lemansnews.utils.reseau.Reseau;
import fr.clivana.lemansnews.vue.VuePrincipaleActivity;

public class AsyncTaskVuePrincipale extends AsyncTask<Void, Void, Void> {
	
	
	Context context;
	ProgressDialog progress;
	
	
	public AsyncTaskVuePrincipale(Context context) {
		super();
		this.context = context;
		progress = new ProgressDialog(this.context);
		progress.setMessage("Mise à jour en cours...");

	}

	@Override
	protected void onPreExecute() {
		
		super.onPreExecute();
		progress.show();
	}
	
	@Override
	protected Void doInBackground(Void... params) {
		Reseau.majEvenements(context, 0, 0);
		Reseau.majArticles(context, "all", 0, 0);
		Reseau.majCategories(context);
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
		progress.dismiss();
	}
	
}
