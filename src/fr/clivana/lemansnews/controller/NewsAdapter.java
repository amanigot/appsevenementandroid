package fr.clivana.lemansnews.controller;

import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import fr.clivana.lemansnews.R;
import fr.clivana.lemansnews.entity.Article;


public class NewsAdapter extends BaseAdapter {

	LayoutInflater inflater;
	List<Article> events;
	//ClivanaAndroidVueNewsActivity act;
	Context ctx;
	Bitmap bitmap=null;
	HashMap<Integer, ImageView> views ;
	
	public NewsAdapter(Context context, List<Article> events
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
			
			View view = inflater.inflate(R.layout.newslist, null);	

			TextView titre = (TextView) view.findViewById(R.id.textNewsCateg);
			titre.setText(events.get(position).getTitre());
			ImageView imageNews = (ImageView) view.findViewById(R.id.imageNewsCateg);
			imageNews.setImageResource(R.drawable.illustaucuneimage240);
			views.put(position, imageNews);
		return view;
	}
	
	
}
