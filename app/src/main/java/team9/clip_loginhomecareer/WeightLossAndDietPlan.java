package team9.clip_loginhomecareer;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class WeightLossAndDietPlan extends ActionBarActivity {
    private long dbUserRow = 0;
    private int User_ID = 0;
    private DatabaseContract db;
    private boolean hasData = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        openDB();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_loss_and_diet_plan);

        Cursor c = db.getAllWeightDietRows();
        if(c.moveToFirst())
        {
            do if(c.getInt(6) == User_ID) {
                buildFields(c);
                dbUserRow = c.getInt(0);
                hasData = true;
                break;
            } while (c.moveToNext());
        }
    }
    protected void onDestroy() {
        closeDB();
        super.onDestroy();
    }

    private void openDB() {
        User_ID = getSharedPreferences("loginPrefs", MODE_PRIVATE).getInt("ID", -1);
        db = new DatabaseContract(this);
        db.open();
    }

    private void closeDB() {
        db.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_weight_loss_and_diet_plan, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Intent intent;

        //noinspection SimplifiableIfStatement
        switch (id) {
            case (R.id.action_settings):
                intent = new Intent(this, Settings.class);
                startActivity(intent);
                break;
            case (R.id.action_Career):
                setContentView(R.layout.home_career_activity);
                break;
            case (R.id.action_Finance):

                break;
            case (R.id.action_Health):
                intent = new Intent(this, HealthHomePage.class);
                break;
            case (R.id.action_Education):

                break;
        }
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void buildFields(Cursor c)
    {
        EditText text;
        text = (EditText) findViewById(R.id.editText4);
        text.setText(c.getString(1));

        text = (EditText) findViewById(R.id.editText5);
        text.setText(c.getString(2));

        text = (EditText) findViewById(R.id.editText6);
        text.setText(c.getString(3));

        text = (EditText) findViewById(R.id.editText7);
        text.setText(c.getString(4));

        text = (EditText) findViewById(R.id.editText8);
        text.setText(c.getString(5));




    }

    public void add_new(View v) {
        EditText text;
        if (validItems()) {
            text = (EditText) findViewById(R.id.editText4);
            String planName = text.getText().toString();
            text = (EditText) findViewById(R.id.editText5);
            int startDate = Integer.parseInt(text.getText().toString());
            text = (EditText) findViewById(R.id.editText6);
            int endDate = Integer.parseInt(text.getText().toString());
            text = (EditText) findViewById(R.id.editText7);
            int currentWeight = Integer.parseInt(text.getText().toString());
            text = (EditText) findViewById(R.id.editText8);
            int goalWeight = Integer.parseInt(text.getText().toString());


            if(!hasData) {
                Log.d("Stored info for User ID", "" + User_ID);
                dbUserRow = db.insertWeightDietRow(planName,startDate,endDate, currentWeight,goalWeight, User_ID);
                hasData = true;
            } else {
                Log.d("Updated for User ID", ""+User_ID);
                db.updateWeightDietRow(dbUserRow, planName,startDate,endDate, currentWeight,goalWeight);
            }

            toastNotification("Weight Loss and Diet Plan Saved");
            //  clearData();
        }

        else
        {
            toastNotification("Invalid Information");
        }

        finish();
    }

    private boolean validItems() {
        //TODO: Check for invalid input data
        return true;
    }
    private void toastNotification(String description) {
        Toast.makeText(getApplicationContext(), description, Toast.LENGTH_LONG).show();
    }
    public void goToExercisePlanList(View view)
    {
        Intent intent = new Intent(WeightLossAndDietPlan.this, ExercisePlansList.class);
        startActivity(intent);
    }
    public void goToHealthyRecipeList(View view)
    {
        Intent intent = new Intent(WeightLossAndDietPlan.this, HealthyRecipesList.class);
        startActivity(intent);
    }
}


