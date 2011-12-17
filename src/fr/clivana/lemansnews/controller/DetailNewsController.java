package fr.clivana.lemansnews.controller;

import fr.clivana.lemansnews.R;
import fr.clivana.lemansnews.dao.NewsDAO;
import fr.clivana.lemansnews.entity.Article;
import fr.clivana.lemansnews.utils.Formatage;
import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;

public class DetailNewsController implements OnClickListener {

	Context context;
	NewsDAO newsDao;
	Article article;
	int idArticle;
	String categorie;
	
	public DetailNewsController(Context c, int idArticle, String categorie) {
		context=c;
		this.idArticle=idArticle;
		newsDao=new NewsDAO(context);
		article=newsDao.getArticle(idArticle);
		this.categorie= categorie;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId()==R.id.buttonRetour){
			((Activity) context).finish();
		}
		if(v.getId()==R.id.imageViewPartager){
			
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