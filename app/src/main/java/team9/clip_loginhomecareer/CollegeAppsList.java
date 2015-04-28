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

/**
 * Created by Mary on 4/18/2015.
 */
public class CollegeAppsList extends ActionBarActivity {

    private int User_ID = 0;
    private DatabaseContract db;
    private ArrayList<EduApp> list = new ArrayList<>();
    private ListView applicationsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edu_apps_list);

        openDB();
        Bundle extras = getIntent().getExtras();
        if(extras != null)
        {
            User_ID = getSharedPreferences("loginPrefs", MODE_PRIVATE).getInt("ID", -1);
        }

        applicationsList = (ListView) findViewById(R.id.applications_list);

        buildList();
        setList();

		/*ArrayList<String> temp = new ArrayList<>();
		for(Contact c : list) {
			temp.add(c.toString());
		}*/

        ArrayAdapter<EduApp> arrayAdapter = new ArrayAdapter<EduApp>(
                this, android.R.layout.simple_list_item_1, list);

        applicationsList.setAdapter(arrayAdapter);
    }

    @Override
    protected void onDestroy() {
        closeDB();
        super.onDestroy();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edu_apps_list, menu);
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


    public void createNewApplication(View v) {
        Intent intent = new Intent(this,EduNewApp.class);
        startActivity(intent);
    }
    private void openDB() {
        db = new DatabaseContract(this);
        db.open();
    }
    private void closeDB() {
        db.close();
    }

    private void buildList() {
        Cursor cursor = db.getAllCollegeApplications();
        EduApp temp = null;
        if (cursor.moveToFirst()) {
            do {
                if(true) {
                    temp = new EduApp();
                    //_ID, college, due date, reply date
                    temp.setCollege(cursor.getString(1));
                    temp.setDeadline(cursor.getInt(2));
                    temp.setReply_expected(cursor.getInt(3));
                    list.add(temp);
                }
            } while (cursor.moveToNext());
        }
        cursor.close();

    }

    private void setList() {
        //Possible to change simple_list_item_1 into our own xml object
        ArrayAdapter<CollegeAppsList> arrayAdapter = new ArrayAdapter<CollegeAppsList>(
                this, android.R.layout.simple_list_item_1, list);

        applicationsList.setAdapter(arrayAdapter);

        applicationsList.setClickable(true);
        applicationsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                moveToView(position);
            }
        });
    }
    public void createNewInstance(View v) {
        Intent intent = new Intent(this, EduNewApp.class);
        intent.putExtra("ID", User_ID);
        startActivity(intent);
    }
}

