package fr.clivana.lemansnews.controller;

import com.google.android.apps.analytics.GoogleAnalyticsTracker;

import fr.clivana.lemansnews.R;
import fr.clivana.lemansnews.async.AsyncTaskImage;
import fr.clivana.lemansnews.dao.NewsDAO;
import fr.clivana.lemansnews.entity.Article;
import fr.clivana.lemansnews.utils.Formatage;
import fr.clivana.lemansnews.view.CategoriesDialog;
import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class DetailNewsController implements OnClickListener {

	Context context;
	NewsDAO newsDao;
	Article article;
	long idArticle;
	String categorie;
	String[] items={"Facebook", "Mail", "SMS", "Google+"};
	CategoriesDialog dialog;
	GoogleAnalyticsTracker tracker;
	AsyncTaskImage asyncTask;
	Button bouton;
	
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
		if(v.getId()==R.id.buttonRetour|| v.getId()==R.id.buttonNews){
			tracker.trackEvent("Detail d'une news", "clic", "Précédent", 1);
			((Activity) context).finish();
		}
		if(v.getId()==R.id.imageViewPartager){
			tracker.trackEvent("Detail d'une news", "clic", "Partager-"+article.getId()+"-"+article.getTitre(), 1);
			dialog=new CategoriesDialog(context, "Partager", "", "", "Annuler", items, -1, 3);
			dialog.addInfos(article.getTitre(), article.getArticle(), article.getUrlImageMobile());
			dialog.getBuilder().show();
		}
		if(v.getId()==R.id.buttonInfo){
			if(!article.isFavoris()){
				tracker.trackEvent("Detail d'une news", "clic", "Favoris-"+article.getId()+"-"+article.getTitre(), 1);
				article.setFavoris(true);
				newsDao.updateNews(article);
				setFavButton();
				Toast.makeText(context, "Mis en favoris", Toast.LENGTH_SHORT).show();
			}else{
				tracker.trackEvent("Detail d'une news", "clic", "Non-Favoris-"+article.getId()+"-"+article.getTitre(), 1);
				article.setFavoris(false);
				newsDao.updateNews(article);
				setFavButton();
				Toast.makeText(context, "Retirée des favoris", Toast.LENGTH_SHORT).show();
			}
		}
	}

	public CharSequence initTitre() {
		return "Actualités";
	}

	public CharSequence getTitreNews() {
		return article.getTitre();
	}

	public CharSequence initDateAuteur() {
		String retString = "Publié le "+Formatage.dateEnTexteComplet(Formatage.stringToDate(article.getDateParution()))+" | "+article.getAuteur();
		retString.toUpperCase();
		return retString;
	}

	public String getDescription() {
		return article.getArticle();
	}

	public void initFavButton(Button bouton) {
		this.bouton=bouton;
		
	}
	
	public void setFavButton(){
		if(article.isFavoris()){
			bouton.setBackgroundResource( R.drawable.btnmenuhautfavoris);
		}else{
			bouton.setBackgroundResource( R.drawable.btnmenuhautfavorisoff);
		}
	}
	
	public void setImage(ImageView imageEvenement) {
		asyncTask = new AsyncTaskImage(article.getUrlImageMobile(), imageEvenement, R.drawable.illustaucuneimage480);
		asyncTask.execute();
	}
	
}
