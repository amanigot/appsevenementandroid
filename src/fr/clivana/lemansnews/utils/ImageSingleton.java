package fr.clivana.lemansnews.utils;

import java.util.HashMap;
import java.util.Map;

import fr.clivana.lemansnews.utils.reseau.Reseau;

import android.graphics.Bitmap;

public class ImageSingleton {

	private static ImageSingleton instance = new ImageSingleton();
	private Map<String, Bitmap> cache;
	
	private ImageSingleton() {
		super();
		this.cache = new HashMap<String, Bitmap>();
	}
	
	public static ImageSingleton getInstance(){
		return instance;
	}
	
	public synchronized Bitmap chargementImage(String nom){
		if (cache.containsKey(nom)){
			//Debugger.logHeap();
			return cache.get(nom);
		}else{
			Bitmap bitmap = Reseau.chargementImage(nom);
			if (bitmap != null){
				cache.put(nom, bitmap);
			}
			cache.put(nom, bitmap);
			return bitmap;
		}
	}
}
