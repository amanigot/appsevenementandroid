package fr.clivana.lemansnews.vue;

import android.app.AlertDialog;
import android.content.Context;
import fr.clivana.lemansnews.controller.CategoriesDialogController;

public class CategoriesDialog extends AlertDialog {

	private Context ctx;
	private String title, message, nomPositiveButton, nomNegativeButton;
	private String[] items;
	private CategoriesDialogController controller;
	int pos;
	Builder builder;
	
	public CategoriesDialog(Context context, String titre, String message, String posButton, String negButton, String[] objets, int position, int id) {
		super(context);
		
		this.ctx=context;
		this.title=titre;
		this.message=message;
		this.nomPositiveButton=posButton;
		this.nomNegativeButton=negButton;
		this.items=objets;
		
		pos = position;
		if(pos!=-1){
			controller = new CategoriesDialogController(ctx, id, pos);
		}else{
			controller = new CategoriesDialogController(ctx, id);
		}
		
		if(!titre.equals(null)){
			
			setTitle(title);
		}
		
		if(!this.message.equals(null)){
			
			setMessage(this.message);
		}
		
		if(!nomPositiveButton.equals(null)){
			
			builder.setPositiveButton(nomPositiveButton, controller);
		}
		
		if(!nomNegativeButton.equals(null)){
			
			builder.setNegativeButton(nomNegativeButton, controller);
		}
		
		if(!items.equals(null)){
			
			builder.setItems(items, controller);
			
		}
		
		
		builder.create();
		
	}

	public void addInfos(String titre, String details) {
		controller.setTitre(titre);
		controller.setDescription(details);
	}

	
	
}
