package team9.clip_loginhomecareer;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class DegreesList extends ActionBarActivity {
    private int User_ID = 0;
    private DatabaseContract db;
    private ArrayList<Degree> degreeArrayList = new ArrayList<>();
    private ListView degrees;

    public ArrayList<Degree> getDegreeArrayList() {
        return degreeArrayList;
    }

    public void setDegreeArrayList(ArrayList<Degree> degreeArrayList) {
        this.degreeArrayList = degreeArrayList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.education_degrees_list);

        openDB();
        Bundle extras = getIntent().getExtras();
        if(extras != null)
        {
            User_ID = extras.getInt("ID");
            Log.d("User ID: ", "" + User_ID);
        }


        degrees = (ListView) findViewById(R.id.degree_list);

        buildList();

		/*ArrayList<String> temp = new ArrayList<>();
		for(Contact c : list) {
			temp.add(c.toString());
		}*/

        ArrayAdapter<Degree> arrayAdapter = new ArrayAdapter<Degree>(
                this, android.R.layout.simple_list_item_1, degreeArrayList);

        degrees.setAdapter(arrayAdapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_degrees_list, menu);
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


    //putting together contact list
    private void buildList() {
        Cursor cursor = db.getAllColleges();
        Degree temp = null;
        if (cursor.moveToFirst()) {
            do {
                if(cursor.getInt(7) == User_ID) {
                    temp = new Degree();
                    //_ID, COLUMN_INSTITUTION, COLUMN_COLLEGE_CITY, COLUMN_STUDY_FIELD,
                    //COLUMN_DEGREE, COLUMN_START_DATE, COLUMN_COMPLETION_DATE, COLUMN_USER_ID};
                    temp.setCollege(cursor.getString(1));
                    temp.setStart_date(cursor.getInt(5));
                    temp.setDegree_type(cursor.getString(4));
                    temp.setGrad_date(cursor.getInt(6));
                    temp.setLocation(cursor.getString(2));
                    temp.setStudy_field(cursor.getString(3));
                    degreeArrayList.add(temp);
                }
            } while (cursor.moveToNext());
        }
        cursor.close();

    }

    private void openDB() {
        db = new DatabaseContract(this);
        db.open();
    }
    private void closeDB() {
        db.close();
    }


    public void createNewInstance(View v) {
        Intent intent = new Intent(this,new_degree_activity.class);
        startActivity(intent);
    }
}
