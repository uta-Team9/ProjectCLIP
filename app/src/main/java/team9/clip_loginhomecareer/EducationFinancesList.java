package team9.clip_loginhomecareer;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class EducationFinancesList extends ActionBarActivity {
    private int User_ID = 0;
    private DatabaseContract db;
    private ArrayList<EduFinanceItem> EduFinanceArrayList = new ArrayList<>();
    private ListView EduFinancials;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        db = new DatabaseContract(this);
        db.open();
        User_ID = getSharedPreferences("loginPrefs", MODE_PRIVATE).getInt("ID", -1);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education_finances_list);

        EduFinancials = (ListView) findViewById(R.id.edu_finances_list);

    }

    @Override
    protected void onDestroy() {
        db.close();
        super.onDestroy();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_education_finances_list, menu);
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
    private void buildList() {
        EduFinanceArrayList=new ArrayList<>();
        Cursor cursor = db.getAllCollegeFinances();
        EduFinanceItem temp = null;
        if (cursor.moveToFirst()) {
            do {
                if(cursor.getInt(5) == User_ID) {
                    temp = new EduFinanceItem(cursor.getLong(0));
                    //_ID, COLUMN_AWARD_NAME, COLUMN_AMOUNT, COLUMN_PERIOD, COLUMN_CONDITION, COLUMN_USER_ID;
                    temp.setAwardName(cursor.getString(1));
                    temp.setAmount(cursor.getInt(2));
                    temp.setCondition(cursor.getString(4));
                    temp.setPeriod(cursor.getString(3));
                    EduFinanceArrayList.add(temp);
                }
            } while (cursor.moveToNext());
        }
        cursor.close();

    }
    @Override
    protected void onResume() {
        super.onResume();

        buildList();
        setList();
    }
    private void setList() {
        //Possible to change simple_list_item_1 into our own xml object
        ArrayAdapter<EduFinanceItem> arrayAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, EduFinanceArrayList);

        EduFinancials.setAdapter(arrayAdapter);

        EduFinancials.setClickable(true);
        EduFinancials.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                moveToView(position);
            }
        });
    }
    private void moveToView(int position) {
        startActivity(
                new Intent(this, ViewEduFinance.class).putExtra("Education Finance", EduFinanceArrayList.get(position))
        );
    }
    public void createNewInstance(View v) {
        Intent intent = new Intent(this,EduNewFinance.class);
        startActivity(intent);
    }
}
