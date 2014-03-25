package fr.clivana.lemansnews.controller;

import java.util.List;

import com.google.android.apps.analytics.GoogleAnalyticsTracker;
//import com.markupartist.android.widget.PullToRefreshListView.OnRefreshListener;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import android.widget.Toast;
import fr.clivana.lemansnews.R;
import fr.clivana.lemansnews.async.AsyncTaskPullTorefreshVuPrincipale;
import fr.clivana.lemansnews.async.AsyncTaskVuePrincipale;
import fr.clivana.lemansnews.dao.EventsDAO;
import fr.clivana.lemansnews.dao.NewsDAO;
import fr.clivana.lemansnews.entity.Article;
import fr.clivana.lemansnews.entity.Evenement;
import fr.clivana.lemansnews.utils.reseau.Reseau;
import fr.clivana.lemansnews.view.CategoriesActivity;
import fr.clivana.lemansnews.view.DetailEvenementActivity;
import fr.clivana.lemansnews.view.DetailNewsActivity;
import fr.clivana.lemansnews.view.FavorisActivity;
import fr.clivana.lemansnews.view.InfoActivity;
import fr.clivana.lemansnews.view.ListeEvenementsActivity;

public class VuePrincipaleController implements OnClickListener, OnItemClickListener{

	Context ctx;
	EventsDAO eventsDao;
	NewsDAO newsDao;
	GalleryAdapter galleryAdapter;
	GridNewsAdapter newsAdapter;
	List<Evenement> evenements;
	List<Article> articles;
	AsyncTaskVuePrincipale asyncTask;
	AsyncTaskPullTorefreshVuPrincipale asyncTaskPullTorefreshVuPrincipale;
	GoogleAnalyticsTracker tracker;
	GalleryOneByOne galleryAccueil;
	MotionEvent e1, e2;
	int position;
	
	public VuePrincipaleController(Context context, GalleryOneByOne galleryEvents){
		ctx = context;
		eventsDao = new EventsDAO(ctx);
		newsDao = new NewsDAO(ctx);
		galleryAccueil=galleryEvents;
		tracker = GoogleAnalyticsTracker.getInstance();
		position=0;
	}
	
	public GalleryAdapter initGalleryAdapter(){
		evenements=eventsDao.getAllEvents();
		
		galleryAdapter=new GalleryAdapter(ctx, evenements, this);
		return galleryAdapter;
	}
	
	public GridNewsAdapter initNewsAdapter(){
		//Récupération des articles issue du DAO
		articles=newsDao.getAllArticles();
		//Initialisation de la GridNewsAdapter en envoyant le context de l'application et les articles
		newsAdapter= new GridNewsAdapter(ctx, articles);
		return newsAdapter;
	}
	//méthode permettant de modifier la police
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
		if(v.getId()==R.id.suivant){
			
			galleryAccueil.setSelection(galleryAccueil.getSelectedItemPosition()+1);
		}
		if(v.getId()==R.id.precedent){
			
			galleryAccueil.setSelection(galleryAccueil.getSelectedItemPosition()-1);
			
		}
	}

	public void Actualisation() {
		if(Reseau.verifReseau(ctx)){
			asyncTask=new AsyncTaskVuePrincipale(ctx);
			asyncTask.execute();
		}else{
			Toast.makeText(ctx, "Problème de connexion réseau. Actualisation impossible.", Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
		if(parent.getId()==R.id.galleryEvents){
			tracker.trackEvent("Accueil", "clic", "Evenement-"+evenements.get(position).getId()+"-"+evenements.get(position).getTitre(), 1);
			Intent evenementIntent = new Intent(ctx, DetailEvenementActivity.class);
			evenementIntent.putExtra("event", evenements.get(position).getId());
			ctx.startActivity(evenementIntent);
		}
		if(parent.getId()==R.id.pullToRefreshListView){
			tracker.trackEvent("Accueil", "clic", "Article-"+articles.get(position).getId()+"-"+articles.get(position).getTitre(), 1);
			Intent intentNews = new Intent(ctx, DetailNewsActivity.class);
			intentNews.putExtra("article", articles.get(position-1).getId());
			ctx.startActivity(intentNews);
		}
	}

	//@Override
//	public void onRefresh() {
//		// TODO Auto-generated method stub
//		if(Reseau.verifReseau(ctx)){
//			asyncTaskPullTorefreshVuPrincipale=new AsyncTaskPullTorefreshVuPrincipale(ctx);
//			asyncTaskPullTorefreshVuPrincipale.execute();
//		}else{
//			Toast.makeText(ctx, "Problème de connexion réseau. Actualisation impossible.", Toast.LENGTH_SHORT).show();
//		}
//		
//	}
	
	
}


