package team9.clip_loginhomecareer;

import android.content.Context;
import android.database.Cursor;
import android.print.PrintAttributes;
import android.print.PrintJob;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ListView;
import android.widget.TextView;
import android.print.PrintManager;
import android.print.PrintDocumentAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;


public class FinanceSummaryView extends ActionBarActivity {
    private int User_ID;
    private DatabaseContract db;
    private WebView myWebView;
    private TextView summarybalance;
    private TextView cash;
    private TextView asset;
    private TextView stock;
    private TextView liabilities;
    private TextView credits;
    private Calendar cal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        db = new DatabaseContract(this);
        db.open();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.finance_summary_view);
        cal = Calendar.getInstance();
        summarybalance = (TextView)findViewById(R.id.txt_finance_summary);
        cash = (TextView)findViewById(R.id.txt_total_cash);
        asset = (TextView)findViewById(R.id.txt_total_asset);
        stock = (TextView)findViewById(R.id.txt_total_stocks);
        liabilities = (TextView)findViewById(R.id.txt_total_liabilities);
        credits = (TextView)findViewById(R.id.txt_total_credits);
        summarybalance.setText("Finance Summary as of " + ((int)cal.get(Calendar.MONTH) + 1) + "/"
                + cal.get(Calendar.DAY_OF_MONTH) + "/" + cal.get(Calendar.YEAR));
        cash.setText("Cash: " + this.getCashBalance());
        asset.setText("Assets: " + this.getAssetBalance());
        stock.setText("Stocks and Securities: " + this.getStockBalance());
        liabilities.setText("Liabilities: " + this.getLiabilityBalance());
        credits.setText("Credit Cards: " + this.getCreditBalance());
    }

    protected void onDestroy() {
        db.close();
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_finance_summary_view, menu);
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
    public double getCashBalance()
    {
        Cursor c = db.getAllCash();
        //db.deleteAllCash();
        double amount = 0.00;
        User_ID = getSharedPreferences("loginPrefs", MODE_PRIVATE).getInt("ID", -1);
        if(c.moveToFirst()) {
            do if(c.getInt(7) == User_ID){
                amount += c.getDouble(1);
            }while(c.moveToNext());
        }
        c.close();
        return amount;
    }
    public double getAssetBalance()
    {
        Cursor c = db.getAllAssets();
        double market_value = 0.00;
        // db.deleteAsset(rowIdArray.get(0));
        User_ID = getSharedPreferences("loginPrefs", MODE_PRIVATE).getInt("ID", -1);
        if(c.moveToFirst()) {
            do if(c.getInt(8) == User_ID){
                market_value += c.getDouble(6);
                //rowIdArray.add(c.getLong(0));

            } while (c.moveToNext());
        }
        c.close();
        return market_value;
    }
    public double getStockBalance()
    {
        Cursor c = db.getAllStockSecurities();
        double noofunits = 0.00;
        //db.deleteAllStockSecurities();
        double market_amt = 0.00;
        double currentprice = 0.00;
        double totalvalue = 0.00;
        // {"ID", "YEAR", "MONTH", "DAY", "STOCK_NAME", "STOCK_NO_OF_UNITS", "STOCK_PURCHASE_PRICE", "STOCK_CURRENT_PRICE","STOCK_NOTE", "USER_ID"};
        User_ID = getSharedPreferences("loginPrefs", MODE_PRIVATE).getInt("ID", -1);
        if(c.moveToFirst()) {
            do if(c.getInt(9) == User_ID){
                noofunits = c.getInt(5);
                currentprice = c.getDouble(7);
                totalvalue += currentprice * (double)noofunits;
            }while(c.moveToNext());
        }
        c.close();
        return totalvalue;
    }
    public double getLiabilityBalance()
    {
        Cursor c = db.getAllLiabilities();
        //db.deleteAllLiabilities();
        double liabilityamount = 0.00;
        // 	{"ID", "YEAR", "MONTH", "DAY", "LENDER_NAME", "AMOUNT", "INTEREST_RATE", "LENDING_TERM", "DESCRIPTION", "NOTE", "USER_ID"};
        User_ID = getSharedPreferences("loginPrefs", MODE_PRIVATE).getInt("ID", -1);
        if(c.moveToFirst()) {
            do if(c.getInt(10) == User_ID){
                liabilityamount += c.getDouble(5);
            }while(c.moveToNext());
        }
        c.close();
        return  liabilityamount;
    }
    public double getCreditBalance()
    {
        Cursor c = db.getAllCreditCards();
        double amount = 0.00;
        User_ID = getSharedPreferences("loginPrefs", MODE_PRIVATE).getInt("ID", -1);
        if(c.moveToFirst()) {
            do if(c.getInt(7) == User_ID){
                amount += c.getDouble(2);
            }while(c.moveToNext());
        }
        c.close();
        return  amount;
    }

    public void printContent(View view)
    {
        toastNotification("This feature has not been implemented yet");
    }
    private void toastNotification(String description) {
        Toast.makeText(getApplicationContext(), description, Toast.LENGTH_LONG).show();
    }
}
