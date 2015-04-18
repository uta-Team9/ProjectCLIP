package team9.clip_loginhomecareer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class AppointmentsList extends ActionBarActivity {

    private ArrayList<Appointment> list = new ArrayList<>();
    private ListView activityList;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        Bundle extras = getIntent().getExtras();

       // var = extras.getInt("Appointment1");

       // if()

        setContentView(R.layout.activity_appointments_list);
        activityList = (ListView) findViewById(R.id.listView);

        ArrayAdapter<Appointment> arrayAdapter = new ArrayAdapter<Appointment>( this, android.R.layout.simple_list_item_1, list);


        activityList.setAdapter(arrayAdapter);
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void createAppointment(View view)
    {
        Intent intent = new Intent(AppointmentsList.this, Appointment.class);
        startActivity(intent);
    }
}
