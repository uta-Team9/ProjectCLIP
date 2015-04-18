package team9.clip_loginhomecareer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class DoctorsVisits extends ActionBarActivity {
    private Appointment thisappointment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors_visits);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_doctors_visits, menu);
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
    public void goToAppointmentList(View view)
    {
        Intent intent = new Intent(DoctorsVisits.this, AppointmentsList.class);
        thisappointment = new Appointment();
        intent.putExtra("Appointment1", 0);
        startActivity(intent);
    }
    public void goToMedicationsList(View view)
    {
        Intent intent = new Intent(DoctorsVisits.this, MedicationsList.class);
        startActivity(intent);
    }
}
