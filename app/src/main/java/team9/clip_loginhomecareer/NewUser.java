package team9.clip_loginhomecareer;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


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
		closeDB();
		super.onDestroy();
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

	public void saveUser(View v) {
		if(tryToSave()) {
			Toast.makeText(getApplicationContext(), "User Saved", Toast.LENGTH_SHORT).show();
		} else {
			Toast.makeText(getApplicationContext(), "User Not Saved", Toast.LENGTH_LONG).show();
		}
	}

	public boolean tryToSave() {
		//save name
		EditText text = (EditText) findViewById(R.id.new_user_name);
		String name = text.getText().toString();

		//save email
		text = (EditText) findViewById(R.id.new_user_email);
		String email = text.getText().toString();
		//return false if, contains :, contains @, is registered
		if(email.contains(":") || /*!email.contains("@") ||*/ isRegistered(email))
			return resetFields(false); //reset email and password

		//save password
		text = (EditText) findViewById(R.id.new_user_password);
		String password = text.getText().toString();
		//cannot contain ":", length must be greater than 4
		if(password.contains(":") || !(password.length() > 0))
			return resetFields(false); //reset email and password

		//save spinner choice
		Spinner choice = (Spinner) findViewById(R.id.new_user_question);
		int question = choice.getSelectedItemPosition();

		//save answer
		text = (EditText) findViewById(R.id.new_user_answer);
		String answer = text.getText().toString();

		//insert new row into DB
		myDB.insertLoginRow(name, email, password, question, answer);
		return resetFields(true); //reset all fields
	}

	public boolean isRegistered(String email) {
		Cursor c = myDB.getAllLoginRows();

		if(c.moveToFirst()) {
			do { //check for name
				if(c.getString(2).equalsIgnoreCase(email)) {
					return true;
				}
			} while(c.moveToNext());
		}
		return false;
	}

	public boolean resetFields(Boolean trigger) {
		EditText text;
		if(trigger) {
			text = (EditText) findViewById(R.id.new_user_name);
			text.setText("");
			text = (EditText) findViewById(R.id.new_user_email);
			text.setText("");
			text = (EditText) findViewById(R.id.new_user_password);
			text.setText("");
			Spinner choice = (Spinner) findViewById(R.id.new_user_question);
			choice.setSelection(0);
			text = (EditText) findViewById(R.id.new_user_answer);
			text.setText("");
			return true;
		}

		text = (EditText) findViewById(R.id.new_user_name);
		text.setText("");
		text = (EditText) findViewById(R.id.new_user_email);
		text.setText("");
		return false;
	}
}
