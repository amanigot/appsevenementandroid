package fr.clivana.lemansnews.vue;

import android.app.AlertDialog;
import android.content.Context;
import fr.clivana.lemansnews.controller.CategoriesDialogController;

public class CategoriesDialog extends AlertDialog.Builder {

	private Context ctx;
	private String title, message, nomPositiveButton, nomNegativeButton;
	private String[] items;
	private CategoriesDialogController controller;
	int pos;
	
	public CategoriesDialog(Context context, String titre, String message, String posButton, String negButton, String[] objets, int position) {
		super(context);
		
		this.ctx=context;
		this.title=titre;
		this.message=message;
		this.nomPositiveButton=posButton;
		this.nomNegativeButton=negButton;
		this.items=objets;
		pos = position;
		if(pos!=-1){
			controller = new CategoriesDialogController(ctx, pos);
		}else{
			controller = new CategoriesDialogController(ctx);
		}
		
		if(!titre.equals(null)){
			
			setTitle(title);
		}
		
		if(!this.message.equals(null)){
			
			setMessage(this.message);
		}
		
		if(!nomPositiveButton.equals(null)){
			
			setPositiveButton(nomPositiveButton, controller);
		}
		
		if(!nomNegativeButton.equals(null)){
			
			setNegativeButton(nomNegativeButton, controller);
		}
		
		if(!items.equals(null)){
			
			setItems(items, controller);
			
		}
		
		create();
		
	}
	
	
}
