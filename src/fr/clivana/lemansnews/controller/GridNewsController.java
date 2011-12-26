package fr.clivana.lemansnews.controller;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

import com.google.android.apps.analytics.GoogleAnalyticsTracker;

import fr.clivana.lemansnews.R;
import fr.clivana.lemansnews.async.AsyncTaskGridNews;
import fr.clivana.lemansnews.dao.NewsDAO;
import fr.clivana.lemansnews.entity.Article;
import fr.clivana.lemansnews.utils.reseau.Reseau;
import fr.clivana.lemansnews.vue.DetailNewsActivity;

public class GridNewsController implements OnClickListener, OnItemClickListener {

	NewsDAO newsDao;
	List<Article> articles;
	GridNewsAdapter adapter;
	Context context;
	String categorie;
	AsyncTaskGridNews asyncTask;
	GoogleAnalyticsTracker tracker;
	
	public GridNewsController(Context c, String categorie) {
		context = c;
		newsDao = new NewsDAO(context);
		this.categorie=categorie;
		tracker = GoogleAnalyticsTracker.getInstance();
	}

	public GridNewsAdapter initGridNewsAdapter() {
		articles=newsDao.getArticlesWithMotsclefs(categorie);
		adapter=new GridNewsAdapter(context, articles);
		return adapter;
	}

	@Override
	public void onClick(View v) {
		if(v.getId()==R.id.buttonRetour || v.getId()==R.id.buttonNews){
			tracker.trackEvent("Categorie-"+categorie, "clic", "Retour aux Categories", 1);
			((Activity) context).finish();
		}
		if(v.getId()==R.id.buttonActualiser){
			tracker.trackEvent("Categorie-"+categorie, "clic", "Actualiser", 1);
			if(Reseau.verifReseau(context)){
				if(categorie.equals("Toutes les news")){categorie="all";}
				asyncTask=new AsyncTaskGridNews(context, categorie);
				asyncTask.execute();
			}else{
				Toast.makeText(context, "Problème de connexion réseau. Actualisation impossible.", Toast.LENGTH_SHORT).show();
			}
		}
		
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
		tracker.trackEvent("Categorie-"+categorie, "clic", "article-"+articles.get(position).getId()+"-"+articles.get(position).getTitre(), 1);
		Intent intentNews = new Intent(context, DetailNewsActivity.class);
		intentNews.putExtra("article", articles.get(position).getId());
		intentNews.putExtra("categorie", categorie);
		context.startActivity(intentNews);
	}

	public CharSequence initTitre() {
		if(categorie.equals("all")){
			categorie="Toutes les news";
		}
		return categorie;
	}

}
