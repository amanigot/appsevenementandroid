package fr.clivana.lemansnews.controller;

import fr.clivana.lemansnews.dao.EventsDAO;
import fr.clivana.lemansnews.dao.NewsDAO;
import android.content.Context;

public class SplashController {

	Context ctx;
	NewsDAO newsDao;
	EventsDAO eventsDao;
	
	public SplashController(Context context){
		ctx = context;
		newsDao = new NewsDAO(ctx);
		eventsDao = new EventsDAO(ctx);
	}
	
	
}
