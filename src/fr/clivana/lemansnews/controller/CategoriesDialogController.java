package fr.clivana.lemansnews.controller;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.widget.Toast;
import fr.clivana.lemansnews.dao.CategoriesDAO;
import fr.clivana.lemansnews.entity.Categorie;
import fr.clivana.lemansnews.utils.facebook.FacebookFunctions;
import fr.clivana.lemansnews.utils.facebook.FacebookFunctions.PocRequestListener;
import fr.clivana.lemansnews.utils.facebook.SessionEvents;
import fr.clivana.lemansnews.utils.facebook.SessionEvents.AuthListener;
import fr.clivana.lemansnews.vue.CategoriesActivity;

public class CategoriesDialogController implements DialogInterface.OnClickListener{

	ProgressDialog publishDialog;
	int FACEBOOK_REQUEST_CODE=123;
	CategoriesDAO categoriesDao;
	Context context;
	int position;
	CharSequence[] categories;
	//Cet id correspond au type de dialog affiché : 1: Ajouter une catégorie, 2: Supprimer, 3: Partager
	//Il est utilisé pour le onclick listener.
	int dialID; 
	Categorie categ;
	String titre, description;
	
	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getDialID() {
		return dialID;
	}

	public void setDialID(int dialID) {
		this.dialID = dialID;
	}

	
	
	public CategoriesDialogController(Context ctx, int id, CharSequence[] items) {
		context = ctx;
		categoriesDao=new CategoriesDAO(context);
		categories=items;
	}

	public CategoriesDialogController(Context ctx, int id, CharSequence[] items, int pos) {
		context = ctx;
		categoriesDao=new CategoriesDAO(context);
		dialID=id;
		categories=items;
	}
	

	public CategoriesDialogController(Context ctx, int id, int pos) {
		context = ctx;
		categoriesDao=new CategoriesDAO(context);
		dialID=id;
		position=pos;
	}

	@Override
	public void onClick(DialogInterface dialog, int which) {
		
		//Le bouton annuler est présent sur tous les dialogs
		if(which== AlertDialog.BUTTON_NEGATIVE){
			dialog.cancel();
		}
			
		if(dialID==1){ //Ici on ajoute une catégorie
			
			categ=categoriesDao.getCategorie(categories[which].toString());
			categ.setSelected(true);
			categoriesDao.updateCategorie(categ);
			((CategoriesActivity) context).initAdapters();
		}
		
		if(dialID==2){//Ici on supprime une catégorie
			if(which== AlertDialog.BUTTON_POSITIVE){
				categ=categoriesDao.getCategorie(position);
				categ.setSelected(false);
				categoriesDao.updateCategorie(categ);
				((CategoriesActivity) context).initAdapters();
			}
		}
		
		if(dialID==3){//Ici on partage sur facebook, twitter,...
			switch(which){
			case 0:
				FacebookFunctions.initialize(context);
				SessionEvents.addAuthListener(returnAuthListener(titre, description));
				if (!FacebookFunctions.isConnected()) {
					FacebookFunctions.login(((Activity)context), FACEBOOK_REQUEST_CODE);
				} else {
					publishMessage(titre, description);
				}
			break;
			case 1:
				Intent TwitterIntent = findTwitterClient();
		    	if(TwitterIntent != null)
		    	{
		    		TwitterIntent.putExtra(android.content.Intent.EXTRA_TEXT, titre+" : "+description);
		    		context.startActivity(Intent.createChooser(TwitterIntent, "Partager..."));
		    	}
		    	else
		    	{
		    		Toast toast=new Toast(context);
					CharSequence text = "Vous devez d'abord telecharger une application Twitter.";
					int duration = Toast.LENGTH_SHORT;
					toast = Toast.makeText(context, text, duration);
					toast.show();
		    	}
		    break;
			case 2:
					Intent sendIntent = new Intent(Intent.ACTION_SEND);
			    	sendIntent.setType("plain/text");
					sendIntent .putExtra(android.content.Intent.EXTRA_SUBJECT, titre);
					sendIntent .putExtra(android.content.Intent.EXTRA_TEXT, description);
					context.startActivity(Intent.createChooser( sendIntent, "Envoyer un mail..."));
			break;
			case 3:
					Intent sendIntentSMS = new Intent(Intent.ACTION_VIEW);
					sendIntentSMS.putExtra("sms_body", titre+" : "+description); 
					sendIntentSMS.setType("vnd.android-dir/mms-sms");
					context.startActivity(sendIntentSMS);
			break;
			case 4:
					Intent sendIntentG = new Intent(Intent.ACTION_SEND);
			    	sendIntentG.setType("text/plain");
					sendIntentG.putExtra(android.content.Intent.EXTRA_SUBJECT, titre);
					sendIntentG.putExtra(android.content.Intent.EXTRA_TEXT, description);
					sendIntentG.setPackage("com.google.android.apps.plus"); 
					context.startActivity(Intent.createChooser( sendIntentG, "Partager sur Google+"));
			break;
			}
		}
	}
	
	public Intent findTwitterClient() {
        final String[] twitterApps = {
                // package // name - nb installs (thousands)
                "com.twitter.android", // official - 10 000
                "com.twidroid", // twidroyd - 5 000
                "com.handmark.tweetcaster", // Tweecaster - 5 000
                "com.thedeck.android"  // TweetDeck - 5 000
                };
        Intent tweetIntent = new Intent();
        tweetIntent.setType("text/plain");
        final PackageManager packageManager = context.getPackageManager();
        List<ResolveInfo> list = packageManager.queryIntentActivities(
                tweetIntent, PackageManager.MATCH_DEFAULT_ONLY);
 
        for (int i = 0; i <twitterApps.length; i++) {
            for (ResolveInfo resolveInfo : list) {
                String p = resolveInfo.activityInfo.packageName;
                if (p != null && p.startsWith(twitterApps[i])) {
                    tweetIntent.setPackage(p);
                    return tweetIntent;
                }
            }
        }
        return null;
    }
	
	public void publishMessage(String titre, String comment) {
		publishDialog = ProgressDialog.show(context,"","");
		FacebookFunctions.publishCommentOnWallPerso(titre, comment,pocRequestListener);
	}

	public AuthListener returnAuthListener(final String titre,
			final String comment) {
		final AuthListener authListener = new AuthListener() {

			@Override
			public void onAuthSucceed() {
				publishMessage(titre, comment);
			}

			@Override
			public void onAuthFail(final String error) {
				final String message = "Echec de login : " + error;
				Toast.makeText(context, message, Toast.LENGTH_LONG).show();
			}
		};
		return authListener;
	}
	
	private final PocRequestListener pocRequestListener = new PocRequestListener() {

		public void cancelDialog() {
			if (publishDialog.isShowing()) {
				publishDialog.dismiss();
			}
		}

		@Override
		public void onSuccess(String response) {

			cancelDialog();
			((Activity) context).runOnUiThread(new Runnable() {
				@Override
				public void run() {
					Toast.makeText(context, "Publication reussie", Toast.LENGTH_SHORT).show();
				}
			});

		}

		@Override
		public void onError(final Throwable t) {
			((Activity) context).runOnUiThread(new Runnable() {
				@Override
				public void run() {
					cancelDialog();
					final String message = "Echec de publication : "
							+ t.getMessage();
					Toast.makeText(context, message,Toast.LENGTH_LONG).show();
				}
			});
		}
	};
}
