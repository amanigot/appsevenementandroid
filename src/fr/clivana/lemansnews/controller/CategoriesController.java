package fr.clivana.lemansnews.controller;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import fr.clivana.lemansnews.R;
import fr.clivana.lemansnews.dao.CategoriesDAO;
import fr.clivana.lemansnews.dao.NewsDAO;
import fr.clivana.lemansnews.entity.Categorie;
import fr.clivana.lemansnews.vue.CategoriesDialog;
import fr.clivana.lemansnews.vue.VuePrincipaleActivity;

public class CategoriesController implements OnClickListener,
		OnItemLongClickListener, OnItemClickListener {

	CategorieAdapter adapter;
	Context context;
	CategoriesDAO categoriesDao;
	List<Categorie> categories;
	CategoriesDialog dialog;
	NewsDAO newsDao;
	
	public CategoriesController(Context c) {
		context = c;
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.buttonRetour) {
			((Activity) context).finish();
		}
		if (v.getId() == R.id.buttonAjouterCategorie) {
			
		}
		if (v.getId() == R.id.buttonActualiser) {

		}

	}

	public CategorieAdapter initCategorieAdapter() {
		categories = categoriesDao.getSelectedCategories();
		adapter = new CategorieAdapter(context, categories);
		return adapter;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
		
				// Intent de la Grid News
				// if(newsDao.countArticles()>0){
				// Intent intentNews = new Intent(context, GridNewsActivity.class);
				// intentNews.putExtra("categorie", categories.get(position).getMotClef());
				// categoriesDao.updateDate(categories.get(position).getId(), new Date());
				// context.startActivity(intentNews);
				// }else{
				// Toast.makeText(ctx, "Aucun article pour le moment dans la catégorie.", Toast.LENGTH_SHORT).show();
				// }
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View v,
			final int position, long id) {

		
		return false;
	}

}
