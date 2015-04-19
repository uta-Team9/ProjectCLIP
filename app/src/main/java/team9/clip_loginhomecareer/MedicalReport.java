package team9.clip_loginhomecareer;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class MedicalReport extends ActionBarActivity {

    private DatabaseContract db;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        openDB();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_report);
    }

    @Override
    protected void onDestroy() {
        closeDB();
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
        getMenuInflater().inflate(R.menu.menu_medical_report, menu);
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

    public void add_new(View v) {/*
        EditText text;
        if (validItems()) {
            text = (EditText) findViewById(R.id.add_LDL_cholesterol);
            String inst = text.getText().toString();
            text = (EditText) findViewById(R.id.editText5);
            String loc = text.getText().toString();
            text = (EditText) findViewById(R.id.editText6);
            String ds = text.getText().toString();
            text = (EditText) findViewById(R.id.editText7);
            String major = text.getText().toString();
            text = (EditText) findViewById(R.id.editText8);
            Integer grad = Integer.parseInt(text.getText().toString());
            text = (EditText) findViewById(R.id.editText9);
            String matr = Integer.parseInt(text.getText().toString());

            db.insertColleges(inst, loc, major, ds, matr, grad, 0);
            toastNotification("Degree Saved");
            clearData();
        } else {
            toastNotification("Invalid Information");
        }*/
    }
    private boolean validItems() {
        //TODO: Check for invalid input data
        return true;
    }
    private void toastNotification(String description) {
        Toast.makeText(getApplicationContext(), description, Toast.LENGTH_LONG).show();
    }
    private void clearData()
    {
        EditText text;
        text = (EditText) findViewById(R.id.add_LDL_cholesterol);
        text.setText("");
        text = (EditText) findViewById(R.id.new_college_degree_sought);
        text.setText("");
        text = (EditText) findViewById(R.id.new_college_location);
        text.setText("");
        text = (EditText) findViewById(R.id.new_college_start_date);
        text.setText("");
        text = (EditText) findViewById(R.id.new_college_grad_date);
        text.setText("");
        text = (EditText) findViewById(R.id.new_college_field_of_study);
        text.setText("");
    }
}
