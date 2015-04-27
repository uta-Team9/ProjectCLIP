package team9.clip_loginhomecareer;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class ContactList extends ActionBarActivity {

	private int User_ID;
	private DatabaseContract db;
	private ArrayList<Contact> list;
	private ListView activityList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		openDB();

		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_contacts_activity);

		activityList = (ListView) findViewById(R.id.contacts_list);
	}

	@Override
	//close db before activity exit
	protected void onDestroy() {
		db.close();
		super.onDestroy();
	}

	@Override
	protected void onResume() {
		super.onResume();
		buildList();
		setList();
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

	//instantiates the database and recovers User_ID from the shared preference file
	private void openDB() {
		User_ID = getSharedPreferences("loginPrefs", MODE_PRIVATE).getInt("ID", -1);
		Log.d("User ID ContactList", "" + User_ID);

		db = new DatabaseContract(this);
		db.open();
	}

	//putting together contact list, cycle through db
	private void buildList() {
		list = new ArrayList<>();
		Cursor cursor = db.getAllContacts();
		Contact temp = null;

		if (cursor.moveToFirst()) {
			do if(cursor.getInt(6) == User_ID) {

					temp = new Contact(cursor.getInt(0));
					//_ID, name, phone, email, used, met
					Log.d( "Contact Found: ", cursor.getString(1));
					temp.setName(cursor.getString(1));
					temp.setPhone(cursor.getString(2));
					temp.setEmail(cursor.getString(3));
					temp.setUsed(cursor.getInt(4));
					temp.setDateMet(cursor.getString(5));
					list.add(temp);
				}
			while (cursor.moveToNext());
		}
		cursor.close();
	}

	//Add the adapter to the list ui
	private void setList() {
		//Possible to change simple_list_item_1 into our own xml object
		ArrayAdapter<Contact> arrayAdapter = new ArrayAdapter<>(
				this, android.R.layout.simple_list_item_1, list);

		activityList.setAdapter(arrayAdapter);

		activityList.setClickable(true);
		activityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
				moveToView(position);
			}
		});
	}

	/**
	 *
	 * @param position
	 */
	private void moveToView(int position) {
		startActivity(
				new Intent(this, ViewContact.class).putExtra("Contact", list.get(position))
		);
	}

	/**
	 * Move to activity to create the appropriate item when "New" button is pressed
	 * @param v
	 */
	public void createNewInstance(View v) {
		startActivity(new Intent(this, NewContact.class));
	}
}
