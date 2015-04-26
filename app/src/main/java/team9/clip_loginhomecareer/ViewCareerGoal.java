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


public class ViewCareerGoal extends ActionBarActivity {
	//"ID", "Description", "EndDate", "TermLength", "HashID"
	private DatabaseContract db;
	private int User_ID;
	private CareerGoal goal = null;
	private TextView DESC;
	private TextView DATE;
	private TextView LENGTH;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		openDB();

		super.onCreate(savedInstanceState);
		setContentView(R.layout.display_career_goal_activity);

		Bundle extras = getIntent().getExtras();
		if(extras != null)
		{
			goal = (CareerGoal)extras.getSerializable("CareerGoal");
			Log.d("Goal received", goal.getDescription());

			setTitle(goal.getTitle());
			setUpTextBoxes();
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
		getMenuInflater().inflate(R.menu.menu_display_future_goal_activity, menu);
		return true;
	}

	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

	//Added By Edward
	private void openDB() {
		User_ID = getSharedPreferences("loginPrefs", MODE_PRIVATE).getInt("ID", -1);
		Log.d("ID in New Goal", "" + User_ID);

		db = new DatabaseContract(this);
		db.open();
	}

	private void setUpTextBoxes() {
		if(goal != null) {
			//initialize boxes using findViewById and setText(goal.get())
			DESC = (TextView) findViewById(R.id.viewCareerGoal_desc);
			DESC.setText(goal.getDescription());
			DATE = (TextView) findViewById(R.id.viewCareerGoal_date);
			DATE.setText(goal.getDate());
			LENGTH = (TextView) findViewById(R.id.viewCareerGoal_length);
			if(goal.isLongTerm())
				LENGTH.setText("Long Term");
			else LENGTH.setText("Short Term");
		}
	}

	public void editInstance(View v) {
		startActivity(new Intent(this, NewCareerGoal.class).putExtra("CareerGoal", goal));
	}

	public void deleteInstance(View v) {
		if(db.deleteCareerGoal(goal.getDatabaseID())) {
			toast("Goal removed");
			finish();
		} else
			toast("Goal already removed");
	}

	private void toast(String description) {
		Toast.makeText(getApplicationContext(), description, Toast.LENGTH_LONG).show();
	}
}
