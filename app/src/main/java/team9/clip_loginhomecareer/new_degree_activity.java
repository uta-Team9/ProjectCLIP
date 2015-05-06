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


public class new_degree_activity extends ActionBarActivity {

    private DatabaseContract db;
    private Degree degree= null;
    private int User_ID;
    private EditText TEXT_COLLEGE;
    private EditText TEXT_LOCATION;
    private EditText TEXT_LEVEL;
    private EditText TEXT_FOS;
    private DatePicker PICKER_MATR;
    private DatePicker PICKER_GRAD;
    //private DatePicker PICKER;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        openDB();
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            degree = (Degree)extras.getSerializable("Degree");
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_degree_activity);
        TEXT_COLLEGE = (EditText) findViewById(R.id.new_college_name);
        TEXT_LOCATION = (EditText) findViewById(R.id.new_college_location);
        TEXT_LEVEL = (EditText) findViewById(R.id.new_college_degree_sought);
        TEXT_FOS = (EditText) findViewById(R.id.new_college_field_of_study);
        PICKER_GRAD = (DatePicker) findViewById(R.id.edu_grad_date);
        PICKER_MATR = (DatePicker) findViewById(R.id.edu_start_date);
       // PICKER = (DatePicker) findViewById(R.id.new_contact_met);

        if(degree != null) {
            Button b = (Button)findViewById(R.id.add);
            b.setText("Update");
            populateItems();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        closeDB();
    }

    public void openDB() {
        User_ID = getSharedPreferences("loginPrefs", MODE_PRIVATE).getInt("ID", -1);
        db = new DatabaseContract(this);
        db.open();
    }

    public void closeDB() {
        db.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_degree_activity, menu);
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

        if(degree != null) {
            updateItems();
        }else
            saveItems();
    }
    private void saveItems() {
        String inst= "";
        String loc = "";
        String ds = "";
        String major = "";
        Integer grad = 0;
        Integer matr = 0;
        EditText text;
        DatePicker date;

        if (validItems()) {
            text = (EditText) findViewById(R.id.new_college_name);
            inst = text.getText().toString();
            text = (EditText) findViewById(R.id.new_college_location);
            loc = text.getText().toString();
            text = (EditText) findViewById(R.id.new_college_degree_sought);
            ds = text.getText().toString();
            text = (EditText) findViewById(R.id.new_college_field_of_study);
            major = text.getText().toString();
            date = (DatePicker) findViewById(R.id.edu_grad_date);
            grad = Integer.parseInt(date.getYear() + "" + date.getMonth() + "" + date.getDayOfMonth());
            date = (DatePicker) findViewById(R.id.edu_start_date);
            matr = Integer.parseInt(date.getYear() + "" + date.getMonth() + "" + date.getDayOfMonth());

            db.insertColleges(inst, loc, major, ds, matr, grad, User_ID);
            toastNotification("Degree Saved");
            clearData();
        } else {
            toastNotification("Invalid Information");
        }
    }
    private boolean validItems() {
        //TODO: Check for invalid input data
        return true;
    }
    private void toastNotification(String description) {
        Toast.makeText(getApplicationContext(), description, Toast.LENGTH_LONG).show();
    }
    private void clearData()
    {
        EditText text;
        text = (EditText) findViewById(R.id.new_college_name);
        text.setText("");
        text = (EditText) findViewById(R.id.new_college_degree_sought);
        text.setText("");
        text = (EditText) findViewById(R.id.new_college_location);
        text.setText("");
        text = (EditText) findViewById(R.id.new_college_field_of_study);
        text.setText("");
    }
    private void populateItems() {
        TEXT_COLLEGE.setText("" + degree.getCollege());
        TEXT_LOCATION.setText("" + degree.getLocation());
        TEXT_LEVEL.setText("" + degree.getDegree_type());
        TEXT_FOS.setText("" + degree.getStudy_field());
}
    private void updateItems() {
        String I = TEXT_COLLEGE.getText().toString();
        String loc = TEXT_LOCATION.getText().toString();
        String fos = TEXT_FOS.getText().toString();
        String lev = TEXT_LEVEL.getText().toString();
        Integer sD = Integer.parseInt(("" + PICKER_MATR.getYear()+PICKER_MATR.getMonth()+PICKER_MATR.getDayOfMonth()));
        Integer gD = Integer.parseInt(("" + PICKER_GRAD.getYear()+PICKER_GRAD.getMonth()+PICKER_GRAD.getDayOfMonth()));
        //Integer m = Integer.parseInt(
         //       PICKER.getYear() + "" + PICKER.getMonth() + "" + PICKER.getDayOfMonth()
       // );
        if(db.updateCollege(degree.getDatabaseID(), I, loc, fos, lev, sD.intValue(), gD.intValue()) == false) toastNotification("Update done Failed");
        //toastNotification("Degree Updated");
        clearData();
        finish();
    }
}
