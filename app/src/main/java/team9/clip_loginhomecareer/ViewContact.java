package team9.clip_loginhomecareer;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class ViewContact extends ActionBarActivity {
	private int User_ID;
	private Contact contact = null;
	private DatabaseContract db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		db = new DatabaseContract(this);
		db.open();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.display_contact_activity);

		Bundle extras = getIntent().getExtras();
		if(extras != null)
		{
			db = new DatabaseContract(this);
			db.open();
			User_ID = extras.getInt("ID");
			contact = (Contact)getIntent().getSerializableExtra("Contact");
			Log.d("User ID ", "" + User_ID);
			Log.d("Contact passed ", contact.getEmail());

			setTitle(contact.getName());
			setUpTextBoxes();
		}


		Button edit = (Button) findViewById(R.id.edit_button);
		edit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				editInstance(v);
			}
		});
		Button delete = (Button) findViewById(R.id.delete_button);
		delete.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				deleteInstance(v);
			}
		});

		final TextView phoneNumber = (TextView)findViewById(R.id.viewContact_phone);
		phoneNumber.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View arg0) {
				String call = phoneNumber.getText().toString().replaceAll("-","");
				Intent callIntent = new Intent(Intent.ACTION_CALL);
				callIntent.setData(Uri.parse("tel:"+call));
				contact.incrementUsed();
				startActivity(callIntent);
			}
		});

		final TextView emailAddress = (TextView)findViewById(R.id.viewContact_email);
		emailAddress.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View arg0) {
				String em = emailAddress.getText().toString();
				Intent i = new Intent(Intent.ACTION_SEND);
				i.setType("message/rfc822");
				i.putExtra(Intent.EXTRA_EMAIL, em);
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
		super.onDestroy();
		db.close();
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

	private void setUpTextBoxes() {
		if(contact != null) {
			TextView text = (TextView) findViewById(R.id.viewContact_met);
			text.setText("" + contact.getMet());
			text = (TextView) findViewById(R.id.viewContact_email);
			text.setText(contact.getEmail());
			text = (TextView) findViewById(R.id.viewContact_used);
			text.setText("" + contact.getUsed());
			text = (TextView) findViewById(R.id.viewContact_phone);
			text.setText("" + contact.getPhone());
		}
	}

	//Added By Edward
	public void editInstance(View v) {
		Intent intent = new Intent(this, NewContact.class);
		intent.putExtra("ID", User_ID);
		intent.putExtra("Contact", contact);
		startActivity(intent);
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
