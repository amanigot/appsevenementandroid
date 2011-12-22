package fr.clivana.lemansnews.controller;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import fr.clivana.lemansnews.R;
import fr.clivana.lemansnews.entity.Evenement;


public class GalleryAdapter extends BaseAdapter {

	LayoutInflater inflater;
	List<Evenement> events;
	
	Context context;
	Bitmap bitmap=null;
	
	
	
	public GalleryAdapter(Context context, List<Evenement> events
			) {
		//this.events = events;
		this.inflater = LayoutInflater.from(context);
		this.context=context;
		
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
		convertView = inflater.inflate(R.layout.eventitem, null);
		LinearLayout l1 = (LinearLayout) convertView.findViewById(R.id.linearLayout1);
		LinearLayout l2 = (LinearLayout) convertView.findViewById(R.id.linearLayout2);
		l1.setVisibility(View.GONE);
		l2.setVisibility(View.GONE);
		ImageView image = (ImageView) convertView.findViewById(R.id.imageEventDetail);
		TextView titre = (TextView) convertView.findViewById(R.id.textViewTitreEvent);
		ImageView suivant = (ImageView)convertView.findViewById(R.id.suivant);
		ImageView precedant = (ImageView)convertView.findViewById(R.id.precedent);
		
		//affichage des fleches du slider : les fleches n'apparaissent que s'il y a plus d'1 image
		if(getCount()>1){
			//si ce n'est ni la dernière, ni la première, on affiche les deux flèches
			if(position!=0 && position!=getCount()-1){
				suivant.setVisibility(View.VISIBLE);
				precedant.setVisibility(View.VISIBLE);
			}
			//si c'est la première, on affiche la flèche de droite seulement
			if(position==0){
				suivant.setVisibility(View.VISIBLE);
			}
			//si c'est la dernière, on affiche la flèche de gauche seulement
			if(position==getCount()-1){
				precedant.setVisibility(View.VISIBLE);
			}
		}
		
		image.setImageResource(R.drawable.illustaucuneimage480);
		
		
		titre.setText(events.get(position).getTitre());
		return convertView;
	}
		
	



}
