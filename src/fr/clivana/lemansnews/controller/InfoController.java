package fr.clivana.lemansnews.controller;

import fr.clivana.lemansnews.R;
import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;

public class InfoController implements OnClickListener {

	Context context;
	
	public InfoController(Context c) {
		// TODO Auto-generated constructor stub
		context=c;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId()==R.id.buttonRetour){
			((Activity) context).finish();
		}
	}

	public CharSequence initTitre() {
		// TODO Auto-generated method stub
		return "Informations";
	}

}
