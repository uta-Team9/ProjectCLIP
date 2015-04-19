package team9.clip_loginhomecareer;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class HealthHomePage extends ActionBarActivity {
    int medreport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle extras = getIntent().getExtras();
        if(extras!=null)
        {
            medreport = extras.getInt("ID");
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_home_page);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_health_home_page, menu);
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

    public void sendMessage(View view)
    {
        Intent intent = new Intent(HealthHomePage.this, DoctorsVisits.class);
        startActivity(intent);
    }
    public void sendMessageMedReport(View view)
    {
        Intent intent = new Intent(HealthHomePage.this, MedicalReport.class);
        intent.putExtra("ID", medreport);
        startActivity(intent);
    }
    public void sendMessageWeightLossAndDietPlan(View view)
    {
        Intent intent = new Intent(HealthHomePage.this, WeightLossAndDietPlan.class);

        startActivity(intent);
    }

}
