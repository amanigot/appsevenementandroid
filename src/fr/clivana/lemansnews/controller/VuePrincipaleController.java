package fr.clivana.lemansnews.controller;

import java.util.List;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;
import fr.clivana.lemansnews.R;
import fr.clivana.lemansnews.dao.EventsDAO;
import fr.clivana.lemansnews.dao.NewsDAO;
import fr.clivana.lemansnews.entity.Article;
import fr.clivana.lemansnews.entity.Evenement;

public class VuePrincipaleController implements OnClickListener{

	Context ctx;
	EventsDAO eventsDao;
	NewsDAO newsDao;
	GalleryAdapter galleryAdapter;
	NewsAdapter newsAdapter;
	List<Evenement> evenements;
	List<Article> articles;
	
	public VuePrincipaleController(Context context){
		ctx = context;
		eventsDao = new EventsDAO(ctx);
		newsDao = new NewsDAO(ctx);
	}
	
	public GalleryAdapter initGalleryAdapter(){
		evenements=eventsDao.getAllEvents();
		galleryAdapter=new GalleryAdapter(ctx, evenements);
		return galleryAdapter;
	}
	
	public NewsAdapter initNewsAdapter(){
		articles=newsDao.getAllArticle();
		newsAdapter= new NewsAdapter(ctx, articles);
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
			//Intent intentNews = new Intent(ctx, CategoriesActivity.class);
    		//ctx.startActivity(intentNews);
		}
		if(v.getId()==R.id.buttonEvents){
			//Intent intentEvents = new Intent(ctx, ListeEvenementActivity.class);
    		//ctx.startActivity(intentEvents);
		}
		if(v.getId()==R.id.buttonInfo){
			//Intent intentInfo = new Intent(ctx, InfosActivity.class);
    		//ctx.startActivity(intentInfo);
		}
		if(v.getId()==R.id.buttonActualiser){
			Toast.makeText(ctx, "actualisation", Toast.LENGTH_SHORT).show();
		}
	}
	
	
}


