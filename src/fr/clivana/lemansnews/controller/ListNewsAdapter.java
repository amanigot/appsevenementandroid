package fr.clivana.lemansnews.controller;

import java.util.List;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import fr.clivana.lemansnews.R;
import fr.clivana.lemansnews.async.AsyncTaskImage;
import fr.clivana.lemansnews.entity.Article;
import fr.clivana.lemansnews.utils.Formatage;

public class ListNewsAdapter extends BaseAdapter{

	LayoutInflater inflater;
	Context ctx;
	List<Article> articles;
	AsyncTaskImage asyncTask;
	Typeface tfLight;
	
	public ListNewsAdapter(Context context, List<Article> articlesRecus) {
		ctx=context;
		articles=articlesRecus;
		this.inflater = LayoutInflater.from(ctx);
	}

	@Override
	public int getCount() {
		return articles.size();
	}

	@Override
	public Object getItem(int position) {
		return articles.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		convertView = inflater.inflate(R.layout.eventitem, null);
		LinearLayout ll=(LinearLayout)convertView.findViewById(R.id.linearLayout2);
		ll.setVisibility(View.GONE);
		TextView titre = (TextView) convertView
				.findViewById(R.id.textViewTitreEvent);
		titre.setText(articles.get(position).getTitre().toUpperCase());
		tfLight=Typeface.createFromAsset(ctx.getAssets(), "fonts/helveticalight.otf");
		titre.setTypeface(tfLight);
		TextView date = (TextView) convertView
				.findViewById(R.id.textViewDateEvent);
		date.setText(Formatage.dateEnTexteComplet(Formatage.stringToDate(articles.get(position).getDateParution())));
		TextView lieu = (TextView) convertView
				.findViewById(R.id.textViewLieuEvent);
		lieu.setVisibility(View.GONE);
		ImageView imageEvent = (ImageView)convertView.findViewById(R.id.imageEventDetail);
		asyncTask = new AsyncTaskImage(articles.get(position).getUrlImageMobile(), imageEvent, R.drawable.illustaucuneimage480);
		asyncTask.execute();
		return convertView;
	}


}
