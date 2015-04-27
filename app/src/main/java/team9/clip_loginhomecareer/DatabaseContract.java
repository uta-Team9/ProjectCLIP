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
    private static final int DATABASE_VERSION = 32;
    //download and merge changes to update to current db before changing number
    //always save work! GitHub can be evil.

    //Separators for SQL Creation
    private static final String TEXT_TYPE = " TEXT";
    private static final String INT_TYPE = " INTEGER";
	private static final String LONG_TYPE = " LONG";
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
                    ContactEntries.NUMBER + TEXT_TYPE + COMMA_SEP +
                    ContactEntries.EMAIL + TEXT_TYPE + COMMA_SEP +
                    ContactEntries.USED + INT_TYPE + COMMA_SEP +
                    ContactEntries.MET + TEXT_TYPE + COMMA_SEP +
                    ContactEntries.HASH_ID + INT_TYPE +
                    //add entries following instructions above
                    ");";
    private static final String SQL_DELETE_CONTRACT_ENTRIES =
            "DROP TABLE IF EXISTS " + ContactEntries.TABLE_NAME;

    //GOALS
    private static abstract class CareerGoalEntries implements BaseColumns {
        public static final String TABLE_NAME = "Goals";
        public static final String[] ALL_COLUMNS =
                {"ID", "Title", "Description", "EndDate", "TermLength", "HashID"};
    }

    private static final String SQL_CREATE_CAREER_GOAL_ENTRIES =
            "CREATE TABLE " + CareerGoalEntries.TABLE_NAME + " (" +
		            CareerGoalEntries.ALL_COLUMNS[0] + " INTEGER PRIMARY KEY," +
		            CareerGoalEntries.ALL_COLUMNS[1] + TEXT_TYPE + COMMA_SEP +
		            CareerGoalEntries.ALL_COLUMNS[2] + TEXT_TYPE + COMMA_SEP +
		            CareerGoalEntries.ALL_COLUMNS[3] + TEXT_TYPE + COMMA_SEP +
		            CareerGoalEntries.ALL_COLUMNS[4] + TEXT_TYPE + COMMA_SEP +
		            CareerGoalEntries.ALL_COLUMNS[5] + INT_TYPE +
                    //add entries following instructions above
                    ");";
    private static final String SQL_DELETE_CAREER_GOAL_ENTRIES =
            "DROP TABLE IF EXISTS " + CareerGoalEntries.TABLE_NAME;

    //IDENTITIES
    private static abstract class IdentityEntries implements BaseColumns {
        public static final String TABLE_NAME = "Identities";
        public static final String[] ALL_COLUMNS =
                {"ID", "Login", "Website", "Password", "HashID"};
    }

    private static final String SQL_CREATE_IDENTITY_ENTRIES =
            "CREATE TABLE " + IdentityEntries.TABLE_NAME + " (" +
                    IdentityEntries.ALL_COLUMNS[0] + " INTEGER PRIMARY KEY," +
		            IdentityEntries.ALL_COLUMNS[1] + TEXT_TYPE + COMMA_SEP +
		            IdentityEntries.ALL_COLUMNS[2] + TEXT_TYPE + COMMA_SEP +
		            IdentityEntries.ALL_COLUMNS[3] + TEXT_TYPE + COMMA_SEP +
		            IdentityEntries.ALL_COLUMNS[4] + INT_TYPE +
                    //add entries following instructions above
                    ");";
    private static final String SQL_DELETE_IDENTITY_ENTRIES =
            "DROP TABLE IF EXISTS " + IdentityEntries.TABLE_NAME;

    //JOBS
    private static abstract class JobEntries implements BaseColumns {
        public static final String TABLE_NAME = "JobSearches";
        public static final String[] ALL_COLUMNS =
                {"ID", "Company", "Status", "Applied", "HashID"};
    }

    private static final String SQL_CREATE_JOB_ENTRIES =
            "CREATE TABLE " + JobEntries.TABLE_NAME + " (" +
                    JobEntries.ALL_COLUMNS[0] + " INTEGER PRIMARY KEY," +
		            JobEntries.ALL_COLUMNS[1] + TEXT_TYPE + COMMA_SEP +
		            JobEntries.ALL_COLUMNS[2] + TEXT_TYPE + COMMA_SEP +
		            JobEntries.ALL_COLUMNS[3] + TEXT_TYPE + COMMA_SEP +
		            JobEntries.ALL_COLUMNS[4] + INT_TYPE +
                    //add entries following instructions above
                    ");";
    private static final String SQL_DELETE_JOB_ENTRIES =
            "DROP TABLE IF EXISTS " + JobEntries.TABLE_NAME;

    //COMPANIES
    private static abstract class CompanyEntries implements BaseColumns {
        public static final String TABLE_NAME = "Companies";
        public static final String[] ALL_COLUMNS =
                {"ID", "Name", "Address", "Phone", "Description", "AppliedDate", "InterviewDate", "HashID"};
    }

    private static final String SQL_CREATE_COMPANY_ENTRIES =
            "CREATE TABLE " + CompanyEntries.TABLE_NAME + " (" +
                    CompanyEntries.ALL_COLUMNS[0] + " INTEGER PRIMARY KEY," +
		            CompanyEntries.ALL_COLUMNS[1] + TEXT_TYPE + COMMA_SEP +
		            CompanyEntries.ALL_COLUMNS[2] + TEXT_TYPE + COMMA_SEP +
		            CompanyEntries.ALL_COLUMNS[3] + TEXT_TYPE + COMMA_SEP +
		            CompanyEntries.ALL_COLUMNS[4] + TEXT_TYPE + COMMA_SEP +
		            CompanyEntries.ALL_COLUMNS[5] + TEXT_TYPE + COMMA_SEP +
		            CompanyEntries.ALL_COLUMNS[6] + TEXT_TYPE + COMMA_SEP +
		            CompanyEntries.ALL_COLUMNS[7] + INT_TYPE +
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
						COLUMN_DEGREE, COLUMN_START_DATE, COLUMN_COMPLETION_DATE, COLUMN_USER_ID};
	}
	private static final String SQL_CREATE_COLLEGE_ENTRIES =
			"CREATE TABLE " + CollegeEntries.TABLE_NAME + " (" +
					CollegeEntries._ID + " INTEGER PRIMARY KEY," +
					CollegeEntries.COLUMN_INSTITUTION + TEXT_TYPE + COMMA_SEP +
					CollegeEntries.COLUMN_COLLEGE_CITY + TEXT_TYPE + COMMA_SEP +
					CollegeEntries.COLUMN_STUDY_FIELD + TEXT_TYPE + COMMA_SEP +
                    CollegeEntries.COLUMN_DEGREE + TEXT_TYPE + COMMA_SEP +
					CollegeEntries.COLUMN_START_DATE + INT_TYPE + COMMA_SEP +
					CollegeEntries.COLUMN_COMPLETION_DATE + INT_TYPE + COMMA_SEP +
					CollegeEntries.COLUMN_USER_ID + INT_TYPE +
			");";
    private static final String SQL_DELETE_COLLEGE_ENTRIES =
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
	private static final String SQL_CREATE_COLLEGE_APPLICATION_ENTRIES =
			"CREATE TABLE " + ApplicationEntries.TABLE_NAME + " (" +
					ApplicationEntries._ID + " INTEGER PRIMARY KEY," +
					ApplicationEntries.COLUMN_COLLEGE + TEXT_TYPE + COMMA_SEP +
					ApplicationEntries.COLUMN_DUE_DATE + INT_TYPE + COMMA_SEP +
					ApplicationEntries.COLUMN_REPLY_DATE + INT_TYPE + COMMA_SEP +
					ApplicationEntries.COLUMN_USER_ID + INT_TYPE +
					");";
	private static final String SQL_DELETE_COLLEGE_APPLICATION_ENTRIES =
			"DROP TABLE IF EXISTS " + ApplicationEntries.TABLE_NAME;

	//CollegeFinances
	private static abstract class CollegeFinanceEntries implements BaseColumns {
		public static final String TABLE_NAME = "EducationFinances";
		public static final String _ID = "ID";
		public static final String COLUMN_AWARD_NAME = "AwardName";
		public static final String COLUMN_AMOUNT = "Amount";
		public static final String COLUMN_PERIOD = "Period  ";
		public static final String COLUMN_CONDITION = "Condition";
		public static final String COLUMN_USER_ID = "User_ID";
		public static final String[] ALL_COLUMNS =
				{_ID, COLUMN_AWARD_NAME, COLUMN_AMOUNT, COLUMN_PERIOD, COLUMN_CONDITION, COLUMN_USER_ID};
	}
	private static final String SQL_CREATE_COLLEGE_FINANCE_ENTRIES =
			"CREATE TABLE " + CollegeFinanceEntries.TABLE_NAME + " (" +
					CollegeFinanceEntries._ID + " INTEGER PRIMARY KEY," +
					CollegeFinanceEntries.COLUMN_AWARD_NAME + TEXT_TYPE + COMMA_SEP +
					CollegeFinanceEntries.COLUMN_AMOUNT + DOUBLE_TYPE + COMMA_SEP +
					CollegeFinanceEntries.COLUMN_PERIOD + TEXT_TYPE + COMMA_SEP +
					CollegeFinanceEntries.COLUMN_CONDITION + TEXT_TYPE + COMMA_SEP +
					CollegeFinanceEntries.COLUMN_USER_ID + INT_TYPE +
					");";
	private static final String SQL_DELETE_COLLEGE_FINANCE_ENTRIES =
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
        public static final String COLUMN_YEAR = "YEAR";
	    public static final String COLUMN_MONTH = "MONTH";
	    public static final String COLUMN_DAY = "DAY";
        public static final String COLUMN_USER_ID = "USER_ID";
        public static final String[] ALL_COLUMNS =
                {_ID, COLUMN_CASH_AMOUNT, COLUMN_SOURCE, COLUMN_NOTE, COLUMN_YEAR,
		                COLUMN_MONTH, COLUMN_DAY, COLUMN_USER_ID};
    }
    private static final String SQL_CREATE_CASH_ENTRIES =
            "CREATE TABLE " + CashEntries.TABLE_NAME + " (" +
                    CashEntries._ID + " INTEGER PRIMARY KEY," +
                    CashEntries.COLUMN_CASH_AMOUNT + DOUBLE_TYPE + COMMA_SEP +
                    CashEntries.COLUMN_SOURCE + TEXT_TYPE + COMMA_SEP +
                    CashEntries.COLUMN_NOTE + TEXT_TYPE + COMMA_SEP +
                    CashEntries.COLUMN_YEAR + INT_TYPE + COMMA_SEP +
		            CashEntries.COLUMN_MONTH + INT_TYPE + COMMA_SEP +
		            CashEntries.COLUMN_DAY + INT_TYPE + COMMA_SEP +
                    CashEntries.COLUMN_USER_ID + INT_TYPE +
                    ");";
    private static final String SQL_DELETE_CASH_ENTRIES =
            "DROP TABLE IF EXISTS " + CashEntries.TABLE_NAME;

	//Stock Security Section (Using new method)
	private static abstract class StockSecurityEntries implements BaseColumns {
		public static final String TABLE_NAME = "STOCK_SECURITIES";
		public static final String[] ALL_COLUMNS =
				{"ID", "YEAR", "MONTH", "DAY", "STOCK_NAME", "STOCK_NO_OF_UNITS", "STOCK_PURCHASE_PRICE",
				"STOCK_CURRENT_PRICE","STOCK_NOTE", "USER_ID"};
	}
	private static final String SQL_CREATE_STOCK_SECURITY_ENTRIES =
			"CREATE TABLE " + StockSecurityEntries.TABLE_NAME + " (" +
					StockSecurityEntries.ALL_COLUMNS[0] + " INTEGER PRIMARY KEY," +
					StockSecurityEntries.ALL_COLUMNS[1] + INT_TYPE + COMMA_SEP +
					StockSecurityEntries.ALL_COLUMNS[2] + INT_TYPE + COMMA_SEP +
					StockSecurityEntries.ALL_COLUMNS[3] + INT_TYPE + COMMA_SEP +
					StockSecurityEntries.ALL_COLUMNS[4] + TEXT_TYPE + COMMA_SEP +
					StockSecurityEntries.ALL_COLUMNS[5] + INT_TYPE + COMMA_SEP +
					StockSecurityEntries.ALL_COLUMNS[6] + DOUBLE_TYPE + COMMA_SEP +
					StockSecurityEntries.ALL_COLUMNS[7] + DOUBLE_TYPE + COMMA_SEP +
					StockSecurityEntries.ALL_COLUMNS[8] + TEXT_TYPE + COMMA_SEP +
					StockSecurityEntries.ALL_COLUMNS[9] + INT_TYPE +
					");";
	private static final String SQL_DELETE_STOCK_SECURITY_ENTRIES =
			"DROP TABLE IF EXISTS " + StockSecurityEntries.TABLE_NAME;

	//Finance Goals (Using new method)
	private static abstract class FinancialGoalEntries implements BaseColumns {
		public static final String TABLE_NAME = "FINANCIAL_GOALS";
		public static final String[] ALL_COLUMNS =
				{"ID", "IS_FULFILLED", "IS_SHORT_TERM", "DESCRIPTION", "YEAR", "MONTH", "DAY",
						"GOAL_NOTE", "USER_ID"};
	}
	private static final String SQL_CREATE_FINANCIAL_GOAL_ENTRIES =
			"CREATE TABLE " + FinancialGoalEntries.TABLE_NAME + " (" +
					FinancialGoalEntries.ALL_COLUMNS[0] + " INTEGER PRIMARY KEY," +
					FinancialGoalEntries.ALL_COLUMNS[1] + INT_TYPE + COMMA_SEP +
					FinancialGoalEntries.ALL_COLUMNS[2] + INT_TYPE + COMMA_SEP +
					FinancialGoalEntries.ALL_COLUMNS[3] + TEXT_TYPE + COMMA_SEP +
					FinancialGoalEntries.ALL_COLUMNS[4] + INT_TYPE + COMMA_SEP +
					FinancialGoalEntries.ALL_COLUMNS[5] + INT_TYPE + COMMA_SEP +
					FinancialGoalEntries.ALL_COLUMNS[6] + INT_TYPE + COMMA_SEP +
					FinancialGoalEntries.ALL_COLUMNS[7] + TEXT_TYPE + COMMA_SEP +
					FinancialGoalEntries.ALL_COLUMNS[8] + INT_TYPE +
					");";
	private static final String SQL_DELETE_FINANCIAL_GOAL_ENTRIES =
			"DROP TABLE IF EXISTS " + FinancialGoalEntries.TABLE_NAME;

	//ASSETS (Using new method)
	private static abstract class AssetEntries implements BaseColumns {
		public static final String TABLE_NAME = "ASSETS";
		public static final String[] ALL_COLUMNS =
				{"ID", "YEAR", "MONTH", "DAY", "TYPE", "VALUE", "MARKET_VALUE", "NOTE", "USER_ID"};
	}
	private static final String SQL_CREATE_ASSET_ENTRIES =
			"CREATE TABLE " + AssetEntries.TABLE_NAME + " (" +
					AssetEntries.ALL_COLUMNS[0] + " INTEGER PRIMARY KEY," +
					AssetEntries.ALL_COLUMNS[1] + INT_TYPE + COMMA_SEP +
					AssetEntries.ALL_COLUMNS[2] + INT_TYPE + COMMA_SEP +
					AssetEntries.ALL_COLUMNS[3] + INT_TYPE + COMMA_SEP +
					AssetEntries.ALL_COLUMNS[4] + TEXT_TYPE + COMMA_SEP +
					AssetEntries.ALL_COLUMNS[5] + DOUBLE_TYPE + COMMA_SEP +
					AssetEntries.ALL_COLUMNS[6] + DOUBLE_TYPE + COMMA_SEP +
					AssetEntries.ALL_COLUMNS[7] + TEXT_TYPE + COMMA_SEP +
					AssetEntries.ALL_COLUMNS[8] + INT_TYPE +
					");";
	private static final String SQL_DELETE_ASSET_ENTRIES =
			"DROP TABLE IF EXISTS " + AssetEntries.TABLE_NAME;

	//Liability (Using new method)
	// String date, String lenderName, double amount, double interestRate,
	// int lendingTerm, String desc, String note
	private static abstract class LiabilityEntries implements BaseColumns {
		public static final String TABLE_NAME = "LIABILITIES";
		public static final String[] ALL_COLUMNS =
				{"ID", "YEAR", "MONTH", "DAY", "LENDER_NAME", "AMOUNT", "INTEREST_RATE", "LENDING_TERM", "DESCRIPTION", "NOTE", "USER_ID"};
	}
	private static final String SQL_CREATE_LIABILITY_ENTRIES =
			"CREATE TABLE " + LiabilityEntries.TABLE_NAME + " (" +
					LiabilityEntries.ALL_COLUMNS[0] + " INTEGER PRIMARY KEY," +
					LiabilityEntries.ALL_COLUMNS[1] + INT_TYPE + COMMA_SEP +
                    LiabilityEntries.ALL_COLUMNS[2] + INT_TYPE + COMMA_SEP +
                    LiabilityEntries.ALL_COLUMNS[3] + INT_TYPE + COMMA_SEP +
					LiabilityEntries.ALL_COLUMNS[4] + TEXT_TYPE + COMMA_SEP +
					LiabilityEntries.ALL_COLUMNS[5] + DOUBLE_TYPE + COMMA_SEP +
					LiabilityEntries.ALL_COLUMNS[6] + DOUBLE_TYPE + COMMA_SEP +
					LiabilityEntries.ALL_COLUMNS[7] + INT_TYPE + COMMA_SEP +
					LiabilityEntries.ALL_COLUMNS[8] + TEXT_TYPE + COMMA_SEP +
					LiabilityEntries.ALL_COLUMNS[9] + TEXT_TYPE + COMMA_SEP +
					LiabilityEntries.ALL_COLUMNS[10] + INT_TYPE +
					");";
	private static final String SQL_DELETE_LIABILITY_ENTRIES =
			"DROP TABLE IF EXISTS " + LiabilityEntries.TABLE_NAME;

	//Credit Card (Using new method)
	private static abstract class CreditCardEntries implements BaseColumns {
		public static final String TABLE_NAME = "CREDIT_CARDS";
		public static final String[] ALL_COLUMNS =
				{"ID", "PROVIDER", "BALANCE", "YEAR", "MONTH", "DAY", "NOTE", "USER_ID"};
	}
	private static final String SQL_CREATE_CREDIT_CARD_ENTRIES =
			"CREATE TABLE " + CreditCardEntries.TABLE_NAME + " (" +
					CreditCardEntries.ALL_COLUMNS[0] + " INTEGER PRIMARY KEY," +
					CreditCardEntries.ALL_COLUMNS[1] + TEXT_TYPE + COMMA_SEP +
					CreditCardEntries.ALL_COLUMNS[2] + DOUBLE_TYPE + COMMA_SEP +
					CreditCardEntries.ALL_COLUMNS[3] + INT_TYPE + COMMA_SEP +
					CreditCardEntries.ALL_COLUMNS[4] + INT_TYPE + COMMA_SEP +
                    CreditCardEntries.ALL_COLUMNS[5] + INT_TYPE + COMMA_SEP +
					CreditCardEntries.ALL_COLUMNS[6] + TEXT_TYPE + COMMA_SEP +
					CreditCardEntries.ALL_COLUMNS[7] + INT_TYPE +
					");";
	private static final String SQL_DELETE_CREDIT_CARD_ENTRIES =
			"DROP TABLE IF EXISTS " + CreditCardEntries.TABLE_NAME;

	//Financial Other (Using new method)
	private static abstract class FinancialMiscEntries implements BaseColumns {
		public static final String TABLE_NAME = "ASSET";
		public static final String[] ALL_COLUMNS =
				{"ID", "YEAR", "MONTH", "DAY", "DESCRIPTION", "AMOUNT", "NOTE", "USER_ID"};
	}
	private static final String SQL_CREATE_FINANCIAL_MISC_ENTRIES =
			"CREATE TABLE " + FinancialMiscEntries.TABLE_NAME + " (" +
					FinancialMiscEntries.ALL_COLUMNS[0] + " INTEGER PRIMARY KEY," +
					FinancialMiscEntries.ALL_COLUMNS[1] + INT_TYPE + COMMA_SEP +
					FinancialMiscEntries.ALL_COLUMNS[2] + INT_TYPE + COMMA_SEP +
					FinancialMiscEntries.ALL_COLUMNS[3] + INT_TYPE + COMMA_SEP +
					FinancialMiscEntries.ALL_COLUMNS[4] + TEXT_TYPE + COMMA_SEP +
					FinancialMiscEntries.ALL_COLUMNS[5] + DOUBLE_TYPE + COMMA_SEP +
					FinancialMiscEntries.ALL_COLUMNS[6] + TEXT_TYPE + COMMA_SEP +
					FinancialMiscEntries.ALL_COLUMNS[7] + INT_TYPE +
					");";
	private static final String SQL_DELETE_FINANCIAL_MISC_ENTRIES =
			"DROP TABLE IF EXISTS " + FinancialMiscEntries.TABLE_NAME;

    /*
     * HEALTH
     * TODO: AMEERA
     */
	//DOCTOR VISITS
    private static abstract class DoctorVisitEntries implements BaseColumns {
        public static final String TABLE_NAME = "DoctorsVisit";
	    public static final String _ID = "ID";
        public static final String LAST_CHECK_UP_DATE = "LastCheckUpDate";
        public static final String HEALTH_INSURANCE_COMPANY = "HealthInsuranceCompany";
        public static final String HEALTH_INSURANCE_POLICY_NUM = "HealthInsurancePolicyNumber";
	    public static final String HASH_ID = "HashID";
	    public static final String[] ALL_COLUMNS =
			    {_ID, LAST_CHECK_UP_DATE, HEALTH_INSURANCE_COMPANY, HEALTH_INSURANCE_POLICY_NUM, HASH_ID};
    }
	private static final String SQL_CREATE_DOCTOR_VISIT_ENTRIES =
			"CREATE TABLE " + DoctorVisitEntries.TABLE_NAME + " (" +
					DoctorVisitEntries._ID + " INTEGER PRIMARY KEY," +
					DoctorVisitEntries.LAST_CHECK_UP_DATE + INT_TYPE + COMMA_SEP +
					DoctorVisitEntries.HEALTH_INSURANCE_COMPANY + TEXT_TYPE + COMMA_SEP +
					DoctorVisitEntries.HEALTH_INSURANCE_POLICY_NUM + INT_TYPE + COMMA_SEP +
					DoctorVisitEntries.HASH_ID + INT_TYPE
					+ ");";
	private static final String SQL_DELETE_DOCTOR_VISIT_ENTRIES =
			"DROP TABLE IF EXISTS " + DoctorVisitEntries.TABLE_NAME;

	//MEDICATION ENTRIES
    public static abstract class MedicationEntries implements BaseColumns {
        public static final String TABLE_NAME = "Medication";
	    public static final String _ID = "ID";
        public static final String MEDICATION_NAME = "MedicationName";
        public static final String DOSAGE = "Dosage";
        public static final String MEDICATION_DURATION = "MedicationDuration";
        public static final String MEDICATION_REASON = "MedReason";
        public static final String PHARMACY_NAME = "PharmacyName";
        public static final String PHARMACY_PHONE = "PharmacyPhone";
	    public static final String HASH_ID = "HashID";
	    public static final String[] ALL_COLUMNS =
			    {_ID, MEDICATION_NAME, DOSAGE, MEDICATION_DURATION, MEDICATION_REASON,
					    PHARMACY_NAME, PHARMACY_PHONE, HASH_ID};
    }
	private static final String SQL_CREATE_MEDICATION_ENTRIES =
			"CREATE TABLE " + MedicationEntries.TABLE_NAME + " (" +
					MedicationEntries._ID + " INTEGER PRIMARY KEY," +
					MedicationEntries.MEDICATION_NAME + TEXT_TYPE + COMMA_SEP +
					MedicationEntries.DOSAGE + INT_TYPE + COMMA_SEP +
					MedicationEntries.MEDICATION_DURATION + TEXT_TYPE + COMMA_SEP +
					MedicationEntries.MEDICATION_REASON + TEXT_TYPE + COMMA_SEP +
					MedicationEntries.PHARMACY_NAME + TEXT_TYPE + COMMA_SEP +
					MedicationEntries.PHARMACY_PHONE + INT_TYPE + COMMA_SEP +
					MedicationEntries.HASH_ID + INT_TYPE
					+");";
	private static final String SQL_DELETE_MEDICATION_ENTRIES =
			"DROP TABLE IF EXISTS " + MedicationEntries.TABLE_NAME;

	//MEDICAL REPORT ENTRIES
    public static abstract class MedicalReportEntries implements BaseColumns {
        public static final String TABLE_NAME = "MedicalReport";
        public static final String[] ALL_COLUMNS =
		        {"ID", "BloodPressure", "LDL", "HDL", "CholesterolTotal", "Glucose",
				        "BloodType", "Allergies", "HashID"};
    }
	private static final String SQL_CREATE_MEDICAL_REPORT_ENTRIES =
			"CREATE TABLE " + MedicalReportEntries.TABLE_NAME + " (" +
					MedicalReportEntries.ALL_COLUMNS[0] + " INTEGER PRIMARY KEY," +
					MedicalReportEntries.ALL_COLUMNS[1] + TEXT_TYPE + COMMA_SEP +
					MedicalReportEntries.ALL_COLUMNS[2] + TEXT_TYPE + COMMA_SEP +
					MedicalReportEntries.ALL_COLUMNS[3] + TEXT_TYPE + COMMA_SEP +
					MedicalReportEntries.ALL_COLUMNS[4] + TEXT_TYPE + COMMA_SEP +
					MedicalReportEntries.ALL_COLUMNS[5] + TEXT_TYPE + COMMA_SEP +
					MedicalReportEntries.ALL_COLUMNS[6] + TEXT_TYPE + COMMA_SEP +
					MedicalReportEntries.ALL_COLUMNS[7] + TEXT_TYPE + COMMA_SEP +
					MedicalReportEntries.ALL_COLUMNS[8] + INT_TYPE
					+");";
	private static final String SQL_DELETE_MEDICAL_REPORT_ENTRIES =
			"DROP TABLE IF EXISTS " + MedicalReportEntries.TABLE_NAME;

	//APPOINTMENTS
    public static abstract class AppointmentEntries implements BaseColumns {
        public static final String TABLE_NAME = "Appointment";
	    public static final String _ID = "ID";
        public static final String DOCTOR_NAME = "DoctorsName";
        public static final String DATE_OF_APPOINTMENT = "DateofAppointment";
        public static final String TIME_OF_APPOINTMENT = "AppointmentTime";
        public static final String REASON_FOR_APPOINTMENT = "Reason";
        public static final String OFFICE_ADDRESS = "OfficeAddress";
        public static final String DOCTOR_PHONE = "DoctorPhone";
	    public static final String HASH_ID = "HashID";
	    public static final String[] ALL_COLUMNS =
			    {_ID, DOCTOR_NAME, DATE_OF_APPOINTMENT, TIME_OF_APPOINTMENT,
			    REASON_FOR_APPOINTMENT, OFFICE_ADDRESS, DOCTOR_PHONE, HASH_ID};
	}
	private static final String SQL_CREATE_APPOINTMENT_ENTRIES =
			"CREATE TABLE " + AppointmentEntries.TABLE_NAME + " (" +
					AppointmentEntries.ALL_COLUMNS[0] + " INTEGER PRIMARY KEY," +
					AppointmentEntries.DOCTOR_NAME + TEXT_TYPE + COMMA_SEP +
					AppointmentEntries.DATE_OF_APPOINTMENT + INT_TYPE + COMMA_SEP +
					AppointmentEntries.TIME_OF_APPOINTMENT + INT_TYPE+ COMMA_SEP+
					AppointmentEntries.REASON_FOR_APPOINTMENT + TEXT_TYPE + COMMA_SEP +
					AppointmentEntries.OFFICE_ADDRESS + TEXT_TYPE + COMMA_SEP +
					AppointmentEntries.DOCTOR_PHONE + INT_TYPE + COMMA_SEP +
					AppointmentEntries.HASH_ID + INT_TYPE
					+");";
	private static final String SQL_DELETE_APPOINTMENT_ENTRIES=
			"DROP TABLE IF EXISTS " + AppointmentEntries.TABLE_NAME;

	//WEIGHT LOSS PLAN
    public static abstract class WeightLossDietPlanEntries implements BaseColumns {
        public static final String TABLE_NAME = "WeightLossDietPlan";
	    public static final String _ID = "ID";
        public static final String DIET_NAME = "DietName";
        public static final String DIET_START_DATE = "DietStartDate";
        public static final String DIET_END_DATE = "DietEndDate";
        public static final String CURRENT_WEIGHT = "CurrentWeight";
        public static final String GOAL_WEIGHT = "GoalWeight";
	    public static final String HASH_ID = "HashID";
	    public static final String[] ALL_COLUMNS = {_ID,DIET_NAME,DIET_START_DATE,DIET_END_DATE,
			    CURRENT_WEIGHT,GOAL_WEIGHT,HASH_ID};
    }
	private static final String SQL_CREATE_WEIGHT_LOSS_AND_DIET_PLAN_ENTRIES =
			"CREATE TABLE " + WeightLossDietPlanEntries.TABLE_NAME + " (" +
					WeightLossDietPlanEntries.ALL_COLUMNS[0] + " INTEGER PRIMARY KEY," +
					WeightLossDietPlanEntries.DIET_NAME + TEXT_TYPE + COMMA_SEP +
					WeightLossDietPlanEntries.DIET_START_DATE + INT_TYPE+ COMMA_SEP +
					WeightLossDietPlanEntries.DIET_END_DATE + INT_TYPE + COMMA_SEP+
					WeightLossDietPlanEntries.CURRENT_WEIGHT + INT_TYPE + COMMA_SEP +
					WeightLossDietPlanEntries.GOAL_WEIGHT + INT_TYPE + COMMA_SEP +
					WeightLossDietPlanEntries.HASH_ID + INT_TYPE
					+");";
	private static final String SQL_DELETE_WEIGHT_LOSS_AND_DIET_PLAN_ENTRIES =
			"DROP TABLE IF EXISTS " + WeightLossDietPlanEntries.TABLE_NAME;

	//EXERCISE PLANS
    public static abstract class ExercisePlanEntries implements BaseColumns {
        public static final String TABLE_NAME = "ExercisePlan";
	    public static final String _ID = "ID";
        public static final String EXERCISE_NAME = "ExerciseName";
        public static final String CALORIES_BURNED = "CaloriesBurned";
        public static final String DURATION_WORKOUT = "DurationWorkout";
        public static final String MUSCLE_GROUP = "MuscleGroup";
	    public static final String HASH_ID = "HashID";
	    public static final String[] ALL_COLUMNS =
			    {_ID,EXERCISE_NAME,CALORIES_BURNED,DURATION_WORKOUT,MUSCLE_GROUP,HASH_ID};
	}
	private static final String SQL_CREATE_EXERCISE_PLAN_ENTRIES =
			"CREATE TABLE " + ExercisePlanEntries.TABLE_NAME + " (" +
					ExercisePlanEntries.ALL_COLUMNS[0] + " INTEGER PRIMARY KEY," +
					ExercisePlanEntries.EXERCISE_NAME + TEXT_TYPE + COMMA_SEP +
					ExercisePlanEntries.CALORIES_BURNED + INT_TYPE+ COMMA_SEP +
					ExercisePlanEntries.DURATION_WORKOUT + INT_TYPE + COMMA_SEP+
					ExercisePlanEntries.MUSCLE_GROUP + TEXT_TYPE + COMMA_SEP +
					ExercisePlanEntries.HASH_ID + INT_TYPE
					+");";
	private static final String SQL_DELETE_EXERCISE_PLAN_ENTRIES =
			"DROP TABLE IF EXISTS " + ExercisePlanEntries.TABLE_NAME;

	//RECIPES
    public static abstract class RecipeEntries implements BaseColumns {
        public static final String TABLE_NAME = "HealthyRecipe";
	    public static final String _ID = "ID";
        public static final String RECIPE_NAME = "RecipeName";
        public static final String SERVINGS = "Servings";
        public static final String COOK_TIME = "CookTime";
        public static final String RECIPE_DESCRIPTION = "RecipeDescription";
        public static final String RECIPE_INGREDIENTS = "RecipeIngredients";
        public static final String RECIPE_DIRECTIONS = "RecipeDirections";
	    public static final String HASH_ID = "HashID";
	    public static final String[] ALL_COLUMNS = {_ID,RECIPE_NAME,SERVINGS,COOK_TIME,
			    RECIPE_DESCRIPTION,RECIPE_INGREDIENTS,RECIPE_DIRECTIONS,HASH_ID};
	}
	private static final String SQL_CREATE_RECIPE_ENTRIES =
			"CREATE TABLE " + RecipeEntries.TABLE_NAME + " (" +
					RecipeEntries.ALL_COLUMNS[0] + " INTEGER PRIMARY KEY," +
					RecipeEntries.RECIPE_NAME + TEXT_TYPE + COMMA_SEP +
					RecipeEntries.SERVINGS + INT_TYPE  + COMMA_SEP +
					RecipeEntries.COOK_TIME + INT_TYPE + COMMA_SEP +
					RecipeEntries.RECIPE_DESCRIPTION + TEXT_TYPE+ COMMA_SEP +
					RecipeEntries.RECIPE_INGREDIENTS + TEXT_TYPE + COMMA_SEP+
					RecipeEntries.RECIPE_DIRECTIONS + TEXT_TYPE + COMMA_SEP +
					RecipeEntries.HASH_ID + INT_TYPE
					+");";
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
	public long insertLoginRow(String name, String email, String password,
			int question, String answer)  {
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

	// Change an existing row to be equal to new data.
	public boolean updateLoginRow(long rowId, String name, String email,
	                              String password, int question, String answer) {
		String where = LoginEntries._ID + "=" + rowId;
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

	// Delete a row from the database, by rowId (primary key)
	public boolean deleteLoginRow(long rowId) {
		String where = LoginEntries._ID + "=" + rowId;
		return db.delete(LoginEntries.TABLE_NAME, where, null) != 0;
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


	//CONTACT METHODS
	/**
	 * Add a new set of values to the database.
	 * @param name
	 * @param email
	 * @param phone
	 * @param dateMet
	 * @param used
	 * @param user
	 * @return The DB table _ID row number.
	 */
	public long insertContact(String name, String email, String phone, String dateMet, int used, int user) {
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
		initialValues.put(ContactEntries.MET, dateMet);
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
	                             String phone, String dateMet, int used) {
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
		newValues.put(ContactEntries.MET, dateMet);
		newValues.put(ContactEntries.USED, used);


		// Insert it into the database.
		return db.update(ContactEntries.TABLE_NAME, newValues, where, null) != 0;
	}

	//CAREER GOALS
	//"ID", "title", "Description", "EndDate", "TermLength", "HashID"
	/**
	 * Insert a row of data into the career goal table
	 * @param title
	 * @param desc
	 * @param endDate
	 * @param isLongTerm
	 * @param user
	 * @return rowID, the long primary key
	 */
	public long insertCareerGoal(String title, String desc, String endDate, boolean isLongTerm, int user ) {
		// Create row's data:
		ContentValues initialValues = new ContentValues();
		initialValues.put(CareerGoalEntries.ALL_COLUMNS[1], title);
		initialValues.put(CareerGoalEntries.ALL_COLUMNS[2], desc);
		initialValues.put(CareerGoalEntries.ALL_COLUMNS[3], endDate);
		initialValues.put(CareerGoalEntries.ALL_COLUMNS[4], isLongTerm);
		initialValues.put(CareerGoalEntries.ALL_COLUMNS[5], user);

		// Insert it into the database.
		return db.insert(CareerGoalEntries.TABLE_NAME, null, initialValues);
	}

	/**
	 * Update a row in the career goal table
	 * @param rowId long value primary key
	 * @param title
	 * @param desc
	 * @param endDate
	 * @param isLongTerm
	 * @return true if successful
	 */
	public boolean updateCareerGoal(long rowId, String title, String desc, String endDate,
	                                boolean isLongTerm) {
		String where = CareerGoalEntries.ALL_COLUMNS[0] + "=" + rowId;
		// TODO: Update data in the row with new fields.
		// TODO: Also change the function's arguments to be what you need!
		ContentValues newValues = new ContentValues();
		newValues.put(CareerGoalEntries.ALL_COLUMNS[1], title);
		newValues.put(CareerGoalEntries.ALL_COLUMNS[2], desc);
		newValues.put(CareerGoalEntries.ALL_COLUMNS[3], endDate);
		newValues.put(CareerGoalEntries.ALL_COLUMNS[4], isLongTerm);

		// Insert it into the database.
		return db.update(CareerGoalEntries.TABLE_NAME, newValues, where, null) != 0;
	}

	public Cursor getCareerGoal(long rowId) {
		String where = CareerGoalEntries.ALL_COLUMNS[0] + "=" + rowId;
		Cursor c = 	db.query(true, CareerGoalEntries.TABLE_NAME, CareerGoalEntries.ALL_COLUMNS,
				where, null, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}

	// Return all data in the database.
	public Cursor getAllCareerGoals() {
		String where = null;
		Cursor c = 	db.query(CareerGoalEntries.TABLE_NAME, CareerGoalEntries.ALL_COLUMNS,
				where, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}

	// Delete a row from the database, by rowId (primary key)
	public boolean deleteCareerGoal(long rowId) {
		String where = CareerGoalEntries.ALL_COLUMNS[0] + "=" + rowId;
		return db.delete(CareerGoalEntries.TABLE_NAME, where, null) != 0;
	}

	public void deleteAllCareerGoals() {
		Cursor c = getAllCareerGoals();
		long rowId = c.getColumnIndexOrThrow(CareerGoalEntries.ALL_COLUMNS[0]);
		if (c.moveToFirst()) {
			do {
				deleteCareerGoal(c.getLong((int) rowId));
			} while (c.moveToNext());
		}
		c.close();
	}

	//OnlineIdentities
	//ID, WEBSITE, LOGIN, PASSWORD, HASH
	/**
	 * Insert a row of data into the career goal table
	 * @param website
	 * @param login
	 * @param password
	 * @param user the User_ID
	 * @return rowID, the long primary key
	 */
	public long insertOnlineIdentity(String website, String login, String password, int user ) {
		// Create row's data:
		ContentValues initialValues = new ContentValues();
		initialValues.put(IdentityEntries.ALL_COLUMNS[1], website);
		initialValues.put(IdentityEntries.ALL_COLUMNS[2], login);
		initialValues.put(IdentityEntries.ALL_COLUMNS[3], password);
		initialValues.put(IdentityEntries.ALL_COLUMNS[4], user);

		// Insert it into the database.
		return db.insert(IdentityEntries.TABLE_NAME, null, initialValues);
	}

	/**
	 * Update a row in the career goal table
	 * @param rowId long value primary key
	 * @param website
	 * @param login
	 * @param password
	 * @return true if successful
	 */
	public boolean updateOnlineIdentity(long rowId, String website, String login, String password) {
		String where = IdentityEntries.ALL_COLUMNS[0] + "=" + rowId;
		// TODO: Update data in the row with new fields.
		// TODO: Also change the function's arguments to be what you need!
		ContentValues newValues = new ContentValues();
		newValues.put(IdentityEntries.ALL_COLUMNS[1], website);
		newValues.put(IdentityEntries.ALL_COLUMNS[2], login);
		newValues.put(IdentityEntries.ALL_COLUMNS[3], password);

		// Insert it into the database.
		return db.update(IdentityEntries.TABLE_NAME, newValues, where, null) != 0;
	}

	public Cursor getOnlineIdentity(long rowId) {
		String where = IdentityEntries.ALL_COLUMNS[0] + "=" + rowId;
		Cursor c = 	db.query(true, IdentityEntries.TABLE_NAME, IdentityEntries.ALL_COLUMNS,
				where, null, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}

	// Return all data in the database.
	public Cursor getAllOnlineIdentities() {
		String where = null;
		Cursor c = 	db.query(IdentityEntries.TABLE_NAME, IdentityEntries.ALL_COLUMNS,
				where, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}

	// Delete a row from the database, by rowId (primary key)
	public boolean deleteOnlineIdentity(long rowId) {
		String where = IdentityEntries.ALL_COLUMNS[0] + "=" + rowId;
		return db.delete(IdentityEntries.TABLE_NAME, where, null) != 0;
	}

	public void deleteAllOnlineIdentities() {
		Cursor c = getAllOnlineIdentities();
		long rowId = c.getColumnIndexOrThrow(IdentityEntries.ALL_COLUMNS[0]);
		if (c.moveToFirst()) {
			do {
				deleteOnlineIdentity(c.getLong((int) rowId));
			} while (c.moveToNext());
		}
		c.close();
	}

	//Job Searches
	//"ID", "Company", "Status", "Applied", "HashID"
	/**
	 * Insert a row of data into the career goal table
	 * @param company
	 * @param status
	 * @param appliedDate
	 * @param user
	 * @return rowID, the long primary key
	 */
	public long insertJobSearch(String company, String status, String appliedDate, int user ) {
		// Create row's data:
		ContentValues initialValues = new ContentValues();
		initialValues.put(JobEntries.ALL_COLUMNS[1], company);
		initialValues.put(JobEntries.ALL_COLUMNS[2], status);
		initialValues.put(JobEntries.ALL_COLUMNS[3], appliedDate);
		initialValues.put(JobEntries.ALL_COLUMNS[4], user);

		// Insert it into the database.
		return db.insert(JobEntries.TABLE_NAME, null, initialValues);
	}

	/**
	 * Update a row in the career goal table
	 * @param rowId long value primary key
	 * @param company
	 * @param status
	 * @param appliedDate
	 * @return true if successful
	 */
	public boolean updateJobSearch(long rowId, String company, String status, String appliedDate) {
		String where = CareerGoalEntries.ALL_COLUMNS[0] + "=" + rowId;
		// TODO: Update data in the row with new fields.
		// TODO: Also change the function's arguments to be what you need!
		ContentValues newValues = new ContentValues();
		newValues.put(JobEntries.ALL_COLUMNS[1], company);
		newValues.put(JobEntries.ALL_COLUMNS[2], status);
		newValues.put(JobEntries.ALL_COLUMNS[3], appliedDate);

		// Insert it into the database.
		return db.update(JobEntries.TABLE_NAME, newValues, where, null) != 0;
	}

	public Cursor getJobSearch(long rowId) {
		String where = JobEntries.ALL_COLUMNS[0] + "=" + rowId;
		Cursor c = 	db.query(true, JobEntries.TABLE_NAME, JobEntries.ALL_COLUMNS,
				where, null, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}

	// Return all data in the database.
	public Cursor getAllJobSearches() {
		String where = null;
		Cursor c = 	db.query(JobEntries.TABLE_NAME, JobEntries.ALL_COLUMNS,
				where, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}

	// Delete a row from the database, by rowId (primary key)
	public boolean deleteJobSearch(long rowId) {
		String where = JobEntries.ALL_COLUMNS[0] + "=" + rowId;
		return db.delete(JobEntries.TABLE_NAME, where, null) != 0;
	}

	public void deleteAllJobSearches() {
		Cursor c = getAllJobSearches();
		long rowId = c.getColumnIndexOrThrow(JobEntries.ALL_COLUMNS[0]);
		if (c.moveToFirst()) {
			do {
				deleteJobSearch(c.getLong((int) rowId));
			} while (c.moveToNext());
		}
		c.close();
	}

	//Company Information
	//"ID", "Name", "Address", "Phone", "Description", "AppliedDate", "InterviewDate", "HashID"
	/**
	 * Insert a row of data into the career goal table
	 * @param name
	 * @param  address
	 * @param phone
	 * @param desc
	 * @param appliedDate
	 * @param interviewDate
	 * @param user
	 * @return rowID, the long primary key
	 */
	public long insertCompany(String name, String address, String phone, String desc,
	                             String appliedDate, String interviewDate, int user ) {
		// Create row's data:
		ContentValues initialValues = new ContentValues();
		initialValues.put(CompanyEntries.ALL_COLUMNS[1], name);
		initialValues.put(CompanyEntries.ALL_COLUMNS[2], address);
		initialValues.put(CompanyEntries.ALL_COLUMNS[3], phone);
		initialValues.put(CompanyEntries.ALL_COLUMNS[4], desc);
		initialValues.put(CompanyEntries.ALL_COLUMNS[5], appliedDate);
		initialValues.put(CompanyEntries.ALL_COLUMNS[6], interviewDate);
		initialValues.put(CompanyEntries.ALL_COLUMNS[7], user);

		// Insert it into the database.
		return db.insert(CompanyEntries.TABLE_NAME, null, initialValues);
	}

	/**
	 * Update a row in the career goal table
	 * @param rowId long value primary key
	 * @param name
	 * @param address
	 * @param phone
	 * @param desc
	 * @param appliedDate
	 * @param interviewDate
	 * @return true if successful
	 */
	public boolean updateCompany(long rowId, String name, String address, String phone, String desc,
	                                String appliedDate, String interviewDate) {
		String where = CompanyEntries.ALL_COLUMNS[0] + "=" + rowId;
		// TODO: Update data in the row with new fields.
		// TODO: Also change the function's arguments to be what you need!
		ContentValues initialValues = new ContentValues();
		initialValues.put(CompanyEntries.ALL_COLUMNS[1], name);
		initialValues.put(CompanyEntries.ALL_COLUMNS[2], address);
		initialValues.put(CompanyEntries.ALL_COLUMNS[3], phone);
		initialValues.put(CompanyEntries.ALL_COLUMNS[4], desc);
		initialValues.put(CompanyEntries.ALL_COLUMNS[5], appliedDate);
		initialValues.put(CompanyEntries.ALL_COLUMNS[6], interviewDate);

		// Insert it into the database.
		return db.update(CompanyEntries.TABLE_NAME, initialValues, where, null) != 0;
	}

	public Cursor getCompany(long rowId) {
		String where = CompanyEntries.ALL_COLUMNS[0] + "=" + rowId;
		Cursor c = 	db.query(true, CompanyEntries.TABLE_NAME, CompanyEntries.ALL_COLUMNS,
				where, null, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}

	// Return all data in the database.
	public Cursor getAllCompanies() {
		String where = null;
		Cursor c = 	db.query(CompanyEntries.TABLE_NAME, CompanyEntries.ALL_COLUMNS,
				where, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}

	// Delete a row from the database, by rowId (primary key)
	public boolean deleteCompany(long rowId) {
		String where = CompanyEntries.ALL_COLUMNS[0] + "=" + rowId;
		return db.delete(CompanyEntries.TABLE_NAME, where, null) != 0;
	}

	public void deleteAllCompanies() {
		Cursor c = getAllCompanies();
		long rowId = c.getColumnIndexOrThrow(CompanyEntries.ALL_COLUMNS[0]);
		if (c.moveToFirst()) {
			do {
				deleteCompany(c.getLong((int) rowId));
			} while (c.moveToNext());
		}
		c.close();
	}


	//EDUCATION METHODS
	//TODO: MARY
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
		Cursor c = getAllColleges();
		long rowId = c.getColumnIndexOrThrow(CollegeEntries._ID);
		if (c.moveToFirst()) {
			do {
				deleteCollege(c.getLong((int) rowId));
			} while (c.moveToNext());
		}
		c.close();
	}

	// Return all data in the database.
	public Cursor getAllColleges() {
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
	public long insertCollegeFinance(String awardName, double amount, String period, String condition, int user) {
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
	public boolean updateCollegeFinance(long rowId, String awardName, double amount, String period, String condition) {
		String where = CollegeFinanceEntries._ID + "=" + rowId;
		// TODO: Update data in the row with new fields.
		// TODO: Also change the function's arguments to be what you need!
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
	 * @param year
	 * @param month
	 * @param day
	 * @param user ID of user
	 * @return The Primary Key number.
	 */
	public long insertCash(double value, String source, String note, int year, int month,
	                       int day, int user) {
		// TODO: Update data in the row with new fields.
		// TODO: Also change the function's arguments to be what you need!
		// Create row's data:
		ContentValues initialValues = new ContentValues();
		initialValues.put(CashEntries.COLUMN_CASH_AMOUNT, value);
		initialValues.put(CashEntries.COLUMN_SOURCE, source);
		initialValues.put(CashEntries.COLUMN_NOTE, note);
		initialValues.put(CashEntries.COLUMN_YEAR, year);
		initialValues.put(CashEntries.COLUMN_MONTH, month);
		initialValues.put(CashEntries.COLUMN_DAY, day);
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
	 * @param year
	 * @param month
	 * @param day
	 * @return true if successful
	 */
	public boolean updateCash(long rowId, double value, String source, String note, int year,
	                          int month, int day) {
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
		newValues.put(CashEntries.COLUMN_YEAR, year);
		newValues.put(CashEntries.COLUMN_MONTH, month);
		newValues.put(CashEntries.COLUMN_DAY, day);


		// Insert it into the database.
		return db.update(CashEntries.TABLE_NAME, newValues, where, null) != 0;
	}

	//Stock Securities
	/**
	 * insert a new row
	 * @param year
	 * @param month
	 * @param day
	 * @param stockName
	 * @param units
	 * @param purchasePrice
	 * @param currentPrice
	 * @param note
	 * @param user
	 * @return long, primary key
	 */
	public long insertStockSecurity(int year, int month, int day, String stockName, int units, double purchasePrice,
			double currentPrice, String note, int user) {
		// Create row's data:
		ContentValues initialValues = new ContentValues();
		initialValues.put(StockSecurityEntries.ALL_COLUMNS[1], year);
		initialValues.put(StockSecurityEntries.ALL_COLUMNS[2], month);
		initialValues.put(StockSecurityEntries.ALL_COLUMNS[3], day);
		initialValues.put(StockSecurityEntries.ALL_COLUMNS[4], stockName);
		initialValues.put(StockSecurityEntries.ALL_COLUMNS[5], units);
		initialValues.put(StockSecurityEntries.ALL_COLUMNS[6], purchasePrice);
		initialValues.put(StockSecurityEntries.ALL_COLUMNS[7], currentPrice);
		initialValues.put(StockSecurityEntries.ALL_COLUMNS[8], note);
		initialValues.put(StockSecurityEntries.ALL_COLUMNS[9], user);

		// Insert it into the database.
		return db.insert(StockSecurityEntries.TABLE_NAME, null, initialValues);
	}

	/**
	 * Update a area of the db
	 * @param rowId
	 * @param year
	 * @param month
	 * @param day
	 * @param stockName
	 * @param units
	 * @param purchasePrice
	 * @param currentPrice
	 * @param note
	 * @return true if successful
	 */
	public boolean updateStockSecurity(long rowId, int year, int month, int day, String stockName, int units, double purchasePrice,
	                                   double currentPrice, String note) {
		String where = StockSecurityEntries._ID + "=" + rowId;
		// TODO: Update data in the row with new fields.
		// TODO: Also change the function's arguments to be what you need!
		ContentValues newValues = new ContentValues();
		newValues.put(StockSecurityEntries.ALL_COLUMNS[1], year);
		newValues.put(StockSecurityEntries.ALL_COLUMNS[2], month);
		newValues.put(StockSecurityEntries.ALL_COLUMNS[3], day);
		newValues.put(StockSecurityEntries.ALL_COLUMNS[4], stockName);
		newValues.put(StockSecurityEntries.ALL_COLUMNS[5], units);
		newValues.put(StockSecurityEntries.ALL_COLUMNS[6], purchasePrice);
		newValues.put(StockSecurityEntries.ALL_COLUMNS[7], currentPrice);
		newValues.put(StockSecurityEntries.ALL_COLUMNS[8], note);

		// Insert it into the database.
		return db.update(StockSecurityEntries.TABLE_NAME, newValues, where, null) != 0;
	}

	public Cursor getStockSecurity(long rowId) {
		String where = StockSecurityEntries.ALL_COLUMNS[0] + "=" + rowId;
		Cursor c = 	db.query(true, StockSecurityEntries.TABLE_NAME, StockSecurityEntries.ALL_COLUMNS,
				where, null, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}

	// Return all data in the database.
	public Cursor getAllStockSecurities() {
		String where = null;
		Cursor c = 	db.query(StockSecurityEntries.TABLE_NAME, StockSecurityEntries.ALL_COLUMNS,
				where, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}

	// Delete a row from the database, by rowId (primary key)
	public boolean deleteStockSecurity(long rowId) {
		String where = StockSecurityEntries.ALL_COLUMNS[0] + "=" + rowId;
		return db.delete(StockSecurityEntries.TABLE_NAME, where, null) != 0;
	}

	public void deleteAllStockSecurities() {
		Cursor c = getAllStockSecurities();
		long rowId = c.getColumnIndexOrThrow(StockSecurityEntries.ALL_COLUMNS[0]);
		if (c.moveToFirst()) {
			do {
				deleteStockSecurity(c.getLong((int) rowId));
			} while (c.moveToNext());
		}
		c.close();
	}

	//Financial Goals
	/**
	 *
	 * @param isFulfilled
	 * @param shortTerm
	 * @param desc
	 * @param year
	 * @param month
	 * @param day
	 * @param note
	 * @param user
	 * @return
	 */
	public long insertFinancialGoal(boolean isFulfilled, boolean shortTerm, String desc, int year,
	                                int month, int day, String note, int user) {
		// Create row's data:
		ContentValues initialValues = new ContentValues();
		initialValues.put(FinancialGoalEntries.ALL_COLUMNS[1], isFulfilled);
		initialValues.put(FinancialGoalEntries.ALL_COLUMNS[2], shortTerm);
		initialValues.put(FinancialGoalEntries.ALL_COLUMNS[3], desc);
		initialValues.put(FinancialGoalEntries.ALL_COLUMNS[4], year);
		initialValues.put(FinancialGoalEntries.ALL_COLUMNS[5], month);
		initialValues.put(FinancialGoalEntries.ALL_COLUMNS[6], day);
		initialValues.put(FinancialGoalEntries.ALL_COLUMNS[7], note);
		initialValues.put(FinancialGoalEntries.ALL_COLUMNS[8], user);

		// Insert it into the database.
		return db.insert(FinancialGoalEntries.TABLE_NAME, null, initialValues);
	}

	/**
	 * Update a area of the db.
	 * @param rowId the primary key as long
	 * @param isFulfilled
	 * @param shortTerm
	 * @param desc
	 * @param year
	 * @param month
	 * @param day
	 * @param note
	 * @return true if successful
	 */
	public boolean updateFinancialGoal(long rowId, boolean isFulfilled, boolean shortTerm,
	                                   String desc, int year, int month, int day, String note) {
		String where = FinancialGoalEntries.ALL_COLUMNS[0] + "=" + rowId;
		// TODO: Update data in the row with new fields.
		// TODO: Also change the function's arguments to be what you need!
		ContentValues newValues = new ContentValues();
		newValues.put(FinancialGoalEntries.ALL_COLUMNS[1], isFulfilled);
		newValues.put(FinancialGoalEntries.ALL_COLUMNS[2], shortTerm);
		newValues.put(FinancialGoalEntries.ALL_COLUMNS[3], desc);
		newValues.put(FinancialGoalEntries.ALL_COLUMNS[4], year);
		newValues.put(FinancialGoalEntries.ALL_COLUMNS[5], month);
		newValues.put(FinancialGoalEntries.ALL_COLUMNS[6], day);
		newValues.put(FinancialGoalEntries.ALL_COLUMNS[7], note);

		// Insert it into the database.
		return db.update(FinancialGoalEntries.TABLE_NAME, newValues, where, null) != 0;
	}

	public Cursor getFinancialGoal(long rowId) {
		String where = FinancialGoalEntries.ALL_COLUMNS[0] + "=" + rowId;
		Cursor c = 	db.query(true, FinancialGoalEntries.TABLE_NAME, FinancialGoalEntries.ALL_COLUMNS,
				where, null, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}

	// Return all data in the database.
	public Cursor getAllFinancialGoals() {
		String where = null;
		Cursor c = 	db.query(FinancialGoalEntries.TABLE_NAME, FinancialGoalEntries.ALL_COLUMNS,
				where, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}

	// Delete a row from the database, by rowId (primary key)
	public boolean deleteFinancialGoal(long rowId) {
		String where = FinancialGoalEntries.ALL_COLUMNS[0] + "=" + rowId;
		return db.delete(FinancialGoalEntries.TABLE_NAME, where, null) != 0;
	}

	public void deleteAllFinancialGoals() {
		Cursor c = getAllFinancialGoals();
		long rowId = c.getColumnIndexOrThrow(FinancialGoalEntries.ALL_COLUMNS[0]);
		if (c.moveToFirst()) {
			do {
				deleteFinancialGoal(c.getLong((int) rowId));
			} while (c.moveToNext());
		}
		c.close();
	}

	//Assets
	/**
	 *
	 * @param year
	 * @param month
	 * @param day
	 * @param type
	 * @param value
	 * @param marketValue
	 * @param note
	 * @param user
	 * @return
	 */
	public long insertAsset(int year, int month, int day, String type, double value, double marketValue, String note, int user ) {
		// Create row's data:
		ContentValues initialValues = new ContentValues();
		initialValues.put(AssetEntries.ALL_COLUMNS[1], year);
		initialValues.put(AssetEntries.ALL_COLUMNS[2], month);
		initialValues.put(AssetEntries.ALL_COLUMNS[3], day);
		initialValues.put(AssetEntries.ALL_COLUMNS[4], type);
		initialValues.put(AssetEntries.ALL_COLUMNS[5], value);
		initialValues.put(AssetEntries.ALL_COLUMNS[6], marketValue);
		initialValues.put(AssetEntries.ALL_COLUMNS[7], note);
		initialValues.put(AssetEntries.ALL_COLUMNS[8], user);

		// Insert it into the database.
		return db.insert(AssetEntries.TABLE_NAME, null, initialValues);
	}

	/**
	 *
	 * @param rowId
	 * @param year
	 * @param month
	 * @param day
	 * @param type
	 * @param value
	 * @param marketValue
	 * @param note
	 * @return
	 */
	public boolean updateAsset(long rowId, int year, int month, int day, String type, double value,
	                           double marketValue, String note) {
		String where = AssetEntries.ALL_COLUMNS[0] + "=" + rowId;
		// TODO: Update data in the row with new fields.
		// TODO: Also change the function's arguments to be what you need!
		ContentValues newValues = new ContentValues();
		newValues.put(AssetEntries.ALL_COLUMNS[1], year);
		newValues.put(AssetEntries.ALL_COLUMNS[2], month);
		newValues.put(AssetEntries.ALL_COLUMNS[3], day);
		newValues.put(AssetEntries.ALL_COLUMNS[4], type);
		newValues.put(AssetEntries.ALL_COLUMNS[5], value);
		newValues.put(AssetEntries.ALL_COLUMNS[6], marketValue);
		newValues.put(AssetEntries.ALL_COLUMNS[7], note);

		// Insert it into the database.
		return db.update(AssetEntries.TABLE_NAME, newValues, where, null) != 0;
	}

	public Cursor getAsset(long rowId) {
		String where = AssetEntries.ALL_COLUMNS[0] + "=" + rowId;
		Cursor c = 	db.query(true, AssetEntries.TABLE_NAME, AssetEntries.ALL_COLUMNS,
				where, null, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}

	// Return all data in the database.
	public Cursor getAllAssets() {
		String where = null;
		Cursor c = 	db.query(AssetEntries.TABLE_NAME, AssetEntries.ALL_COLUMNS,
				where, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}

	// Delete a row from the database, by rowId (primary key)
	public boolean deleteAsset(long rowId) {
		String where = AssetEntries.ALL_COLUMNS[0] + "=" + rowId;
		return db.delete(AssetEntries.TABLE_NAME, where, null) != 0;
	}

	public void deleteAllAssets() {
		Cursor c = getAllAssets();
		long rowId = c.getColumnIndexOrThrow(AssetEntries.ALL_COLUMNS[0]);
		if (c.moveToFirst()) {
			do {
				deleteAsset(c.getLong((int) rowId));
			} while (c.moveToNext());
		}
		c.close();
	}

	//Liabilities
	/**
	 * insert into the database
	 * @param date Date in string format
	 * @param lenderName Name of lender
	 * @param amount the amount being lent
	 * @param interestRate the rate of the interest
	 * @param lendingTerm How ever you want to store it
	 * @param desc Description
	 * @param note Note
	 * @param user User's ID
	 * @return the primary key
	 */
	public long insertLiability(int year, int month, int day, String lenderName, double amount, double interestRate,
	                            int lendingTerm, String desc, String note, int user ) {
		// Create row's data:
		ContentValues initialValues = new ContentValues();
		initialValues.put(LiabilityEntries.ALL_COLUMNS[1], year);
        initialValues.put(LiabilityEntries.ALL_COLUMNS[2], month);
        initialValues.put(LiabilityEntries.ALL_COLUMNS[3], day);
		initialValues.put(LiabilityEntries.ALL_COLUMNS[4], lenderName);
		initialValues.put(LiabilityEntries.ALL_COLUMNS[5], amount);
		initialValues.put(LiabilityEntries.ALL_COLUMNS[6], interestRate);
		initialValues.put(LiabilityEntries.ALL_COLUMNS[7], lendingTerm);
		initialValues.put(LiabilityEntries.ALL_COLUMNS[8], desc);
		initialValues.put(LiabilityEntries.ALL_COLUMNS[9], note);
		initialValues.put(LiabilityEntries.ALL_COLUMNS[10], user);

		// Insert it into the database.
		return db.insert(LiabilityEntries.TABLE_NAME, null, initialValues);
	}

	/**
	 *
	 * @param rowId the primary key
	 * @param date
	 * @param lenderName
	 * @param amount
	 * @param interestRate
	 * @param lendingTerm
	 * @param desc
	 * @param note
	 * @return true if succsesful
	 */
	public boolean updateLiability(long rowId, String date, String lenderName, double amount, double interestRate,
	                               int lendingTerm, String desc, String note) {
		String where = LiabilityEntries.ALL_COLUMNS[0] + "=" + rowId;
		// TODO: Update data in the row with new fields.
		// TODO: Also change the function's arguments to be what you need!
		ContentValues initialValues = new ContentValues();
		initialValues.put(LiabilityEntries.ALL_COLUMNS[1], date);
		initialValues.put(LiabilityEntries.ALL_COLUMNS[2], lenderName);
		initialValues.put(LiabilityEntries.ALL_COLUMNS[3], amount);
		initialValues.put(LiabilityEntries.ALL_COLUMNS[4], interestRate);
		initialValues.put(LiabilityEntries.ALL_COLUMNS[5], lendingTerm);
		initialValues.put(LiabilityEntries.ALL_COLUMNS[6], desc);
		initialValues.put(LiabilityEntries.ALL_COLUMNS[7], note);

		// Insert it into the database.
		return db.update(LiabilityEntries.TABLE_NAME, initialValues, where, null) != 0;
	}

	/**
	 * Get a Cursor array with columns of
	 * @param rowId
	 * @return Cursor with the columns of String date, String lenderName, double amount, double interestRate,
	 * int lendingTerm, String desc, String note
	 */
	public Cursor getLiability(long rowId) {
		String where = LiabilityEntries.ALL_COLUMNS[0] + "=" + rowId;
		Cursor c = 	db.query(true, LiabilityEntries.TABLE_NAME, LiabilityEntries.ALL_COLUMNS,
				where, null, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}

	// Return all data in the database.
	public Cursor getAllLiabilities() {
		String where = null;
		Cursor c = 	db.query(LiabilityEntries.TABLE_NAME, LiabilityEntries.ALL_COLUMNS,
				where, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}

	// Delete a row from the database, by rowId (primary key)
	public boolean deleteLiability(long rowId) {
		String where = LiabilityEntries.ALL_COLUMNS[0] + "=" + rowId;
		return db.delete(LiabilityEntries.TABLE_NAME, where, null) != 0;
	}

	public void deleteAllLiabilities() {
		Cursor c = getAllLiabilities();
		long rowId = c.getColumnIndexOrThrow(LiabilityEntries.ALL_COLUMNS[0]);
		if (c.moveToFirst()) {
			do {
				deleteLiability(c.getLong((int) rowId));
			} while (c.moveToNext());
		}
		c.close();
	}

	//Credit Card
	//{"ID", "PROVIDER", "BALANCE", "YEAR", "MONTH", "DAY", "NOTE", "USER_ID"};

	/**
	 *
	 * @param provider
	 * @param balance
	 * @param year
	 * @param month
	 * @param day
	 * @param note
	 * @param user
	 * @return
	 */
	public long insertCreditCard(String provider, double balance, int year, int month, int day, String note, int user ) {
		// Create row's data:
		ContentValues initialValues = new ContentValues();
		initialValues.put(CreditCardEntries.ALL_COLUMNS[1], provider);
		initialValues.put(CreditCardEntries.ALL_COLUMNS[2], balance);
		initialValues.put(CreditCardEntries.ALL_COLUMNS[3], year);
		initialValues.put(CreditCardEntries.ALL_COLUMNS[4], month);
		initialValues.put(CreditCardEntries.ALL_COLUMNS[5], day);
		initialValues.put(CreditCardEntries.ALL_COLUMNS[6], note);
		initialValues.put(CreditCardEntries.ALL_COLUMNS[7], user);

		// Insert it into the database.
		return db.insert(CreditCardEntries.TABLE_NAME, null, initialValues);
	}

	/**
	 *
	 * @param rowId
	 * @param provider
	 * @param balance
	 * @param year
	 * @param month
	 * @param day
	 * @param note
	 * @return
	 */
	public boolean updateCreditCard(long rowId, String provider, double balance, int year, int month, int day, String note) {
		String where = CreditCardEntries.ALL_COLUMNS[0] + "=" + rowId;
		// TODO: Update data in the row with new fields.
		// TODO: Also change the function's arguments to be what you need!
		ContentValues initialValues = new ContentValues();
		initialValues.put(CreditCardEntries.ALL_COLUMNS[1], provider);
		initialValues.put(CreditCardEntries.ALL_COLUMNS[2], balance);
		initialValues.put(CreditCardEntries.ALL_COLUMNS[3], year);
		initialValues.put(CreditCardEntries.ALL_COLUMNS[4], month);
		initialValues.put(CreditCardEntries.ALL_COLUMNS[5], day);
		initialValues.put(CreditCardEntries.ALL_COLUMNS[6], note);

		// Insert it into the database.
		return db.update(CreditCardEntries.TABLE_NAME, initialValues, where, null) != 0;
	}

	public Cursor getCreditCard(long rowId) {
		String where = CreditCardEntries.ALL_COLUMNS[0] + "=" + rowId;
		Cursor c = 	db.query(true, CreditCardEntries.TABLE_NAME, CreditCardEntries.ALL_COLUMNS,
				where, null, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}

	// Return all data in the database.
	public Cursor getAllCreditCards() {
		String where = null;
		Cursor c = 	db.query(CreditCardEntries.TABLE_NAME, CreditCardEntries.ALL_COLUMNS,
				where, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}

	// Delete a row from the database, by rowId (primary key)
	public boolean deleteCreditCard(long rowId) {
		String where = CreditCardEntries.ALL_COLUMNS[0] + "=" + rowId;
		return db.delete(CreditCardEntries.TABLE_NAME, where, null) != 0;
	}

	public void deleteAllCreditCards() {
		Cursor c = getAllCreditCards();
		long rowId = c.getColumnIndexOrThrow(CreditCardEntries.ALL_COLUMNS[0]);
		if (c.moveToFirst()) {
			do {
				deleteCreditCard(c.getLong((int) rowId));
			} while (c.moveToNext());
		}
		c.close();
	}

	//Financial Misc
	/**
	 *
	 * @param year
	 * @param month
	 * @param day
	 * @param desc
	 * @param amount
	 * @param note
	 * @param user
	 * @return
	 */
	public long insertFinancialMisc(int year, int month, int day, String desc, double amount, String note, int user) {
		// Create row's data:
		ContentValues initialValues = new ContentValues();
		initialValues.put(FinancialMiscEntries.ALL_COLUMNS[1], year);
		initialValues.put(FinancialMiscEntries.ALL_COLUMNS[2], month);
		initialValues.put(FinancialMiscEntries.ALL_COLUMNS[3], day);
		initialValues.put(FinancialMiscEntries.ALL_COLUMNS[4], desc);
		initialValues.put(FinancialMiscEntries.ALL_COLUMNS[5], amount);
		initialValues.put(FinancialMiscEntries.ALL_COLUMNS[6], note);
		initialValues.put(FinancialMiscEntries.ALL_COLUMNS[7], user);

		// Insert it into the database.
		return db.insert(FinancialMiscEntries.TABLE_NAME, null, initialValues);
	}

	/**
	 *
	 * @param rowId
	 * @param year
	 * @param month
	 * @param day
	 * @param desc
	 * @param amount
	 * @param note
	 * @return
	 */
	public boolean updateFinancialMisc(long rowId, int year, int month, int day, String desc, double amount, String note) {
		String where = FinancialMiscEntries.ALL_COLUMNS[0] + "=" + rowId;
		// TODO: Update data in the row with new fields.
		// TODO: Also change the function's arguments to be what you need!
		ContentValues newValues = new ContentValues();
		newValues.put(FinancialMiscEntries.ALL_COLUMNS[1], year);
		newValues.put(FinancialMiscEntries.ALL_COLUMNS[2], month);
		newValues.put(FinancialMiscEntries.ALL_COLUMNS[3], day);
		newValues.put(FinancialMiscEntries.ALL_COLUMNS[2], desc);
		newValues.put(FinancialMiscEntries.ALL_COLUMNS[3], amount);
		newValues.put(FinancialMiscEntries.ALL_COLUMNS[4], note);

		// Insert it into the database.
		return db.update(FinancialMiscEntries.TABLE_NAME, newValues, where, null) != 0;
	}

	public Cursor getFinancialMisc(long rowId) {
		String where = FinancialMiscEntries.ALL_COLUMNS[0] + "=" + rowId;
		Cursor c = 	db.query(true, FinancialMiscEntries.TABLE_NAME, FinancialMiscEntries.ALL_COLUMNS,
				where, null, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}

	// Return all data in the database.
	public Cursor getAllFinancialMiscs() {
		String where = null;
		Cursor c = 	db.query(FinancialMiscEntries.TABLE_NAME, FinancialMiscEntries.ALL_COLUMNS,
				where, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}

	// Delete a row from the database, by rowId (primary key)
	public boolean deleteFinancialMisc(long rowId) {
		String where = FinancialMiscEntries.ALL_COLUMNS[0] + "=" + rowId;
		return db.delete(FinancialMiscEntries.TABLE_NAME, where, null) != 0;
	}

	public void deleteAllFinancialMiscs() {
		Cursor c = getAllFinancialMiscs();
		long rowId = c.getColumnIndexOrThrow(FinancialMiscEntries.ALL_COLUMNS[0]);
		if (c.moveToFirst()) {
			do {
				deleteFinancialMisc(c.getLong((int) rowId));
			} while (c.moveToNext());
		}
		c.close();
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
			db.execSQL(SQL_CREATE_CAREER_GOAL_ENTRIES);
			db.execSQL(SQL_CREATE_JOB_ENTRIES);
			db.execSQL(SQL_CREATE_IDENTITY_ENTRIES);
			db.execSQL(SQL_CREATE_COMPANY_ENTRIES);

			//finance
			db.execSQL(SQL_CREATE_CASH_ENTRIES);
			db.execSQL(SQL_CREATE_ASSET_ENTRIES);
			db.execSQL(SQL_CREATE_STOCK_SECURITY_ENTRIES);
			db.execSQL(SQL_CREATE_FINANCIAL_GOAL_ENTRIES);
			db.execSQL(SQL_CREATE_LIABILITY_ENTRIES);
			db.execSQL(SQL_CREATE_CREDIT_CARD_ENTRIES);
			db.execSQL(SQL_CREATE_FINANCIAL_MISC_ENTRIES);

			//EDUCATION SECTION
            db.execSQL(SQL_CREATE_COLLEGE_ENTRIES);
            db.execSQL(SQL_CREATE_COLLEGE_APPLICATION_ENTRIES);
            db.execSQL(SQL_CREATE_COLLEGE_FINANCE_ENTRIES);

			//HEALTH SECTION
			db.execSQL(SQL_CREATE_MEDICAL_REPORT_ENTRIES);
			db.execSQL(SQL_CREATE_MEDICATION_ENTRIES);
			db.execSQL(SQL_CREATE_APPOINTMENT_ENTRIES);
			db.execSQL(SQL_CREATE_DOCTOR_VISIT_ENTRIES);
			db.execSQL(SQL_CREATE_WEIGHT_LOSS_AND_DIET_PLAN_ENTRIES);
			db.execSQL(SQL_CREATE_EXERCISE_PLAN_ENTRIES);
			db.execSQL(SQL_CREATE_RECIPE_ENTRIES);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// This database is only a cache for online data, so its upgrade policy is
			// to simply to discard the data and start over
			//LOGIN
			db.execSQL(SQL_DELETE_LOGIN_ENTRIES);
			//CAREER
			db.execSQL(SQL_DELETE_CONTRACT_ENTRIES);
			db.execSQL(SQL_DELETE_CAREER_GOAL_ENTRIES);
			db.execSQL(SQL_DELETE_IDENTITY_ENTRIES);
			db.execSQL(SQL_DELETE_JOB_ENTRIES);
			db.execSQL(SQL_DELETE_COMPANY_ENTRIES);
			//FINANCE
			db.execSQL(SQL_DELETE_CASH_ENTRIES);
			db.execSQL(SQL_DELETE_STOCK_SECURITY_ENTRIES);
			db.execSQL(SQL_DELETE_FINANCIAL_GOAL_ENTRIES);
			db.execSQL(SQL_DELETE_ASSET_ENTRIES);
			db.execSQL(SQL_DELETE_LIABILITY_ENTRIES);
			db.execSQL(SQL_DELETE_CREDIT_CARD_ENTRIES);
			db.execSQL(SQL_DELETE_FINANCIAL_MISC_ENTRIES);
			//EDUCATION
            db.execSQL(SQL_DELETE_COLLEGE_ENTRIES);
            db.execSQL(SQL_DELETE_COLLEGE_FINANCE_ENTRIES);
            db.execSQL(SQL_DELETE_COLLEGE_APPLICATION_ENTRIES);
			//HEALTH
			db.execSQL(SQL_DELETE_MEDICAL_REPORT_ENTRIES);
			db.execSQL(SQL_DELETE_MEDICATION_ENTRIES);
			db.execSQL(SQL_DELETE_APPOINTMENT_ENTRIES);
			db.execSQL(SQL_DELETE_DOCTOR_VISIT_ENTRIES);
			db.execSQL(SQL_DELETE_WEIGHT_LOSS_AND_DIET_PLAN_ENTRIES);
			db.execSQL(SQL_DELETE_EXERCISE_PLAN_ENTRIES);
			db.execSQL(SQL_DELETE_RECIPE_ENTRIES);
			onCreate(db);
		}

		public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			onUpgrade(db, oldVersion, newVersion);
		}
	}







	/* MEDICAL REPORT ENTRIES */
	/**
	 * insert a new medical report into the database
     * @param bloodPressure
     * @param ldl
     * @param hdl
     * @param cholestot
     * @param glucose
     * @param bloodtype
     * @param allergies
     * @param user
     * @return
     */
	public long insertMedicalReport(String bloodPressure, String ldl, String hdl,
			String cholestot, String glucose, String bloodtype, String allergies, int user ) {
		// Create row's data:
		ContentValues initialValues = new ContentValues();
		initialValues.put(MedicalReportEntries.ALL_COLUMNS[1], bloodPressure);
		initialValues.put(MedicalReportEntries.ALL_COLUMNS[2], ldl);
		initialValues.put(MedicalReportEntries.ALL_COLUMNS[3], hdl);
		initialValues.put(MedicalReportEntries.ALL_COLUMNS[4], cholestot);
		initialValues.put(MedicalReportEntries.ALL_COLUMNS[5], glucose);
		initialValues.put(MedicalReportEntries.ALL_COLUMNS[6], bloodtype);
		initialValues.put(MedicalReportEntries.ALL_COLUMNS[7], allergies);
		initialValues.put(MedicalReportEntries.ALL_COLUMNS[8], user);

		// Insert it into the database.
		return db.insert(MedicalReportEntries.TABLE_NAME, null, initialValues);
	}

	/**
	 * Update a area of the db.
	 * @param rowId
	 * @param bloodPressure
	 * @param ldl
	 * @param hdl
	 * @param cholestot
	 * @param glucose
	 * @param bloodtype
	 * @param allergiese
	 * @return true if successful
	 */
	public boolean updateMedicalReport(long rowId, String bloodPressure, String ldl, String hdl,
	                                   String cholestot, String glucose, String bloodtype, String allergiese) {
		String where = MedicalReportEntries._ID + "=" + rowId;
		// TODO: Update data in the row with new fields.
		// TODO: Also change the function's arguments to be what you need!
		ContentValues newValues = new ContentValues();
		newValues.put(MedicalReportEntries.ALL_COLUMNS[1], bloodPressure);
		newValues.put(MedicalReportEntries.ALL_COLUMNS[2], ldl);
		newValues.put(MedicalReportEntries.ALL_COLUMNS[3], hdl);
		newValues.put(MedicalReportEntries.ALL_COLUMNS[4], cholestot);
		newValues.put(MedicalReportEntries.ALL_COLUMNS[5], glucose);
		newValues.put(MedicalReportEntries.ALL_COLUMNS[6], bloodtype);
		newValues.put(MedicalReportEntries.ALL_COLUMNS[7], allergiese);

		// Insert it into the database.
		return db.update(MedicalReportEntries.TABLE_NAME, newValues, where, null) != 0;
	}

	public Cursor getMedicalReport(long rowId) {
		String where = MedicalReportEntries._ID + "=" + rowId;
		Cursor c = 	db.query(true, MedicalReportEntries.TABLE_NAME, MedicalReportEntries.ALL_COLUMNS,
				where, null, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}

	// Return all data in the database.
	public Cursor getAllMedicalReports() {
		String where = "";
		Cursor c = 	db.query(MedicalReportEntries.TABLE_NAME, MedicalReportEntries.ALL_COLUMNS,
				where, null, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}

	// Delete a row from the database, by rowId (primary key)
	public boolean deleteMedicalReport(long rowId) {
		String where = MedicalReportEntries._ID + "=" + rowId;
		return db.delete(MedicalReportEntries.TABLE_NAME, where, null) != 0;
	}

	public void deleteAllMedicalReports() {
		Cursor c = getAllMedicalReports();
		long rowId = c.getColumnIndexOrThrow(MedicalReportEntries._ID);
		if (c.moveToFirst()) {
			do {
				deleteMedicalReport(c.getLong((int) rowId));
			} while (c.moveToNext());
		}
		c.close();
	}



	//APPOINTMENT METHODS
	/**
	 *
	 * @param doctor
	 * @param date
	 * @param time
	 * @param reason
	 * @param address
	 * @param phone
	 * @param user
	 * @return row number, type long Primary Key
	 */
	public long insertAppointment(String doctor, int date, int time, String reason, String address,
	                               int phone, int user) {
		// Create row's data:
		ContentValues initialValues = new ContentValues();
		initialValues.put(AppointmentEntries.ALL_COLUMNS[1], doctor);
		initialValues.put(AppointmentEntries.ALL_COLUMNS[2], date);
		initialValues.put(AppointmentEntries.ALL_COLUMNS[3], time);
		initialValues.put(AppointmentEntries.ALL_COLUMNS[4], reason);
		initialValues.put(AppointmentEntries.ALL_COLUMNS[5], address);
		initialValues.put(AppointmentEntries.ALL_COLUMNS[6], phone);
		initialValues.put(AppointmentEntries.ALL_COLUMNS[7], user);

		// Insert it into the database.
		return db.insert(AppointmentEntries.TABLE_NAME, null, initialValues);
	}

	/**
	 *
	 * @param rowId row number, type long Primary Key
	 * @param doctor
	 * @param date
	 * @param time
	 * @param reason
	 * @param address
	 * @param phone
	 * @return
	 */
	public boolean updateAppointment(long rowId, String doctor, int date, int time,
	                                   String reason, String address, int phone) {
		String where = AppointmentEntries._ID + "=" + rowId;
		// TODO: Update data in the row with new fields.
		// TODO: Also change the function's arguments to be what you need!
		ContentValues initialValues = new ContentValues();
		initialValues.put(AppointmentEntries.ALL_COLUMNS[1], doctor);
		initialValues.put(AppointmentEntries.ALL_COLUMNS[2], date);
		initialValues.put(AppointmentEntries.ALL_COLUMNS[3], time);
		initialValues.put(AppointmentEntries.ALL_COLUMNS[4], reason);
		initialValues.put(AppointmentEntries.ALL_COLUMNS[5], address);
		initialValues.put(AppointmentEntries.ALL_COLUMNS[6], phone);

		// Insert it into the database.
		return db.update(AppointmentEntries.TABLE_NAME, initialValues, where, null) != 0;
	}

	public Cursor getAppointment(long rowId) {
		String where = AppointmentEntries._ID + "=" + rowId;
		Cursor c = 	db.query(true, AppointmentEntries.TABLE_NAME, AppointmentEntries.ALL_COLUMNS,
				where, null, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}

	// Return all data in the database.
	public Cursor getAllAppointments() {
		String where = null;
		Cursor c = 	db.query(AppointmentEntries.TABLE_NAME, AppointmentEntries.ALL_COLUMNS,
				where, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}

	// Delete a row from the database, by rowId (primary key)
	public boolean deleteAppointment(long rowId) {
		String where = AppointmentEntries._ID + "=" + rowId;
		return db.delete(AppointmentEntries.TABLE_NAME, where, null) != 0;
	}

	public void deleteAllAppointments() {
		Cursor c = getAllAppointments();
		long rowId = c.getColumnIndexOrThrow(AppointmentEntries._ID);
		if (c.moveToFirst()) {
			do {
				deleteAppointment(c.getLong((int) rowId));
			} while (c.moveToNext());
		}
		c.close();
	}


	//DOCTORS VISIT METHODS
	/**
	 *
	 * @param lastCheckUpDate
	 * @param insuranceCompany
	 * @param insurancePolicyNum
	 * @param user
	 * @return row number, type long Primary Key
	 */
	public long insertDoctorVisit(int lastCheckUpDate, String insuranceCompany, int insurancePolicyNum, int user) {
		// Create row's data:
		ContentValues initialValues = new ContentValues();
		initialValues.put(DoctorVisitEntries.LAST_CHECK_UP_DATE, lastCheckUpDate);
		initialValues.put(DoctorVisitEntries.HEALTH_INSURANCE_COMPANY, insuranceCompany);
		initialValues.put(DoctorVisitEntries.HEALTH_INSURANCE_POLICY_NUM, insurancePolicyNum);
		initialValues.put(DoctorVisitEntries.HASH_ID, user);

		// Insert it into the database.
		return db.insert(DoctorVisitEntries.TABLE_NAME, null, initialValues);
	}

	/**
	 *
	 * @param rowId row number, type long Primary Key
	 * @param lastCheckUpDate
	 * @param insuranceCompany
	 * @param insurancePolicyNum
	 * @return
	 */
	public boolean updateDoctorVisit(long rowId, int lastCheckUpDate, String insuranceCompany, int insurancePolicyNum) {
		String where = DoctorVisitEntries._ID + "=" + rowId;
		// TODO: Update data in the row with new fields.
		// TODO: Also change the function's arguments to be what you need!
		ContentValues newValues = new ContentValues();
		newValues.put(DoctorVisitEntries.LAST_CHECK_UP_DATE, lastCheckUpDate);
		newValues.put(DoctorVisitEntries.HEALTH_INSURANCE_COMPANY, insuranceCompany);
		newValues.put(DoctorVisitEntries.HEALTH_INSURANCE_POLICY_NUM, insurancePolicyNum);

		// Insert it into the database.
		return db.update(DoctorVisitEntries.TABLE_NAME, newValues, where, null) != 0;
	}

	public Cursor getDoctorVisit(long rowId) {
		String where = DoctorVisitEntries._ID + "=" + rowId;
		Cursor c = 	db.query(true, DoctorVisitEntries.TABLE_NAME, DoctorVisitEntries.ALL_COLUMNS,
				where, null, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}

	// Return all data in the database.
	public Cursor getAllDoctorVisits() {
		String where = null;
		Cursor c = 	db.query(DoctorVisitEntries.TABLE_NAME, DoctorVisitEntries.ALL_COLUMNS,
				where, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}

	// Delete a row from the database, by rowId (primary key)
	public boolean deleteDoctorVisit(long rowId) {
		String where = DoctorVisitEntries._ID + "=" + rowId;
		return db.delete(DoctorVisitEntries.TABLE_NAME, where, null) != 0;
	}

	public void deleteAllDoctorVisits() {
		Cursor c = getAllDoctorVisits();
		long rowId = c.getColumnIndexOrThrow(DoctorVisitEntries._ID);
		if (c.moveToFirst()) {
			do {
				deleteDoctorVisit(c.getLong((int) rowId));
			} while (c.moveToNext());
		}
		c.close();
	}


	//MEDICATION METHODS
	/**
	 *
	 * @param medName
	 * @param dosage
	 * @param medDuration
	 * @param medReason
	 * @param pharmName
	 * @param pharmPhone
	 * @param user
	 * @return row number, type long Primary Key
	 */
	public long insertMedication(String medName, String dosage, String medDuration,
	                             String medReason, String pharmName, String pharmPhone, int user) {

		// Create row's data:
		ContentValues initialValues = new ContentValues();
		initialValues.put(MedicationEntries.MEDICATION_NAME, medName);
		initialValues.put(MedicationEntries.DOSAGE, dosage);
		initialValues.put(MedicationEntries.MEDICATION_DURATION, medDuration);
		initialValues.put(MedicationEntries.MEDICATION_REASON, medReason);
		initialValues.put(MedicationEntries.PHARMACY_NAME, pharmName);
		initialValues.put(MedicationEntries.PHARMACY_PHONE, pharmPhone);
		initialValues.put(MedicationEntries.HASH_ID, user);

		// Insert it into the database.
		return db.insert(MedicationEntries.TABLE_NAME, null, initialValues);
	}

	/**
	 *
	 * @param rowId row number, type long Primary Key
	 * @param medName
	 * @param dosage
	 * @param medDuration
	 * @param medReason
	 * @param pharmName
	 * @param pharmPhone
	 * @return
	 */
	public boolean updateMedication(long rowId, String medName, String dosage, String medDuration,
	                                   String medReason, String pharmName, String pharmPhone) {
		String where = MedicationEntries._ID + "=" + rowId;
		// TODO: Update data in the row with new fields.
		// TODO: Also change the function's arguments to be what you need!
		ContentValues initialValues = new ContentValues();
		initialValues.put(MedicationEntries.MEDICATION_NAME, medName);
		initialValues.put(MedicationEntries.DOSAGE, dosage);
		initialValues.put(MedicationEntries.MEDICATION_DURATION, medDuration);
		initialValues.put(MedicationEntries.MEDICATION_REASON, medReason);
		initialValues.put(MedicationEntries.PHARMACY_NAME, pharmName);
		initialValues.put(MedicationEntries.PHARMACY_PHONE, pharmPhone);

		// Insert it into the database.
		return db.update(MedicationEntries.TABLE_NAME, initialValues, where, null) != 0;
	}

	public Cursor getMedication(long rowId) {
		String where = MedicationEntries._ID + "=" + rowId;
		Cursor c = 	db.query(true, MedicationEntries.TABLE_NAME, MedicationEntries.ALL_COLUMNS,
				where, null, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}

	// Return all data in the database.
	public Cursor getAllMedications() {
		String where = null;
		Cursor c = 	db.query(MedicationEntries.TABLE_NAME, MedicationEntries.ALL_COLUMNS,
				where, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}

	// Delete a row from the database, by rowId (primary key)
	public boolean deleteMedication(long rowId) {
		String where = MedicationEntries._ID + "=" + rowId;
		return db.delete(MedicationEntries.TABLE_NAME, where, null) != 0;
	}

	public void deleteAllMedications() {
		Cursor c = getAllMedications();
		long rowId = c.getColumnIndexOrThrow(MedicationEntries._ID);
		if (c.moveToFirst()) {
			do {
				deleteMedication(c.getLong((int) rowId));
			} while (c.moveToNext());
		}
		c.close();
	}

	//WEIGHT LOSS and DIET PLAN
	/**
	 *
	 * @param planName
	 * @param startDate
	 * @param endDate
	 * @param currentWeight
	 * @param goalWeight
	 * @param user
	 * @return row number, type long Primary Key
	 */
	public long insertWeightDietRow(String planName, int startDate, int endDate, int currentWeight,
	                                int goalWeight, int user) {
		// Create row's data:
		ContentValues initialValues = new ContentValues();
		initialValues.put(WeightLossDietPlanEntries.ALL_COLUMNS[1], planName);
		initialValues.put(WeightLossDietPlanEntries.ALL_COLUMNS[2], startDate);
		initialValues.put(WeightLossDietPlanEntries.ALL_COLUMNS[3], endDate);
		initialValues.put(WeightLossDietPlanEntries.ALL_COLUMNS[4], currentWeight);
		initialValues.put(WeightLossDietPlanEntries.ALL_COLUMNS[5], goalWeight);
		initialValues.put(WeightLossDietPlanEntries.ALL_COLUMNS[6], user);

		// Insert it into the database.
		return db.insert(WeightLossDietPlanEntries.TABLE_NAME, null, initialValues);
	}

	/**
	 *
	 * @param rowId row number, type long Primary Key
	 * @param planName
	 * @param startDate
	 * @param endDate
	 * @param currentWeight
	 * @param goalWeight
	 * @return
	 */
	public boolean updateWeightDietRow(long rowId, String planName, int startDate, int endDate, int currentWeight,
	                                   int goalWeight) {
		String where = WeightLossDietPlanEntries._ID + "=" + rowId;
		// TODO: Update data in the row with new fields.
		// TODO: Also change the function's arguments to be what you need!
		ContentValues initialValues = new ContentValues();
		initialValues.put(WeightLossDietPlanEntries.ALL_COLUMNS[1], planName);
		initialValues.put(WeightLossDietPlanEntries.ALL_COLUMNS[2], startDate);
		initialValues.put(WeightLossDietPlanEntries.ALL_COLUMNS[3], endDate);
		initialValues.put(WeightLossDietPlanEntries.ALL_COLUMNS[4], currentWeight);
		initialValues.put(WeightLossDietPlanEntries.ALL_COLUMNS[5], goalWeight);

		// Insert it into the database.
		return db.update(WeightLossDietPlanEntries.TABLE_NAME, initialValues, where, null) != 0;
	}

	public Cursor getWeightDietRow(long rowId) {
		String where = WeightLossDietPlanEntries._ID + "=" + rowId;
		Cursor c = 	db.query(true, WeightLossDietPlanEntries.TABLE_NAME, WeightLossDietPlanEntries.ALL_COLUMNS,
				where, null, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}

	// Return all data in the database.
	public Cursor getAllWeightDietRows() {
		String where = null;
		Cursor c = 	db.query(WeightLossDietPlanEntries.TABLE_NAME, WeightLossDietPlanEntries.ALL_COLUMNS,
				where, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}

	// Delete a row from the database, by rowId (primary key)
	public boolean deleteWeightDietRow(long rowId) {
		String where = WeightLossDietPlanEntries._ID + "=" + rowId;
		return db.delete(WeightLossDietPlanEntries.TABLE_NAME, where, null) != 0;
	}

	public void deleteAllWeightDietRows() {
		Cursor c = getAllWeightDietRows();
		long rowId = c.getColumnIndexOrThrow(WeightLossDietPlanEntries._ID);
		if (c.moveToFirst()) {
			do {
				deleteWeightDietRow(c.getLong((int) rowId));
			} while (c.moveToNext());
		}
		c.close();
	}

	//EXERCISE PLAN
	/**
	 *
	 * @param planName
	 * @param caloriesBurned
	 * @param duration
	 * @param muscleGroup
	 * @param user
	 * @return row number, type long Primary Key
	 */
	public long insertExercisePlan(String planName, int caloriesBurned, int duration, String muscleGroup, int user) {
		// Create row's data:
		ContentValues initialValues = new ContentValues();
		initialValues.put(ExercisePlanEntries.ALL_COLUMNS[1], planName);
		initialValues.put(ExercisePlanEntries.ALL_COLUMNS[2], caloriesBurned);
		initialValues.put(ExercisePlanEntries.ALL_COLUMNS[3], duration);
		initialValues.put(ExercisePlanEntries.ALL_COLUMNS[4], muscleGroup);
		initialValues.put(ExercisePlanEntries.ALL_COLUMNS[5], user);

		// Insert it into the database.
		return db.insert(ExercisePlanEntries.TABLE_NAME, null, initialValues);
	}

	/**
	 *
	 * @param rowId row number, type long Primary Key
	 * @param planName
	 * @param caloriesBurned
	 * @param duration
	 * @param muscleGroup
	 * @return
	 */
	public boolean updateExercisePlan(long rowId, String planName, int caloriesBurned,
	                                   int duration, String muscleGroup) {
		String where = ExercisePlanEntries._ID + "=" + rowId;
		// TODO: Update data in the row with new fields.
		// TODO: Also change the function's arguments to be what you need!
		ContentValues initialValues = new ContentValues();
		initialValues.put(ExercisePlanEntries.ALL_COLUMNS[1], planName);
		initialValues.put(ExercisePlanEntries.ALL_COLUMNS[2], caloriesBurned);
		initialValues.put(ExercisePlanEntries.ALL_COLUMNS[3], duration);
		initialValues.put(ExercisePlanEntries.ALL_COLUMNS[4], muscleGroup);

		// Insert it into the database.
		return db.update(ExercisePlanEntries.TABLE_NAME, initialValues, where, null) != 0;
	}

	public Cursor getExercisePlan(long rowId) {
		String where = ExercisePlanEntries._ID + "=" + rowId;
		Cursor c = 	db.query(true, ExercisePlanEntries.TABLE_NAME, ExercisePlanEntries.ALL_COLUMNS,
				where, null, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}

	// Return all data in the database.
	public Cursor getAllExercisePlans() {
		String where = null;
		Cursor c = 	db.query(ExercisePlanEntries.TABLE_NAME, ExercisePlanEntries.ALL_COLUMNS,
				where, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}

	// Delete a row from the database, by rowId (primary key)
	public boolean deleteExercisePlan(long rowId) {
		String where = ExercisePlanEntries._ID + "=" + rowId;
		return db.delete(ExercisePlanEntries.TABLE_NAME, where, null) != 0;
	}

	public void deleteAllExercisePlans() {
		Cursor c = getAllExercisePlans();
		long rowId = c.getColumnIndexOrThrow(ExercisePlanEntries._ID);
		if (c.moveToFirst()) {
			do {
				deleteExercisePlan(c.getLong((int) rowId));
			} while (c.moveToNext());
		}
		c.close();
	}

	//RECIPES

	/**
	 * Inserts recipe variables into the database
	 * @param recipeName
	 * @param servings
	 * @param cookTime
	 * @param description
	 * @param ingredients
	 * @param directions
	 * @param user
	 * @return row number, type long Primary Key
	 */
	public long insertRecipe(String recipeName, int servings, int cookTime, String description,
	                         String ingredients, String directions, int user) {
		// Create row's data:
		ContentValues initialValues = new ContentValues();
		initialValues.put(RecipeEntries.ALL_COLUMNS[1], recipeName);
		initialValues.put(RecipeEntries.ALL_COLUMNS[2], servings);
		initialValues.put(RecipeEntries.ALL_COLUMNS[3], cookTime);
		initialValues.put(RecipeEntries.ALL_COLUMNS[4], description);
		initialValues.put(RecipeEntries.ALL_COLUMNS[5], ingredients);
		initialValues.put(RecipeEntries.ALL_COLUMNS[6], directions);
		initialValues.put(RecipeEntries.ALL_COLUMNS[7], user);

		// Insert it into the database.
		return db.insert(RecipeEntries.TABLE_NAME, null, initialValues);
	}

	/**
	 *
	 * @param rowId the primary key, row number
	 * @param recipeName
	 * @param servings
	 * @param cookTime
	 * @param description
	 * @param ingredients
	 * @param directions
	 * @return true if update is successful
	 */
	public boolean updateRecipe(long rowId, String recipeName, int servings, int cookTime,
	                                   String description, String ingredients, String directions) {
		String where = RecipeEntries._ID + "=" + rowId;
		// TODO: Update data in the row with new fields.
		// TODO: Also change the function's arguments to be what you need!
		ContentValues initialValues = new ContentValues();
		initialValues.put(RecipeEntries.ALL_COLUMNS[1], recipeName);
		initialValues.put(RecipeEntries.ALL_COLUMNS[2], servings);
		initialValues.put(RecipeEntries.ALL_COLUMNS[3], cookTime);
		initialValues.put(RecipeEntries.ALL_COLUMNS[4], description);
		initialValues.put(RecipeEntries.ALL_COLUMNS[5], ingredients);
		initialValues.put(RecipeEntries.ALL_COLUMNS[6], directions);

		// Insert it into the database.
		return db.update(RecipeEntries.TABLE_NAME, initialValues, where, null) != 0;
	}

	public Cursor getRecipe(long rowId) {
		String where = RecipeEntries._ID + "=" + rowId;
		Cursor c = 	db.query(true, RecipeEntries.TABLE_NAME, RecipeEntries.ALL_COLUMNS,
				where, null, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}

	// Return all data in the database.
	public Cursor getAllRecipes() {
		String where = null;
		Cursor c = 	db.query(RecipeEntries.TABLE_NAME, RecipeEntries.ALL_COLUMNS,
				where, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}

	// Delete a row from the database, by rowId (primary key)
	public boolean deleteRecipe(long rowId) {
		String where = RecipeEntries._ID + "=" + rowId;
		return db.delete(RecipeEntries.TABLE_NAME, where, null) != 0;
	}

	public void deleteAllRecipes() {
		Cursor c = getAllRecipes();
		long rowId = c.getColumnIndexOrThrow(RecipeEntries._ID);
		if (c.moveToFirst()) {
			do {
				deleteRecipe(c.getLong((int) rowId));
			} while (c.moveToNext());
		}
		c.close();
	}
}