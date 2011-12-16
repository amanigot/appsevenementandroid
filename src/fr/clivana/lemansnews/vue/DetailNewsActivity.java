package fr.clivana.lemansnews.vue;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import fr.clivana.lemansnews.R;
import fr.clivana.lemansnews.controller.DetailNewsController;

public class DetailNewsActivity extends Activity{
	
	TextView titreApplication;
	TextView titreNews;
	TextView dateAuteur;
	ImageView imageNews;
	ImageView partager;
	WebView detailNews;
	Button boutonRetour;
	DetailNewsController detailNewsController;
	int idArticle;
	String categorie;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
        setContentView(R.layout.visuelnews);
        
        Bundle extras = getIntent().getExtras();
		if(extras!=null){
			idArticle=extras.getInt("article");
			categorie=extras.getString("categorie");
		}else{
			idArticle=savedInstanceState.getInt("article");
			categorie=savedInstanceState.getString("categorie");
		}
        
        titreApplication=(TextView)findViewById(R.id.textViewTitreApplication);
        titreNews=(TextView)findViewById(R.id.textViewTitreNews);
        dateAuteur=(TextView)findViewById(R.id.textViewDateNews);
        imageNews=(ImageView)findViewById(R.id.imageViewNewsEvents);
        detailNews=(WebView)findViewById(R.id.descriptionNews);
        partager=(ImageView)findViewById(R.id.imageViewPartager);
        boutonRetour=(Button)findViewById(R.id.buttonRetour);
        
        detailNewsController=new DetailNewsController(this, idArticle, categorie);
        
        partager.setOnClickListener(detailNewsController);
        boutonRetour.setOnClickListener(detailNewsController);
        titreApplication.setText(detailNewsController.initTitre());
        titreNews.setText(detailNewsController.getTitreNews());
        dateAuteur.setText(detailNewsController.initDateAuteur());
        detailNews.loadData(detailNewsController.getDescription(), "text/html", null);
        
	}
}
