package team9.clip_loginhomecareer;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;


public class EduNewApp extends ActionBarActivity {

    private DatabaseContract db;
    private EditText TEXT_COLLEGE;
    private DatePicker PICKER_DUE;
    private DatePicker PICKER_REPLY;
    private EduApp eduApp= null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        openDB();
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            eduApp = (EduApp)extras.getSerializable("College Application");
        }
        setContentView(R.layout.activity_edu_new_app);
        TEXT_COLLEGE = (EditText) findViewById(R.id.edu_detail_collApp);
        PICKER_DUE = (DatePicker) findViewById(R.id.application_due_date);
        PICKER_REPLY = (DatePicker) findViewById(R.id.edu_appl_reply);

        if(eduApp != null) {
            Button b = (Button)findViewById(R.id.add);
            b.setText("Update");
            populateItems();
        }

    }

    @Override
    protected void onDestroy() {
        closeDB();
        super.onDestroy();
    }
    private void populateItems() {
        TEXT_COLLEGE.setText("" + eduApp.getCollege());
    }

    public void openDB() {
        db = new DatabaseContract(this);
        db.open();
    }

    public void closeDB() {
        db.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edu_new_app, menu);
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

    public void add_new(View v) {

        if (eduApp != null) {
            updateItems();
        } else
            saveItems();
    }

    private boolean validItems() {
        //TODO: Check for invalid input data
        return true;
    }

    private void toastNotification(String description) {
        Toast.makeText(getApplicationContext(), description, Toast.LENGTH_LONG).show();
    }

    private void updateItems() {
        String I = TEXT_COLLEGE.getText().toString();
        Integer dD = Integer.parseInt(("" + PICKER_DUE.getYear()+PICKER_DUE.getMonth()+PICKER_DUE.getDayOfMonth()));
        Integer rD = Integer.parseInt(("" + PICKER_REPLY.getYear()+PICKER_REPLY.getMonth()+PICKER_REPLY.getDayOfMonth()));
        //Integer m = Integer.parseInt(
        //       PICKER.getYear() + "" + PICKER.getMonth() + "" + PICKER.getDayOfMonth()
        // );
        if(db.updateCollegeApplication(eduApp.getDbRow(), I, dD.intValue(), rD.intValue()) == false) toastNotification("Update Failed");
        //toastNotification("Degree Updated");
        clearData();
        finish();
    }
    private void saveItems() {
        String inst= "";
        Integer due = 0;
        Integer reply = 0;
        EditText text;
        DatePicker date;

        if (validItems()) {
            text = (EditText) findViewById(R.id.app_college_name);
            inst = text.getText().toString();
            date = (DatePicker) findViewById(R.id.application_due_date);
            due = Integer.parseInt(date.getYear() + "" + date.getMonth() + "" + date.getDayOfMonth());
            date = (DatePicker) findViewById(R.id.edu_appl_reply);
            reply = Integer.parseInt(date.getYear() + "" + date.getMonth() + "" + date.getDayOfMonth());

            db.insertCollegeApplication(inst, due, reply, 0);
            toastNotification("Application Saved");
            clearData();
        } else {
            toastNotification("Invalid Information");
        }
    }
    private void clearData() {
        EditText text;
        text = (EditText) findViewById(R.id.app_college_name);
        text.setText("");
        text = (EditText) findViewById(R.id.application_due_date);
        text.setText("");
        text = (EditText) findViewById(R.id.edu_appl_reply);
        text.setText("");

    }
}