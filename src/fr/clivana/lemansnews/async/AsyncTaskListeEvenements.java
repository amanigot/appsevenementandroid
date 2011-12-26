package fr.clivana.lemansnews.async;

import fr.clivana.lemansnews.utils.reseau.Reseau;
import fr.clivana.lemansnews.vue.ListeEvenementsActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

public class AsyncTaskListeEvenements extends AsyncTask<Void, Void, Void> {

	Context context;
	ProgressDialog progress;
	
	public AsyncTaskListeEvenements(Context context) {
		super();
		this.context = context;
		progress=new ProgressDialog(this.context);
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
		return null;
	}
	
	@Override
	protected void onPostExecute(Void result) {
		super.onPostExecute(result);
		progress.dismiss();
		((ListeEvenementsActivity) context).initAdapters();
	}

}
