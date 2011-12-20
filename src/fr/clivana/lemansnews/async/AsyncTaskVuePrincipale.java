package fr.clivana.lemansnews.async;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import fr.clivana.lemansnews.vue.VuePrincipaleActivity;

public class AsyncTaskVuePrincipale extends AsyncTask<Void, Void, Void> {
	
	
	Context context;
	ProgressDialog progress;
	
	
	public AsyncTaskVuePrincipale(Context context) {
		super();
		this.context = context;
		progress = new ProgressDialog(this.context);

		
	}

	@Override
	protected void onPreExecute() {
		
		super.onPreExecute();
		progress.show();
	}
	
	@Override
	protected Void doInBackground(Void... params) {
		//Reseau.webservice();
		//Reseau.webservice();
		return null;
	}
	
	@Override
	protected void onPostExecute(Void result) {
		
		super.onPostExecute(result);
		((VuePrincipaleActivity) context).initAdapters();
		progress.dismiss();
	}
	
}
