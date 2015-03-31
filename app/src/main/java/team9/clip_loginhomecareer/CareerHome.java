package team9.clip_loginhomecareer;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class CareerHome extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.career_home_activity);

		TextView registerButton = (TextView) findViewById(R.id.view_contacts_button);
		registerButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				goToContactList();
			}
		});
	}

	public void goToContactList() {
		setContentView(R.layout.contact_list_activity);
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
		Intent intent;

		//noinspection SimplifiableIfStatement
		switch(id) {
			case(R.id.action_settings):
				intent = new Intent(this, Settings.class);
				startActivity(intent);
			break;
			case(R.id.action_Career):
				setContentView(R.layout.career_home_activity);
			break;
			case(R.id.action_Finance):

				break;
			case(R.id.action_Health):

				break;
			case(R.id.action_Education):

				break;
		}
		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	public void onDestroy() {
		//save DB, log out user

		super.onDestroy();

		//go back to Login Screen
	}
}
