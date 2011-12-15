package fr.clivana.lemansnews.vue;

import fr.clivana.lemansnews.R;
import fr.clivana.lemansnews.controller.CategoriesController;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.GridView;

public class CategoriesActivity extends Activity{

	CategoriesController controller;
	Button ajouterCategorie;
	Button boutonRetour;
	Button boutonActualiser;
	GridView categories;
	
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
	}
	
}
