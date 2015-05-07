package team9.clip_loginhomecareer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


public class FinanceHome extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.finance_home_view);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_finance_home, menu);
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
            case(R.id.txt_financial_state):
                intent = new Intent(this, FinanceStateView.class);
                break;
            case(R.id.txt_stock_security):
                intent = new Intent(this, FinanceStockSecurityView.class);
                break;
            case(R.id.txt_financial_goals):
                //intent = new Intent(this, FinanceGoalView.class);
	            Toast.makeText(getApplicationContext(), "feature not complete", Toast.LENGTH_LONG).show();
                break;
            case(R.id.txt_financial_summary):
                intent = new Intent(this, FinanceSummaryView.class);
                break;
        }

        //intent.putExtra("tagName", value);
        if(intent != null)
            startActivity(intent);
    }
}
