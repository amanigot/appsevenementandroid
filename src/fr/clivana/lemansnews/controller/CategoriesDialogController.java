package fr.clivana.lemansnews.controller;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import fr.clivana.lemansnews.dao.CategoriesDAO;
import fr.clivana.lemansnews.entity.Categorie;

public class CategoriesDialogController implements DialogInterface.OnClickListener{

	CategoriesDAO categoriesDao;
	Context context;
	int position;
	Categorie categ;
	
	public CategoriesDialogController(Context ctx) {
		context = ctx;
		categoriesDao=new CategoriesDAO(context);
	}

	public CategoriesDialogController(Context ctx, int pos) {
		context = ctx;
		categoriesDao=new CategoriesDAO(context);
		position=pos;
	}

	@Override
	public void onClick(DialogInterface dialog, int which) {
		switch (which) {
		case AlertDialog.BUTTON_NEGATIVE:
			dialog.cancel();
			break;

		case AlertDialog.BUTTON_POSITIVE:
			//categ=categoriesDao.selectCategorie(position);
			//categ.setSelected(false);
			//categoriesDao.update(categ);
			break;
			
		default:
			break;
		}
	}
	

}
