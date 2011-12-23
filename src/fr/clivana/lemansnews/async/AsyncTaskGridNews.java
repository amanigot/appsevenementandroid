package fr.clivana.lemansnews.async;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import fr.clivana.lemansnews.utils.reseau.Reseau;
import fr.clivana.lemansnews.vue.GridNewsActivity;

public class AsyncTaskGridNews extends AsyncTask<Void, Void, Void> {

	Context context;
	ProgressDialog progress;
	String categorie;
	
	public AsyncTaskGridNews(Context context, String categ) {
		super();
		this.context = context;
		progress = new ProgressDialog(this.context);
		progress.setMessage("Mise à jour en cours...");
		categorie=categ;
	}


	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		progress.show();
	}
	
	@Override
	protected Void doInBackground(Void... params) {
		Reseau.majArticles(context, categorie, 0, 0);
		return null;
	}
	
	@Override
	protected void onPostExecute(Void result) {
		super.onPostExecute(result);
		((GridNewsActivity) context).initAdapters();
		progress.dismiss();
	}

}
