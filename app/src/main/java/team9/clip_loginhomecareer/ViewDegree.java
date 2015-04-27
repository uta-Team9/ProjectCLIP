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


public class ViewDegree extends ActionBarActivity {
    private int User_ID;
    private Degree degree = null;
    private DatabaseContract db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        openDB();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_degree);
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            db = new DatabaseContract(this);
            db.open();
            degree = (Degree) extras.getSerializable("Degree");

            setTitle(degree.getCollege());
            setUpTextBoxes();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_degree, menu);
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
        db = new DatabaseContract(this);
        db.open();
        User_ID = getSharedPreferences("loginPrefs", MODE_PRIVATE).getInt("ID", -1);
        Log.d("ID in ViewContact", "" + User_ID);
    }
    private void setUpTextBoxes() {
        if(degree != null) {
            TextView text = (TextView) findViewById(R.id.edu_detail_college);
            text.setText("" + degree.getCollege());
            text = (TextView) findViewById(R.id.edu_detail_location);
            text.setText("" + degree.getLocation());
            text = (TextView) findViewById(R.id.edu_detail_fos);
            text.setText("" + degree.getStudy_field());
            text = (TextView) findViewById(R.id.edu_detail_matr);
            text.setText("" + degree.getStart_date());
            text = (TextView) findViewById(R.id.edu_detail_gradDate);
            text.setText("" + degree.getGrad_date());
            text = (TextView) findViewById(R.id.edu_detail_degree_level);
            text.setText("" + degree.getDegree_type());
        }
    }

    public void editInstance(View v) {
        startActivity(new Intent(this, new_degree_activity.class).putExtra("Degree", degree));
    }
    private void toast(String description) {
        Toast.makeText(getApplicationContext(), description, Toast.LENGTH_LONG).show();
    }
    public void deleteInstance(View v) {
        if(db.deleteCollege(degree.getDatabaseID())) {
            toast("Degree removed");
            finish();
        } else
            toast("Degree already removed");
    }
}
