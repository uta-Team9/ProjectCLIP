package team9.clip_loginhomecareer;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
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

import java.util.ArrayList;
import java.util.Calendar;


public class FinanceLiabilityNew extends ActionBarActivity {
    private int User_ID;
    private DatabaseContract db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.finance_liability_new);
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
        getMenuInflater().inflate(R.menu.menu_finance_liability_new, menu);
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
        int typenum;
        int term;

        if(validItems()) {

            Spinner spn = (Spinner) findViewById(R.id.spn_lending_term);
            typenum = spn.getSelectedItemPosition();
            switch (typenum)
            {
                case 0:
                    term = 12;
                    break;
                case 1:
                    term = 24;
                    break;
                case 2:
                    term = 36;
                    break;
                case 3:
                    term = 48;
                    break;
                case 4:
                    term = 60;
                    break;
                default:
                    term = 0;
                    break;
            }
            String lender = ((EditText) findViewById(R.id.txt_lender)).getText().toString();
            Double liabilityamount = Double.parseDouble(((EditText) findViewById(R.id.txt_liability_amt)).getText().toString());
            Double interestrate = Double.parseDouble(((EditText) findViewById(R.id.txt_interest_rate)).getText().toString());
            String desc = ((EditText) findViewById(R.id.txt_liability_desc)).getText().toString();
            String note = ((EditText) findViewById(R.id.txt_liability_note)).getText().toString();

            // 	{"ID", "YEAR", "MONTH", "DAY", "LENDER_NAME", "AMOUNT", "INTEREST_RATE", "LENDING_TERM", "DESCRIPTION", "NOTE", "USER_ID"};

            //TODO: get date and userID for saving
            db.insertLiability(year, month, day, lender, liabilityamount, interestrate, term, desc, note, User_ID);
            //Log.d("Contact Saved: ", "" + source);
            toastNotification("Liability information saved");
            ((EditText) findViewById(R.id.txt_lender)).setText("");
            ((EditText) findViewById(R.id.txt_liability_amt)).setText("");
            ((EditText) findViewById(R.id.txt_interest_rate)).setText("");
            ((EditText) findViewById(R.id.txt_liability_desc)).setText("");
            ((EditText) findViewById(R.id.txt_liability_note)).setText("");
            //clearData();
        } else {
            toastNotification("Invalid Information");
        }

    }
    public void cancel(View v) {
        Intent intent = null;
        intent = new Intent(this, FinanceLiabilityView.class);
        startActivity(intent);
    }
    private boolean validItems() {
        //TODO: Check for invalid input data
        return true;
    }

    private void toastNotification(String description) {
        Toast.makeText(getApplicationContext(), description, Toast.LENGTH_LONG).show();
    }
    public void calculatePayable(View v) {
        int typenum;
        int term;
        double iniamt = Double.parseDouble(((EditText) findViewById(R.id.txt_liability_amt)).getText().toString());
        double intrate = Double.parseDouble(((EditText) findViewById(R.id.txt_interest_rate)).getText().toString());
        double payable = 0.00;
        double monthly = 0.00;
        if(validItems()) {
                Spinner spn = (Spinner) findViewById(R.id.spn_lending_term);
                typenum = spn.getSelectedItemPosition();
                switch (typenum)
                {
                    case 0:
                        term = 12;
                        break;
                    case 1:
                        term = 24;
                        break;
                    case 2:
                        term = 36;
                        break;
                    case 3:
                        term = 48;
                        break;
                    case 4:
                        term = 60;
                        break;
                    default:
                        term = 0;
                        break;
                }
                double intamt = 0.00;
                for(int i = 0; i < term; i++) {
                    intamt += (iniamt * (intrate / 100));
                }
                payable = iniamt + intamt;
                monthly = Math.round( (payable/(double)term) * 100.0 ) / 100.0;

                //Log.d("Contact Saved: ", "" + source);
                toastNotification("Total amount payable in " + term + " months: " + payable);
                toastNotification("Monthly installment: " + monthly);

            } else {
                toastNotification("Invalid Information");
            }
        }
}
