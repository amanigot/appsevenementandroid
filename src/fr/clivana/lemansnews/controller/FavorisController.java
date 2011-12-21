package fr.clivana.lemansnews.controller;

import java.util.List;

import fr.clivana.lemansnews.R;
import fr.clivana.lemansnews.dao.EventsDAO;
import fr.clivana.lemansnews.dao.NewsDAO;
import fr.clivana.lemansnews.entity.Article;
import fr.clivana.lemansnews.entity.Evenement;
import fr.clivana.lemansnews.vue.DetailEvenementActivity;
import fr.clivana.lemansnews.vue.DetailNewsActivity;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;

public class FavorisController implements OnItemClickListener, OnClickListener {

	Context context;
	NewsDAO newsDao;
	EventsDAO eventsDao;
	ListEvenementsAdapter eventsAdapter;
	ListNewsAdapter newsAdapter;
	List<Article> articles;
	List<Evenement> evenements;
	
	public FavorisController(Context c) {
		context=c;
		newsDao=new NewsDAO(context);
		eventsDao=new EventsDAO(context);
	}

	public ListNewsAdapter initListNewsAdapter() {
		articles=newsDao.getAllArticles();
		newsAdapter=new ListNewsAdapter(context, articles);
		return newsAdapter;
	}

	public ListAdapter initListEventsAdapter() {
		evenements=eventsDao.getAllEvents();
		eventsAdapter=new ListEvenementsAdapter(context, evenements);
		return eventsAdapter;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
		// TODO Auto-generated method stub
		if(parent.getId()==R.id.listViewEvenementsFav){
			Intent evenementIntent = new Intent(context, DetailEvenementActivity.class);
			evenementIntent.putExtra("event", evenements.get(position).getId());
			context.startActivity(evenementIntent);
		}
		if(parent.getId()==R.id.listViewNews){
			Intent intentNews = new Intent(context, DetailNewsActivity.class);
			intentNews.putExtra("article", articles.get(position).getId());
			context.startActivity(intentNews);
		}
	}

	public CharSequence initTitre() {
		// TODO Auto-generated method stub
		return "Vos favoris";
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		((Activity)context).finish();
	}

}
