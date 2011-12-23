package fr.clivana.lemansnews.vue;

import android.app.AlertDialog;
import android.content.Context;
import fr.clivana.lemansnews.controller.CategoriesDialogController;

public class CategoriesDialog extends AlertDialog {

	private Context ctx;
	private String title, message, nomPositiveButton, nomNegativeButton;
	CharSequence[] items;
	private CategoriesDialogController controller;
	int pos;
	Builder builder;
	
	public CategoriesDialog(Context context, String titre, String message, String posButton, String negButton, String[] objets, int position, int id) {
		super(context);
		
		//----------------------------------------------------------
		//Attention : 	un alertDialog ne peut pas faire un setItems 
		//				et un setMessage en meme temps sinon conflit
		//----------------------------------------------------------
		
		this.ctx=context;
		this.title=titre;
		this.message=message;
		this.nomPositiveButton=posButton;
		this.nomNegativeButton=negButton;
		items=new String[objets.length];
		for(int i=0;i<objets.length;i++){
			items[i]=objets[i];
		}
		
		builder= new Builder(ctx);
		
		//le constructeur utilisant la position n'est utilisé que pour la suppression
		pos = position;
		if(pos==-1){
			controller = new CategoriesDialogController(ctx, id, items);
		}else{
			controller = new CategoriesDialogController(ctx, id, items, pos);
		}
		
		if(!titre.equals("")){
			
			builder.setTitle(title);
		}
		
		if(!this.message.equals("")){
			
			if(this.message.equals("Supprimer")){ builder.setMessage("Voulez-vous supprimer la catégorie ?"); }
			if(this.message.equals("NonSupprimer")){ builder.setMessage("Cette catégorie n'est pas supprimable."); }
			
		}
		
		if(!nomPositiveButton.equals("")){
			
			builder.setPositiveButton(nomPositiveButton, controller);
		}
		
		if(!nomNegativeButton.equals("")){
			
			builder.setNegativeButton(nomNegativeButton, controller);
		}
		
		if(items.length != 0 ){
			
			builder.setItems(items, controller);
			
		}else{
			if(this.message.equals("")){
				builder.setMessage("Aucune nouvelle catégorie à ajouter");
			}
			
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
