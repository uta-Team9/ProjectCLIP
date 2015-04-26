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


public class ViewIdentity extends ActionBarActivity {
	private DatabaseContract db;
	private int User_ID;
	private OnlineIdentity identity = null;
	private TextView LOGIN;
	private TextView WEBSITE;
	private TextView PASSWORD;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		openDB();

		super.onCreate(savedInstanceState);
		setContentView(R.layout.display_identity_activity);

		Bundle extras = getIntent().getExtras();
		if(extras != null)
		{
			identity = (OnlineIdentity)extras.getSerializable("OnlineIdentity");
			Log.d("Identity received", identity.getWebsite());

			setTitle("View Identity");
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
		getMenuInflater().inflate(R.menu.view_identity_menu, menu);
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
                setContentView(R.layout.home_career_activity);
                break;
            case(R.id.action_Finance):

                break;
            case(R.id.action_Health):
                intent= new Intent(this, HealthHomePage.class);
                break;
            case(R.id.action_Education):

                break;
        }
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

	//Added By Edward
	private void openDB() {
		User_ID = getSharedPreferences("loginPrefs", MODE_PRIVATE).getInt("ID", -1);
		Log.d("ID in New Identity", "" + User_ID);

		db = new DatabaseContract(this);
		db.open();
	}

	private void setUpTextBoxes() {
		if(identity != null) {
			//initialize boxes using findViewById and setText(goal.get())
			WEBSITE = (TextView) findViewById(R.id.viewIdentity_website);
			WEBSITE.setText(identity.getWebsite());
			LOGIN = (TextView) findViewById(R.id.viewIdentity_login);
			LOGIN.setText(identity.getAccount());
			PASSWORD = (TextView) findViewById(R.id.viewIdentity_password);
			PASSWORD.setText(identity.getPassword());
		}
	}

	public void editInstance(View v) {
		startActivity(new Intent(this, NewIdentity.class).putExtra("OnlineIdentity", identity));
	}

	public void deleteInstance(View v) {
		if(db.deleteOnlineIdentity(identity.getDatabaseID())) {
			toast("Identity removed");
			finish();
		} else
			toast("Identity already removed");
	}

	private void toast(String description) {
		Toast.makeText(getApplicationContext(), description, Toast.LENGTH_LONG).show();
	}
}
