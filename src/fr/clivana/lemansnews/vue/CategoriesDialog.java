package fr.clivana.lemansnews.vue;

import android.app.AlertDialog;
import android.content.Context;
import fr.clivana.lemansnews.controller.CategoriesDialogController;

public class CategoriesDialog extends AlertDialog {

	Context ctx;
	String title, message, nomPositiveButton, nomNegativeButton;
	String[] items;
	CategoriesDialogController controller;
	
	
	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public String getNomPositiveButton() {
		return nomPositiveButton;
	}


	public void setNomPositiveButton(String nomPositiveButton) {
		this.nomPositiveButton = nomPositiveButton;
	}


	public String getNomNegativeButton() {
		return nomNegativeButton;
	}


	public void setNomNegativeButton(String nomNegativeButton) {
		this.nomNegativeButton = nomNegativeButton;
	}


	public String[] getItems() {
		return items;
	}


	public void initItems() {
		this.items=controller.initItems();
		
	}

	
	
	
	
	
	public CategoriesDialog(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	


	


}
