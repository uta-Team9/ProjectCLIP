package team9.clip_loginhomecareer;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;


public class FinanceAssetView extends ActionBarActivity {
    private DatabaseContract db;
    private ListView onScreenList;
    private ArrayList<String> list = new ArrayList<>();
    private Double total;
    private EditText text;

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total += total;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.finance_assets_view);
        db = new DatabaseContract(this);
        db.open();
        buildList();
        onScreenList = (ListView) findViewById(R.id.lst_asset_view);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, list);
        onScreenList.setAdapter(arrayAdapter);
        text = (EditText)findViewById(R.id.txt_total_current_value);

    }

    protected void onDestroy() {
        db.close();
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_finance_assets_view, menu);
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
        intent = new Intent(this, FinanceAssetNew.class);
        startActivity(intent);
    }
    public void buildList() {
        Cursor c = db.getAllCash();
        Double total = 0.00, value, market_value;
        int month, year, day;
        String type, notes;
        if (c.moveToFirst()) {
            do {
                month = c.getInt(2)+1;
                year = c.getInt(1);
                day = c.getInt(3);
                value = c.getDouble(5);
                market_value = c.getDouble(6);
                type = c.getString(4);
                notes = c.getString(7);
                // {"ID", "YEAR", "MONTH", "DAY", "TYPE", "VALUE", "MARKET_VALUE", "NOTE", "USER_ID"};
                list.add(new String(month + "/" + day + "/" + year + " " + type + "Purchased value: $" +
                        value + "Current value: $" + market_value + " Notes:" + notes));
                setTotal(market_value);
                //rowIdArray.add(c.getLong(0));
               // db.deleteAsset(rowIdArray.get(0));
            } while (c.moveToNext());
        }
        text.setText("Current Asset Value: " +getTotal());
        c.close();
    }
}
