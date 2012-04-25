package fr.clivana.lemansnews.view;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import fr.clivana.lemansnews.R;

public class MessageReceivedActivity extends Activity {
	
	Bundle extras;
	String message;
	TextView view;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_result);
		extras = getIntent().getExtras();
		if (extras != null) {
			message = extras.getString("payload");
			if (message != null && message.length() > 0) {
				TextView view = (TextView) findViewById(R.id.result);
				view.setText(message);
			}
		}

		super.onCreate(savedInstanceState);
	}
	
}
