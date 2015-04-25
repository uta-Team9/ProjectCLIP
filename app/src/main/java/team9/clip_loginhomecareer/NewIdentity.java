package team9.clip_loginhomecareer;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class NewIdentity extends ActionBarActivity {
	private DatabaseContract db;
	private int User_ID;
	private OnlineIdentity OI = null;
	private EditText TEXT_WEB;
	private EditText TEXT_LOGIN;
	private EditText TEXT_PASSWORD;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		openDB();

		Bundle extras = getIntent().getExtras();
		if(extras != null) {
			OI = (OnlineIdentity)extras.getSerializable("OnlineIdentity");
		}

		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_identity_activity);

		TEXT_WEB = (EditText) findViewById(R.id.newIdent_website);
		TEXT_LOGIN = (EditText) findViewById(R.id.newIdent_login);
		TEXT_PASSWORD = (EditText) findViewById(R.id.newIdent_password);
	}

	@Override
	protected void onDestroy() {
		db.close();
		super.onDestroy();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_identity_menu, menu);
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

	//ADDED METHODS
	private void openDB() {
		User_ID = getSharedPreferences("loginPrefs", MODE_PRIVATE).getInt("ID", -1);
		Log.d("ID in New Ident", "" + User_ID);

		db = new DatabaseContract(this);
		db.open();
	}

	public void add_new(View v) {
		if(OI != null) {
			updateItems();
		}else
			saveItems();
	}
	private void populateItems() {
		TEXT_WEB.setText(OI.getWebsite());
		TEXT_LOGIN.setText(OI.getAccount());
		TEXT_PASSWORD.setText(OI.getPassword());
	}

	private void updateItems() {
		String website = TEXT_WEB.getText().toString();
		String login = TEXT_LOGIN.getText().toString();
		String password = TEXT_PASSWORD.getText().toString();

		db.updateOnlineIdentity(OI.getDatabaseID(), website, login, password);
		Log.d("Identity Saved: ", website + " " + login);
		toastNotification("Identity Updated");
		clearData();
		finish();
	}

	private void saveItems() {
		if(validItems()) {
			String website = TEXT_WEB.getText().toString();
			String login = TEXT_LOGIN.getText().toString();
			String password = TEXT_PASSWORD.getText().toString();

			db.insertOnlineIdentity(website, login, password, User_ID);
			Log.d("Identity Saved: ", website + " " + login);
			toastNotification("Identity Saved");
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
		TEXT_WEB.setText("");
		TEXT_LOGIN.setText("");
		TEXT_PASSWORD.setText("");
	}

	private void toastNotification(String description) {
		Toast.makeText(getApplicationContext(), description, Toast.LENGTH_LONG).show();
	}
}
