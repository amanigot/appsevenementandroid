package fr.clivana.lemansnews.controller;

import java.util.Date;
import java.util.List;

import com.google.android.apps.analytics.GoogleAnalyticsTracker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import fr.clivana.lemansnews.R;
import fr.clivana.lemansnews.async.AsyncTaskCategories;
import fr.clivana.lemansnews.dao.CategoriesDAO;
import fr.clivana.lemansnews.dao.NewsDAO;
import fr.clivana.lemansnews.entity.Categorie;
import fr.clivana.lemansnews.utils.Formatage;
import fr.clivana.lemansnews.utils.reseau.Reseau;
import fr.clivana.lemansnews.vue.CategoriesDialog;
import fr.clivana.lemansnews.vue.GridNewsActivity;


public class CategoriesController implements OnClickListener,
		OnItemLongClickListener, OnItemClickListener {

	CategorieAdapter adapter;
	Context context;
	CategoriesDAO categoriesDao;
	List<Categorie> categoriesMenu, categoriesAAjouter;
	String[] categoriesAAjouterTitre;
	CategoriesDialog categoriesDialog, ajouterCategorie;
	NewsDAO newsDao;
	AsyncTaskCategories asyncTask;
	GoogleAnalyticsTracker tracker;
	
	public CategoriesController(Context c) {
		context = c;
		
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
			ajouterCategorie= new CategoriesDialog(context, "Ajouter une catégorie", "Appuyez sur une catégorie pour l'ajouter. Appuyez longuement sur une catégorie du menu pour la supprimer.", "", "Annuler",categoriesAAjouterTitre, -1, 1 );
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

	}

	public CategorieAdapter initCategorieAdapter() {
		categoriesMenu = categoriesDao.getSelectedCategories();
		adapter = new CategorieAdapter(context, categoriesMenu);
		return adapter;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
		tracker.trackEvent("Categories", "clic", categoriesMenu.get(position).getNom(), 1);
				// Intent de la Grid News
				 if(categoriesMenu.get(position).getTotal()>0){
					Intent intentNews = new Intent(context, GridNewsActivity.class);
				 	intentNews.putExtra("categorie", categoriesMenu.get(position).getNom());
				 	categoriesMenu.get(position).setDateConsult(Formatage.datePourPlay(new Date().getTime()));
				 	categoriesDao.updateCategorie(categoriesMenu.get(position) );
				 	context.startActivity(intentNews);
				 }else{
					 Toast.makeText(context, "Aucun article pour le moment dans la catégorie.", Toast.LENGTH_SHORT).show();
				 }
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View v,
			final int position, long id) {
		tracker.trackEvent("Categories", "long-clic", categoriesMenu.get(position).getNom(), 1);
		if(categoriesMenu.get(position).isSupprimable()){
			categoriesDialog = new CategoriesDialog(context, "Supprimer", "Voulez-vous supprimer la catégorie ?", "Supprimer", "Annuler", new String[0], position, 2);
			categoriesDialog.getBuilder().show();
			
		}
		return false;
	}

	public CharSequence initTitre() {
		// TODO Auto-generated method stub
		return "Catégories";
	}

}
