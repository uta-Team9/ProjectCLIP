package team9.clip_loginhomecareer;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class EduNewApp extends ActionBarActivity {

    private DatabaseContract db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        openDB();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edu_new_app);

    }

    @Override
    protected void onDestroy() {
        closeDB();
        super.onDestroy();
    }

    public void openDB() {
        db = new DatabaseContract(this);
        db.open();
    }

    public void closeDB() {
        db.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edu_new_app, menu);
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

    public void add_new(View v) {
        EditText text;
        if (validItems()) {
            text = (EditText) findViewById(R.id.app_college_name);
            String inst = text.getText().toString();
            text = (EditText) findViewById(R.id.application_due_date);
            Integer due = Integer.parseInt(text.getText().toString());
            text = (EditText) findViewById(R.id.reply_date);
            Integer reply = Integer.parseInt(text.getText().toString());

            db.insertCollegeApplication(inst, due, reply, 0);
            toastNotification("Application Saved");
            clearData();
        } else {
            toastNotification("Invalid Information");
        }
    }

    private boolean validItems() {
        //TODO: Check for invalid input data
        return true;
    }

    private void toastNotification(String description) {
        Toast.makeText(getApplicationContext(), description, Toast.LENGTH_LONG).show();
    }

    private void clearData() {
        EditText text;
        text = (EditText) findViewById(R.id.app_college_name);
        text.setText("");
        text = (EditText) findViewById(R.id.application_due_date);
        text.setText("");
        text = (EditText) findViewById(R.id.reply_date);
        text.setText("");

    }
}