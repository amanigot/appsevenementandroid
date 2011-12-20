package fr.clivana.lemansnews.async;

import fr.clivana.lemansnews.dao.EventsDAO;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

public class AsyncTaskListeEvenements extends AsyncTask<Void, Void, Void> {

	Context context;
	EventsDAO eventsDao;
	ProgressDialog progress;
	
	@Override
	protected Void doInBackground(Void... params) {
		// TODO Auto-generated method stub
		return null;
	}

}
