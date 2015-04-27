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


public class FinanceCreditView extends ActionBarActivity {
    private DatabaseContract db;
    private ListView onScreenList;
    private ArrayList<String> list = new ArrayList<>();
    private TextView txtbalance;
    private double totalCreditBalance = 0.00;

    public double getTotalCreditBalance() {
        return totalCreditBalance;
    }

    public void setTotalCreditBalance(double totalCreditBalance) {
        this.totalCreditBalance += totalCreditBalance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        db = new DatabaseContract(this);
        db.open();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.finance_credit_view);
        txtbalance = (TextView)findViewById(R.id.txt_credit_balance);

        buildList();
        onScreenList = (ListView) findViewById(R.id.lst_credit);
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
        getMenuInflater().inflate(R.menu.menu_finance_credit_balance_view, menu);
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
        intent = new Intent(this, FinanceCreditNew.class);
        startActivity(intent);
    }

    public void buildList(){
        Cursor c = db.getAllCreditCards();
        int month, day, year;
        double amount = 0.00;
        String source, notes;
        // {"ID", "PROVIDER", "BALANCE", "YEAR", "MONTH", "DAY", "NOTE", "USER_ID"};
        if(c.moveToFirst()) {
            do{
                month = c.getInt(4)+1;
                day = c.getInt(5);
                year = c.getInt(3);
                amount = c.getDouble(2);
                source = c.getString(1);
                notes = c.getString(6);
                //4 2 1 3
                //{_ID, COLUMN_CASH_AMOUNT, COLUMN_SOURCE, COLUMN_NOTE, COLUMN_YEAR, COLUMN_MONTH, COLUMN_DAY, COLUMN_USER_ID};
                list.add(new String("Card Name: " + source + " Amount: $" +
                        amount +" Expiration Date:  "+ month +"/"+ day +"/"+ year + " Notes:" + notes));
                setTotalCreditBalance(amount);
            }while(c.moveToNext());
        }
        txtbalance.setText("Total Credit Balance: $" + this.totalCreditBalance);
        c.close();
    }
}
