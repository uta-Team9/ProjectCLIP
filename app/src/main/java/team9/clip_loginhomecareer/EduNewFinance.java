package team9.clip_loginhomecareer;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class EduNewFinance extends ActionBarActivity {
    double amount =0;
    String period = "";
    String awardName = "";
    String condition =   "";
    private EduFinanceItem eduMoney = null;
    private DatabaseContract db;
    private int User_ID;
    private EditText TEXTawardName;
    private EditText TEXTawardPeriod;
    private EditText TEXTawardConditions;
    private EditText TEXTawardAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        openDB();
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            eduMoney = (EduFinanceItem)extras.getSerializable("Education Finance");
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edu_new_finance);
        TEXTawardName = (EditText) findViewById(R.id.edu_awardName);
        TEXTawardAmount = (EditText) findViewById(R.id.edu_aid_amount);
        TEXTawardPeriod = (EditText) findViewById(R.id.edu_awardPeriod);
        TEXTawardConditions = (EditText) findViewById(R.id.edu_financeCondition);

        if(eduMoney != null) {
            Button b = (Button)findViewById(R.id.add);
            b.setText("Update");
            populateItems();
        }
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
    public void saveItems() {
        EditText text;
        if (true) {
            text = (EditText) findViewById(R.id.edu_awardName);
            awardName = text.getText().toString();
            text = (EditText) findViewById(R.id.edu_aid_amount);
            amount = Double.parseDouble(text.getText().toString());
            text = (EditText) findViewById(R.id.edu_awardPeriod);
            period = text.getText().toString();
            text = (EditText) findViewById(R.id.edu_financeCondition);
            condition = text.getText().toString();

            if((!awardName.equals("")) || (amount != 0)){
                db.insertCollegeFinance(awardName,amount,period,condition,0);

            }else{
                toastNotification("Invalid Information");

            }

            db.insertCollegeFinance(awardName, amount, period, condition, User_ID);
            toastNotification("Application Saved");
            clearData();
        } else {
            toastNotification("Invalid Information");
        }
        finish();
    }

    public void add_new(View v) {
        if (eduMoney != null) {
            updateItems();
        } else
            saveItems();
    }
    private void updateItems() {
        EditText text;

        text = (EditText) findViewById(R.id.edu_awardName);
        awardName = text.getText().toString();
        text = (EditText) findViewById(R.id.edu_aid_amount);
        amount = Double.parseDouble(text.getText().toString());
        text = (EditText) findViewById(R.id.edu_awardPeriod);
        period = text.getText().toString();
        text = (EditText) findViewById(R.id.edu_financeCondition);
        condition = text.getText().toString();

        db.updateCollegeFinance(eduMoney.getDatabaseID(),awardName,amount,period,condition);
        toastNotification("Funds Source Updated");
        clearData();
        finish();
    }
    @Override
    protected void onDestroy() {
        closeDB();
        super.onDestroy();
    }

    public void openDB() {
        db = new DatabaseContract(this);
        db.open();
        User_ID = getSharedPreferences("loginPrefs", MODE_PRIVATE).getInt("ID", -1);
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
    private void populateItems() {
        TEXTawardName.setText("" + eduMoney.getAwardName());
        TEXTawardAmount.setText("" + eduMoney.getAmount());
        TEXTawardPeriod.setText("" + eduMoney.getPeriod());
        TEXTawardConditions.setText("" + eduMoney.getCondition());
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
