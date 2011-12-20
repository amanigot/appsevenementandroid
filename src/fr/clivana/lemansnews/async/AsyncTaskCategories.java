package fr.clivana.lemansnews.async;

import java.util.List;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import fr.clivana.lemansnews.dao.CategoriesDAO;
import fr.clivana.lemansnews.entity.Categorie;

public class AsyncTaskCategories extends AsyncTask<Void, Void, Void> {

	Context context;
	CategoriesDAO categoriesDao;
	ProgressDialog progress;
	List<Categorie> notifications;
	
	public AsyncTaskCategories(Context context) {
		super();
		this.context = context;
		categoriesDao=new CategoriesDAO(this.context);
		progress=new ProgressDialog(this.context);
	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		progress.show();
	}

	@Override
	protected Void doInBackground(Void... params) {
		// Reseau.webservice();
		return null;
	}

	@Override
	protected void onPostExecute(Void result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		
		progress.dismiss();
	}
}
