package team9.clip_loginhomecareer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by Edward on 3/24/2015.
 */
public final class DatabaseContract {

	//Database Name and Version Number. Change V# if you add new columns
	public static final String DATABASE_NAME = "UserDatabase.db";
	public static final int DATABASE_VERSION = 2;
		//download and merge changes to update to current db before changing number
		//always save work! GitHub can be evil.

	//Separators for SQL Creation
	private static final String TEXT_TYPE = " TEXT";
	private static final String INT_TYPE = " INTEGER";
	private static final String COMMA_SEP = ",";

	//TODO: This is where we enter in information for database

	//LOGIN INFORMATION TABLE
	public static abstract class LoginEntries implements BaseColumns {
		public static final String TABLE_NAME = "Passwords";
		public static final String _ID = "ID";
		public static final String NAME = "Name";
		public static final String EMAIL = "Email";
		public static final String PASSWORD = "Password";
		public static final String SECRET_QUESTION = "Secret Question";
		public static final String SECRET_ANSWER = "Secret Answer";
		public static final String[] ALL_COLUMNS =
				{_ID, NAME, EMAIL, PASSWORD, SECRET_QUESTION, SECRET_ANSWER};
	}

	/*
	 * CAREER SECTION
	 */


	//CONTACTS
	public static abstract class ContactEntries implements BaseColumns {
		public static final String TABLE_NAME = "Contacts";
		public static final String _ID = "ID";
		public static final String NAME = "Name";
		public static final String NUMBER = "Phone Number";
		public static final String EMAIL = "Email";
		public static final String USED = "Used";
		public static final String MET = "Met";
		public static final String[] ALL_COLUMNS =
				{_ID, NAME, NUMBER, EMAIL, USED, MET};
	}
	//GOALS
	//IDENTITIES
	//JOBS
	//COMPANIES

	// TODO: Place your fields here!
	// + KEY{...} + " {type} not null"
	//	- Key is the column name you created above.
	//	- {type} is one of: text, integer, real, blob
	//		(http://www.sqlite.org/datatype3.html)
	//  - "not null" means it is a required field (must be given a value).
	// NOTE: All must be comma separated (end of line!) Last one must have NO comma!!
	private static final String SQL_CREATE_LOGIN_ENTRIES =
			"CREATE TABLE " + LoginEntries.TABLE_NAME + " (" +
					LoginEntries._ID + " INTEGER PRIMARY KEY," +
					LoginEntries.NAME + TEXT_TYPE + COMMA_SEP +
					LoginEntries.EMAIL + TEXT_TYPE + COMMA_SEP +
					LoginEntries.PASSWORD + TEXT_TYPE + COMMA_SEP +
					LoginEntries.SECRET_QUESTION + INT_TYPE + COMMA_SEP +
					LoginEntries.SECRET_ANSWER + TEXT_TYPE
			//any other options for the create command
			+");";

	private static final String SQL_DELETE_ENTRIES =
			"DROP TABLE IF EXISTS " + LoginEntries.TABLE_NAME;


	/*
	 * EDUCATION SECTION
	 */

	//COLLEGES
	public static abstract class EduEntries implements BaseColumns {
	    public static final String TABLE_NAME = "Colleges";
	    public static final String _ID = "ID";
	    public static final String COLUMN_INSTITUTION = "Institution Name";
		public static final String[] ALL_COLUMNS = {};
	}

	// TODO: Place your fields here!
	// + KEY{...} + " {type} not null"
	//	- Key is the column name you created above.
	//	- {type} is one of: text, integer, real, blob
	//		(http://www.sqlite.org/datatype3.html)
	//  - "not null" means it is a required field (must be given a value).
	// NOTE: All must be comma separated (end of line!) Last one must have NO comma!!
	private static final String SQL_CREATE_Edu_Entries =
			"CREATE TABLE " + EduEntries.TABLE_NAME + " (" +
					EduEntries._ID + " INTEGER PRIMARY KEY," +
					//add entries following instructions above
			");";

	//delete statement
	private static final String SQL_DELETE_Edu_Entries =
			"DROP TABLE IF EXISTS " + EduEntries.TABLE_NAME;


