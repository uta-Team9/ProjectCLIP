package team9.clip_loginhomecareer;

import java.util.ArrayList;
import java.util.Locale;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class FinanceGoalView extends ActionBarActivity implements ActionBar.TabListener {
    private DatabaseContract db;
    private ListView shortGoalList;
    private ListView longGoalList;
    private ArrayList<String> shortlist;// = new ArrayList<>();
    private ArrayList<String> longlist;// = new ArrayList<>();
    private boolean isShortTerm;

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.finance_goal_view);
        db = new DatabaseContract(this);
        db.open();

        shortlist = new ArrayList<>();
        longlist = new ArrayList<>();
        buildList();

        // Set up the action bar.
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        // When swiping between different sections, select the corresponding
        // tab. We can also use ActionBar.Tab#select() to do this if we have
        // a reference to the Tab.
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
                switch(position) {
                    case(0):
                        setContentView(R.layout.finance_short_term_view);
                        break;
                    case(1):
                        setContentView(R.layout.finance_long_term_view);
                        break;
                }
            }
        });

        // For each of the sections in the app, add a tab to the action bar.
        for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
            // Create a tab with text corresponding to the page title defined by
            // the adapter. Also specify this Activity object, which implements
            // the TabListener interface, as the callback (listener) for when
            // this tab is selected.
            actionBar.addTab(
                    actionBar.newTab()
                            .setText(mSectionsPagerAdapter.getPageTitle(i))
                            .setTabListener(this));
        }
    }

    protected void onDestroy() {
        db.close();
        super.onDestroy();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_finance_goal_view, menu);
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

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // When the given tab is selected, switch to the corresponding page in
        // the ViewPager.
        mViewPager.setCurrentItem(tab.getPosition());
        if(tab.getPosition()==1)
        {
            longGoalList = (ListView) findViewById(R.id.lst_long_goal);
            setLongList();
        }
        else
        {
            shortGoalList = (ListView) findViewById(R.id.lst_short_goal);
            setSortList();
        }
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return "Short Term".toUpperCase(l);
                case 1:
                    return "Long Term".toUpperCase(l);
            }
            return null;
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.finance_short_term_view, container, false);
            return rootView;
        }
    }
    public void createNewInstance(View view) {
        Intent intent = null;
        intent = new Intent(this, FinanceGoalNew.class);
        startActivity(intent);
    }
    public void buildList(){
        Cursor c = db.getAllFinancialGoals();
        int month, day, year;
        boolean isShortTerm;
        boolean isFulFilled;
        String desc, notes;
        if(c.moveToFirst()) {
            do{
                if(c.getInt(1) == 0)
                    isFulFilled = false;
                else
                    isFulFilled = true;
                if(c.getInt(2) == 1)
                    isShortTerm = true;
                else
                    isShortTerm = false;
                desc = c.getString(3);
                month = c.getInt(5)+1;
                day = c.getInt(6);
                year = c.getInt(4);
                notes = c.getString(3);
                //4 2 1 3
                //{"ID", "IS_FULFILLED", "IS_SHORT_TERM", "DESCRIPTION", "YEAR", "MONTH", "DAY", "GOAL_NOTE", "USER_ID"};
                if(isShortTerm) {
                    shortlist.add(new String(desc + " " + month + "/" + day + "/" + year + " Notes: " + notes));
                }
                else {
                    longlist.add(new String(desc + " " + month + "/" + day + "/" + year + " Notes: " + notes));
                }
            }while(c.moveToNext());
        }
        c.close();
    }


    /*
    public void help()
    {
        shortGoalList = (ListView) findViewById(R.id.lst_short_goal);
        longGoalList = (ListView) findViewById(R.id.lst_long_goal);
        ArrayAdapter<String> arrayAdapterShort = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, shortlist);
        ArrayAdapter<String> arrayAdapterLong = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, longtlist);
        shortGoalList.setAdapter(arrayAdapterShort);
        longGoalList.setAdapter(arrayAdapterLong);
    }
    */
    private void setSortList() {
        //Possible to change simple_list_item_1 into our own xml object
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, shortlist);

        shortGoalList.setAdapter(arrayAdapter);

        shortGoalList.setClickable(true);
        shortGoalList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                //Intent intent = new Intent(this, FinanceGoalEditView.class);
                String test = shortlist.get(position);
                //intent.put(test);
                Log.d("selected item", test);
            }
        });
    }
    private void setLongList() {
        //Possible to change simple_list_item_1 into our own xml object
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, longlist);
        longGoalList.setAdapter(arrayAdapter);
        longGoalList.setClickable(true);
        longGoalList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                //Intent intent = new Intent(this, FinanceGoalEditView.class);
                String test = longlist.get(position);
                //intent.put(test);
                Log.d("selected item", test);
            }
        });
    }
}
