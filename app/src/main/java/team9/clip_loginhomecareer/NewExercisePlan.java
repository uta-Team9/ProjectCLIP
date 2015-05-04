package team9.clip_loginhomecareer;

import android.R;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by AmeeraK on 5/3/2015.
 */
public class NewExercisePlan extends ActionBarActivity
{

    private DatabaseContract db;
    private int User_ID;
    private ExercisePlan exercisePlan = null;
    private EditText EXERCISE_NAME;
    private EditText CALORIES_BURNED;
    private EditText DURATION_WORKOUT;
    private EditText MUSCLE_TARGET;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        openDB();

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            exercisePlan = (ExercisePlan)extras.getSerializable("ExercisePlan");
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_plan);

        //setup UI objects for use
        EXERCISE_NAME = (EditText) findViewById(R.id.editText9);
        CALORIES_BURNED = (EditText) findViewById(R.id.editText14);
        DURATION_WORKOUT = (EditText) findViewById(R.id.editText5);
        MUSCLE_TARGET = (EditText) findViewById(R.id.editText13);



        if(exercisePlan != null) {
            Button b = (Button)findViewById(R.id.add);
            b.setText("Update");
            populateItems();
        }
    }

    protected void onDestroy() {
        closeDB();
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_appointment, menu);
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

    private void openDB()
    {
        User_ID = getSharedPreferences("loginPrefs", MODE_PRIVATE).getInt("ID", -1);
        Log.d("ID in Exercise Plan", "" + User_ID);

        db = new DatabaseContract(this);
        db.open();
    }

    public void add_new(View v) {
        if(exercisePlan != null) {
            updateItems();
        }else
            saveItems();
    }
    private void closeDB() {
        db.close();
    }

    private void populateItems() {
        EXERCISE_NAME.setText(exercisePlan.getExerciseName());
        CALORIES_BURNED.setText(exercisePlan.getCaloriesBurned());
        DURATION_WORKOUT.setText(exercisePlan.getDurationOfWorkout());
        MUSCLE_TARGET.setText(exercisePlan.getMuscleTarget());

    }

    private void updateItems() {
        String exerciseName = EXERCISE_NAME.getText().toString();
        Integer caloriesBurned = Integer.parseInt(CALORIES_BURNED.getText().toString());
        Integer durationWorkout = Integer.parseInt(DURATION_WORKOUT.getText().toString());
        String muscleTarget = MUSCLE_TARGET.getText().toString();



        db.updateExercisePlan(exercisePlan.getDatabaseID(), exerciseName, caloriesBurned, durationWorkout, muscleTarget);
        Log.d("Exercise Plan Saved: ", "" + exercisePlan);
        toastNotification("Exercise Plan Updated");
        clearData();
        finish();
    }

    private void saveItems() {
        if(validItems()) {
            String exerciseName = EXERCISE_NAME.getText().toString();
            Integer caloriesBurned = Integer.parseInt(CALORIES_BURNED.getText().toString());
            Integer durationWorkout = Integer.parseInt(DURATION_WORKOUT.getText().toString());
            String muscleTarget = MUSCLE_TARGET.getText().toString();



            db.insertExercisePlan(exerciseName, caloriesBurned, durationWorkout, muscleTarget, User_ID);
            Log.d("Exercise Plan Saved: ", "" + exerciseName);
            toastNotification("Exercise Plan Saved");
            clearData();
            finish();
        } else {
            toastNotification("Invalid Information");
        }
    }

    private boolean validItems() {
        //TODO: Check for invalid input data
        return true;
    }


    private void clearData() {
        EXERCISE_NAME.setText("");
        CALORIES_BURNED.setText("");
        DURATION_WORKOUT.setText("");
        MUSCLE_TARGET.setText("");

    }

    private void toastNotification(String description) {
        Toast.makeText(getApplicationContext(), description, Toast.LENGTH_LONG).show();
    }
}
