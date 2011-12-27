package fr.clivana.lemansnews.controller;

import java.util.List;

import com.google.android.apps.analytics.GoogleAnalyticsTracker;

import fr.clivana.lemansnews.dao.NewsDAO;
import fr.clivana.lemansnews.entity.Article;
import fr.clivana.lemansnews.vue.DetailNewsActivity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;

public class ListeNewsFavorisController implements OnItemClickListener {

	Context context;
	List<Article> articles;
	NewsDAO newsDao;
	ListNewsAdapter newsAdapter;
	GoogleAnalyticsTracker tracker;
	
	public ListeNewsFavorisController(Context c) {
		context=c;
		newsDao= new NewsDAO(context);
		tracker = GoogleAnalyticsTracker.getInstance();
	}

	public void initAdapter(ListView listeNewsFavoris) {
		
			articles=newsDao.getFavoriteArticles();
			if(articles.size()==1 && articles.get(0).getId()==0){
				listeNewsFavoris.setVisibility(View.GONE);
			}else{
				newsAdapter=new ListNewsAdapter(context, articles);
				listeNewsFavoris.setAdapter(newsAdapter);
			}

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
		tracker.trackEvent("Favoris-News", "clic", "detail-"+articles.get(position).getId()+"-"+articles.get(position).getTitre(), 1);
		Intent intentNews = new Intent(context, DetailNewsActivity.class);
		intentNews.putExtra("article", articles.get(position).getId());
		context.startActivity(intentNews);
		
	}

}
