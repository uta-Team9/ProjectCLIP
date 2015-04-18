package team9.clip_loginhomecareer;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;


public class DegreesList extends ActionBarActivity {
    private int User_ID = 0;
    private DatabaseContract db;
    private ArrayList<Degree> degreeArrayList = new ArrayList<>();

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
                    //_ID, name, phone, email, used, met
                    temp.setCollege(cursor.getString(2));
                    temp.setStart_date(cursor.getInt(3));
                    temp.setDegree_type(cursor.getString(5));
                    temp.setGrad_date(cursor.getInt(6));
                    temp.setLocation(cursor.getString(2));
                    temp.setStudy_field(cursor.getString(4));
                    degreeArrayList.add(temp);
                }
            } while (cursor.moveToNext());
        }
        cursor.close();

    }


    public void createNewInstance(View v) {
        Intent intent = new Intent(this,new_degree_activity.class);
        startActivity(intent);
    }
}
