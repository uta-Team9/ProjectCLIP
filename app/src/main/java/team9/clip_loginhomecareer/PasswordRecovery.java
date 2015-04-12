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


public class PasswordRecovery extends ActionBarActivity {

	private DatabaseContract db;
	private int passwordTries = 0;
	private int userID = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.password_recovery_activity);
		dbSet(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_password_recovery, menu);
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

	@Override
	public void onDestroy() {
		super.onDestroy();
		dbSet(false);
	}

	private void dbSet(boolean b) {
		if(b) {
			db = new DatabaseContract(this);
			db.open();
		} else
			db.close();
	}

	public void recoverPassword(View v) {
		String showText = "";
		Cursor c = db.getAllLoginRows();
		//max of 3;
		if(passwordTries==3) {
			Toast.makeText(getApplicationContext(), "Too many attempts", Toast.LENGTH_LONG).show();
			return;
		};

		if(userFound(c)) {

			Spinner secQ = (Spinner) findViewById(R.id.recovery_question);
			EditText secAns = (EditText) findViewById(R.id.recovery_answer);
			boolean question = (c.getInt(4) == secQ.getSelectedItemPosition());
			boolean answer = c.getString(5).equals(secAns.getText().toString());

			//if question and answer are right, show password
			if(question && answer) {
				showText = "The password is: " + c.getString(3);
				Toast.makeText(getApplicationContext(), showText, Toast.LENGTH_LONG).show();
			//if only question is wrong, don't increment counter
			} else if(answer) {
				Toast.makeText(getApplicationContext(), "Incorrect", Toast.LENGTH_LONG).show();
			//if Answer is wrong, increment counter
			} else {
				passwordTries++;
				showText = "Incorrect Answer: " + (3 - passwordTries) + " remaining tries.";
				Toast.makeText(getApplicationContext(), showText, Toast.LENGTH_LONG).show();
			}
		} else {
			showText = "Email Not Found";
			Toast.makeText(getApplicationContext(), showText, Toast.LENGTH_SHORT).show();
		}

		c.close();
	}

	private boolean userFound(Cursor c) {
		EditText email = (EditText) findViewById(R.id.recovery_email);

		//locate user
		if(c.moveToFirst()) {
			do { //check for email
				if(c.getString(2).equalsIgnoreCase(email.getText().toString())) {
					userID = c.getPosition();
					return true;
				}
			} while(c.moveToNext());
		}

		return false;
	}
}
