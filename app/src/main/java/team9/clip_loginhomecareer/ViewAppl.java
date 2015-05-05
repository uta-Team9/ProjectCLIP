package team9.clip_loginhomecareer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class ViewAppl extends ActionBarActivity {
    private int User_ID;
    private EduApp CollApp = null;
    private DatabaseContract db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        openDB();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_appl);
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            db = new DatabaseContract(this);
            db.open();
            CollApp = (EduApp) extras.getSerializable("Application");

            setTitle(CollApp.getCollege());
            setUpTextBoxes();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_appl, menu);
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
    private void openDB() {
        db = new DatabaseContract(this);
        db.open();
        User_ID = getSharedPreferences("loginPrefs", MODE_PRIVATE).getInt("ID", -1);
        Log.d("ID in ViewAppl", "" + User_ID);
    }
    private void setUpTextBoxes() {
        if(CollApp != null) {
            TextView text = (TextView) findViewById(R.id.edu_detail_collApp);
            text.setText("" + CollApp.getCollege());
            text = (EditText) findViewById(R.id.edu_appl_reply);
            text.setText("" + CollApp.getReply_expected());
            text = (EditText) findViewById(R.id.application_due_date);
            text.setText("" + CollApp.getDeadline());

        }
    }
    public void editInstance(View v) {
        startActivity(new Intent(this, EduNewApp.class).putExtra("College Application", CollApp));
    }
    private void toast(String description) {
        Toast.makeText(getApplicationContext(), description, Toast.LENGTH_LONG).show();
    }
    public void deleteInstance(View v) {
        if(db.deleteCollege(CollApp.getDbRow())) {
            toast("Application removed");
            finish();
        } else
            toast("Application already removed");
    }
}
