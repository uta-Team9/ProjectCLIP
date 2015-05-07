package team9.clip_loginhomecareer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


public class CalculatePayable extends ActionBarActivity {

    private TextView totalpayable;
    private TextView monthlypayable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.finance_calculate_payable);
        totalpayable = (TextView)findViewById(R.id.txt_amt_payable);
        monthlypayable = (TextView)findViewById(R.id.txt_amt_monthly);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_calculate_payable, menu);
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
        double iniamt = Double.parseDouble(((EditText) findViewById(R.id.txt_liability_amt)).getText().toString());
        double intrate = Double.parseDouble(((EditText) findViewById(R.id.txt_interest_rate)).getText().toString());
        Spinner spn = (Spinner) findViewById(R.id.spn_lending_term);
        int typenum = spn.getSelectedItemPosition();
        int term;
        double payable = 0.00;
        double monthly = 0.00;
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
        for(int i = 0; i < term; i++)
            intamt += iniamt * (intrate/100);
        payable = iniamt + intamt;
        monthly = payable/(double)term;
        totalpayable.setText("Total Payable after "+term+" months: " +payable);
        monthlypayable.setText("Monthly Payable: "+monthly);
    }
    public void cancel(View v) {
        Intent intent = null;
        intent = new Intent(this, FinanceLiabilityNew.class);
        startActivity(intent);
    }

}
