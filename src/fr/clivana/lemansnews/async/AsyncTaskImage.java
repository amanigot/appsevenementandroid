package fr.clivana.lemansnews.async;

import android.graphics.Bitmap;
import android.widget.ImageView;
import fr.clivana.lemansnews.utils.ImageSingleton;

public class AsyncTaskImage extends AsyncTaskEx<Void, Void, Void> {

	String nomImage;
	Bitmap bitmapRecu;
	ImageView imageView;
	ImageSingleton imageSingleton;
	int imageParDefaut;
	
	public AsyncTaskImage(String nomImage, ImageView viewRecue , int defResId) {
		super();
		this.nomImage = nomImage;
		imageSingleton = ImageSingleton.getInstance();
		imageView=viewRecue;
		imageParDefaut=defResId;
	}
	
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}
	
	@Override
	protected Void doInBackground(Void... arg0) {
		bitmapRecu = imageSingleton.chargementImage(nomImage);
		return null;
	}

	protected void onPostExecute(Void result) {
		if(bitmapRecu==null){
			imageView.setBackgroundResource(imageParDefaut);
		
		}else{
			imageView.setImageBitmap(bitmapRecu);
		}
	};
	
}
