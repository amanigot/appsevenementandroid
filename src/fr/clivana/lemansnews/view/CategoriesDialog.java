package fr.clivana.lemansnews.view;

import android.app.AlertDialog;
import android.content.Context;
import fr.clivana.lemansnews.controller.CategoriesDialogController;

public class CategoriesDialog extends AlertDialog {

	private Context ctx;
	private String title, message, boutonValider, boutonAnnuler;
	CharSequence[] items;
	private CategoriesDialogController categoriesDialogController;
	long positionElementGridview;
	Builder builder;
	
	public CategoriesDialog(Context context, String titre, String message, String validerButton, String annulerButton, String[] tabCategories, long position, int id) {
		super(context);
		
		//----------------------------------------------------------
		//Attention : 	un alertDialog ne peut pas faire un setItems 
		//				et un setMessage en meme temps sinon conflit
		//----------------------------------------------------------
		
		this.ctx=context;
		this.title=titre;
		this.message=message;
		this.boutonValider=validerButton;
		this.boutonAnnuler=annulerButton;
		this.items=tabCategories;
		
		builder= new Builder(ctx);
		
		//le constructeur utilisant la position n'est utilisé que pour la suppression
		positionElementGridview = position; 
		if(positionElementGridview == -1){
			categoriesDialogController = new CategoriesDialogController(ctx, id, items);
		}else{
			categoriesDialogController = new CategoriesDialogController(ctx, id, items, positionElementGridview);
		}
		
		if(!titre.equals("")){
			
			builder.setTitle(title);
		}
		
		if(!this.message.equals("")){
			
			if(this.message.equals("Supprimer")){ builder.setMessage("Voulez-vous supprimer la catégorie ?"); }
			if(this.message.equals("NonSupprimer")){ builder.setMessage("Cette catégorie n'est pas supprimable."); }
			
		}
		
		if(!boutonValider.equals("")){
			
			builder.setPositiveButton(boutonValider, categoriesDialogController);
		}
		
		if(!boutonAnnuler.equals("")){
			
			builder.setNegativeButton(boutonAnnuler, categoriesDialogController);
		}
		
		if(items.length != 0 ){
			
			builder.setItems(items, categoriesDialogController);
			
		}else{
			if(this.message.equals("")){
				builder.setMessage("Aucune nouvelle catégorie à ajouter");
			}
			
		}
		
		
		
		builder.create();
		
	}

	//Méthode utilisée pour le dialog de partage
	public void addInfos(String titre, String details, String image) {
		categoriesDialogController.setTitre(titre);
		categoriesDialogController.setDescription(details);
		categoriesDialogController.setImage(image);
	}

	public Builder getBuilder() {
		return builder;
	}

	public void setBuilder(Builder builder) {
		this.builder = builder;
	}

	
	
}
