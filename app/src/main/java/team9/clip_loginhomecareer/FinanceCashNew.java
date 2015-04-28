package team9.clip_loginhomecareer;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import java.util.Calendar;


public class FinanceCashNew extends ActionBarActivity {
    private int User_ID;
    private DatabaseContract db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.finance_cash_new);
        db = new DatabaseContract(this);
        db.open();
    }

    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_finances_cash_new, menu);
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
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        User_ID = getSharedPreferences("loginPrefs", MODE_PRIVATE).getInt("ID", -1);

        if(validItems()) {
            text = (EditText) findViewById(R.id.txt_cash_source);
            String source = text.getText().toString();
            text = (EditText) findViewById(R.id.txt_cash_amt);
            Double amount = Double.parseDouble(text.getText().toString());
            text = (EditText) findViewById(R.id.txt_cash_note);
            String note = text.getText().toString();

            //TODO: get date and userID for saving
            db.insertCash(amount, source, note, year, month, day, User_ID);
            //Log.d("Contact Saved: ", "" + source);
            toastNotification("Cash amount saved");
            ((EditText) findViewById(R.id.txt_cash_source)).setText("");
            ((EditText) findViewById(R.id.txt_cash_amt)).setText("");
            ((EditText) findViewById(R.id.txt_cash_amt)).setText("");
            //clearData();
        } else {
            toastNotification("Invalid Information");
        }

    }
    public void cancel(View v) {
        Intent intent = null;
        intent = new Intent(this, FinanceCashView.class);
        startActivity(intent);
    }


    private boolean validItems() {
    //TODO: Check for invalid input data
    return true;
}

    private void toastNotification(String description) {
        Toast.makeText(getApplicationContext(), description, Toast.LENGTH_LONG).show();
    }
}
