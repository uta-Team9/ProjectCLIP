package team9.clip_loginhomecareer;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by Edward on 3/24/2015.
 */
public final class LoginDatabaseContract {
	// To prevent someone from accidentally instantiating the contract class,
	// give it an empty constructor.
	public LoginDatabaseContract() {}

	public static abstract class Entries implements BaseColumns {
		public static final String TABLE_NAME = "Passwords";
		public static final String _ID = "ID";
		public static final String COLUMN_EMAIL = "Email";
		public static final String COLUMN_PASSWORD = "Password";
		public static final String COLUMN_SECRET_QUESTION = "Secret Question";
		public static final String COLUMN_SECRET_ANSWER = "Secret Answer";
	}

    public static abstract class EduEntries implements BaseColumns {
        public static final String TABLE_NAME = "Colleges";
        public static final String _ID = "UNI";
        public static final String COLUMN_INSTITUTION = "Institution Name";
    }


	public static abstract class ContactEntries implements BaseColumns {
		public static final String TABLE_NAME = "Contacts";
	}

    public static abstract class HealthTest implements BaseColumns
    {

    }


	private static final String TEXT_TYPE = " TEXT";
	private static final String COMMA_SEP = ",";

	private static final String SQL_CREATE_ENTRIES =
			"CREATE TABLE " + Entries.TABLE_NAME + " (" +
					Entries._ID + " INTEGER PRIMARY KEY," +
					Entries.COLUMN_EMAIL + TEXT_TYPE + COMMA_SEP +
					Entries.COLUMN_PASSWORD + TEXT_TYPE + COMMA_SEP+
					Entries.COLUMN_SECRET_QUESTION + TEXT_TYPE + COMMA_SEP+
					Entries.COLUMN_SECRET_ANSWER + TEXT_TYPE + COMMA_SEP
			//any other options for the create command
			;

	private static final String SQL_DELETE_ENTRIES =
			"DROP TABLE IF EXISTS " + Entries.TABLE_NAME;



	// If you change the database schema, you must increment the database version.
	public static final int DATABASE_VERSION = 2;
	public static final String DATABASE_NAME = "UserDatabase.db";

	public class DatabaseHelper extends SQLiteOpenHelper {
		public DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(SQL_CREATE_ENTRIES);
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
