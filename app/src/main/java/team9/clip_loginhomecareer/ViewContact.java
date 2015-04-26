package team9.clip_loginhomecareer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class ViewContact extends ActionBarActivity {
	private int User_ID;
	private Contact contact = null;
	private DatabaseContract db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		openDB();

		super.onCreate(savedInstanceState);
		setContentView(R.layout.display_contact_activity);

		Bundle extras = getIntent().getExtras();
		if(extras != null)
		{
			db = new DatabaseContract(this);
			db.open();
			contact = (Contact)extras.getSerializable("Contact");
			Log.d("Contact received", contact.getEmail());

			setTitle(contact.getName());
			setUpTextBoxes();
		}

		final TextView emailAddress = (TextView)findViewById(R.id.viewContact_email);
		emailAddress.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View arg0) {
				String em = emailAddress.getText().toString();
				Intent i = new Intent(Intent.ACTION_SEND);
				i.setType("message/rfc822");
				i.putExtra(Intent.EXTRA_EMAIL, new String[] {em});
				try {
					startActivity(Intent.createChooser(i, "Send mail..."));
				} catch(android.content.ActivityNotFoundException ex) {
					toast("No Email Client Found");
				}
			}
		});
	}

	@Override
	protected void onDestroy() {
		db.close();
		super.onDestroy();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display_contact_menu, menu);
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

	//Added By Edward
	private void openDB() {
		db = new DatabaseContract(this);
		db.open();
		User_ID = getSharedPreferences("loginPrefs", MODE_PRIVATE).getInt("ID", -1);
		Log.d("ID in ViewContact", "" + User_ID);
	}

	private void setUpTextBoxes() {
		if(contact != null) {
			TextView text = (TextView) findViewById(R.id.viewContact_met);
			text.setText(contact.getDateMet());
			text = (TextView) findViewById(R.id.viewContact_email);
			text.setText(contact.getEmail());
			text = (TextView) findViewById(R.id.viewContact_used);
			text.setText("" + contact.getUsed());
			text = (TextView) findViewById(R.id.viewContact_phone);
			text.setText(contact.getPhone());
		}
	}

	public void editInstance(View v) {
		startActivity(new Intent(this, NewContact.class).putExtra("Contact", contact));
	}

	public void deleteInstance(View v) {
		if(db.deleteContact(contact.getDatabaseID())) {
			toast("contact removed");
			finish();
		} else
			toast("Contact already removed");
	}

	private void toast(String description) {
		Toast.makeText(getApplicationContext(), description, Toast.LENGTH_LONG).show();
	}
}
