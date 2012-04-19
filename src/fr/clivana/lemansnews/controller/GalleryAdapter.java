package fr.clivana.lemansnews.controller;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import fr.clivana.lemansnews.R;
import fr.clivana.lemansnews.async.AsyncTaskImage;
import fr.clivana.lemansnews.entity.Evenement;


public class GalleryAdapter extends BaseAdapter {

	
	//Attributs de classe BaseAdapter
	LayoutInflater inflater;
	List<Evenement> events;
	//Le controller de la vue principale qui recoit les événements
	VuePrincipaleController controller;
	//Le context de l'application
	Context context;
	//Une image
	Bitmap bitmap=null;
	//Un asynTaskImage thread, le thread se lance en dehors de l'application
	AsyncTaskImage asyncTask;
	
	public GalleryAdapter(Context context, List<Evenement> events, VuePrincipaleController vuePrincipaleController
			) {
		//Une liste d'événements, le texte
		this.events = events;
		//Construction de la vue à partir d'un xml
		this.inflater = LayoutInflater.from(context);
		//Initialisation du context de l'application
		this.context=context;
		controller = vuePrincipaleController;
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
		Typeface tfLight = Typeface.createFromAsset(context.getAssets(), "fonts/helveticalight.otf");
		convertView = inflater.inflate(R.layout.eventitem, null);
		LinearLayout l1 = (LinearLayout) convertView.findViewById(R.id.linearLayout1);
		LinearLayout l2 = (LinearLayout) convertView.findViewById(R.id.linearLayout2);
		l1.setVisibility(View.GONE);
		l2.setVisibility(View.GONE);
		ImageView image = (ImageView) convertView.findViewById(R.id.imageEventDetail);
		//image.setLayoutParams(new Gallery.LayoutParams(200, 200));
		//image.setScaleType(ImageView.ScaleType.FIT_CENTER);
	
		TextView titre = (TextView) convertView.findViewById(R.id.textViewTitreEvent);
		ImageView suivant = (ImageView)convertView.findViewById(R.id.suivant);
		ImageView precedent = (ImageView)convertView.findViewById(R.id.precedent);
		
		//affichage des fleches du slider : les fleches n'apparaissent que s'il y a plus d'1 image
		if(getCount()>1){
			//si ce n'est ni la dernière, ni la première, on affiche les deux flèches
			if(position!=0 && position!=getCount()-1){
				suivant.setVisibility(View.VISIBLE);
				precedent.setVisibility(View.VISIBLE);
			}
			//si c'est la première, on affiche la flèche de droite seulement
			if(position==0){
				suivant.setVisibility(View.VISIBLE);
			}
			//si c'est la dernière, on affiche la flèche de gauche seulement
			if(position==getCount()-1){
				precedent.setVisibility(View.VISIBLE);
			}
		}
		
		asyncTask = new AsyncTaskImage(events.get(position).getNomImageMobile(), image, R.drawable.illustaucuneimage480);
		asyncTask.execute();
		
		//Pour modifier le texte sur la gallerie d'image
		titre.setText(events.get(position).getTitre().toString().toUpperCase());
		//titre.setText("TOTO");
		
	    titre.setTypeface(tfLight);
	    
	    suivant.setOnClickListener(controller);
	    precedent.setOnClickListener(controller);
	    
		return convertView;
	}
		
	



}
