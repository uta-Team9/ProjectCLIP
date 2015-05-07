package team9.clip_loginhomecareer;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class ExercisePlansList extends ActionBarActivity {
    private int User_ID;
    private DatabaseContract db;
    private ArrayList<ExercisePlan> list;
    private ListView activityList;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        openDB();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_plans_list);
//TODO: NEED LISTVIEW
        activityList = (ListView) findViewById(R.id.listView_exercise_plan);



        buildList();
        setList();
    }
    protected void onDestroy() {
        db.close();
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_appointments_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void openDB() {
        User_ID = getSharedPreferences("loginPrefs", MODE_PRIVATE).getInt("ID", -1);
        Log.d("User ID ContactList", "" + User_ID);

        db = new DatabaseContract(this);
        db.open();
    }

    private void buildList () {
        list = new ArrayList<>();
        Cursor cursor = db.getAllExercisePlans();
        ExercisePlan temp = null;

        if (cursor.moveToFirst()) {
            do if (cursor.getInt(5) == User_ID) {

                temp = new ExercisePlan(cursor.getInt(0));
                Log.d("Exercise Plan Found: ", cursor.getString(1));
                temp.setExerciseName(cursor.getString(1));
                temp.setCaloriesBurned(cursor.getInt(2));
                temp.setDurationOfWorkout(cursor.getInt(3));
                temp.setMuscleTarget(cursor.getString(4));
                list.add(temp);
            }
            while (cursor.moveToNext());
        }
        cursor.close();
    }

    //Add the adapter to the list ui

    private void setList() {
        //Possible to change simple_list_item_1 into our own xml object
        ArrayAdapter<ExercisePlan> arrayAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, list);

        activityList.setAdapter(arrayAdapter);

        activityList.setClickable(true);
        activityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                moveToView(position);
            }
        });
    }

    protected void onResume() {
        super.onResume();
        buildList();
        setList();
    }

    /**
     *
     * @param position
     */
    private void moveToView(int position) {
        startActivity(
                new Intent(this, ViewExercisePlan.class).putExtra("ExercisePlan", list.get(position))
        );

    }

    /**
     * Move to activity to create the appropriate item when "New" button is pressed
     * @param v
     */
    public void createNewInstance1(View v)
    {
        startActivity(new Intent(this, NewExercisePlan.class));
    }
}
