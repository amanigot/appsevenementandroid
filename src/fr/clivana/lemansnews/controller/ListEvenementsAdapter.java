package fr.clivana.lemansnews.controller;

import java.util.List;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import fr.clivana.lemansnews.R;
import fr.clivana.lemansnews.async.AsyncTaskImage;
import fr.clivana.lemansnews.entity.Evenement;

public class ListEvenementsAdapter extends BaseAdapter {

	LayoutInflater inflater;
	Context ctx;
	List<Evenement> events;
	AsyncTaskImage asyncTask;
	TextView titre;
	TextView date;
	TextView lieu;
	ImageView imageEvent;
	Typeface tfLight;
	
	public ListEvenementsAdapter(Context context, List<Evenement> evenements) {
		ctx=context;
		events=evenements;
		this.inflater = LayoutInflater.from(ctx);
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
	public View getView(int position, View convertView, ViewGroup parent) {
		convertView = inflater.inflate(R.layout.eventitem, null);
		titre = (TextView) convertView
				.findViewById(R.id.textViewTitreEvent);
		titre.setText(events.get(position).getTitre());
		date = (TextView) convertView
				.findViewById(R.id.textViewDateEvent);
		date.setText(events.get(position).getDateHeureEvenement());
		lieu = (TextView) convertView
				.findViewById(R.id.textViewLieuEvent);
		lieu.setText(events.get(position).getLieu());
		imageEvent = (ImageView)convertView.findViewById(R.id.imageEventDetail);
		
		tfLight = Typeface.createFromAsset(ctx.getAssets(), "fonts/helveticalight.otf");
		titre.setTypeface(tfLight);
		date.setTypeface(tfLight);
		lieu.setTypeface(tfLight);
		
		asyncTask = new AsyncTaskImage(events.get(position).getNomImageMobile(), imageEvent, R.drawable.illustaucuneimage480);
		asyncTask.execute();
		return convertView;
	}

}
