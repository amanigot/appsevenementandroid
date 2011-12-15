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
import android.widget.LinearLayout;
import android.widget.TextView;
import fr.clivana.lemansnews.R;
import fr.clivana.lemansnews.entity.Evenement;


public class GalleryAdapter extends BaseAdapter {

	LayoutInflater inflater;
	List<Evenement> events;
	
	Context context;
	Bitmap bitmap=null;
	HashMap<Integer, ImageView> views ;

	public GalleryAdapter(Context context, List<Evenement> events
			) {
		this.events = events;
		this.inflater = LayoutInflater.from(context);
		this.context=context;
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
		convertView = inflater.inflate(R.layout.eventitem, null);
		LinearLayout l1 = (LinearLayout) convertView.findViewById(R.id.linearLayout1);
		LinearLayout l2 = (LinearLayout) convertView.findViewById(R.id.linearLayout2);
		l1.setVisibility(View.GONE);
		l2.setVisibility(View.GONE);
		ImageView image = (ImageView) convertView.findViewById(R.id.imageEventDetail);
		image.setImageResource(R.drawable.illustaucuneimage480);
		views.put(position, image);
		TextView titre = (TextView) convertView.findViewById(R.id.textViewTitreEvent);
		titre.setText(events.get(position).getTitre());
		return convertView;
	}
		
	



}
