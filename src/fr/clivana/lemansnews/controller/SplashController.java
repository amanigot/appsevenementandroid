package fr.clivana.lemansnews.controller;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.widget.ProgressBar;
import android.widget.TextView;
import fr.clivana.lemansnews.async.AsyncTaskBDD;
import fr.clivana.lemansnews.utils.reseau.Reseau;

public class SplashController {

	Context ctx;
	AsyncTaskBDD asyncBdd;
	PackageInfo versionInfo;
	
	public SplashController(Context context, TextView load, ProgressBar progress){
		ctx = context;
		asyncBdd = new AsyncTaskBDD(ctx, load, progress);
	}
	
	public void execute(){
		if (Reseau.verifReseau(ctx)) {
			asyncBdd.execute();
		}else{
			
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
