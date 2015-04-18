package team9.clip_loginhomecareer;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;


public class ContactList extends ActionBarActivity {

	private int User_ID = 0;
	private DatabaseContract db;
	private ArrayList<Contact> list = new ArrayList<>();
	private ListView activityList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		openDB();
		Bundle extras = getIntent().getExtras();
		if(extras != null) {
			User_ID = extras.getInt("ID");
			Log.d("User ID: ", "" + User_ID);
		}
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_contacts_activity);

		activityList = (ListView) findViewById(R.id.contacts_list);

		Button edit = (Button) findViewById(R.id.new_instance_button);
		edit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				createNewInstance(v);
			}
		});

		buildList();

		/*ArrayList<String> temp = new ArrayList<>();
		for(Contact c : list) {
			temp.add(c.toString());
		}*/

		ArrayAdapter<Contact> arrayAdapter = new ArrayAdapter<Contact>(
				this, android.R.layout.simple_list_item_1, list);

		activityList.setAdapter(arrayAdapter);
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
		getMenuInflater().inflate(R.menu.menu_contact_list, menu);
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

	public void createNewInstance(View v) {
		Intent intent = new Intent(this, NewContact.class);
		intent.putExtra("ID", User_ID);
		startActivity(intent);
	}

	//putting together contact list
	private void buildList() {
		Cursor cursor = db.getAllContacts();
		Contact temp = null;
		if (cursor.moveToFirst()) {
			do {
				if(cursor.getInt(6) == User_ID) {
					temp = new Contact();
					//_ID, name, phone, email, used, met
					Log.d( "Contact Saved: ", cursor.getString(1) );
					temp.setName(cursor.getString(1));
					temp.setPhone(cursor.getInt(2));
					temp.setEmail(cursor.getString(3));
					temp.setUsed(cursor.getInt(4));
					temp.setUsed(cursor.getInt(5));
					list.add(temp);
				}
			} while (cursor.moveToNext());
		}
		cursor.close();

	}
}
