package fr.clivana.lemansnews.async;

import java.util.List;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.GridView;
import fr.clivana.lemansnews.controller.GridNewsAdapter;
import fr.clivana.lemansnews.dao.NewsDAO;
import fr.clivana.lemansnews.entity.Article;

public class AsyncTaskGridNews extends AsyncTask<Void, Void, Void> {

	Context context;
	NewsDAO newsDao;
	ProgressDialog progress;
	List<Article> articles;
	GridView gridViewNews;
	GridNewsAdapter newsAdapter;
	String categorie;
	
	public AsyncTaskGridNews(Context context, String categ) {
		super();
		this.context = context;
		newsDao= new NewsDAO(this.context);
		progress = new ProgressDialog(this.context);
		categorie=categ;
//		gridViewNews = (GridView)findViewById(R.id.gridViewNews);
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
		//articles=newsDao.getAllArticles(categorie);
		newsAdapter= new GridNewsAdapter(this.context, articles);
		gridViewNews.setAdapter(newsAdapter);
		progress.dismiss();
	}

}
