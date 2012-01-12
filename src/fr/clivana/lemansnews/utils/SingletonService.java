package fr.clivana.lemansnews.utils;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
//import android.os.RemoteException;

public class SingletonService extends Service {

//	private final ISingletonService.Stub mBinder = new ISingletonService.Stub() {

//		public void startSingletons() throws RemoteException {
//
//		initializeSingletons();
//
//		}
//
//		public void stopSingletons() throws RemoteException {
//
//		shutdownSingletons();
//
//		}

//		};

		@Override
		public IBinder onBind(Intent intent) {
			return null;
//		return mBinder;

		}

		@Override
		public void onCreate() {
			super.onCreate();
			initializeSingletons();
		}

		@Override
		public void onDestroy() {
			super.onDestroy();
			shutdownSingletons();
		}

		protected void initializeSingletons() {

		ImageSingleton.create(getApplicationContext());

		}

		private void shutdownSingletons() {

			ImageSingleton singleton = ImageSingleton.getInstance();
			singleton.shutdown();

		}


}
