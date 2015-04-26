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


public class IdentitiesList extends ActionBarActivity {
	private int User_ID;
	private DatabaseContract db;
	private ArrayList<OnlineIdentity> list;
	private ListView activityList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		openDB();

		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_identities_activity);

		activityList = (ListView) findViewById(R.id.identities_list);
	}

	@Override
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
		getMenuInflater().inflate(R.menu.menu_online_ident_list, menu);
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

	private void openDB() {
		User_ID = getSharedPreferences("loginPrefs", MODE_PRIVATE).getInt("ID", -1);
		Log.d("User ID IdentitiesList", "" + User_ID);

		db = new DatabaseContract(this);
		db.open();
	}

	//putting together contact list
	private void buildList() {
		list = new ArrayList<>();
		Cursor cursor = db.getAllOnlineIdentities();
		OnlineIdentity temp = null;

		if (cursor.moveToFirst()) {
			do if(cursor.getInt(4) == User_ID) {

				temp = new OnlineIdentity(cursor.getInt(0));
				//ID, WEBSITE, LOGIN, PASSWORD, HASH
				Log.d( "OnlineIdentity Found: ", cursor.getString(1));
				temp.setWebsite(cursor.getString(1));
				temp.setAccount(cursor.getString(2));
				temp.setPassword(cursor.getString(3));
				list.add(temp);
			}
			while (cursor.moveToNext());
		}
		cursor.close();
	}

	private void setList() {
		ArrayAdapter<OnlineIdentity> arrayAdapter = new ArrayAdapter<>(
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

	private void moveToView(int position) {
		startActivity(
				new Intent(this, ViewIdentity.class).putExtra("OnlineIdentity", list.get(position))
		);
	}

	public void createNewInstance(View v) {
		startActivity(new Intent(this, NewIdentity.class));
	}
}
