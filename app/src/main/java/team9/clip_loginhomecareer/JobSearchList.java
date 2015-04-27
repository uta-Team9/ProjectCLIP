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


public class JobSearchList extends ActionBarActivity {
	private int User_ID;
	private DatabaseContract db;
	private ArrayList<JobSearch> list;
	private ListView activityList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		openDB();

		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_jobs_activity);

		activityList = (ListView) findViewById(R.id.job_search_list);
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
		getMenuInflater().inflate(R.menu.menu_job_list, menu);
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
		Log.d("User ID ContactList", "" + User_ID);

		db = new DatabaseContract(this);
		db.open();
	}

	//putting together contact list
	private void buildList() {
		list = new ArrayList<>();
		Cursor cursor = db.getAllJobSearches();
		JobSearch temp = null;

		if (cursor.moveToFirst()) {
			do if(cursor.getInt(4) == User_ID) {

				temp = new JobSearch(cursor.getInt(0));
				//"ID", "Company", "Status", "Applied", "HashID"
				Log.d( "JobSearch Found", cursor.getString(1));
				temp.setCompany(cursor.getString(1));
				temp.setStatus(cursor.getString(2));
				temp.setDateApplied(cursor.getString(3));
				list.add(temp);
			}
			while (cursor.moveToNext());
		}
		cursor.close();
	}

	private void setList() {
		ArrayAdapter<JobSearch> arrayAdapter = new ArrayAdapter<>(
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
				new Intent(this, ViewJobSearch.class).putExtra("JobSearch", list.get(position))
		);
	}

	public void createNewInstance(View v) {
		startActivity(new Intent(this, NewJobSearch.class));
	}
}
