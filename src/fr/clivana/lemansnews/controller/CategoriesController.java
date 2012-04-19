package fr.clivana.lemansnews.controller;

import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;

import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ImageView;

import android.widget.Toast;

import com.google.android.apps.analytics.GoogleAnalyticsTracker;

import fr.clivana.lemansnews.R;
import fr.clivana.lemansnews.async.AsyncTaskCategories;
import fr.clivana.lemansnews.dao.CategoriesDAO;
import fr.clivana.lemansnews.dao.NewsDAO;
import fr.clivana.lemansnews.entity.Categorie;
import fr.clivana.lemansnews.utils.Formatage;
import fr.clivana.lemansnews.utils.reseau.Reseau;
import fr.clivana.lemansnews.view.CategoriesDialog;
import fr.clivana.lemansnews.view.GridNewsActivity;


public class CategoriesController implements OnClickListener,
		OnItemLongClickListener, OnItemClickListener {

	CategorieAdapter adapter;
	Context context;
	CategoriesDAO categoriesDao;
	List<Categorie> categoriesAffichees, categoriesAAjouter;
	String[] categoriesAAjouterTitre;
	CategoriesDialog categoriesDialog, ajouterCategorie;
	NewsDAO newsDao;
	AsyncTaskCategories asyncTask;
	GoogleAnalyticsTracker tracker;
	LayoutParams layoutParams;
	
	Dialog dialog;
	
	public CategoriesController(Context c) {
		context = c;
		categoriesDao = new CategoriesDAO(context);
		newsDao=new NewsDAO(context);
		tracker = GoogleAnalyticsTracker.getInstance();
		
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.buttonRetour || v.getId() == R.id.buttonNews) {
			tracker.trackEvent("Categories", "clic", "Retour à l'accueil", 1);
			((Activity)context).finish();
			
		}
		if (v.getId() == R.id.buttonAjouterCategorie) {
			tracker.trackEvent("Categories", "clic", "Ajouter une categorie", 1);
			categoriesAAjouter=categoriesDao.getNonSelectedCategories();
			categoriesAAjouterTitre = new String[categoriesAAjouter.size()];
			for(int i=0;i<categoriesAAjouter.size();i++){
				categoriesAAjouterTitre[i]=categoriesAAjouter.get(i).getNom();
			}
			ajouterCategorie= new CategoriesDialog(context, "Ajouter une catégorie", "", "", "Annuler",categoriesAAjouterTitre, -1, 1 );
			ajouterCategorie.getBuilder().show();
		}
		if (v.getId() == R.id.buttonActualiser) {
			tracker.trackEvent("Categories", "clic", "actualiser", 1);
			if(Reseau.verifReseau(context)){
				asyncTask=new AsyncTaskCategories(context);
				asyncTask.execute();
			}else{
				Toast.makeText(context, "Problème de connexion réseau. Actualisation impossible.", Toast.LENGTH_SHORT).show();
			}
		}
		if(v.getId() == R.id.buttonInfo){
			
			ImageView imageViewAideCategorie=new ImageView(context);
			imageViewAideCategorie.setImageResource(R.drawable.illustaidecategorieactu);
		    
			dialog = new Dialog(context, android.R.style.Theme_Panel);
			dialog.setContentView(imageViewAideCategorie);
			
			layoutParams = dialog.getWindow().getAttributes();
			
			layoutParams.gravity=Gravity.TOP;
			layoutParams.y=55;
			
			dialog.getWindow().setAttributes(layoutParams);
			
		    
		    imageViewAideCategorie.setOnClickListener(new OnClickListener() { public void onClick(View v) { dialog.dismiss(); } });
		    dialog.show();
		}
	}

	public CategorieAdapter initCategorieAdapter() {
		categoriesAffichees = categoriesDao.getSelectedCategories();
		adapter = new CategorieAdapter(context, categoriesAffichees);
		return adapter;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
		tracker.trackEvent("Categories", "clic", categoriesAffichees.get(position).getNom(), 1);
				// Intent de la Grid News
				 if(categoriesAffichees.get(position).getTotal()>0){
					Intent intentNews = new Intent(context, GridNewsActivity.class);
				 	intentNews.putExtra("categorie", categoriesAffichees.get(position).getNom());
				 	categoriesAffichees.get(position).setDateConsult(Formatage.datePourPlay(new Date()));
				 	categoriesAffichees.get(position).setCount(0);
				 	categoriesDao.updateCategorie(categoriesAffichees.get(position) );
				 	context.startActivity(intentNews);
				 }else{
					 Toast.makeText(context, "Aucun article pour le moment dans la catégorie.", Toast.LENGTH_SHORT).show();
				 }
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View v,
			final int position, long id) {
		tracker.trackEvent("Categories", "long-clic", categoriesAffichees.get(position).getNom(), 1);
		if(categoriesAffichees.get(position).isSupprimable()){
			categoriesDialog = new CategoriesDialog(context, "Supprimer", "Supprimer", "Supprimer", "Annuler", new String[0], categoriesAffichees.get(position).getId(), 2);
			categoriesDialog.getBuilder().show();
			
		}else{
			categoriesDialog = new CategoriesDialog(context, "Supprimer", "NonSupprimer", "", "Annuler", new String[0], categoriesAffichees.get(position).getId(), 2);
			categoriesDialog.getBuilder().show();
			
		}
		return false;
	}

	public CharSequence initTitre() {
		return "Catégories";
	}

}
