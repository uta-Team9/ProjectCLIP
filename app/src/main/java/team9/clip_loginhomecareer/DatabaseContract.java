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
	private static final String DATABASE_NAME = "UserDatabase.db";
	private static final int DATABASE_VERSION = 4; //database not yet implemented in code
		//download and merge changes to update to current db before changing number
		//always save work! GitHub can be evil.

	//Separators for SQL Creation
	private static final String TEXT_TYPE = " TEXT";
	private static final String INT_TYPE = " INTEGER";
	private static final String COMMA_SEP = ",";

	//TODO: This is where we enter in information for database

	//LOGIN INFORMATION TABLE
	private static abstract class LoginEntries implements BaseColumns {
		public static final String TABLE_NAME = "Passwords";
		public static final String _ID = "ID";
		public static final String NAME = "Name";
		public static final String EMAIL = "Email";
		public static final String PASSWORD = "Password";
		public static final String SECRET_QUESTION = "SecretQuestion";
		public static final String SECRET_ANSWER = "SecretAnswer";
		public static final String APP_ID = "AppID";
		public static final String[] ALL_COLUMNS =
				{_ID, NAME, EMAIL, PASSWORD, SECRET_QUESTION, SECRET_ANSWER, APP_ID};
	}
	private static final String SQL_CREATE_LOGIN_ENTRIES =
			"CREATE TABLE " + LoginEntries.TABLE_NAME + " (" +
					LoginEntries._ID + " INTEGER PRIMARY KEY," +
					LoginEntries.NAME + TEXT_TYPE + COMMA_SEP +
					LoginEntries.EMAIL + TEXT_TYPE + COMMA_SEP +
					LoginEntries.PASSWORD + TEXT_TYPE + COMMA_SEP +
					LoginEntries.SECRET_QUESTION + INT_TYPE + COMMA_SEP +
					LoginEntries.SECRET_ANSWER + TEXT_TYPE + COMMA_SEP +
					LoginEntries.APP_ID + INT_TYPE
					+");";
	private static final String SQL_DELETE_LOGIN_ENTRIES =
			"DROP TABLE IF EXISTS " + LoginEntries.TABLE_NAME;



	/*
	 * CAREER SECTION
	 */

	//CONTACTS
	private static abstract class ContactEntries implements BaseColumns {
		public static final String TABLE_NAME = "Contacts";
		public static final String _ID = "ID";
		public static final String NAME = "Name";
		public static final String NUMBER = "Phone Number";
		public static final String EMAIL = "Email";
		public static final String USED = "Used";
		public static final String MET = "Met";
		public static final String HASH_ID = "Hash ID";
		public static final String[] ALL_COLUMNS =
				{_ID, NAME, NUMBER, EMAIL, USED, MET, HASH_ID};
	}
	private static final String SQL_CREATE_CONTRACT_ENTRIES =
			"CREATE TABLE " + ContactEntries.TABLE_NAME + " (" +
					ContactEntries._ID + " INTEGER PRIMARY KEY," +
					ContactEntries.NAME + TEXT_TYPE + COMMA_SEP +
					ContactEntries.NUMBER + INT_TYPE + COMMA_SEP +
					ContactEntries.EMAIL + TEXT_TYPE + COMMA_SEP +
					ContactEntries.USED + INT_TYPE + COMMA_SEP +
					ContactEntries.MET + INT_TYPE + COMMA_SEP +
					ContactEntries.HASH_ID + INT_TYPE +
					//add entries following instructions above
					");";
	private static final String SQL_DELETE_CONTRACT_ENTRIES =
			"DROP TABLE IF EXISTS " + ContactEntries.TABLE_NAME;

	//GOALS
	private static abstract class GoalEntries implements BaseColumns {
		public static final String TABLE_NAME = "Goals";
		public static final String _ID = "ID";
		public static final String DESCRIPTION = "Description";
		public static final String END_DATE = "End Date";
		public static final String TERM_LENGTH = "Term Length";
		public static final String HASH_ID = "Hash ID";
		public static final String[] ALL_COLUMNS =
				{_ID, DESCRIPTION, END_DATE, TERM_LENGTH, HASH_ID};
	}
	private static final String SQL_CREATE_GOAL_ENTRIES =
			"CREATE TABLE " + GoalEntries.TABLE_NAME + " (" +
					GoalEntries._ID + " INTEGER PRIMARY KEY," +
					//add entries following instructions above
					");";
	private static final String SQL_DELETE_GOAL_ENTRIES =
			"DROP TABLE IF EXISTS " + GoalEntries.TABLE_NAME;

	//IDENTITIES
	private static abstract class IdentityEntries implements BaseColumns {
		public static final String TABLE_NAME = "Identities";
		public static final String _ID = "ID";
		public static final String LOGIN = "Login";
		public static final String WEBSITE = "Website";
		public static final String PASSWORD = "Password";
		public static final String HASH_ID = "Hash ID";
		public static final String[] ALL_COLUMNS =
				{_ID, LOGIN, WEBSITE, PASSWORD, HASH_ID};
	}
	private static final String SQL_CREATE_IDENTITY_ENTRIES =
			"CREATE TABLE " + IdentityEntries.TABLE_NAME + " (" +
					IdentityEntries._ID + " INTEGER PRIMARY KEY," +
					//add entries following instructions above
					");";
	private static final String SQL_DELETE_IDENTITY_ENTRIES =
			"DROP TABLE IF EXISTS " + IdentityEntries.TABLE_NAME;

	//JOBS
	private static abstract class JobEntries implements BaseColumns {
		public static final String TABLE_NAME = "Job Searches";
		public static final String _ID = "ID";
		public static final String COMPANY = "Company";
		public static final String STATUS = "Status";
		public static final String APPLIED = "Applied";
		public static final String HASH_ID = "Hash ID";
		public static final String[] ALL_COLUMNS =
				{_ID, COMPANY, STATUS, APPLIED, HASH_ID};
	}
	private static final String SQL_CREATE_JOB_ENTRIES =
			"CREATE TABLE " + JobEntries.TABLE_NAME + " (" +
					JobEntries._ID + " INTEGER PRIMARY KEY," +
					//add entries following instructions above
					");";
	private static final String SQL_DELETE_JOB_ENTRIES =
			"DROP TABLE IF EXISTS " + JobEntries.TABLE_NAME;

	//COMPANIES
	private static abstract class CompanyEntries implements BaseColumns {
		public static final String TABLE_NAME = "Companies";
		public static final String _ID = "ID";
		public static final String NAME = "Name";
		public static final String ADDRESS = "Address";
		public static final String PHONE = "Phone";
		public static final String DESCRIPTION = "Description";
		public static final String APPLIED_DATE = "Applied Date";
		public static final String INTERVIEW_DATE = "Interview Date";
		public static final String HASH_ID = "Hash ID";
		public static final String[] ALL_COLUMNS =
				{_ID, NAME, ADDRESS, PHONE, DESCRIPTION, APPLIED_DATE, INTERVIEW_DATE, HASH_ID};
	}
	private static final String SQL_CREATE_COMPANY_ENTRIES =
			"CREATE TABLE " + CompanyEntries.TABLE_NAME + " (" +
					CompanyEntries._ID + " INTEGER PRIMARY KEY," +
					//add entries following instructions above
					");";
	private static final String SQL_DELETE_COMPANY_ENTRIES =
			"DROP TABLE IF EXISTS " + CompanyEntries.TABLE_NAME;



	/*
	 * EDUCATION SECTION
	 * TODO: Mary
	 */

	//COLLEGES
	private static abstract class EduEntries implements BaseColumns {
	    public static final String TABLE_NAME = "Colleges";
	    public static final String _ID = "ID";
	    public static final String COLUMN_INSTITUTION = "InstitutionName";
        public static final String COLUMN_COLLEGE_CITY = "InstitutionCity";
        public static final String COLUMN_STUDY_FIELD = "FieldStudy";
        public static final String COLUMN_COMPLETION_DATE = "GraduationDate";
        public static final String COLUMN_START_DATE = "StartDate";
        public static final String[] ALL_COLUMNS =
				{_ID, COLUMN_INSTITUTION, COLUMN_COLLEGE_CITY, COLUMN_STUDY_FIELD, COLUMN_COMPLETION_DATE,COLUMN_START_DATE};
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
	 * TODO: Ajoy
	 */


	/*
	 * HEALTH SECTION
	 * TODO: Ameera
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



	/**
	 * Add a new set of values to the database.
	 * @param name
	 * @param email
	 * @param password
	 * @param question
	 * @param answer
	 * @return The DB table _ID row number.
	 */
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

		int ID = 0, i = 0;
		for(char letter : email.toCharArray()) {
			ID += letter + i;
			i++;
		}

		initialValues.put(LoginEntries.APP_ID, ID);

		// Insert it into the database.
		return db.insert(LoginEntries.TABLE_NAME, null, initialValues);
	}

	// Delete a row from the database, by rowId (primary key)
	public boolean deleteLoginRow(long rowId) {
		String where = LoginEntries._ID + "=" + rowId;
		return db.delete(LoginEntries.TABLE_NAME, where, null) != 0;
	}

	public void deleteAllLoginRows() {
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
		Cursor c = 	db.query(LoginEntries.TABLE_NAME, LoginEntries.ALL_COLUMNS,
				where, null, null, null, null);
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

	public int getLoginAuthenticationID(long rowId) {
		int ID = 0;
		String where = LoginEntries._ID + "=" + rowId;
		String[] ALL_KEYS = LoginEntries.ALL_COLUMNS;
		Cursor c = 	db.query(true, LoginEntries.TABLE_NAME, ALL_KEYS,
				where, null, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		ID = c.getInt(LoginEntries.ALL_COLUMNS.length);
		c.close();
		return ID;
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


	//CONTACT METHODS
	/**
	 * Add a new set of values to the database.
	 * @param name
	 * @param email
	 * @param phone
	 * @param met
	 * @param used
	 * @param user
	 * @return The DB table _ID row number.
	 */
	public long insertContact(String name, String email, int phone,	int met, int used, int user) {
		/*
		 * CHANGE 3:
		 */
		// TODO: Update data in the row with new fields.
		// TODO: Also change the function's arguments to be what you need!
		// Create row's data:
		ContentValues initialValues = new ContentValues();
		initialValues.put(ContactEntries.NAME, name);
		initialValues.put(ContactEntries.EMAIL, email);
		initialValues.put(ContactEntries.NUMBER, phone);
		initialValues.put(ContactEntries.MET, met);
		initialValues.put(ContactEntries.USED, used);
		initialValues.put(ContactEntries.HASH_ID, user);

		// Insert it into the database.
		return db.insert(ContactEntries.TABLE_NAME, null, initialValues);
	}

	// Delete a row from the database, by rowId (primary key)
	public boolean deleteContact(long rowId) {
		String where = ContactEntries._ID + "=" + rowId;
		return db.delete(ContactEntries.TABLE_NAME, where, null) != 0;
	}

	public void deleteAllContacts() {
		Cursor c = getAllContacts();
		long rowId = c.getColumnIndexOrThrow(ContactEntries._ID);
		if (c.moveToFirst()) {
			do {
				deleteContact(c.getLong((int) rowId));
			} while (c.moveToNext());
		}
		c.close();
	}

	// Return all data in the database.
	public Cursor getAllContacts() {
		String where = null;
		Cursor c = 	db.query(ContactEntries.TABLE_NAME, ContactEntries.ALL_COLUMNS,
				where, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}

	// Get a specific row (by rowId)
	public Cursor getContact(long rowId) {
		String where = ContactEntries._ID + "=" + rowId;
		String[] ALL_KEYS = ContactEntries.ALL_COLUMNS;
		Cursor c = 	db.query(true, ContactEntries.TABLE_NAME, ALL_KEYS,
				where, null, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}

	//HELPER CLASS, Do not mess with this.
	// Change an existing row to be equal to new data.
	public boolean updateContact(long rowId, String name, String email,
	                             int phone,	int met, int used) {
		String where = ContactEntries._ID + "=" + rowId;

		/*
		 * CHANGE 4:
		 */
		// TODO: Update data in the row with new fields.
		// TODO: Also change the function's arguments to be what you need!
		// Create row's data:
		ContentValues newValues = new ContentValues();
		newValues.put(ContactEntries.NAME, name);
		newValues.put(ContactEntries.EMAIL, email);
		newValues.put(ContactEntries.NUMBER, phone);
		newValues.put(ContactEntries.MET, met);
		newValues.put(ContactEntries.USED, used);


		// Insert it into the database.
		return db.update(ContactEntries.TABLE_NAME, newValues, where, null) != 0;
	}







	public class DatabaseHelper extends SQLiteOpenHelper {
		public DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(SQL_CREATE_LOGIN_ENTRIES);
			//career
			db.execSQL(SQL_CREATE_CONTRACT_ENTRIES);
			/*db.execSQL(SQL_CREATE_COMPANY_ENTRIES);
			db.execSQL(SQL_CREATE_GOAL_ENTRIES);
			db.execSQL(SQL_CREATE_IDENTITY_ENTRIES);
			db.execSQL(SQL_CREATE_JOB_ENTRIES);
			//education
			db.execSQL(SQL_CREATE_Edu_Entries);*/
			//health
			//finance
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// This database is only a cache for online data, so its upgrade policy is
			// to simply to discard the data and start over
			db.execSQL(SQL_DELETE_LOGIN_ENTRIES);
			db.execSQL(SQL_DELETE_CONTRACT_ENTRIES);
			/*db.execSQL(SQL_DELETE_COMPANY_ENTRIES);
			db.execSQL(SQL_DELETE_GOAL_ENTRIES);
			db.execSQL(SQL_DELETE_JOB_ENTRIES);
			db.execSQL(SQL_DELETE_IDENTITY_ENTRIES);*/
			onCreate(db);
		}

		public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			onUpgrade(db, oldVersion, newVersion);
		}
	}

}
