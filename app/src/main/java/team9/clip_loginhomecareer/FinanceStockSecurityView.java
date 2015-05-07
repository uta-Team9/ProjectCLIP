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


public class FinanceStockSecurityView extends ActionBarActivity {
    private int User_ID;
    private DatabaseContract db;
    private ListView onScreenList;
    private ArrayList<String> list = new ArrayList<>();
    private TextView txtpurchasebalance;
    private TextView txtmarketbalance;
    private TextView txtprofitbalance;
    private double total_purchase_value = 0.00;
    private double total_market_value = 0.00;
    private double profit = 0.00;

    public double getTotal_purchase_value() {
        return total_purchase_value;
    }

    public void setTotal_purchase_value(double total_purchase_value) {
        this.total_purchase_value += total_purchase_value;
    }

    public double getTotal_market_value() {
        return total_market_value;
    }

    public void setTotal_market_value(double total_market_value) {
        this.total_market_value += total_market_value;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double total_market_value, double total_purchase_value) {
        this.profit += total_market_value - total_purchase_value;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        db = new DatabaseContract(this);
        db.open();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.finance_stock_security_view);
        txtpurchasebalance = (TextView)findViewById(R.id.txt_purchase_balance);
        txtmarketbalance = (TextView)findViewById(R.id.txt_current_balance);
        txtprofitbalance = (TextView)findViewById(R.id.txt_current_profit);
        buildList();
        onScreenList = (ListView) findViewById(R.id.lst_stock_security);
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
        getMenuInflater().inflate(R.menu.menu_finance_stock_security_view, menu);
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
        intent = new Intent(this, FinanceStockSecurityNew.class);
        startActivity(intent);
    }
    public void buildList(){
        Cursor c = db.getAllStockSecurities();
        int month, day, year, term;
        double noofunits = 0.00;
        double purchaseprice = 0.00;
        //db.deleteAllStockSecurities();
        double purchase_amt = 0.00;
        double market_amt = 0.00;
        double currentprice = 0.00;
        String stockname, notes;
        // {"ID", "YEAR", "MONTH", "DAY", "STOCK_NAME", "STOCK_NO_OF_UNITS", "STOCK_PURCHASE_PRICE", "STOCK_CURRENT_PRICE","STOCK_NOTE", "USER_ID"};
        User_ID = getSharedPreferences("loginPrefs", MODE_PRIVATE).getInt("ID", -1);
        if(c.moveToFirst()) {
            do if(c.getInt(9) == User_ID){
                month = c.getInt(2)+1;
                day = c.getInt(3);
                year = c.getInt(1);
                stockname = c.getString(4);
                noofunits = c.getInt(5);
                purchaseprice = c.getDouble(6);
                currentprice = c.getDouble(7);
                notes = c.getString(8);
                list.add(new String(month +"/"+ day +"/"+ year + ". Stock Name: " + stockname + ". # of Units: " +
                        noofunits +". Purchase Price Per Unit: "+ purchaseprice +". Current Price Per Unit:  "
                        + currentprice + ". Notes: " + notes));
                purchase_amt = purchaseprice * (double)noofunits;
                market_amt = currentprice * (double)noofunits;
                setTotal_purchase_value(purchase_amt);
                setTotal_market_value(market_amt);
                setProfit(market_amt, purchase_amt);
            }while(c.moveToNext());
        }
        txtmarketbalance.setText("Total Current Stocks and Securities Value: $" + this.getTotal_market_value());
        txtpurchasebalance.setText("Total Stocks and Securities Purchase Value: $" + this.getTotal_purchase_value());
        if (this.getProfit() < 0) {
            txtprofitbalance.setText("Current Loss: $" + this.getProfit() * -1);
        }
        else
        {
            txtprofitbalance.setText("Current Profit: $" + this.getProfit());
        }
        c.close();
    }
}
