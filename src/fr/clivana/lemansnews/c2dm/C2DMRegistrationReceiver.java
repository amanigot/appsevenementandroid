package fr.clivana.lemansnews.c2dm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import android.provider.Settings.Secure;
import android.util.Log;
import fr.clivana.lemansnews.R;
import fr.clivana.lemansnews.utils.Params;
import fr.clivana.lemansnews.view.RegistrationResultActivity;

public class C2DMRegistrationReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		String action = intent.getAction();
		Log.w("C2DM", "Registration Receiver called");
		if ("com.google.android.c2dm.intent.REGISTRATION".equals(action)) {
			Log.w("C2DM", "Received registration ID");
			final String registrationId = intent
					.getStringExtra("registration_id");
			String error = intent.getStringExtra("error");

			Log.d("C2DM", "dmControl: registrationId = " + registrationId
					+ ", error = " + error);
			String deviceId = Secure.getString(context.getContentResolver(),
					Secure.ANDROID_ID);
			createNotification(context, registrationId);
			sendRegistrationIdToServer(deviceId, registrationId);
			// Also save it in the preference to be able to show it later
			saveRegistrationId(context, registrationId);
		}
	}

	private void saveRegistrationId(Context context, String registrationId) {
		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(context);
		Editor edit = prefs.edit();
		edit.putString("authentication", registrationId);
		edit.commit();
	}

	public void createNotification(Context context, String registrationId) {
		NotificationManager notificationManager = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);
		Notification notification = new Notification(R.drawable.icon,
				"Registration successful", System.currentTimeMillis());
		// Hide the notification after its selected
		notification.flags |= Notification.FLAG_AUTO_CANCEL;

		Intent intent = new Intent(context, RegistrationResultActivity.class);
		intent.putExtra("registration_id", registrationId);
		PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,
				intent, 0);
		notification.setLatestEventInfo(context, "Registration",
				"Successfully registered", pendingIntent);
		
	}

	// Incorrect usage as the receiver may be canceled at any time
	// do this in an service and in an own thread
	public void sendRegistrationIdToServer(String deviceId,
			String registrationId) {
		Log.d("C2DM", "Sending registration ID to my application server");
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(Params.BASE_SERVEUR+"/register/"+deviceId+"/"+registrationId); //Ajouter l'adresse du serveur
		try {
			
			HttpResponse response = client.execute(post);
			BufferedReader rd = new BufferedReader(new InputStreamReader(
					response.getEntity().getContent()));

			String line = "";
			while ((line = rd.readLine()) != null) {
				Log.e("HttpResponse", line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
