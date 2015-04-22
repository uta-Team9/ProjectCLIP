package team9.clip_loginhomecareer;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;


public class NewContact extends ActionBarActivity {
	//_ID, NAME, NUMBER, EMAIL, USED, MET, HASH_ID

	private DatabaseContract db;
	private int User_ID;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		openDB();
		Bundle extras = getIntent().getExtras();
		if(extras != null) {
			User_ID = extras.getInt("ID");
			Log.d("User ID: ", "" + User_ID);
		}

		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_contact_activity);
	}

	protected void onDestroy() {
		super.onDestroy();
		closeDB();
	}

	private void openDB() {
		db = new DatabaseContract(this);
		db.open();
	}
	private void closeDB() {
		db.close();
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_contact_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	//_ID, NAME, EMAIL, NUMBER, MET, USED, HASH_ID
	public void add_new(View v) {
		EditText text;
		if(validItems()) {
			text = (EditText) findViewById(R.id.new_contact_name);
			String n = text.getText().toString();
			text = (EditText) findViewById(R.id.new_contact_email);
			String e = text.getText().toString();
			text = (EditText) findViewById(R.id.new_contact_phone);
			Long p = Long.parseLong(text.getText().toString());
			DatePicker datePicker = (DatePicker) findViewById(R.id.new_contact_met);
			String test = datePicker.getYear() + "" + datePicker.getMonth() + "" + datePicker.getDayOfMonth();
			Integer m = Integer.parseInt(test);

			db.insertContact(n, e, p.intValue(), m.intValue(), 0, User_ID);
			Log.d("Contact Saved: ", "" + n);
			toastNotification("Contact Saved");
			clearData();
			finish();
		} else {
			toastNotification("Invalid Information");
		}
	}

	private boolean validItems() {
		//TODO: Check for invalid input data
		return true;
	}

	private void clearData() {
		EditText text;
		text = (EditText) findViewById(R.id.new_contact_name);
		text.setText("");
		text = (EditText) findViewById(R.id.new_contact_email);
		text.setText("");
		text = (EditText) findViewById(R.id.new_contact_phone);
		text.setText("");
		DatePicker picker = (DatePicker) findViewById(R.id.new_contact_met);
	}

	private void toastNotification(String description) {
		Toast.makeText(getApplicationContext(), description, Toast.LENGTH_LONG).show();
	}
}
