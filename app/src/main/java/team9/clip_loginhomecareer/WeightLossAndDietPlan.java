package team9.clip_loginhomecareer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class WeightLossAndDietPlan extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_loss_and_diet_plan);
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


