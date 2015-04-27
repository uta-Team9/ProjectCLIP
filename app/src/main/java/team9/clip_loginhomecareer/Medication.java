package team9.clip_loginhomecareer;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class Medication extends ActionBarActivity {
    private long dbUserRow = 0;
    private int User_ID = 0;
    private DatabaseContract db;
    private boolean hasData = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        openDB();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medication);

        Cursor c = db.getAllMedications();
        if(c.moveToFirst())
        {
            do if(c.getInt(7) == User_ID) {
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
        getMenuInflater().inflate(R.menu.menu_medication, menu);
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

    public void buildFields(Cursor c)
    {
        EditText text;
        text = (EditText) findViewById(R.id.editText9);
        text.setText(c.getString(1));

        text = (EditText) findViewById(R.id.editText4);
        text.setText(c.getString(2));

        text = (EditText) findViewById(R.id.editText5);
        text.setText(c.getString(3));

        text = (EditText) findViewById(R.id.editText6);
        text.setText(c.getString(4));

        text = (EditText) findViewById(R.id.editText);
        text.setText(c.getString(5));

        text = (EditText) findViewById(R.id.editText7);
        text.setText(c.getString(6));
    }

    public void add_new(View v) {
        EditText text;
        if (validItems()) {
            text = (EditText) findViewById(R.id.editText9);
            String medName = text.getText().toString();
            text = (EditText) findViewById(R.id.editText4);
            String dosage = text.getText().toString();
            text = (EditText) findViewById(R.id.editText5);
            String medDuration = text.getText().toString();
            text = (EditText) findViewById(R.id.editText6);
            String medReason = text.getText().toString();
            text = (EditText) findViewById(R.id.editText);
            String pharmName = text.getText().toString();
            text = (EditText) findViewById(R.id.editText7);
            String pharmPhone = text.getText().toString();


            if(!hasData) {
                Log.d("Stored info for User ID", "" + User_ID);
                dbUserRow = db.insertMedication( medName, dosage,medDuration,medReason, pharmName, pharmPhone, User_ID);
                hasData = true;
            } else {
                Log.d("Updated for User ID", ""+User_ID);
                db.updateMedication(dbUserRow, medName, dosage,medDuration,medReason, pharmName, pharmPhone);
            }

            toastNotification("Medication Saved");
            //  clearData();
        }

        else
        {
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
        text = (EditText) findViewById(R.id.editText9);
        text.setText("");
        text = (EditText) findViewById(R.id.editText4);
        text.setText("");
        text = (EditText) findViewById(R.id.editText5);
        text.setText("");
        text = (EditText) findViewById(R.id.editText6);
        text.setText("");
        text = (EditText) findViewById(R.id.editText);
        text.setText("");
        text = (EditText) findViewById(R.id.editText7);
        text.setText("");

    }

}
