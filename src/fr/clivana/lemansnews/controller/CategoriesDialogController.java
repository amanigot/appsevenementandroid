package fr.clivana.lemansnews.controller;

import fr.clivana.lemansnews.dao.CategoriesDAO;
import android.content.DialogInterface;

public class CategoriesDialogController implements DialogInterface.OnClickListener{

	CategoriesDAO categoriesDao;
	String[] items;
	
	@Override
	public void onClick(DialogInterface dialog, int which) {
	
		
	}

	public String[] initItems() {
		//items = categoriesDao.selectUnselectedCategoriesNames();
		if(items.length==0){
			
		}
		return items;
	}

}
