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


public class ViewJobSearch extends ActionBarActivity {
	//"ID", "Company", "Status", "Applied", "HashID"
	private DatabaseContract db;
	private int User_ID;
	private JobSearch jobSearch = null;
	private TextView COMPANY;
	private TextView STATUS;
	private TextView DATE;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		openDB();

		super.onCreate(savedInstanceState);
		setContentView(R.layout.display_job_search_activity);

		Bundle extras = getIntent().getExtras();
		if(extras != null)
		{
			jobSearch = (JobSearch)extras.getSerializable("JobSearch");
			Log.d("Job Search received", jobSearch.getCompany());

			setTitle("Job Search");
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
		getMenuInflater().inflate(R.menu.view_job_search_menu, menu);
		return true;
	}

	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Intent intent;

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

	//Added By Edward
	private void openDB() {
		User_ID = getSharedPreferences("loginPrefs", MODE_PRIVATE).getInt("ID", -1);
		Log.d("ID in New JS", "" + User_ID);

		db = new DatabaseContract(this);
		db.open();
	}

	private void setUpTextBoxes() {
		if(jobSearch != null) {
			//initialize boxes using findViewById and setText(goal.get())
			STATUS = (TextView) findViewById(R.id.viewJobSearch_status);
			STATUS.setText(jobSearch.getStatus());
			DATE = (TextView) findViewById(R.id.viewJobSearch_applied);
			DATE.setText(jobSearch.getDateApplied());
			COMPANY = (TextView) findViewById(R.id.viewJobSearch_company);
			COMPANY.setText(jobSearch.getCompany());
		}
	}

	public void editInstance(View v) {
		startActivity(new Intent(this, NewJobSearch.class).putExtra("JobSearch", jobSearch));
	}

	public void deleteInstance(View v) {
		if(db.deleteJobSearch(jobSearch.getDatabaseID())) {
			toast("Job Search removed");
			finish();
		} else
			toast("Job Search already removed");
	}

	private void toast(String description) {
		Toast.makeText(getApplicationContext(), description, Toast.LENGTH_LONG).show();
	}
}
