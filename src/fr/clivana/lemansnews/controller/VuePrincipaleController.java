package fr.clivana.lemansnews.controller;

import java.util.List;

import com.google.android.apps.analytics.GoogleAnalyticsTracker;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import android.widget.Toast;
import fr.clivana.lemansnews.R;
import fr.clivana.lemansnews.async.AsyncTaskVuePrincipale;
import fr.clivana.lemansnews.dao.EventsDAO;
import fr.clivana.lemansnews.dao.NewsDAO;
import fr.clivana.lemansnews.entity.Article;
import fr.clivana.lemansnews.entity.Evenement;
import fr.clivana.lemansnews.utils.reseau.Reseau;
import fr.clivana.lemansnews.vue.CategoriesActivity;
import fr.clivana.lemansnews.vue.DetailEvenementActivity;
import fr.clivana.lemansnews.vue.DetailNewsActivity;
import fr.clivana.lemansnews.vue.InfoActivity;
import fr.clivana.lemansnews.vue.ListeEvenementsActivity;
import fr.clivana.lemansnews.vue.FavorisActivity;

public class VuePrincipaleController implements OnClickListener, OnItemClickListener{

	Context ctx;
	EventsDAO eventsDao;
	NewsDAO newsDao;
	GalleryAdapter galleryAdapter;
	GridNewsAdapter newsAdapter;
	List<Evenement> evenements;
	List<Article> articles;
	AsyncTaskVuePrincipale asyncTask;
	GoogleAnalyticsTracker tracker;
	
	public VuePrincipaleController(Context context){
		ctx = context;
		eventsDao = new EventsDAO(ctx);
		newsDao = new NewsDAO(ctx);
		asyncTask=new AsyncTaskVuePrincipale(ctx);
		tracker = GoogleAnalyticsTracker.getInstance();
	}
	
	public GalleryAdapter initGalleryAdapter(){
		evenements=eventsDao.getAllEvents();
		galleryAdapter=new GalleryAdapter(ctx, evenements);
		return galleryAdapter;
	}
	
	public GridNewsAdapter initNewsAdapter(){
		articles=newsDao.getAllArticles();
		newsAdapter= new GridNewsAdapter(ctx, articles);
		return newsAdapter;
	}
	
	public void miseEnPageRoman(TextView tv){
		tv.setText("Le Mans News & Evénements");
		Typeface tfRoman = Typeface.createFromAsset(ctx.getAssets(), "fonts/helveticaroman.otf");
		tv.setTypeface(tfRoman);
	}
	
	public void miseEnPageRomanLight(TextView tv){
		 Typeface tfLight = Typeface.createFromAsset(ctx.getAssets(), "fonts/helveticalight.otf");
	     tv.setTypeface(tfLight);
	}

	@Override
	public void onClick(View v) {
		
		if(v.getId()==R.id.buttonNews){
			tracker.trackEvent("Accueil", "clic", "Categories", 1);
			Intent intentNews = new Intent(ctx, CategoriesActivity.class);
    		ctx.startActivity(intentNews);
		}
		if(v.getId()==R.id.buttonEvents){
			tracker.trackEvent("Accueil", "clic", "Evenements", 1);
			Intent intentEvents = new Intent(ctx, ListeEvenementsActivity.class);
    		ctx.startActivity(intentEvents);
		}
		if(v.getId()==R.id.buttonInfo){
			tracker.trackEvent("Accueil", "clic", "Infos", 1);
			Intent intentInfo = new Intent(ctx, InfoActivity.class);
    		ctx.startActivity(intentInfo);
		}
		if(v.getId() == R.id.buttonFavoris){
			tracker.trackEvent("Accueil", "clic", "Favoris", 1);
			Intent intentFav = new Intent(ctx, FavorisActivity.class);
			ctx.startActivity(intentFav);
		}
		if(v.getId()==R.id.buttonActualiser){
			tracker.trackEvent("Accueil", "clic", "Actualiser", 1);
			//Toast.makeText(ctx, "actualisation", Toast.LENGTH_SHORT).show();
			Actualisation();
		}
	}

	public void Actualisation() {
		// TODO Auto-generated method stub
		if(Reseau.verifReseau(ctx)){
			asyncTask.execute();
		}else{
			Toast.makeText(ctx, "Problème de connexion réseau. Actualisation impossible.", Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
		// TODO Auto-generated method stub
		if(parent.getId()==R.id.galleryEvents){
			tracker.trackEvent("Accueil", "clic", "Evenement-"+evenements.get(position).getId()+"-"+evenements.get(position).getTitre(), 1);
			Intent evenementIntent = new Intent(ctx, DetailEvenementActivity.class);
			evenementIntent.putExtra("event", evenements.get(position).getId());
			ctx.startActivity(evenementIntent);
		}
		if(parent.getId()==R.id.gridViewNewsPrincipal){
			tracker.trackEvent("Accueil", "clic", "Article-"+articles.get(position).getId()+"-"+articles.get(position).getTitre(), 1);
			Intent intentNews = new Intent(ctx, DetailNewsActivity.class);
			intentNews.putExtra("article", articles.get(position).getId());
			ctx.startActivity(intentNews);
		}
	}
	
	
}


