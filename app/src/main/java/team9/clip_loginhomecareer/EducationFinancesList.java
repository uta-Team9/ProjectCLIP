package team9.clip_loginhomecareer;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education_finances_list);

        EduFinancials = (ListView) findViewById(R.id.edu_finances_list);

        buildList();

		/*ArrayList<String> temp = new ArrayList<>();
		for(Contact c : list) {
			temp.add(c.toString());
		}*/

        ArrayAdapter<EduFinanceItem> arrayAdapter = new ArrayAdapter<EduFinanceItem>(
                this, android.R.layout.simple_list_item_1, EduFinanceArrayList);

        EduFinancials.setAdapter(arrayAdapter);
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
        Cursor cursor = db.getAllCollegeFinances();
        EduFinanceItem temp = null;
        if (cursor.moveToFirst()) {
            do {
                if(cursor.getInt(5) == User_ID) {
                    temp = new EduFinanceItem();
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
    public void createNewInstance(View v) {
        //Intent intent = new Intent(this,.class);
        //startActivity(intent);
    }
}
