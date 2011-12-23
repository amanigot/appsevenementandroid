package fr.clivana.lemansnews.async;

import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;
import fr.clivana.lemansnews.utils.Formatage;
import fr.clivana.lemansnews.utils.reseau.Reseau;
import fr.clivana.lemansnews.vue.VuePrincipaleActivity;

public class AsyncTaskBDD extends AsyncTask<Void, String, Void>{
	
	private Context context;
	private TextView loading;
	private ProgressBar progression;
	
	public AsyncTaskBDD(Context context, TextView load, ProgressBar progress) {
		super();
		this.context = context;
		this.loading=load;
		this.progression = progress;
	}
	
	@Override
	protected void onPreExecute() {
		progression.setIndeterminate(true);
		//progression.startAnimation( AnimationUtils.loadAnimation(context, R.anim.rotateindefinitely));;
	}
	
	@Override
	protected Void doInBackground(Void... params) {
		Log.w("async task", "lancer");
		publishProgress("Chargement des événements..."); //inscrit sur la textview chargement des events
		Reseau.majEvenements(context, 0, 0);
//		Reseau.webService(Reseau.URL_LIST_EVENTS+"all/1");
		publishProgress("Chargement des actualités..."); //inscrit sur la textview chargement des actus
		Reseau.majCategories(context);
//		Reseau.webService(Reseau.URL_LIST_KEYWORDS);
		return null;
	}
	
	@Override
	protected void onProgressUpdate(String... values) {
		//Attention : ici, values n'est pas du type String mais String Array (String[]) !
		super.onProgressUpdate(values);
		loading.setText(values[0]);
	}
	
	@Override
	protected void onPostExecute(Void params){

		Log.w("async task", "post execute");
		Editor editor=context.getSharedPreferences("prefs", 0).edit();
		editor.putString("date", Formatage.dateEnTexteComplet(new Date()));
		editor.putBoolean("newuser", false);
		editor.commit();
		
		Intent VuePrincipale = new Intent(context, VuePrincipaleActivity.class);
		context.startActivity(VuePrincipale);
		((Activity) context).finish();

	}
}