	/*
	 * FINANCE SECTION
	 */


	/*
	 * HEALTH SECTION
	 */



	/*
	 * PUBLIC METHODS
	 */

	// Context of application who uses us.
	private final Context context;

	private DatabaseHelper myDBHelper;
	private SQLiteDatabase db;

	// To prevent someone from accidentally instantiating the contract class,
	// give it an empty constructor.
	//public DatabaseContract() {}

	public DatabaseContract(Context ctx) {
		this.context = ctx;
		myDBHelper = new DatabaseHelper(context);
	}

	// Open the database connection.
	public DatabaseContract open() {
		db = myDBHelper.getWritableDatabase();
		return this;
	}

	// Close the database connection.
	public void close() {
		myDBHelper.close();
	}

	// Add a new set of values to the database.
	public long insertLoginRow(
			String name, String email, String password,
			int question, String answer) {
		/*
		 * CHANGE 3:
		 */
		// TODO: Update data in the row with new fields.
		// TODO: Also change the function's arguments to be what you need!
		// Create row's data:
		ContentValues initialValues = new ContentValues();
		initialValues.put(LoginEntries.NAME, name);
		initialValues.put(LoginEntries.EMAIL, email);
		initialValues.put(LoginEntries.PASSWORD, password);
		initialValues.put(LoginEntries.SECRET_QUESTION, question);
		initialValues.put(LoginEntries.SECRET_ANSWER, answer);

		// Insert it into the database.
		return db.insert(LoginEntries.TABLE_NAME, null, initialValues);
	}

	// Delete a row from the database, by rowId (primary key)
	public boolean deleteLoginRow(long rowId) {
		String where = LoginEntries._ID + "=" + rowId;
		return db.delete(LoginEntries.TABLE_NAME, where, null) != 0;
	}

	public void deleteAll() {
		Cursor c = getAllLoginRows();
		long rowId = c.getColumnIndexOrThrow(LoginEntries._ID);
		if (c.moveToFirst()) {
			do {
				deleteLoginRow(c.getLong((int) rowId));
			} while (c.moveToNext());
		}
		c.close();
	}

	// Return all data in the database.
	public Cursor getAllLoginRows() {
		String where = null;
		Cursor c = 	db.query(true, LoginEntries.TABLE_NAME, LoginEntries.ALL_COLUMNS,
				where, null, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}

	// Get a specific row (by rowId)
	public Cursor getLoginRow(long rowId) {
		String where = LoginEntries._ID + "=" + rowId;
		String[] ALL_KEYS = LoginEntries.ALL_COLUMNS;
		Cursor c = 	db.query(true, LoginEntries.TABLE_NAME, ALL_KEYS,
				where, null, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}

	// Change an existing row to be equal to new data.
	public boolean updateLoginRow(long rowId, String name, String email,
	                              String password, int question, String answer) {
		String where = LoginEntries._ID + "=" + rowId;

		/*
		 * CHANGE 4:
		 */
		// TODO: Update data in the row with new fields.
		// TODO: Also change the function's arguments to be what you need!
		// Create row's data:
		ContentValues newValues = new ContentValues();
		newValues.put(LoginEntries.NAME, name);
		newValues.put(LoginEntries.EMAIL, email);
		newValues.put(LoginEntries.PASSWORD, password);
		newValues.put(LoginEntries.SECRET_QUESTION, question);
		newValues.put(LoginEntries.SECRET_ANSWER, answer);

		// Insert it into the database.
		return db.update(LoginEntries.TABLE_NAME, newValues, where, null) != 0;
	}




	//HELPER CLASS, Do not mess with this.
	public class DatabaseHelper extends SQLiteOpenHelper {
		public DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(SQL_CREATE_LOGIN_ENTRIES);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// This database is only a cache for online data, so its upgrade policy is
			// to simply to discard the data and start over
			db.execSQL(SQL_DELETE_ENTRIES);
			onCreate(db);
		}

		public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			onUpgrade(db, oldVersion, newVersion);
		}
	}

}
