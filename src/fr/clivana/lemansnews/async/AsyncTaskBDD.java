package fr.clivana.lemansnews.async;

import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.widget.ProgressBar;
import android.widget.TextView;
import fr.clivana.lemansnews.utils.Formatage;
import fr.clivana.lemansnews.utils.reseau.Reseau;
import fr.clivana.lemansnews.view.VuePrincipaleActivity;

public class AsyncTaskBDD extends AsyncTask<Void, String, Void>{
	
	private Context context;
	private TextView loading;
	private ProgressBar progression;
	private Date dateMAJ;
	
	public AsyncTaskBDD(Context context, TextView load, ProgressBar progress) {
		super();
		this.context = context;
		this.loading=load;
		this.progression = progress;
	}
	
	@Override
	protected void onPreExecute() {
		progression.setIndeterminate(true);
		dateMAJ = new Date();
		//progression.startAnimation( AnimationUtils.loadAnimation(context, R.anim.rotateindefinitely));;
	}
	
	@Override
	protected Void doInBackground(Void... params) {
		publishProgress("Chargement des événements..."); //inscrit sur la textview chargement des events
		Reseau.majEvenements(context, 0, 0);
		publishProgress("Chargement des actualités..."); //inscrit sur la textview chargement des actus
		Reseau.majCategories(context);
		Reseau.synchroSuppression(context);
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

		Editor editor=context.getSharedPreferences("prefs", 0).edit();
		editor.putString("date", Formatage.dateEnTexteAvecHeure(dateMAJ));
		editor.putString("datePlay", Formatage.datePourPlay(dateMAJ));
		editor.putString("dateGlobalPlay", Formatage.datePourPlay(dateMAJ));
		editor.putBoolean("newuser", false);
		editor.commit();
		
		Intent VuePrincipale = new Intent(context, VuePrincipaleActivity.class);
		context.startActivity(VuePrincipale);
		((Activity) context).finish();

	}
}
