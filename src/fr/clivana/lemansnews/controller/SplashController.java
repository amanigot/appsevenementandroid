package fr.clivana.lemansnews.controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import fr.clivana.lemansnews.async.AsyncTaskBDD;
import fr.clivana.lemansnews.utils.reseau.Reseau;
import fr.clivana.lemansnews.view.VuePrincipaleActivity;

public class SplashController {

	Context ctx;
	AsyncTaskBDD asyncBdd;
	PackageInfo versionInfo;
	Intent vuePrincipale;
	
	//On initialise le constructeur de l'objet controlleur en receptionnant des données
	public SplashController(Context context, TextView load, ProgressBar progress){
		ctx = context;
		asyncBdd = new AsyncTaskBDD(ctx, load, progress);
	}
	
	public void execute(){
		if (Reseau.verifReseau(ctx)) {
			if(Reseau.isSlow(ctx)){
				Toast.makeText(ctx, "Attention : en EDGE, le chargement peut durer plus longtemps", 3000).show();
			}
			asyncBdd.execute();
		}else{
			if(ctx.getSharedPreferences("prefs", 0).getBoolean("newuser", true)){
				Toast.makeText(ctx, "La connexion est introuvable. Pour une première connexion, vous devez vous connecter sur Internet.", Toast.LENGTH_LONG).show();
			}else{
				Intent vuePrincipale = new Intent(ctx, VuePrincipaleActivity.class);
				ctx.startActivity(vuePrincipale);
				((Activity) ctx).finish();
			}
		}
	}
	
	public String getVersion(){
		//Ce code essaie de retourner la version de l'application à partir du manifest
		try {
			versionInfo = ctx.getPackageManager().getPackageInfo(ctx.getPackageName(), PackageManager.GET_ACTIVITIES);
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return versionInfo.versionName;
	}
	
}
