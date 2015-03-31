package team9.clip_loginhomecareer;

import android.provider.BaseColumns;

/**
 * Created by Edward on 3/24/2015.
 */
public class ClipDatabase {
	// To prevent someone from accidentally instantiating the contract class,
	// give it an empty constructor.
	public ClipDatabase() {}

	public static abstract class Entries implements BaseColumns {
		public static final String TABLE_NAME = "User Info";
		public static final String COLUMN_EMAIL = "Email";
		public static final String _ID = "ID";

	}

	private static final String TEXT_TYPE = " TEXT";
	private static final String COMMA_SEP = ",";

	private static final String SQL_CREATE_ENTRIES =
			"CREATE TABLE " + Entries.TABLE_NAME + " (" +
					Entries._ID + " INTEGER PRIMARY KEY," +
					Entries.COLUMN_EMAIL + TEXT_TYPE + COMMA_SEP
			//any other options for the create command
			;

	private static final String SQL_DELETE_ENTRIES =
			"DROP TABLE IF EXISTS " + Entries.TABLE_NAME;
}
