package team9.clip_loginhomecareer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


public class CareerHome extends ActionBarActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_career_activity);

	}

	public void moveViews(View v) {
		Intent intent = null;
		switch(v.getId()) {
			case(R.id.view_contacts_button):
				intent = new Intent(this, ContactList.class);
				break;
			case(R.id.view_jobs_button):
				intent = new Intent(this, JobSearchList.class);
				break;
			case(R.id.view_goals_button):
				intent = new Intent(this, CareerGoalsList.class);
				break;
			case(R.id.view_online_identities_button):
				intent = new Intent(this, IdentitiesList.class);
				break;
			case(R.id.view_career_companies_button):
				Toast.makeText(getApplicationContext(), "Coming soon!", Toast.LENGTH_LONG).show();
				break;
		}

		if(intent != null) {
			startActivity(intent);
		}
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_career_home, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		Intent intent = null;

		//noinspection SimplifiableIfStatement
		switch(id) {
			case(R.id.action_settings):
				intent = new Intent(this, Settings.class);
				startActivity(intent);
				break;
			case(R.id.action_Career):
				setContentView(R.layout.home_career_activity);
				break;
			case(R.id.action_Finance):
				intent= new Intent(this, FinanceHome.class);
				break;
			case(R.id.action_Health):
                intent= new Intent(this, HealthHomePage.class);
				break;
			case(R.id.action_Education):
				intent= new Intent(this, EduMain.class);
				break;
		}
		if(intent != null)
			startActivity(intent);

		return super.onOptionsItemSelected(item);
	}

	public void onDestroy() {
		//save DB, log out user

		super.onDestroy();

		//go back to Login Screen
	}
}
