package fr.clivana.lemansnews.controller;

import java.util.List;

import fr.clivana.lemansnews.dao.CategoriesDAO;
import fr.clivana.lemansnews.entity.Categorie;
import android.content.DialogInterface;

public class CategoriesDialogController implements DialogInterface.OnClickListener{

	CategoriesDAO categoriesDao;
	String[] items;
	List<Categorie> categories;
	
	@Override
	public void onClick(DialogInterface dialog, int which) {
	
		
	}

	public String[] initItems() {
		
		//categories = categoriesDao.selectUnselectedCategoriesNames();
		if(items.length==0){
			
		}
		return items;
	}

	

}
