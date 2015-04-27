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
 * Created by AmeeraK on 4/25/2015.
 */
public class ViewAppointment extends ActionBarActivity
{
    private int User_ID;
    private Appointment appointment = null;
    private DatabaseContract db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        openDB();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_appointment);

        Bundle extras = getIntent().getExtras();
        if(extras != null)
        {
            db = new DatabaseContract(this);
            db.open();
            appointment = (Appointment)extras.getSerializable("Appointment");

            setTitle(appointment.getDoctorName());
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

    //Added By Edward
    private void openDB() {

        User_ID = getSharedPreferences("loginPrefs", MODE_PRIVATE).getInt("ID", -1);
        Log.d("ID in ViewAppointment", "" + User_ID);

        db = new DatabaseContract(this);
        db.open();
    }
//TODO: EDIT TEXT FIELDS, LOOK AT CAREERGOAL XML FILE
    private void setUpTextBoxes() {
        if(appointment != null) {
            TextView text = (TextView) findViewById(R.id.view_doctorName);
            text.setText("" + appointment.getDoctorName());
            text = (TextView) findViewById(R.id.view_apptdate);
            text.setText("" + appointment.getDate());
            text = (TextView) findViewById(R.id.view_time);
            text.setText("" + appointment.getDate());
            text = (TextView) findViewById(R.id.view_reason);
            text.setText("" + appointment.getReason());
            text = (TextView) findViewById(R.id.view_officeAddress);
            text.setText("" + appointment.getOfficeAddress());
            text = (TextView) findViewById(R.id.view_doctorphoneNum);
            text.setText("" + appointment.getPhone());
        }
    }

    public void editInstance(View v) {
        startActivity(new Intent(this, NewAppointment.class).putExtra("Appointment", appointment));
    }

    public void deleteInstance(View v) {
        if(db.deleteAppointment(appointment.getDatabaseID())) {
            toast("Appointment removed");
            finish();
        } else
            toast("Appointment already removed");
    }

    private void toast(String description) {
        Toast.makeText(getApplicationContext(), description, Toast.LENGTH_LONG).show();
    }
}
