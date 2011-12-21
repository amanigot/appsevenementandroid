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
		builder= new Builder(ctx);
		
		//le constructeur utilisant la position n'est utilisé que pour la suppression
		pos = position;
		if(pos==-1){
			controller = new CategoriesDialogController(ctx, id);
		}else{
			controller = new CategoriesDialogController(ctx, id, pos);
		}
		
		if(!titre.equals("")){
			
			builder.setTitle(title);
		}
		
		if(!this.message.equals("")){
			
			builder.setMessage(this.message);
		}
		
		if(!nomPositiveButton.equals("")){
			
			builder.setPositiveButton(nomPositiveButton, controller);
		}
		
		if(!nomNegativeButton.equals("")){
			
			builder.setNegativeButton(nomNegativeButton, controller);
		}
		
		if(items.length>0 && !items.equals(null)){
			
			builder.setItems(items, controller);
			
		}else{
			builder.setMessage("Aucune nouvelle catégorie n'est disponible pour le moment.");
		}
		
		
		builder.create();
		
	}

	//Méthode utilisée pour le dialog de partage
	public void addInfos(String titre, String details) {
		controller.setTitre(titre);
		controller.setDescription(details);
	}

	public Builder getBuilder() {
		return builder;
	}

	public void setBuilder(Builder builder) {
		this.builder = builder;
	}

	
	
}
