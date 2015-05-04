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

/**
 * Created by AmeeraK on 5/3/2015.
 */
public class ViewExercisePlan extends ActionBarActivity
{
    private int User_ID;
    private ExercisePlan exercisePlan = null;
    private DatabaseContract db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        openDB();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_exercise_plan);

        Bundle extras = getIntent().getExtras();
        if(extras != null)
        {
            db = new DatabaseContract(this);
            db.open();
            exercisePlan = (ExercisePlan)extras.getSerializable("ExercisePlan");

            setTitle(exercisePlan.getExerciseName());
            setUpTextBoxes();
        }

    }

    @Override
    protected void onDestroy() {
        db.close();
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.display_contact_menu, menu);
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

        User_ID = getSharedPreferences("loginPrefs", MODE_PRIVATE).getInt("ID", -1);
        Log.d("ID in ViewExercisePlan", "" + User_ID);

        db = new DatabaseContract(this);
        db.open();
    }
    //TODO: EDIT TEXT FIELDS, LOOK AT CAREERGOAL XML FILE
    private void setUpTextBoxes() {
        if(exercisePlan != null) {
            TextView text = (TextView) findViewById(R.id.editText9);
            text.setText("" + exercisePlan.getExerciseName());
            text = (TextView) findViewById(R.id.editText14);
            text.setText("" + exercisePlan.getCaloriesBurned());
            text = (TextView) findViewById(R.id.editText5);
            text.setText("" + exercisePlan.getDurationOfWorkout());
            text = (TextView) findViewById(R.id.editText13);
            text.setText("" + exercisePlan.getMuscleTarget());

        }
    }

    public void editInstance(View v) {
        startActivity(new Intent(this, NewExercisePlan.class).putExtra("ExercisePlan", exercisePlan));
    }

    public void deleteInstance(View v) {
        if(db.deleteExercisePlan(exercisePlan.getDatabaseID())) {
            toast("Exercise Plan removed");
            finish();
        } else
            toast("Exercise Plan already removed");
    }

    private void toast(String description) {
        Toast.makeText(getApplicationContext(), description, Toast.LENGTH_LONG).show();
    }

}
