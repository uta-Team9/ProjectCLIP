package team9.clip_loginhomecareer;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;


public class FinanceCreditNew extends ActionBarActivity {
    private int User_ID;
    private DatePicker dp;
    private DatabaseContract db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.finance_credit_new);
        dp = (DatePicker) findViewById(R.id.dp_credit_exp);
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
        getMenuInflater().inflate(R.menu.menu_finance_credit_new, menu);
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
        int year = dp.getYear();
        int month = dp.getMonth();
        int day = dp.getDayOfMonth();
        User_ID = getSharedPreferences("loginPrefs", MODE_PRIVATE).getInt("ID", -1);
        int typenum;
        String type;

        if(validItems()) {

            Spinner spn = (Spinner) findViewById(R.id.card_provider_spinner);
            typenum = spn.getSelectedItemPosition();
            switch (typenum)
            {
                case 1:
                    type = "Visa";
                    break;
                case 2:
                    type = "Master Card";
                    break;
                case 3:
                    type = "Discover";
                    break;
                case 4:
                    type = "American Express";
                    break;
                case 5:
                    type = "Other";
                    break;
                default:
                    type = " ";
                    break;
            }
            text = (EditText) findViewById(R.id.txt_credit_amt);
            Double amount = Double.parseDouble(text.getText().toString());
            String note = ((EditText) findViewById(R.id.txt_credit_note)).getText().toString();

           // {"ID", "PROVIDER", "BALANCE", "YEAR", "MONTH", "DAY", "NOTE", "USER_ID"};
            //TODO: get date and userID for saving
            //db.insertCreditCard(type, amount, year, month, day, note, User_ID);
            //Log.d("Contact Saved: ", "" + source);
            toastNotification("Credit information saved");
            ((EditText) findViewById(R.id.txt_credit_amt)).setText("");
            ((EditText) findViewById(R.id.txt_credit_note)).setText("");
            //clearData();
        } else {
            toastNotification("Invalid Information");
        }

    }
    public void cancel(View v) {
        Intent intent = null;
        intent = new Intent(this, FinanceCreditView.class);
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
