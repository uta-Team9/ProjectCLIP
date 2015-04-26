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
import android.widget.Toast;


public class NewJobSearch extends ActionBarActivity {
	//"ID", "Company", "Status", "Applied", "HashID"
	private DatabaseContract db;
	private int User_ID;
	private JobSearch jobSearch = null;
	private EditText COMPANY;
	private EditText STATUS;
	private DatePicker DATE_PICKER;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		openDB();

		Bundle extras = getIntent().getExtras();
		if(extras != null) {
			jobSearch = (JobSearch)extras.getSerializable("JobSearch");
		}

		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_job_search_activity);

		//setup UI objects for use
		COMPANY = (EditText) findViewById(R.id.new_JS_company);
		STATUS = (EditText) findViewById(R.id.new_JS_status);
		DATE_PICKER = (DatePicker) findViewById(R.id.new_JS_applied_date);

		if(jobSearch != null) {
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
		getMenuInflater().inflate(R.menu.new_job_search_menu, menu);
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
		Log.d("ID in JS", "" + User_ID);

		db = new DatabaseContract(this);
		db.open();
	}

	public void add_new(View v) {
		if(jobSearch != null) {
			updateItems();
		}else
			saveItems();
	}

	private void populateItems() {
		COMPANY.setText(jobSearch.getCompany());
		STATUS.setText(jobSearch.getStatus());
	}

	private void updateItems() {
		String company = COMPANY.getText().toString();
		String status = STATUS.getText().toString();
		String dateApplied =
				DATE_PICKER.getYear() + "-" + DATE_PICKER.getMonth() + "-" + DATE_PICKER.getDayOfMonth();

		db.updateJobSearch(jobSearch.getDatabaseID(), company, status, dateApplied);
		Log.d("JobSearch Saved", "" + company);
		toastNotification("Job Search Updated");
		clearData();
		finish();
	}

	private void saveItems() {
		if(validItems()) {
			String company = COMPANY.getText().toString();
			String status = STATUS.getText().toString();
			String dateApplied =
					DATE_PICKER.getYear() + "-" + DATE_PICKER.getMonth() + "-" + DATE_PICKER.getDayOfMonth();

			db.insertJobSearch(company, status, dateApplied, User_ID);
			Log.d("JobSearch Saved", "" + company);
			toastNotification("Job Search Saved");
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
		COMPANY.setText("");
		STATUS.setText("");
	}

	private void toastNotification(String description) {
		Toast.makeText(getApplicationContext(), description, Toast.LENGTH_LONG).show();
	}
}
