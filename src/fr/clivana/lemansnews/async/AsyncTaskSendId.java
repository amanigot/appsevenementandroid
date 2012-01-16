package fr.clivana.lemansnews.async;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.util.Log;

public class AsyncTaskSendId extends AsyncTaskEx<Void, Void, Void> {

	String idAppareil, idEnregistrement;
	
	public AsyncTaskSendId(String devId, String regId){
		idAppareil=devId;
		idEnregistrement=regId;
	}
	
	@Override
	protected Void doInBackground(Void... params) {
		sendRegistrationIdToServer(idAppareil, idEnregistrement);
		return null;
	}
	
	public void sendRegistrationIdToServer(String deviceId, String registrationId) {

		Log.d("C2DM", "Sending registration ID to my application server");
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost("http://your_url/register");
		try {
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
			// Get the deviceID
			nameValuePairs.add(new BasicNameValuePair("deviceid", deviceId));
			nameValuePairs.add(new BasicNameValuePair("registrationid", registrationId));

			post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse response = client.execute(post);
			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			String line = "";
			while ((line = rd.readLine()) != null) {
				Log.e("HttpResponse", line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
