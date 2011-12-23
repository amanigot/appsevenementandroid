package fr.clivana.lemansnews.controller;

import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import fr.clivana.lemansnews.R;
import fr.clivana.lemansnews.async.AsyncTaskImage;
import fr.clivana.lemansnews.entity.Article;


public class GridNewsAdapter extends BaseAdapter {

	LayoutInflater inflater;
	List<Article> events;
	Context ctx;
	HashMap<Integer, ImageView> views ;
	View view;
	ImageView imageNews;
	TextView titre;
	AsyncTaskImage asyncTask;
	
	public GridNewsAdapter(Context context, List<Article> events
			) {
		this.events = events;
		this.inflater = LayoutInflater.from(context);
		ctx = context;
		views = new HashMap<Integer, ImageView>();
	}

	@Override
	public int getCount() {
		return events.size();
	}

	@Override
	public Object getItem(int position) {
		return events.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
			
			view = inflater.inflate(R.layout.newslist, null);	
			//view=(View)convertView.findViewById(R.layout.newslist);
		
			titre = (TextView) view.findViewById(R.id.textNewsCateg);
			imageNews = (ImageView) view.findViewById(R.id.imageNewsCateg);
			
			
			titre.setText(events.get(position).getTitre());
			asyncTask = new AsyncTaskImage(events.get(position).getUrlMiniature(), imageNews, R.drawable.illustaucuneimage240);
			asyncTask.execute();
			views.put(position, imageNews);
			
			
		return view;
	}
	
	
}
