package team9.clip_loginhomecareer;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class FinanceStateView extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.finance_state_view);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_finance_state_view, menu);
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

    public void switchViews(View v) {
        Intent intent = null;
        switch(v.getId())
        {
            case(R.id.txt_cash):
                intent = new Intent(this, FinanceCashView.class);
                break;
            case(R.id.txt_asset):
                intent = new Intent(this, FinanceAssetView.class);
                break;
            case(R.id.txt_liability):
                intent = new Intent(this, FinanceLiabilityView.class);
                break;
            case(R.id.txt_credit):
                intent = new Intent(this, FinanceCreditView.class);
                break;
            case(R.id.txt_other):
                intent = new Intent(this, FinanceOtherView.class);
                break;
        }

        //intent.putExtra("tagName", value);
        startActivity(intent);
    }
}
