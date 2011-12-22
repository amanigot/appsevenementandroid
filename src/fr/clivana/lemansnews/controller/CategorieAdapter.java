package fr.clivana.lemansnews.controller;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import fr.clivana.lemansnews.R;
import fr.clivana.lemansnews.entity.Categorie;

public class CategorieAdapter extends BaseAdapter{
	
	
	
	Context mContext;
	public static final int ACTIVITY_CREATE = 10;
	List<Categorie> notifications;
	
	
	
	
	public CategorieAdapter(Context c, List<Categorie> notif){
		mContext = c;
		notifications = notif;
	}
	@Override
	public int getCount() {
		return notifications.size();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
			View v;
			LayoutInflater li = ((Activity) mContext).getLayoutInflater();
			v = li.inflate(R.layout.gridnewsitem, null);
		
			TextView tv = (TextView)v.findViewById(R.id.icon_text);
			if(notifications.get(position).getNom().equals("all")){
				tv.setText("Toutes les news");
			}else{
				tv.setText(notifications.get(position).getNom());
			}
			ImageView iv = (ImageView)v.findViewById(R.id.icon_image);
			iv.setImageResource(R.drawable.illustaucuneimage111);
			iv.setPadding(4, 4, 4, 4);
			TextView badge = (TextView)v.findViewById(R.id.badge_notif);
			int count=notifications.get(position).getCount();
			if(count==0){
				badge.setVisibility(View.INVISIBLE);
			}else{
				badge.setText(String.valueOf(count));
			}

		return v;
	}
	@Override
	public Object getItem(int position) {
		return null;
	}
	@Override
	public long getItemId(int position) {
		return 0;
	}

}
