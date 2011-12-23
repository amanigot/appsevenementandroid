package fr.clivana.lemansnews.async;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;
import fr.clivana.lemansnews.utils.ImageSingleton;

public class AsyncTaskImage extends AsyncTask<Void, Void, Void> {

	String nomImage;
	Bitmap bitmapRecu;
	ImageView imageView;
	ImageSingleton imageSingleton;
	
	
	public AsyncTaskImage(String nomImage, ImageView viewRecue) {
		super();
		this.nomImage = nomImage;
		imageSingleton = ImageSingleton.getInstance();
		imageView=viewRecue;
		
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
		imageView.setImageBitmap(bitmapRecu);
	};
	
}
