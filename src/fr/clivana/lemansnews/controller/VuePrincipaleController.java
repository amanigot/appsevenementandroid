package fr.clivana.lemansnews.controller;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.View;
import android.view.View.OnClickListener;
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
import fr.clivana.lemansnews.vue.InfoActivity;
import fr.clivana.lemansnews.vue.ListeEvenementsActivity;
import fr.clivana.lemansnews.vue.TabNavActivity;

public class VuePrincipaleController implements OnClickListener{

	Context ctx;
	EventsDAO eventsDao;
	NewsDAO newsDao;
	GalleryAdapter galleryAdapter;
	GridNewsAdapter newsAdapter;
	List<Evenement> evenements;
	List<Article> articles;
	AsyncTaskVuePrincipale asyncTask;
	
	public VuePrincipaleController(Context context){
		ctx = context;
		eventsDao = new EventsDAO(ctx);
		newsDao = new NewsDAO(ctx);
		asyncTask=new AsyncTaskVuePrincipale(ctx);
	}
	
	public GalleryAdapter initGalleryAdapter(){
		//evenements=eventsDao.getAllEvents();
		galleryAdapter=new GalleryAdapter(ctx, evenements);
		return galleryAdapter;
	}
	
	public GridNewsAdapter initNewsAdapter(){
		//articles=newsDao.getAllArticles();
		newsAdapter= new GridNewsAdapter(ctx, articles);
		return newsAdapter;
	}
	
	public void miseEnPageRoman(TextView tv){
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
			Intent intentNews = new Intent(ctx, CategoriesActivity.class);
    		ctx.startActivity(intentNews);
		}
		if(v.getId()==R.id.buttonEvents){
			Intent intentEvents = new Intent(ctx, ListeEvenementsActivity.class);
    		ctx.startActivity(intentEvents);
		}
		if(v.getId()==R.id.buttonInfo){
			Intent intentInfo = new Intent(ctx, InfoActivity.class);
    		ctx.startActivity(intentInfo);
		}
		if(v.getId() == R.id.buttonFavoris){
			Intent intentFav = new Intent(ctx, TabNavActivity.class);
			ctx.startActivity(intentFav);
		}
		if(v.getId()==R.id.buttonActualiser){
			//Toast.makeText(ctx, "actualisation", Toast.LENGTH_SHORT).show();
			if(Reseau.verifReseau(ctx)){
				asyncTask.execute();
			}else{
				Toast.makeText(ctx, "Problème de connexion réseau. Actualisation impossible.", Toast.LENGTH_SHORT).show();
			}
		}
	}
	
	
}


