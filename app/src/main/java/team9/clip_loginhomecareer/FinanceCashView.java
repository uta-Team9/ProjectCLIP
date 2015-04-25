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


public class FinanceCashView extends ActionBarActivity {

    private DatabaseContract db;
    private ListView onScreenList;
    private ArrayList<String> list = new ArrayList<>();
    private TextView txtbalance;
    private double totalamount = 0.00;

    public double getTotalamount() {
        return totalamount;
    }

    public void setTotalamount(double totalamount) {
        this.totalamount += totalamount;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        db = new DatabaseContract(this);
        db.open();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.finance_cash_view);
        txtbalance = (TextView)findViewById(R.id.txt_balance);

        buildList();
        onScreenList = (ListView) findViewById(R.id.lst_cash);
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
        getMenuInflater().inflate(R.menu.menu_finance_cash_view, menu);
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
        intent = new Intent(this, FinanceCashNew.class);
        startActivity(intent);
    }

    public void buildList(){
        Cursor c = db.getAllCash();
        int month, day, year;
        double amount = 0.00;
        String source, notes;
        if(c.moveToFirst()) {
            do{
                month = c.getInt(5)+1;
                day = c.getInt(6);
                year = c.getInt(4);
                amount = c.getDouble(1);
                source = c.getString(2);
                notes = c.getString(3);
                //4 2 1 3
                //{_ID, COLUMN_CASH_AMOUNT, COLUMN_SOURCE, COLUMN_NOTE, COLUMN_YEAR, COLUMN_MONTH, COLUMN_DAY, COLUMN_USER_ID};
                list.add(new String(month +"/"+ day +"/"+ year +" Cash Source: " + source + " Amount: $" +
                amount + " Notes:" + notes));
                setTotalamount(amount);
            }while(c.moveToNext());
        }
        txtbalance.setText("Total cash: $" + this.totalamount);
        c.close();
    }

}
