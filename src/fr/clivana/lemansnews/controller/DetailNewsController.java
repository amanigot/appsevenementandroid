package fr.clivana.lemansnews.controller;

import com.google.android.apps.analytics.GoogleAnalyticsTracker;

import fr.clivana.lemansnews.R;
import fr.clivana.lemansnews.dao.NewsDAO;
import fr.clivana.lemansnews.entity.Article;
import fr.clivana.lemansnews.utils.Formatage;
import fr.clivana.lemansnews.vue.CategoriesDialog;
import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;

public class DetailNewsController implements OnClickListener {

	Context context;
	NewsDAO newsDao;
	Article article;
	long idArticle;
	String categorie;
	String[] items={"Facebook", "Twitter", "Mail", "SMS", "Google+"};
	CategoriesDialog dialog;
	GoogleAnalyticsTracker tracker;
	
	public DetailNewsController(Context c, long idArticle2, String categorie) {
		context=c;
		this.idArticle=idArticle2;
		newsDao=new NewsDAO(context);
		article=newsDao.getArticle(idArticle2);
		this.categorie= categorie;
		tracker = GoogleAnalyticsTracker.getInstance();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId()==R.id.buttonRetour|| v.getId()==R.id.buttonNews){
			tracker.trackEvent("Detail d'une news", "clic", "Précédent", 1);
			((Activity) context).finish();
		}
		if(v.getId()==R.id.imageViewPartager){
			tracker.trackEvent("Detail d'une news", "clic", "Partager-"+article.getId()+"-"+article.getTitre(), 1);
			dialog=new CategoriesDialog(context, "Partager", "", "", "Annuler", items, -1, 3);
			dialog.addInfos(article.getTitre(), article.getArticle());
			dialog.show();
		}
		if(v.getId()==R.id.buttonInfo){
			tracker.trackEvent("Detail d'une news", "clic", "Favoris-"+article.getId()+"-"+article.getTitre(), 1);
			article.setFavoris(true);
			newsDao.updateNews(article);
		}
	}

	public CharSequence initTitre() {
		// TODO Auto-generated method stub
		return categorie;
	}

	public CharSequence getTitreNews() {
		return article.getTitre();
	}

	public CharSequence initDateAuteur() {
		String retString = "Publié le "+Formatage.dateEnTexteComplet(article.getDateParution())+" | "+article.getAuteur();
		return retString;
	}

	public String getDescription() {
		// TODO Auto-generated method stub
		return article.getArticle();
	}

}
