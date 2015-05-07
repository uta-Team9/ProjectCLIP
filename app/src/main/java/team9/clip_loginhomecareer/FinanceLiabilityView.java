package team9.clip_loginhomecareer;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class FinanceLiabilityView extends ActionBarActivity {
    private int User_ID;
    private DatabaseContract db;
    private ListView onScreenList;
    private ArrayList<String> list = new ArrayList<>();
    private TextView txtbalance;
    private double totalLiabilityBalance = 0.00;

    public double getTotalLiabilityBalance() {
        return totalLiabilityBalance;
    }

    public void setTotalLiabilityBalance(double totalLiabilityBalance) {
        this.totalLiabilityBalance += totalLiabilityBalance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        db = new DatabaseContract(this);
        db.open();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.finance_liability_view);
        txtbalance = (TextView)findViewById(R.id.txt_total_liability);
        buildList();
        onScreenList = (ListView) findViewById(R.id.lst_liability);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, list);
        onScreenList.setAdapter(arrayAdapter);
    }

    protected void onDestroy() {
        db.close();
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_finance_liability_view, menu);
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
    public void createNewInstance(View view) {
        Intent intent = null;
        intent = new Intent(this, FinanceLiabilityNew.class);
        startActivity(intent);
    }
    public void buildList(){
        Cursor c = db.getAllLiabilities();
        int month, day, year, term;
        double liabilityamount = 0.00;
        double intrate;
        String lender, liabilitydesc, notes;
        // 	{"ID", "YEAR", "MONTH", "DAY", "LENDER_NAME", "AMOUNT", "INTEREST_RATE", "LENDING_TERM", "DESCRIPTION", "NOTE", "USER_ID"};
        User_ID = getSharedPreferences("loginPrefs", MODE_PRIVATE).getInt("ID", -1);
        if(c.moveToFirst()) {
            do if(c.getInt(10) == User_ID){
                month = c.getInt(2)+1;
                day = c.getInt(3);
                year = c.getInt(1);
                lender = c.getString(4);
                liabilityamount = c.getDouble(5);
                intrate = c.getDouble(6);
                term = c.getInt(7);
                liabilitydesc = c.getString(8);
                notes = c.getString(9);
                //4 2 1 3
                //{_ID, COLUMN_CASH_AMOUNT, COLUMN_SOURCE, COLUMN_NOTE, COLUMN_YEAR, COLUMN_MONTH, COLUMN_DAY, COLUMN_USER_ID};
                list.add(new String(month +"/"+ day +"/"+ year + " Lender: " + lender + ". Amount: $" +
                        liabilityamount +". Interest Rate: "+ intrate +"%. Term:  " + term + ". Description: "+ liabilitydesc +". Notes: " + notes));
                setTotalLiabilityBalance(liabilityamount);
            }while(c.moveToNext());
        }
        txtbalance.setText("Total Liability Balance: $" + this.getTotalLiabilityBalance());
        c.close();
    }
}
