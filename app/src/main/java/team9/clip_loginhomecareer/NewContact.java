package team9.clip_loginhomecareer;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;


public class NewContact extends ActionBarActivity {
	//_ID, NAME, NUMBER, EMAIL, USED, MET, HASH_ID
	private DatabaseContract db;
	private int User_ID;
	private Contact contact = null;
	private EditText TEXT_NAME;
	private EditText TEXT_EMAIL;
	private EditText TEXT_PHONE;
	private DatePicker PICKER;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		openDB();

		Bundle extras = getIntent().getExtras();
		if(extras != null) {
			contact = (Contact)extras.getSerializable("Contact");
		}

		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_contact_activity);

		TEXT_NAME = (EditText) findViewById(R.id.new_contact_name);
		TEXT_EMAIL = (EditText) findViewById(R.id.new_contact_email);
		TEXT_PHONE = (EditText) findViewById(R.id.new_contact_phone);
		PICKER = (DatePicker) findViewById(R.id.new_contact_met);

		if(contact != null) {
			Button b = (Button)findViewById(R.id.add);
			b.setText("Update");
			populateItems();
		}
	}

	@Override
	protected void onDestroy() {
		db.close();
		super.onDestroy();
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
		if(contact != null) {
			updateItems();
		}else
			saveItems();
	}

	//Added Extra Methods
	private void openDB() {
		User_ID = getSharedPreferences("loginPrefs", MODE_PRIVATE).getInt("ID", -1);
		Log.d("ID in New Contact", ""+User_ID);

		db = new DatabaseContract(this);
		db.open();
	}

	private void populateItems() {
		TEXT_NAME.setText(contact.getName());
		TEXT_EMAIL.setText(contact.getEmail());
		TEXT_PHONE.setText("" + contact.getPhone());
	}

	private void updateItems() {
		String n = TEXT_NAME.getText().toString();
		String e = TEXT_EMAIL.getText().toString();
		String p = TEXT_PHONE.getText().toString();
		String m = PICKER.getYear() + "-" + PICKER.getMonth() + "-" + PICKER.getDayOfMonth();

		db.updateContact(contact.getDatabaseID(), n, e, p, m, contact.getUsed());
		Log.d("Contact Saved: ", "" + n);
		toastNotification("Contact Updated");
		clearData();
		finish();
	}

	private void saveItems() {
		if(validItems()) {
			String n = TEXT_NAME.getText().toString();
			String e = TEXT_EMAIL.getText().toString();
			String p = TEXT_PHONE.getText().toString();
			String m = PICKER.getYear() + "-" + PICKER.getMonth() + "-" + PICKER.getDayOfMonth();

			db.insertContact(n, e, p, m, 0, User_ID);
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
		TEXT_NAME.setText("");
		TEXT_EMAIL.setText("");
		TEXT_PHONE.setText("");
	}

	private void toastNotification(String description) {
		Toast.makeText(getApplicationContext(), description, Toast.LENGTH_LONG).show();
	}
}
