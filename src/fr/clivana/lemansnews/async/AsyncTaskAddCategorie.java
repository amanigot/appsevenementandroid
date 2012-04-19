package fr.clivana.lemansnews.async;

import fr.clivana.lemansnews.utils.reseau.Reseau;
import fr.clivana.lemansnews.view.CategoriesActivity;
import android.content.Context;
import android.os.AsyncTask;

public class AsyncTaskAddCategorie extends AsyncTask<Void, Void, Void> {

	Context context;
	String nomCategorie;
	
	public AsyncTaskAddCategorie(Context c, String nomCateg) {
		super();
		context=c;
		nomCategorie=nomCateg;
	}

	@Override
	protected Void doInBackground(Void... params) {
		Reseau.countCategorie(context, nomCategorie, "00000000000000");
		return null;
	}
	
	@Override
	protected void onPostExecute(Void result) {
		super.onPostExecute(result);
		((CategoriesActivity) context).initAdapters();
	}

}
