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
    private static final int DATABASE_VERSION = 8; //database not yet implemented in code
    //download and merge changes to update to current db before changing number
    //always save work! GitHub can be evil.

    //Separators for SQL Creation
    private static final String TEXT_TYPE = " TEXT";
    private static final String INT_TYPE = " INTEGER";
    private static final String DOUBLE_TYPE = " DOUBLE";
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

                    + ");";
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
        public static final String NUMBER = "PhoneNumber";
        public static final String EMAIL = "Email";
        public static final String USED = "Used";
        public static final String MET = "Met";
        public static final String HASH_ID = "HashID";
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
        public static final String HASH_ID = "HashID";
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
        public static final String HASH_ID = "HashID";
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
        public static final String TABLE_NAME = "JobSearches";
        public static final String _ID = "ID";
        public static final String COMPANY = "Company";
        public static final String STATUS = "Status";
        public static final String APPLIED = "Applied";
        public static final String HASH_ID = "HashID";
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
        public static final String HASH_ID = "HashID";
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
	private static abstract class CollegeEntries implements BaseColumns {
	    public static final String TABLE_NAME = "Colleges";
	    public static final String _ID = "ID";
	    public static final String COLUMN_INSTITUTION = "InstitutionName";
        public static final String COLUMN_COLLEGE_CITY = "InstitutionCity";
        public static final String COLUMN_STUDY_FIELD = "FieldOfStudy";
        public static final String COLUMN_DEGREE = "Degree";
		public static final String COLUMN_START_DATE = "StartDate";
        public static final String COLUMN_COMPLETION_DATE = "GraduationDate";
		public static final String COLUMN_USER_ID = "User_ID";
        public static final String[] ALL_COLUMNS =
				{_ID, COLUMN_INSTITUTION, COLUMN_COLLEGE_CITY, COLUMN_STUDY_FIELD,
						COLUMN_DEGREE, COLUMN_COMPLETION_DATE, COLUMN_USER_ID};
	}
	// TODO: Place your fields here!
	// + KEY{...} + " {type} not null"
	//	- Key is the column name you created above.
	//	- {type} is one of: text, integer, real, blob
	//		(http://www.sqlite.org/datatype3.html)
	//  - "not null" means it is a required field (must be given a value).
	// NOTE: All must be comma separated (end of line!) Last one must have NO comma!!
	private static final String SQL_CREATE_COLLEGE_Entries =
			"CREATE TABLE " + CollegeEntries.TABLE_NAME + " (" +
					CollegeEntries._ID + " INTEGER PRIMARY KEY," +
					CollegeEntries.COLUMN_INSTITUTION + TEXT_TYPE + COMMA_SEP +
					CollegeEntries.COLUMN_COLLEGE_CITY + TEXT_TYPE + COMMA_SEP +
					CollegeEntries.COLUMN_STUDY_FIELD + TEXT_TYPE + COMMA_SEP +
                    CollegeEntries.COLUMN_DEGREE + TEXT_TYPE + COMMA_SEP +
					CollegeEntries.COLUMN_START_DATE + INT_TYPE + COMMA_SEP +
					CollegeEntries.COLUMN_COMPLETION_DATE + INT_TYPE + COMMA_SEP +
					CollegeEntries.COLUMN_USER_ID + INT_TYPE +
					//add entries following instructions above
			");";
    private static final String SQL_DELETE_COLLEGE_Entries =
            "DROP TABLE IF EXISTS " + CollegeEntries.TABLE_NAME;

	//Applications
	private static abstract class ApplicationEntries implements BaseColumns {
		public static final String TABLE_NAME = "EducationApplications";
		public static final String _ID = "ID";
		public static final String COLUMN_COLLEGE = "College";
		public static final String COLUMN_DUE_DATE = "DueDate";
		public static final String COLUMN_REPLY_DATE = "ReplyDate";
		public static final String COLUMN_USER_ID = "User_ID";
		public static final String[] ALL_COLUMNS =
				{_ID, COLUMN_COLLEGE, COLUMN_DUE_DATE, COLUMN_REPLY_DATE, COLUMN_USER_ID};
	}
	// TODO: Place your fields here!
	// + KEY{...} + " {type} not null"
	//	- Key is the column name you created above.
	//	- {type} is one of: text, integer, real, blob
	//		(http://www.sqlite.org/datatype3.html)
	//  - "not null" means it is a required field (must be given a value).
	// NOTE: All must be comma separated (end of line!) Last one must have NO comma!!
	private static final String SQL_CREATE_APPLICATION_ENTRIES =
			"CREATE TABLE " + ApplicationEntries.TABLE_NAME + " (" +
					ApplicationEntries._ID + " INTEGER PRIMARY KEY," +
					ApplicationEntries.COLUMN_COLLEGE + TEXT_TYPE + COMMA_SEP +
					ApplicationEntries.COLUMN_DUE_DATE + INT_TYPE + COMMA_SEP +
					ApplicationEntries.COLUMN_REPLY_DATE + INT_TYPE + COMMA_SEP +
					ApplicationEntries.COLUMN_USER_ID + INT_TYPE +
					//add entries following instructions above
					");";
	private static final String SQL_DELETE_APPLICATION_ENTRIES =
			"DROP TABLE IF EXISTS " + ApplicationEntries.TABLE_NAME;

	//CollegeFinances
	private static abstract class CollegeFinanceEntries implements BaseColumns {
		public static final String TABLE_NAME = "EducationFinances";
		public static final String _ID = "ID";
		public static final String COLUMN_AWARD_NAME = "AwardName";
		public static final String COLUMN_AMOUNT = "Amount";
		public static final String COLUMN_PERIOD = "Period";
		public static final String COLUMN_CONDITION = "Condition";
		public static final String COLUMN_USER_ID = "User_ID";
		public static final String[] ALL_COLUMNS =
				{_ID, COLUMN_AWARD_NAME, COLUMN_AMOUNT, COLUMN_PERIOD, COLUMN_CONDITION, COLUMN_USER_ID};
	}
	// TODO: Place your fields here!
	// + KEY{...} + " {type} not null"
	//	- Key is the column name you created above.
	//	- {type} is one of: text, integer, real, blob
	//		(http://www.sqlite.org/datatype3.html)
	//  - "not null" means it is a required field (must be given a value).
	// NOTE: All must be comma separated (end of line!) Last one must have NO comma!!
	private static final String SQL_CREATE_FINANCE_ENTRIES =
			"CREATE TABLE " + CollegeFinanceEntries.TABLE_NAME + " (" +
					CollegeFinanceEntries._ID + " INTEGER PRIMARY KEY," +
					CollegeFinanceEntries.COLUMN_AWARD_NAME + TEXT_TYPE + COMMA_SEP +
					CollegeFinanceEntries.COLUMN_AMOUNT + INT_TYPE + COMMA_SEP +
					CollegeFinanceEntries.COLUMN_PERIOD + TEXT_TYPE + COMMA_SEP +
					CollegeFinanceEntries.COLUMN_CONDITION + TEXT_TYPE + COMMA_SEP +
					CollegeFinanceEntries.COLUMN_USER_ID + INT_TYPE +
					//add entries following instructions above
					");";
	private static final String SQL_DELETE_FINANCE_ENTRIES =
			"DROP TABLE IF EXISTS " + CollegeFinanceEntries.TABLE_NAME;

    /*
	 * FINANCE SECTION
	 * TODO: Ajoy
	 */
    //Cash Section
    private static abstract class CashEntries implements BaseColumns {
        public static final String TABLE_NAME = "CASH";
        public static final String _ID = "ID";
        public static final String COLUMN_CASH_AMOUNT = "CASH_AMOUNT";
        public static final String COLUMN_SOURCE = "SOURCE";
        public static final String COLUMN_NOTE = "NOTE";
        public static final String COLUMN_DATE = "DATE";
        public static final String COLUMN_USER_ID = "USER_ID";
        public static final String[] ALL_COLUMNS =
                {_ID, COLUMN_CASH_AMOUNT, COLUMN_SOURCE, COLUMN_NOTE, COLUMN_DATE, COLUMN_USER_ID};
    }

    // TODO: Place your fields here!
    // + KEY{...} + " {type} not null"
    //	- Key is the column name you created above.
    //	- {type} is one of: text, integer, real, blob
    //		(http://www.sqlite.org/datatype3.html)
    //  - "not null" means it is a required field (must be given a value).
    // NOTE: All must be comma separated (end of line!) Last one must have NO comma!!
    private static final String SQL_CREATE_CASH_ENTRIES =
            "CREATE TABLE " + CashEntries.TABLE_NAME + " (" +
                    CashEntries._ID + " INTEGER PRIMARY KEY," +
                    CashEntries.COLUMN_CASH_AMOUNT + DOUBLE_TYPE + COMMA_SEP +
                    CashEntries.COLUMN_SOURCE + TEXT_TYPE + COMMA_SEP +
                    CashEntries.COLUMN_NOTE + TEXT_TYPE + COMMA_SEP +
                    CashEntries.COLUMN_DATE + INT_TYPE + COMMA_SEP +
                    CashEntries.COLUMN_USER_ID + INT_TYPE +
                    //add entries following instructions above
                    ");";
    //delete statement
    private static final String SQL_DELETE_CASH_ENTRIES =
            "DROP TABLE IF EXISTS " + CashEntries.TABLE_NAME;


    //HEALTH

    private static abstract class DoctorVisitEntries implements BaseColumns {

        public static final String TABLE_NAME = "Doctor's Visit";
        public static final String LAST_CHECK_UP_DATE = "Last Check Up Date";
        public static final String HEALTH_INSURANCE_COMPANY = "Health Insurance Company";
        public static final String HEALTH_INSURANCE_POLICY_NUM = "Health Insurance Policy Number";

    }

    public static abstract class MedicationEntries implements BaseColumns {
        public static final String TABLE_NAME = "Medication";
        public static final String MEDICATION_NAME = "Medication Name";
        public static final String DOSAGE = "Dosage";
        public static final String MEDICATION_DURATION = "Medication Duration";
        public static final String MEDICATION_REASON = "Med Reason";
        public static final String PHARMACY_NAME = "Pharmacy Name";
        public static final String PHARMACY_PHONE = "Pharmacy Phone";
    }

    public static abstract class MedicalReportEntries implements BaseColumns {
        public static final String TABLE_NAME = "Medical Report";
        public static final String BLOOD_PRESSURE = "Blood Pressure";
        public static final String LDL = "LDL";
        public static final String HDL = "HDL";
        public static final String CHOLESTEROL_TOTAL = "Cholesterol Total";
        public static final String GLUCOSE = "Glucose";
        public static final String BLOOD_TYPE = "Blood Type";
        public static final String ALLERGIES = "Allergies";
        public static final String userId = "User ID";
        public static final String[] ALL_COLUMNS = { BLOOD_PRESSURE, LDL, HDL, CHOLESTEROL_TOTAL, GLUCOSE, BLOOD_TYPE, ALLERGIES, userId};
    }

    public static abstract class AppointmentEntries implements BaseColumns {
        public static final String TABLE_NAME = "Appointment";
        public static final String DOCTOR_NAME = "Doctor's Name";
        public static final String DATE_OF_APPOINTMENT = "Date of Appointment";
        public static final String TIME_OF_APPOINTMENT = "Appointment Time";
        public static final String REASON_FOR_APPOINTMENT = "Reason for Appointment";
        public static final String OFFICE_ADDRESS = "Office Address";
        public static final String DOCTOR_PHONE = "Doctor Phone";
    }

    public static abstract class WeightLossDietPlanEntries implements BaseColumns {
        public static final String TABLE_NAME = "Weight Loss and Diet Plan";
        public static final String DIET_NAME = "Diet Name";
        public static final String DIET_START_DATE = "Diet Start Date";
        public static final String DIET_END_DATE = "Diet End Date";
        public static final String CURRENT_WEIGHT = "Current Weight";
        public static final String GOAL_WEIGHT = "Goal Weight";
    }

    public static abstract class ExercisePlanEntries implements BaseColumns {
        public static final String TABLE_NAME = "Exercise Plan";
        public static final String EXERCISE_NAME = "Exercise Name";
        public static final String CALORIES_BURNED = "Calories Burned";
        public static final String DURATION_WORKOUT = "Duration Workout";
        public static final String MUSCLE_GROUP = "Muscle Group";
    }

    public static abstract class RecipeEntries implements BaseColumns {
        public static final String TABLE_NAME = "Healthy Recipe";
        public static final String RECIPE_NAME = "Recipe Name";
        public static final String SERVINGS = "Servings";
        public static final String COOK_TIME = "Cook Time";
        public static final String RECIPE_DESCRIPTION = "Recipe Description";
        public static final String RECIPE_INGREDIENTS = "Recipe Ingredients";
        public static final String RECIPE_DIRECTIONS = "Recipe Directions";
    }


    private static final String SQL_CREATE_DOCTOR_VISIT_ENTRIES =
            "CREATE TABLE " + DoctorVisitEntries.TABLE_NAME + " (" +
                    DoctorVisitEntries.LAST_CHECK_UP_DATE + TEXT_TYPE + COMMA_SEP +
                    DoctorVisitEntries.HEALTH_INSURANCE_COMPANY + TEXT_TYPE + COMMA_SEP +
                    DoctorVisitEntries.HEALTH_INSURANCE_POLICY_NUM + TEXT_TYPE
                    + ");";
    private static final String SQL_CREATE_MEDICATION_ENTRIES =
            "CREATE TABLE " + MedicationEntries.TABLE_NAME + " (" +
                    MedicationEntries.MEDICATION_NAME + TEXT_TYPE + COMMA_SEP +
                    MedicationEntries.DOSAGE + INT_TYPE + COMMA_SEP +
                    MedicationEntries.MEDICATION_DURATION + TEXT_TYPE + COMMA_SEP +
                    MedicationEntries.MEDICATION_REASON + INT_TYPE + COMMA_SEP +
                    MedicationEntries.PHARMACY_NAME + TEXT_TYPE + COMMA_SEP +
                    MedicationEntries.PHARMACY_PHONE + TEXT_TYPE
                    +");";
    private static final String SQL_CREATE_MEDICAL_REPORT_ENTRIES =
            "CREATE TABLE " + MedicalReportEntries.TABLE_NAME + " (" +
                    MedicalReportEntries.BLOOD_PRESSURE + INT_TYPE + COMMA_SEP +
                    MedicalReportEntries.LDL + TEXT_TYPE + COMMA_SEP +
                    MedicalReportEntries.HDL + INT_TYPE + COMMA_SEP +
                    MedicalReportEntries.CHOLESTEROL_TOTAL + TEXT_TYPE + COMMA_SEP +
                    MedicalReportEntries.GLUCOSE + TEXT_TYPE + COMMA_SEP +
                    MedicalReportEntries.BLOOD_TYPE + TEXT_TYPE + COMMA_SEP +
                    MedicalReportEntries.ALLERGIES + TEXT_TYPE + COMMA_SEP +
                    MedicalReportEntries.userId + INT_TYPE
                    +");";
    private static final String SQL_CREATE_APPOINTMENT_ENTRIES =
            "CREATE TABLE " + AppointmentEntries.TABLE_NAME + " (" +
                    AppointmentEntries.DOCTOR_NAME + INT_TYPE + COMMA_SEP +
                    AppointmentEntries.DATE_OF_APPOINTMENT + TEXT_TYPE + COMMA_SEP +
                    AppointmentEntries.TIME_OF_APPOINTMENT + INT_TYPE+ COMMA_SEP+
                    AppointmentEntries.REASON_FOR_APPOINTMENT + TEXT_TYPE + COMMA_SEP +
                    AppointmentEntries.OFFICE_ADDRESS + TEXT_TYPE + COMMA_SEP +
                    AppointmentEntries.DOCTOR_PHONE + INT_TYPE
                    +");";
    private static final String SQL_CREATE_WEIGHT_LOSS_AND_DIET_PLAN_ENTRIES =
            "CREATE TABLE " + WeightLossDietPlanEntries.TABLE_NAME + " (" +
                    WeightLossDietPlanEntries.DIET_NAME + TEXT_TYPE + COMMA_SEP +
                    WeightLossDietPlanEntries.DIET_START_DATE + INT_TYPE+ COMMA_SEP +
                    WeightLossDietPlanEntries.DIET_END_DATE + TEXT_TYPE + COMMA_SEP+
                    WeightLossDietPlanEntries.CURRENT_WEIGHT + TEXT_TYPE + COMMA_SEP +
                    WeightLossDietPlanEntries.GOAL_WEIGHT + INT_TYPE
                    +");";
    private static final String SQL_CREATE_EXERCISE_PLAN_ENTRIES =
            "CREATE TABLE " + ExercisePlanEntries.TABLE_NAME + " (" +
                    ExercisePlanEntries.EXERCISE_NAME + TEXT_TYPE + COMMA_SEP +
                    ExercisePlanEntries.CALORIES_BURNED + INT_TYPE+ COMMA_SEP +
                    ExercisePlanEntries.DURATION_WORKOUT + TEXT_TYPE + COMMA_SEP+
                    ExercisePlanEntries.MUSCLE_GROUP + TEXT_TYPE
                    +");";
    private static final String SQL_CREATE_HEALTHY_RECIPE_ENTRIES =
            "CREATE TABLE " + RecipeEntries.TABLE_NAME + " (" +
                    RecipeEntries.RECIPE_NAME + TEXT_TYPE + COMMA_SEP +
                    RecipeEntries.SERVINGS + INT_TYPE  + COMMA_SEP +
                    RecipeEntries.COOK_TIME + TEXT_TYPE + COMMA_SEP +
                    RecipeEntries.RECIPE_DESCRIPTION + INT_TYPE+ COMMA_SEP +
                    RecipeEntries.RECIPE_INGREDIENTS + TEXT_TYPE + COMMA_SEP+
                    RecipeEntries.RECIPE_DIRECTIONS + TEXT_TYPE
                    +");";
    private static final String SQL_DELETE_DOCTOR_VISIT_ENTRIES =
            "DROP TABLE IF EXISTS " + DoctorVisitEntries.TABLE_NAME;
    private static final String SQL_DELETE_MEDICATION_ENTRIES =
            "DROP TABLE IF EXISTS " + MedicationEntries.TABLE_NAME;
    private static final String SQL_DELETE_MEDICAL_REPORT_ENTRIES =
            "DROP TABLE IF EXISTS " + MedicalReportEntries.TABLE_NAME;
    private static final String SQL_DELETE_APPOINTMENT_ENTRIES=
            "DROP TABLE IF EXISTS " + AppointmentEntries.TABLE_NAME;
    private static final String SQL_DELETE_WEIGHT_LOSS_AND_DIET_PLAN_ENTRIES =
            "DROP TABLE IF EXISTS " + WeightLossDietPlanEntries.TABLE_NAME;
    private static final String SQL_DELETE_EXERCISE_PLAN_ENTRIES =
            "DROP TABLE IF EXISTS " + ExercisePlanEntries.TABLE_NAME;
    private static final String SQL_DELETE_RECIPE_ENTRIES =
            "DROP TABLE IF EXISTS " + RecipeEntries.TABLE_NAME;




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



	//LOGIN METHODS
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
			int question, String answer)
    {
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

    public long insertDoctorVisitRow(
            String tableName, String lastCheckUpDate, String insuranceCompany,
            String insurancePolicyNum) {
		/*
		 * CHANGE 3:
		 */
        // Create row's data:
        ContentValues initialValues = new ContentValues();
        initialValues.put(DoctorVisitEntries.TABLE_NAME, tableName);
        initialValues.put(DoctorVisitEntries.LAST_CHECK_UP_DATE, lastCheckUpDate);
        initialValues.put(DoctorVisitEntries.HEALTH_INSURANCE_COMPANY, insuranceCompany);
        initialValues.put(DoctorVisitEntries.HEALTH_INSURANCE_POLICY_NUM, insurancePolicyNum);

        // Insert it into the database.
        return db.insert(LoginEntries.TABLE_NAME, null, initialValues);
    }
    public long insertMedication(
            String tableName, String medName, String dosage,
            String medDuration, String medReason, String pharmName, String pharmPhone) {

        // Create row's data:
        ContentValues initialValues = new ContentValues();
        initialValues.put(MedicationEntries.TABLE_NAME, tableName);
        initialValues.put(MedicationEntries.MEDICATION_NAME, medName);
        initialValues.put(MedicationEntries.DOSAGE, dosage);
        initialValues.put(MedicationEntries.MEDICATION_DURATION, medDuration);
        initialValues.put(MedicationEntries.MEDICATION_REASON, medReason);
        initialValues.put(MedicationEntries.PHARMACY_NAME, pharmName);
        initialValues.put(MedicationEntries.PHARMACY_PHONE, pharmPhone);



        // Insert it into the database.
        return db.insert(LoginEntries.TABLE_NAME, null, initialValues);
    }

    public long insertMedicalReport(
            String tableName, String medName, String dosage,
            String medDuration, String medReason, String pharmName, String pharmPhone, int user)
    {
        // Create row's data:
        ContentValues initialValues = new ContentValues();
        initialValues.put(MedicalReportEntries.TABLE_NAME, tableName);
        initialValues.put(MedicalReportEntries.BLOOD_PRESSURE, medName);
        initialValues.put(MedicalReportEntries.LDL, dosage);
        initialValues.put(MedicalReportEntries.HDL, medDuration);
        initialValues.put(MedicalReportEntries.CHOLESTEROL_TOTAL, medReason);
        initialValues.put(MedicalReportEntries.GLUCOSE, pharmName);
        initialValues.put(MedicalReportEntries.BLOOD_TYPE, pharmPhone);
        initialValues.put(MedicalReportEntries.ALLERGIES, pharmPhone);
        initialValues.put(MedicalReportEntries.userId, user);


        // Insert it into the database.
        return db.insert(LoginEntries.TABLE_NAME, null, initialValues);
    }

    public long insertAppointments(
            String tableName, String medName, String dosage,
            String medDuration, String medReason, String pharmName, String pharmPhone)
    {
        // Create row's data:
        ContentValues initialValues = new ContentValues();
        initialValues.put(AppointmentEntries.TABLE_NAME, tableName);
        initialValues.put(AppointmentEntries.DOCTOR_NAME, medName);
        initialValues.put(AppointmentEntries.DATE_OF_APPOINTMENT, dosage);
        initialValues.put(AppointmentEntries.TIME_OF_APPOINTMENT, medDuration);
        initialValues.put(AppointmentEntries.REASON_FOR_APPOINTMENT, medReason);
        initialValues.put(AppointmentEntries.OFFICE_ADDRESS, pharmName);
        initialValues.put(AppointmentEntries.DOCTOR_PHONE, pharmPhone);

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


	//EDUCATION METHODS
	//COLUMN_INSTITUTION, COLUMN_COLLEGE_CITY, COLUMN_STUDY_FIELD, COLUMN_START_DATE, COLUMN_COMPLETION_DATE, COLUMN_USER_ID
	/**
	* Add a new set of values to the database.
	* @param institute
	* @param city
	* @param field
     * @param degree
	* @param startDate yyyymmdd
	* @param finishDate yyyymmdd
	* @param user ID of user
	* @return The Primary Key number.
	*/
	public long insertColleges(String institute, String city, String field, String degree, int startDate, int finishDate, int user) {
		/*
		 * CHANGE 3:
		 */
		// TODO: Update data in the row with new fields.
		// TODO: Also change the function's arguments to be what you need!
		// Create row's data:
		ContentValues initialValues = new ContentValues();
		initialValues.put(CollegeEntries.COLUMN_INSTITUTION, institute);
		initialValues.put(CollegeEntries.COLUMN_COLLEGE_CITY, city);
		initialValues.put(CollegeEntries.COLUMN_STUDY_FIELD, field);
        initialValues.put(CollegeEntries.COLUMN_DEGREE, degree);
		initialValues.put(CollegeEntries.COLUMN_START_DATE, startDate);
		initialValues.put(CollegeEntries.COLUMN_COMPLETION_DATE, finishDate);
		initialValues.put(CollegeEntries.COLUMN_USER_ID, user);

		// Insert it into the database.
		return db.insert(CollegeEntries.TABLE_NAME, null, initialValues);
	}

	// Delete a row from the database, by rowId (primary key)
	public boolean deleteCollege(long rowId) {
		String where = CollegeEntries._ID + "=" + rowId;
		return db.delete(CollegeEntries.TABLE_NAME, where, null) != 0;
	}

	public void deleteAllColleges() {
		Cursor c = getAllCash();
		long rowId = c.getColumnIndexOrThrow(CollegeEntries._ID);
		if (c.moveToFirst()) {
			do {
				deleteCash(c.getLong((int) rowId));
			} while (c.moveToNext());
		}
		c.close();
	}

	// Return all data in the database.
	public Cursor getAllColleges()
    {
		String where = null;
		Cursor c = 	db.query(CollegeEntries.TABLE_NAME, CollegeEntries.ALL_COLUMNS,
				where, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}

	// Get a specific row (by rowId)
	public Cursor getCollege(long rowId) {
		String where = CollegeEntries._ID + "=" + rowId;
		String[] ALL_KEYS = CollegeEntries.ALL_COLUMNS;
		Cursor c = 	db.query(true, CollegeEntries.TABLE_NAME, ALL_KEYS,
				where, null, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}

	/**
	 * Update a area of the db.
	 * @param rowId the primary key
	 * @param institute
	 * @param city
	 * @param field
     * @param degree
	 * @param startDate yyyymmdd
	 * @param finishDate yyyymmdd
	 * @return true if successful
	 */
	public boolean updateCollege(long rowId, String institute, String city, String field,
                                 String degree, int startDate, int finishDate) {
		String where = CollegeEntries._ID + "=" + rowId;

		/*
		 * CHANGE 4:
		 */
		// TODO: Update data in the row with new fields.
		// TODO: Also change the function's arguments to be what you need!
		// Create row's data:
		ContentValues newValues = new ContentValues();
		newValues.put(CollegeEntries.COLUMN_INSTITUTION, institute);
		newValues.put(CollegeEntries.COLUMN_COLLEGE_CITY, city);
		newValues.put(CollegeEntries.COLUMN_STUDY_FIELD, field);
        newValues.put(CollegeEntries.COLUMN_DEGREE, degree);
		newValues.put(CollegeEntries.COLUMN_START_DATE, startDate);
		newValues.put(CollegeEntries.COLUMN_COMPLETION_DATE, finishDate);


		// Insert it into the database.
		return db.update(CollegeEntries.TABLE_NAME, newValues, where, null) != 0;
	}

	//COLLEGE APPLICATIONS
	/**
	 * Add a new set of values to the database.
	 * @param college
	 * @param dueDate
	 * @param replyDate
	 * @param user
	 * @return The DB table _ID row number.
	 */
	public long insertCollegeApplication(String college, int dueDate, int replyDate, int user) {
		/*
		 * CHANGE 3:
		 */
		// TODO: Update data in the row with new fields.
		// TODO: Also change the function's arguments to be what you need!
		// Create row's data:
		ContentValues initialValues = new ContentValues();
		initialValues.put(ApplicationEntries.COLUMN_COLLEGE, college);
		initialValues.put(ApplicationEntries.COLUMN_DUE_DATE, dueDate);
		initialValues.put(ApplicationEntries.COLUMN_REPLY_DATE, replyDate);
		initialValues.put(ApplicationEntries.COLUMN_USER_ID, user);

		// Insert it into the database.
		return db.insert(ApplicationEntries.TABLE_NAME, null, initialValues);
	}

	// Delete a row from the database, by rowId (primary key)
	public boolean deleteCollegeApplication(long rowId) {
		String where = ApplicationEntries._ID + "=" + rowId;
		return db.delete(ApplicationEntries.TABLE_NAME, where, null) != 0;
	}

	public void deleteAllCollegeApplications() {
		Cursor c = getAllCollegeApplications();
		long rowId = c.getColumnIndexOrThrow(ApplicationEntries._ID);
		if (c.moveToFirst()) {
			do {
				deleteCollegeApplication(c.getLong((int) rowId));
			} while (c.moveToNext());
		}
		c.close();
	}

	// Return all data in the database.
	public Cursor getAllCollegeApplications() {
		String where = null;
		Cursor c = 	db.query(ApplicationEntries.TABLE_NAME, ApplicationEntries.ALL_COLUMNS,
				where, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}

	// Get a specific row (by rowId)
	public Cursor getCollegeApplication(long rowId) {
		String where = ApplicationEntries._ID + "=" + rowId;
		String[] ALL_KEYS = ApplicationEntries.ALL_COLUMNS;
		Cursor c = 	db.query(true, ApplicationEntries.TABLE_NAME, ALL_KEYS,
				where, null, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}

	// Change an existing row to be equal to new data.
	public boolean updateCollegeApplication(long rowId, String college, int dueDate, int replyDate) {
		String where = ApplicationEntries._ID + "=" + rowId;

		/*
		 * CHANGE 4:
		 */
		// TODO: Update data in the row with new fields.
		// TODO: Also change the function's arguments to be what you need!
		// Create row's data:
		ContentValues newValues = new ContentValues();
		newValues.put(ApplicationEntries.COLUMN_COLLEGE, college);
		newValues.put(ApplicationEntries.COLUMN_DUE_DATE, dueDate);
		newValues.put(ApplicationEntries.COLUMN_REPLY_DATE, replyDate);


		// Insert it into the database.
		return db.update(ApplicationEntries.TABLE_NAME, newValues, where, null) != 0;
	}

	//COLLEGE FINANCES
	/**
	 * Add a new set of values to the database.
	 * @param awardName
	 * @param amount
	 * @param period
	 * @param condition
	 * @param user
	 * @return The DB table _ID row number.
	 */
	public long insertCollegeFinance(String awardName, int amount, String period, String condition, int user) {
		/*
		 * CHANGE 3:
		 */
		// TODO: Update data in the row with new fields.
		// TODO: Also change the function's arguments to be what you need!
		// Create row's data:
		ContentValues initialValues = new ContentValues();
		initialValues.put(CollegeFinanceEntries.COLUMN_AWARD_NAME, awardName);
		initialValues.put(CollegeFinanceEntries.COLUMN_AMOUNT, amount);
		initialValues.put(CollegeFinanceEntries.COLUMN_PERIOD, period);
		initialValues.put(CollegeFinanceEntries.COLUMN_CONDITION, condition);
		initialValues.put(CollegeFinanceEntries.COLUMN_USER_ID, user);

		// Insert it into the database.
		return db.insert(CollegeFinanceEntries.TABLE_NAME, null, initialValues);
	}

	// Delete a row from the database, by rowId (primary key)
	public boolean deleteCollegeFinance(long rowId) {
		String where = CollegeFinanceEntries._ID + "=" + rowId;
		return db.delete(CollegeFinanceEntries.TABLE_NAME, where, null) != 0;
	}

	public void deleteAllCollegeFinances() {
		Cursor c = getAllCollegeFinances();
		long rowId = c.getColumnIndexOrThrow(CollegeFinanceEntries._ID);
		if (c.moveToFirst()) {
			do {
				deleteCollegeFinance(c.getLong((int) rowId));
			} while (c.moveToNext());
		}
		c.close();
	}

	// Return all data in the database.
	public Cursor getAllCollegeFinances() {
		String where = null;
		Cursor c = 	db.query(CollegeFinanceEntries.TABLE_NAME, CollegeFinanceEntries.ALL_COLUMNS,
				where, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}

	// Get a specific row (by rowId)
	public Cursor getCollegeFinance(long rowId) {
		String where = CollegeFinanceEntries._ID + "=" + rowId;
		String[] ALL_KEYS = CollegeFinanceEntries.ALL_COLUMNS;
		Cursor c = 	db.query(true, CollegeFinanceEntries.TABLE_NAME, ALL_KEYS,
				where, null, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}

	// Change an existing row to be equal to new data.
	public boolean updateCollegeFinance(long rowId, String awardName, int amount, String period, String condition) {
		String where = CollegeFinanceEntries._ID + "=" + rowId;

		/*
		 * CHANGE 4:
		 */
		// TODO: Update data in the row with new fields.
		// TODO: Also change the function's arguments to be what you need!
		// Create row's data:
		ContentValues newValues = new ContentValues();
		newValues.put(CollegeFinanceEntries.COLUMN_AWARD_NAME, awardName);
		newValues.put(CollegeFinanceEntries.COLUMN_AMOUNT, amount);
		newValues.put(CollegeFinanceEntries.COLUMN_PERIOD, period);
		newValues.put(CollegeFinanceEntries.COLUMN_CONDITION, condition);

		// Insert it into the database.
		return db.update(CollegeFinanceEntries.TABLE_NAME, newValues, where, null) != 0;
	}

	//CASH METHODS
	/**
	 * Add a new set of values to the database.
	 * @param value
	 * @param source
	 * @param note
	 * @param date yyyymmdd
	 * @param user ID of user
	 * @return The Primary Key number.
	 */
	public long insertCash(double value, String source, String note, int date, int user) {
		/*
		 * CHANGE 3:
		 */
		// TODO: Update data in the row with new fields.
		// TODO: Also change the function's arguments to be what you need!
		// Create row's data:
		ContentValues initialValues = new ContentValues();
		initialValues.put(CashEntries.COLUMN_CASH_AMOUNT, value);
		initialValues.put(CashEntries.COLUMN_SOURCE, source);
		initialValues.put(CashEntries.COLUMN_NOTE, note);
		initialValues.put(CashEntries.COLUMN_DATE, date);
		initialValues.put(CashEntries.COLUMN_USER_ID, user);

		// Insert it into the database.
		return db.insert(CashEntries.TABLE_NAME, null, initialValues);
	}

	// Delete a row from the database, by rowId (primary key)
	public boolean deleteCash(long rowId) {
		String where = CashEntries._ID + "=" + rowId;
		return db.delete(CashEntries.TABLE_NAME, where, null) != 0;
	}

	public void deleteAllCash() {
		Cursor c = getAllCash();
		long rowId = c.getColumnIndexOrThrow(CashEntries._ID);
		if (c.moveToFirst()) {
			do {
				deleteCash(c.getLong((int) rowId));
			} while (c.moveToNext());
		}
		c.close();
	}

	// Return all data in the database.
	public Cursor getAllCash() {
		String where = null;
		Cursor c = 	db.query(CashEntries.TABLE_NAME, CashEntries.ALL_COLUMNS,
				where, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}

	// Get a specific row (by rowId)
	public Cursor getCash(long rowId) {
		String where = CashEntries._ID + "=" + rowId;
		String[] ALL_KEYS = CashEntries.ALL_COLUMNS;
		Cursor c = 	db.query(true, CashEntries.TABLE_NAME, ALL_KEYS,
				where, null, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}

	/**
	 * Update a area of the db.
	 * @param rowId the primary key
	 * @param value
	 * @param source
	 * @param note
	 * @param date yyyymmdd
	 * @return true if successful
	 */
	public boolean updateCash(long rowId, double value, String source, String note, int date) {
		String where = CashEntries._ID + "=" + rowId;

		/*
		 * CHANGE 4:
		 */
		// TODO: Update data in the row with new fields.
		// TODO: Also change the function's arguments to be what you need!
		// Create row's data:
		ContentValues newValues = new ContentValues();
		newValues.put(CashEntries.COLUMN_CASH_AMOUNT, value);
		newValues.put(CashEntries.COLUMN_SOURCE, source);
		newValues.put(CashEntries.COLUMN_NOTE, note);
		newValues.put(CashEntries.COLUMN_DATE, date);


		// Insert it into the database.
		return db.update(CashEntries.TABLE_NAME, newValues, where, null) != 0;
	}






	//HELPER CLASS, Do not mess with this.
	// Change an existing row to be equal to new data.
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
			//db.execSQL(SQL_CREATE_Health_Entries);
			//finance
			db.execSQL(SQL_CREATE_CASH_ENTRIES);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// This database is only a cache for online data, so its upgrade policy is
			// to simply to discard the data and start over
			db.execSQL(SQL_DELETE_LOGIN_ENTRIES);
			db.execSQL(SQL_DELETE_CONTRACT_ENTRIES);
			db.execSQL(SQL_DELETE_COMPANY_ENTRIES);
			db.execSQL(SQL_DELETE_GOAL_ENTRIES);
			db.execSQL(SQL_DELETE_JOB_ENTRIES);
			db.execSQL(SQL_DELETE_IDENTITY_ENTRIES);
			db.execSQL(SQL_DELETE_CASH_ENTRIES);
            db.execSQL(SQL_CREATE_MEDICAL_REPORT_ENTRIES);
            db.execSQL(SQL_CREATE_MEDICATION_ENTRIES);
            db.execSQL(SQL_CREATE_APPOINTMENT_ENTRIES);
            db.execSQL(SQL_CREATE_DOCTOR_VISIT_ENTRIES);
            db.execSQL(SQL_CREATE_WEIGHT_LOSS_AND_DIET_PLAN_ENTRIES);
            db.execSQL(SQL_CREATE_EXERCISE_PLAN_ENTRIES);
            db.execSQL(SQL_CREATE_HEALTHY_RECIPE_ENTRIES);
			onCreate(db);
		}

		public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			onUpgrade(db, oldVersion, newVersion);
		}
	}

    public Cursor getMedicalReport(long rowId) {
        String where = MedicalReportEntries._ID + "=" + rowId;
        String[] ALL_KEYS = MedicalReportEntries.ALL_COLUMNS;
        Cursor c = 	db.query(true, MedicalReportEntries.TABLE_NAME, ALL_KEYS,
                where, null, null, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

}
