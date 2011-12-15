package fr.clivana.lemansnews.controller;

import android.content.Context;
import fr.clivana.lemansnews.async.AsyncTaskBDD;
import fr.clivana.lemansnews.utils.reseau.Reseau;

public class SplashController {

	Context ctx;
	AsyncTaskBDD asyncBdd;
	
	public SplashController(Context context){
		ctx = context;
		asyncBdd = new AsyncTaskBDD(ctx);
	}
	
	public void execute(){
		if (Reseau.verifReseau(ctx)) {
			asyncBdd.execute();
		}
	}
	
}
