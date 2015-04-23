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
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;


public class CareerGoalsList extends ActionBarActivity {
	private int User_ID;
	private DatabaseContract db;
	private ArrayList<CareerGoal> list = new ArrayList<>();
	private ListView activityList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		db = new DatabaseContract(this);
		db.open();

		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_career_goals_activity);

		Bundle bundle = getIntent().getExtras();
		if(bundle != null) {
			User_ID = bundle.getInt("ID");
			Log.d("Goal Received ID", ""+User_ID);
		}

		activityList = (ListView) findViewById(R.id.career_goals_list);

		Button edit = (Button) findViewById(R.id.new_instance_button);
		edit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				createNewInstance(v);
			}
		});

		buildList();
		makeList();
	}

	protected void onDestroy() {
		db.close();
		super.onDestroy();
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.future_goals_menu, menu);
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
		Intent intent = new Intent(this, NewCareerGoal.class);
		startActivity(intent);
	}

	//putting together contact list
	private void buildList() {
		Cursor cursor = db.getAllCareerGoals();
		CareerGoal temp = null;
		if (cursor.moveToFirst()) {
			do {
				if(cursor.getInt(6) == User_ID) {
					temp = new CareerGoal(cursor.getInt(0));
					//"ID", "Description", "EndDate", "TermLength", "HashID"
					Log.d( "Contact Found: ", cursor.getString(1));
					temp.setDescription(cursor.getString(1));
					temp.setDate(cursor.getString(2));
					if(cursor.getInt(3) == 0)
						temp.setLongTerm(true);
					else
						temp.setLongTerm(false);
					list.add(temp);
				}
			} while (cursor.moveToNext());
		}
		cursor.close();
	}

	private void makeList() {
		ArrayAdapter<CareerGoal> arrayAdapter = new ArrayAdapter<>(
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
		Intent intent = new Intent(this, ViewCareerGoal.class);
		intent.putExtra("Object", list.get(position));
		intent.putExtra("ID", User_ID);
		startActivity(intent);
	}
}