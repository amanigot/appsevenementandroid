package fr.clivana.lemansnews.controller;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import fr.clivana.lemansnews.R;
import fr.clivana.lemansnews.entity.Evenement;

public class ListEvenementsAdapter extends BaseAdapter {

	LayoutInflater inflater;
	Context ctx;
	List<Evenement> events;
	
	public ListEvenementsAdapter(Context context, List<Evenement> evenements) {
		// TODO Auto-generated constructor stub
		ctx=context;
		events=evenements;
		this.inflater = LayoutInflater.from(ctx);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return events.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return events.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		convertView = inflater.inflate(R.layout.eventitem, null);
		TextView titre = (TextView) convertView
				.findViewById(R.id.textViewTitreEvent);
		titre.setText(events.get(position).getTitre());
		TextView date = (TextView) convertView
				.findViewById(R.id.textViewDateEvent);
		date.setText(events.get(position).getDateHeureEvenement());
		TextView lieu = (TextView) convertView
				.findViewById(R.id.textViewLieuEvent);
		lieu.setText(events.get(position).getLieu());
		ImageView imageEvent = (ImageView)convertView.findViewById(R.id.imageEventDetail);
		imageEvent.setImageResource(R.drawable.illustaucuneimage480);
		return convertView;
	}

}
