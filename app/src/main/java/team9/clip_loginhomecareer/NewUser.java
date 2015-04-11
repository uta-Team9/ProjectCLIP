package team9.clip_loginhomecareer;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class NewUser extends ActionBarActivity {
	DatabaseContract myDB;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_login_activity);

		openDB();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_new_user, menu);
		return true;
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		closeDB();
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
		myDB = new DatabaseContract(this);
		myDB.open();
	}
	private void closeDB() {
		myDB.close();
	}

	public boolean saveUser(View v) {
		//myDB.insertLoginRow();
		return true;
	}
}
