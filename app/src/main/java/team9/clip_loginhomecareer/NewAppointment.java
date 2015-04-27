package team9.clip_loginhomecareer;

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
 * Created by AmeeraK on 4/25/2015.
 */
public class NewAppointment extends ActionBarActivity
{
    private DatabaseContract db;
    private int User_ID;
    private Appointment appt = null;
    private EditText DOC_NAME;
    private EditText APPT_DATE;
    private EditText APPT_TIME;
    private EditText APPT_REASON;
    private EditText APPT_OFFICEADDRESS;
    private EditText APPT_DOCPHONE;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        openDB();

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            appt = (Appointment)extras.getSerializable("Appointment");
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);

        //setup UI objects for use
        DOC_NAME = (EditText) findViewById(R.id.editText9);
        APPT_DATE = (EditText) findViewById(R.id.editText4);
        APPT_TIME = (EditText) findViewById(R.id.editText5);
        APPT_REASON = (EditText) findViewById(R.id.editText10);
        APPT_OFFICEADDRESS = (EditText) findViewById(R.id.editText);
        APPT_DOCPHONE = (EditText) findViewById(R.id.editText7);


        if(appt != null) {
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
        Log.d("ID in Appointment", "" + User_ID);

        db = new DatabaseContract(this);
        db.open();
    }

    public void add_new(View v) {
        if(appt != null) {
            updateItems();
        }else
            saveItems();
    }
    private void closeDB() {
        db.close();
    }

    private void populateItems() {
        DOC_NAME.setText(appt.getDoctorName());
        APPT_DATE.setText(appt.getDate());
        APPT_TIME.setText(appt.getTime());
        APPT_REASON.setText(appt.getReason());
        APPT_OFFICEADDRESS.setText(appt.getOfficeAddress());
        APPT_DOCPHONE.setText(appt.getPhone());
    }

    private void updateItems() {
        String doctorName = DOC_NAME.getText().toString();
        Integer date = Integer.parseInt(APPT_DATE.getText().toString());
        Integer time = Integer.parseInt(APPT_TIME.getText().toString());
        String reason = APPT_REASON.getText().toString();
        String officeAddress = APPT_OFFICEADDRESS.getText().toString();
        Integer doctorphone = Integer.parseInt(APPT_DOCPHONE.getText().toString());


        db.updateAppointment(appt.getDatabaseID(), doctorName, date, time, reason, officeAddress, doctorphone);
        Log.d("Appointment Saved: ", "" + doctorName);
        toastNotification("Appointment Updated");
        clearData();
        finish();
    }

    private void saveItems() {
        if(validItems()) {
            String doctorName = DOC_NAME.getText().toString();
            Integer date = Integer.parseInt(APPT_DATE.getText().toString());
            Integer time = Integer.parseInt(APPT_TIME.getText().toString());
            String reason = APPT_REASON.getText().toString();
            String officeAddress = APPT_OFFICEADDRESS.getText().toString();
            Integer doctorphone = Integer.parseInt(APPT_DOCPHONE.getText().toString());


            db.insertAppointment(doctorName, date, time, reason, officeAddress, doctorphone, User_ID);
            Log.d("Appointment Saved: ", "" + doctorName);
            toastNotification("Appointment Saved");
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
        DOC_NAME.setText("");
        APPT_DATE.setText("");
        APPT_TIME.setText("");
        APPT_REASON.setText("");
        APPT_OFFICEADDRESS.setText("");
        APPT_DOCPHONE.setText("");
    }

    private void toastNotification(String description) {
        Toast.makeText(getApplicationContext(), description, Toast.LENGTH_LONG).show();
    }
}
