package fr.clivana.lemansnews.controller;

import java.util.List;

import fr.clivana.lemansnews.R;
import fr.clivana.lemansnews.dao.NewsDAO;
import fr.clivana.lemansnews.entity.Article;
import fr.clivana.lemansnews.vue.DetailNewsActivity;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;

public class GridNewsController implements OnClickListener, OnItemClickListener {

	NewsDAO newsDao;
	List<Article> articles;
	GridNewsAdapter adapter;
	Context context;
	String categorie;
	
	public GridNewsController(Context c, String categorie) {
		context = c;
		newsDao = new NewsDAO(context);
		this.categorie=categorie;
	}

	public GridNewsAdapter initGridNewsAdapter() {
		articles=newsDao.getAllArticles(categorie);
		adapter=new GridNewsAdapter(context, articles);
		return adapter;
	}

	@Override
	public void onClick(View v) {
		if(v.getId()==R.id.buttonRetour){
			((Activity) context).finish();
		}
		if(v.getId()==R.id.buttonActualiser){
			
		}
		
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
		// TODO Auto-generated method stub
		Intent intentNews = new Intent(context, DetailNewsActivity.class);
		intentNews.putExtra("article", articles.get(position).getId());
		intentNews.putExtra("categorie", categorie);
		context.startActivity(intentNews);
	}

	public CharSequence initTitre() {
		return categorie;
	}

}
