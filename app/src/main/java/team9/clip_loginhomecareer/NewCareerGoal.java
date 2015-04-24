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
import android.widget.RadioButton;
import android.widget.Toast;


public class NewCareerGoal extends ActionBarActivity {
	//"ID", "Description", "EndDate", "TermLength", "HashID"
	private DatabaseContract db;
	private int User_ID;
	private CareerGoal goal = null;
	private EditText TEXT_TITLE;
	private EditText TEXT_DESC;
	private RadioButton RADIO_SELECTED_TERM;
	private DatePicker DATE_PICKER;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		openDB();

		Bundle extras = getIntent().getExtras();
		if(extras != null) {
			goal = (CareerGoal)extras.getSerializable("CareerGoal");
		}

		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_career_goal_activity);

		//setup UI objects for use
		TEXT_TITLE = (EditText) findViewById(R.id.new_CG_title);
		TEXT_DESC = (EditText) findViewById(R.id.new_CG_description);
		RADIO_SELECTED_TERM = (RadioButton) findViewById(R.id.new_CG_radio_is_short_term);
		DATE_PICKER = (DatePicker) findViewById(R.id.new_CG_date);

		if(goal != null) {
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
		getMenuInflater().inflate(R.menu.new_future_goal_menu, menu);
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
		Log.d("ID in New Contact", "" + User_ID);

		db = new DatabaseContract(this);
		db.open();
	}

	public void add_new(View v) {
		if(goal != null) {
			updateItems();
		}else
			saveItems();
	}

	public void swapRadioButtons(View v) {
		switch(v.getId()) {
			case(R.id.new_CG_radio_is_short_term):
				findViewById(R.id.new_CG_radio_is_long_term).setSelected(false);
				break;
			case(R.id.new_CG_radio_is_long_term):
				findViewById(R.id.new_CG_radio_is_short_term).setSelected(false);
				break;
		}
	}

	private void populateItems() {
		TEXT_TITLE.setText(goal.getTitle());
		TEXT_DESC.setText(goal.getDescription());
	}

	private void updateItems() {
		String title = TEXT_TITLE.getText().toString();
		String desc = TEXT_DESC.getText().toString();
		String endDate =
				DATE_PICKER.getYear() + "-" + DATE_PICKER.getMonth() + "-" + DATE_PICKER.getDayOfMonth();
		boolean term = RADIO_SELECTED_TERM.isChecked();

		db.updateCareerGoal(goal.getDatabaseID(), title, desc, endDate, term);
		Log.d("Goal Saved: ", "" + title);
		toastNotification("Goal Updated");
		clearData();
		finish();
	}

	private void saveItems() {
		if(validItems()) {
			String title = TEXT_TITLE.getText().toString();
			String desc = TEXT_DESC.getText().toString();
			String endDate =
					DATE_PICKER.getYear() + "-" + DATE_PICKER.getMonth() + "-" + DATE_PICKER.getDayOfMonth();
			boolean term = RADIO_SELECTED_TERM.isChecked();

			db.insertCareerGoal(title, desc, endDate, term, User_ID);
			Log.d("Goal Saved: ", "" + title);
			toastNotification("Goal Saved");
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
		TEXT_TITLE.setText("");
		TEXT_DESC.setText("");
	}

	private void toastNotification(String description) {
		Toast.makeText(getApplicationContext(), description, Toast.LENGTH_LONG).show();
	}
}
