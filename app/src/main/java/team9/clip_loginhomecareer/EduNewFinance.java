package team9.clip_loginhomecareer;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class EduNewFinance extends ActionBarActivity {
    int amount;
    String period;
    String awardName;
    String condition;
    private DatabaseContract db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        openDB();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edu_new_finance);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edu_new_finance, menu);
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
            text = (EditText) findViewById(R.id.edu_awardName);
            awardName = text.getText().toString();
            text = (EditText) findViewById(R.id.edu_aid_amount);
            amount = Integer.parseInt(text.getText().toString());
            text = (EditText) findViewById(R.id.edu_awardPeriod);
            period = text.getText().toString();
            text = (EditText) findViewById(R.id.edu_financeCondition);
            condition = text.getText().toString();

            db.insertCollegeFinance(awardName,amount,period,condition,0);
            toastNotification("Application Saved");
            clearData();
        } else {
            toastNotification("Invalid Information");
        }
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
    private boolean validItems() {
        //TODO: Check for invalid input data
        return true;
    }

    private void toastNotification(String description) {
        Toast.makeText(getApplicationContext(), description, Toast.LENGTH_LONG).show();
    }

    private void clearData() {
        EditText text;
        text = (EditText) findViewById(R.id.edu_financeCondition);
        text.setText("");
        text = (EditText) findViewById(R.id.edu_awardPeriod);
        text.setText("");
        text = (EditText) findViewById(R.id.edu_awardName);
        text.setText("");
        text = (EditText) findViewById(R.id.edu_aid_amount);
        text.setText("");

    }
}
