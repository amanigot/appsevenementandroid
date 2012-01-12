package fr.clivana.lemansnews.utils;

import java.util.HashMap;
import java.util.Map;

import fr.clivana.lemansnews.utils.reseau.Reseau;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

public class ImageSingleton {

	private static ImageSingleton instance;
	private Map<String, Bitmap> cache;
	
	private ImageSingleton() {
		super();
		this.cache = new HashMap<String, Bitmap>();
	}
	
	private ImageSingleton(Context context){
		super();
		this.cache = new HashMap<String, Bitmap>();
	}
	
	public static ImageSingleton create(Context context){
		if (instance == null){
			instance = new ImageSingleton(context);
		}
		return instance;
	}
	
	public static ImageSingleton getInstance(){
		if (instance == null){
			instance = new ImageSingleton();
		}
		return instance;
	}
	
	public synchronized Bitmap chargementImage(String nom){
		if (cache.containsKey(nom)){
//			Debugger.logHeap();
			return cache.get(nom);
		}else{
			Bitmap bitmap = Reseau.chargementImage(nom);
			if (bitmap != null){
				cache.put(nom, bitmap);
			}
//			cache.put(nom, bitmap);
			return bitmap;
		}
	}
	public void shutdown(){
		cache = null;
	}
}
