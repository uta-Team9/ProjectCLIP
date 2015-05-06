package team9.clip_loginhomecareer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class ViewEduFinance extends ActionBarActivity {
    private int User_ID;
    private EduFinanceItem eduMoney = null;
    private DatabaseContract db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        openDB();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_edu_finance);
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            db = new DatabaseContract(this);
            db.open();
            eduMoney = (EduFinanceItem) extras.getSerializable("Education Finance");

            setTitle(eduMoney.getAwardName());
            setUpTextBoxes();
        }
    }

    public int getUser_ID() {
        return User_ID;
    }

    public void setUser_ID(int user_ID) {
        User_ID = user_ID;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_edu_finance, menu);
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
    private void openDB() {
        db = new DatabaseContract(this);
        db.open();
        User_ID = getSharedPreferences("loginPrefs", MODE_PRIVATE).getInt("ID", -1);
        Log.d("ID in ViewEduFinance", "" + User_ID);
    }
    private void setUpTextBoxes() {
        if(eduMoney != null) {
            TextView text = (TextView) findViewById(R.id.edu_detail_money);
            text.setText("" + eduMoney.getAwardName());
            text = (TextView) findViewById(R.id.edu_money_amount);
            text.setText("" + eduMoney.getAmount());
            text = (TextView) findViewById(R.id.edu_money_period);
            text.setText("" + eduMoney.getPeriod());
            text = (TextView) findViewById(R.id.edu_money_notes);
            text.setText("" + eduMoney.getCondition());
        }
    }
    public void editInstance(View v) {
        startActivity(new Intent(this, EduNewFinance.class).putExtra("Education Finance", eduMoney));
    }
    private void toast(String description) {
        Toast.makeText(getApplicationContext(), description, Toast.LENGTH_LONG).show();
    }
    public void deleteInstance(View v) {
        if(db.deleteCollegeFinance(eduMoney.getDatabaseID())) {
            toast("Funds source removed");
            finish();
        } else
            toast("Funds source already removed");
    }
}
