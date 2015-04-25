package team9.clip_loginhomecareer;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class HomeScreen extends ActionBarActivity implements ActionBar.TabListener {
	//database stuff
	private int User_ID;
	private DatabaseContract db;

	//Tab stuff
	SectionsPagerAdapter mSectionsPagerAdapter;
	ViewPager mViewPager;

	//drawer stuff
	private String[] mCLIPModules = {"Career","Education","Health","Finance"};
	private DrawerLayout drawerLayout;
	private ListView drawerList;
	private ActionBarDrawerToggle mDrawerToggle;

	//
	private ListView taskList;
	private ArrayList<String> list;
	private boolean built = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		openDB();

		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_calendar_activity);

		// Set up tabs
		setupApplicationTabs();
		// Set up Drawer
		//setupApplicationDrawer();
		// Set up task view list
	}

	@Override
	protected void onDestroy() {
		db.close();
		super.onDestroy();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		Intent intent = null;

		switch(id) {
			case(R.id.action_settings):
				intent = new Intent(this, Settings.class);
				break;
			case(R.id.action_Career):
				intent = new Intent(this, CareerHome.class);
				break;
			case(R.id.action_Finance):
				intent = new Intent(this, FinanceHome.class);
				break;
			case(R.id.action_Health):
                intent = new Intent(this, HealthHomePage.class);
				break;
			case(R.id.action_Education):
				intent = new Intent(this, EduMain.class);
				break;
		}

		if(intent != null) {
			intent.putExtra("ID", User_ID);
			startActivity(intent);
		}

		return true;
	}

	/**
	 * Set up the application tabs
	 */
	private void setupApplicationTabs() {
		final ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the activity.
		mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.calendar_task_pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

		// When swiping between different sections, select the corresponding
		// tab. We can also use ActionBar.Tab#select() to do this if we have
		// a reference to the Tab.
		mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				//actionBar.setSelectedNavigationItem(position);
				switch(position) {
					case(0):
						setContentView(R.layout.calendar_view);
						break;
					case(1):
						setContentView(R.layout.task_view);
						//TODO: populate list
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

	@Override
	public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
		// When the given tab is selected, switch to the corresponding page in
		// the ViewPager.
		mViewPager.setCurrentItem(tab.getPosition());
		if(tab.getPosition()==1) {
			if(!built) {
				buildList();
				built = true;
			}
			taskList = (ListView) findViewById(R.id.task_view_list);
			setList();
		}
	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {	}

	@Override
	public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {	}

	/**
	 * Setup the application drawer
	 */
	private void setupApplicationDrawer() {
		drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		drawerList = (ListView) findViewById(R.id.home_screen_drawer);
		ArrayAdapter<String> temp = new ArrayAdapter<>(
				this,
				android.R.layout.simple_list_item_1,
				mCLIPModules);
		drawerList.setAdapter(temp);
		drawerList.setOnItemClickListener(new DrawerItemClickListener());

		mDrawerToggle = new ActionBarDrawerToggle(
				this,                  /* host Activity */
				drawerLayout,         /* DrawerLayout object */
				//R.mipmap.ic_launcher,  /* nav drawer icon to replace 'Up' caret */
				R.string.drawer_open,  /* "open drawer" description */
				R.string.drawer_close  /* "close drawer" description */
		) {	};

		drawerLayout.setDrawerListener(mDrawerToggle);
	}

	public void viewContactsList(View v) {
		startActivity(new Intent(this, ContactList.class));
	}

	private void openDB() {
		User_ID = getSharedPreferences("loginPrefs", MODE_PRIVATE).getInt("ID", -1);
		Log.d("Home Screen ID", "" + User_ID);

		db = new DatabaseContract(this);
		db.open();
	}

	/**
	 * Changes the view after selecting an item in the APP DRAWER
	 */
	private void selectItem(int position) {
		// Highlight the selected item, update the title, and close the drawer
		drawerList.setItemChecked(position, true);
		Intent intent = null;

		switch(mCLIPModules[position]) {
			case("Career"):
				intent = new Intent(this, CareerHome.class);
				break;
			case("Finance"):
				intent = new Intent(this, FinanceHome.class);
				break;
			case("Health"):
				intent = new Intent(this, HealthHomePage.class);
				break;
			case("Education"):
				intent = new Intent(this, EduMain.class);
				break;
		}
		drawerLayout.closeDrawer(drawerList);

		if(intent != null) {
			startActivity(intent);
		}
	}

	/** List Methods */
	private void buildList() {
		list = new ArrayList<>();
		Cursor cursor = db.getAllCareerGoals();
		if(cursor.moveToFirst()) {
			do if(cursor.getInt(5) == User_ID) {
				list.add(new String(cursor.getString(1)));
			} while(cursor.moveToNext());
		}

		cursor = db.getAllFinancialGoals();
		if(cursor.moveToFirst()) {
			do if(cursor.getInt(6) == 0) {
				list.add(new String(cursor.getString(3)));
			} while(cursor.moveToNext());
		}

		cursor.close();
	}

	private void setList() {
		//Possible to change simple_list_item_1 into our own xml object
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
				this, android.R.layout.simple_list_item_1, list);

		taskList.setAdapter(arrayAdapter);

		taskList.setClickable(true);
		taskList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
				//moveToView(position);
			}
		});

	}

	/**
	 * Send a toast notification to the user
	 * @param description
	 */
	private void toastNotification(String description) {
		Toast.makeText(getApplicationContext(), description, Toast.LENGTH_LONG).show();
	}

	/** INTERNAL CLASSES FOR APP NAVIGATION */

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
			// Show 2 total pages.
			return 2;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
				case 0:
					return getString(R.string.title_section1).toUpperCase(l);
				case 1:
					return getString(R.string.title_section2).toUpperCase(l);
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
			View rootView = inflater.inflate(R.layout.calendar_view, container, false);
			return rootView;
		}
	}

	/**
	 *
	 */
	private class DrawerItemClickListener implements ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView parent, View view, int position, long id) {
			selectItem(position);
		}
	}
}
