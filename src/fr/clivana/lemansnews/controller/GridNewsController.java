package fr.clivana.lemansnews.controller;

import java.util.List;

import fr.clivana.lemansnews.dao.NewsDAO;
import fr.clivana.lemansnews.entity.Article;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListAdapter;

public class GridNewsController implements OnClickListener {

	NewsDAO newsDao;
	List<Article> articles;
	GridNewsAdapter adapter;
	Context context;
	
	public GridNewsController(Context c) {
		context = c;
		newsDao = new NewsDAO(context);
	}

	public GridNewsAdapter initGridNewsAdapter() {
		articles=newsDao.getAllArticle();
		adapter=new GridNewsAdapter(context, articles);
		return adapter;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

}
