package fr.clivana.lemansnews.vue;

import fr.clivana.lemansnews.R;
import fr.clivana.lemansnews.controller.CategoriesController;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

public class CategoriesActivity extends Activity{

	CategoriesController controller;
	Button ajouterCategorie;
	Button boutonRetour;
	Button boutonActualiser;
	GridView categories;
	TextView titreApplication;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
        setContentView(R.layout.categorie);
        
        ajouterCategorie=(Button)findViewById(R.id.buttonAjouterCategorie);
        boutonRetour=(Button)findViewById(R.id.buttonRetour);
        boutonActualiser=(Button)findViewById(R.id.buttonActualiser);
        categories=(GridView)findViewById(R.id.gridViewCategorie);
        
        ajouterCategorie.setOnClickListener(controller);
        boutonActualiser.setOnClickListener(controller);
        boutonRetour.setOnClickListener(controller);
        
        categories.setAdapter(controller.initCategorieAdapter());
        categories.setOnItemClickListener(controller);
        categories.setOnItemLongClickListener(controller);
	}
	
}
