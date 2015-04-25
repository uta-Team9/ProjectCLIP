package team9.clip_loginhomecareer;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A login screen that offers login via email/password.
 */
public class Login extends Activity {

	//ADDED FROM http://stackoverflow.com/questions/9370293/add-a-remember-me-checkbox
	private String username,password;
	private SharedPreferences loginPreferences;
	private SharedPreferences.Editor loginPrefsEditor;
	private Boolean saveLogin;
	//END ADDED FROM

	private DatabaseContract dB;
	private int User_ID = 0;

	/**
	 * Keep track of the login task to ensure we can cancel it if requested.
	 */
	private UserLoginTask mAuthTask = null;

	// UI references.
	private AutoCompleteTextView mEmailView;
	private EditText mPasswordView;
	private View mProgressView;
	private View mLoginFormView;
	private CheckBox mRememberMe;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		openDB();

		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_activity);

		// Set up the login form.
		mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
		mRememberMe = (CheckBox) findViewById(R.id.remember_me);
		mPasswordView = (EditText) findViewById(R.id.password);

		//ADDED FROM WEBSITE
		saveLogin = loginPreferences.getBoolean("saveLogin", false);
		if (saveLogin == true) {
			mEmailView.setText(loginPreferences.getString("username", ""));
			mPasswordView.setText(loginPreferences.getString("password", ""));
			mRememberMe.setChecked(true);
		}
		//ADDED FROM WEBSITE


		mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
				if (id == R.id.login || id == EditorInfo.IME_NULL) {
					attemptLogin();
					return true;
				}
				return false;
			}
		});

		Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
		mEmailSignInButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				attemptLogin();

				//ADDED FROM http://stackoverflow.com/questions/9370293/add-a-remember-me-checkbox
				InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(mEmailView.getWindowToken(), 0);

				username = mEmailView.getText().toString();
				password = mPasswordView.getText().toString();

				if (mRememberMe.isChecked()) {
					loginPrefsEditor.putBoolean("saveLogin", true);
					loginPrefsEditor.putString("username", username);
					loginPrefsEditor.putString("password", password);
					loginPrefsEditor.commit();
				} else {
					loginPrefsEditor.clear();
					loginPrefsEditor.commit();
				}
				//End of added

			}
		});

		Button registerButton = (Button) findViewById(R.id.register_button);
		registerButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				moveToRegister();
			}
		});

		Button recoveryButton = (Button) findViewById(R.id.forgot_password_button);
		recoveryButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				moveToRecovery();
			}
		});


		mLoginFormView = findViewById(R.id.login_form);
		mProgressView = findViewById(R.id.login_progress);
	}

	public void moveToRegister() {
		Intent intent = new Intent(this, NewUser.class);
		startActivity(intent);
	}

	public void moveToMainPage() {
		Intent intent = new Intent(this, HomeScreen.class);
		loginPrefsEditor.putInt("ID", User_ID);
		loginPrefsEditor.commit();
		dB.close();
		startActivity(intent);
	}

	public void moveToRecovery() {
		Intent intent = new Intent(this, PasswordRecovery.class);
		startActivity(intent);
	}

	/**
	 * Attempts to sign in or register the account specified by the login form.
	 * If there are form errors (invalid email, missing fields, etc.), the
	 * errors are presented and no actual login attempt is made.
	 */
	public void attemptLogin() {
		if (mAuthTask != null) {
			return;
		}

		// Reset errors.
		mEmailView.setError(null);
		mPasswordView.setError(null);

		// Store values at the time of the login attempt.
		String email = mEmailView.getText().toString();
		String password = mPasswordView.getText().toString();

		boolean cancel = false;
		View focusView = null;


		// Check for a valid password, if the user entered one.
		if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
			mPasswordView.setError(getString(R.string.error_invalid_password));
			focusView = mPasswordView;
			cancel = true;
		}

		// Check for a valid email address.
		if (TextUtils.isEmpty(email)) {
			mEmailView.setError(getString(R.string.error_field_required));
			focusView = mEmailView;
			cancel = true;
		} else if (!isEmailValid(email)) {
			mEmailView.setError(getString(R.string.error_invalid_email));
			focusView = mEmailView;
			cancel = true;
		}

		if (cancel) {
			// There was an error; don't attempt login and focus the first
			// form field with an error.
			focusView.requestFocus();
		} else {
			// Show a progress spinner, and kick off a background task to
			// perform the user login attempt.
			showProgress(true);
			mAuthTask = new UserLoginTask(email, password);
			mAuthTask.execute((Void) null);
		}
	}

	private boolean isEmailValid(String email) {
		//TODO: Replace this with your own logic
		if(!email.contains(":") && email.contains("@"))
			return true;
		return false;
	}

	private boolean isPasswordValid(String password) {
		//TODO: Replace this with your own logic
		if(!password.contains(":") && password.length() > 0)
			return true;
		return false;
	}

	private void openDB() {
		dB = new DatabaseContract(this);
		dB.open();
		loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
		loginPrefsEditor = loginPreferences.edit();
	}

	/**
	 * Shows the progress UI and hides the login form.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
	public void showProgress(final boolean show) {
		// On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
		// for very easy animations. If available, use these APIs to fade-in
		// the progress spinner.
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
			int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

			mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
			mLoginFormView.animate().setDuration(shortAnimTime).alpha(
					show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
				@Override
				public void onAnimationEnd(Animator animation) {
					mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
				}
			});

			mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
			mProgressView.animate().setDuration(shortAnimTime).alpha(
					show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
				@Override
				public void onAnimationEnd(Animator animation) {
					mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
				}
			});


		} else {
			// The ViewPropertyAnimator APIs are not available, so simply show
			// and hide the relevant UI components.
			mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
			mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
		}
	}

	/**
	 * Represents an asynchronous login/registration task used to authenticate
	 * the user.
	 */
	public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

		private final String mEmail;
		private final String mPassword;

		UserLoginTask(String email, String password) {
			mEmail = email;
			mPassword = password;
		}

		@Override
		protected Boolean doInBackground(Void... params) {
			// TODO: attempt authentication against a network service.

			//putting together credentials list
			Cursor cursor = dB.getAllLoginRows();
			ArrayList<String> creds = new ArrayList<>();
			if (cursor.moveToFirst()) {
				do {
					//email is 2, password is 3
					creds.add((cursor.getString(2) + ":" + cursor.getString(3)));
					if(cursor.getString(2).equals(mEmail)) {
						if(cursor.getString(3).equals(mPassword)) {
							User_ID = cursor.getInt(0); //set 0 for row, 5 for hash
							cursor.close();
							return true;
						}
					}
				} while (cursor.moveToNext());
			}
			cursor.close();

			return false;
		}

		@Override
		protected void onPostExecute(final Boolean success) {
			mAuthTask = null;
			showProgress(false);

			if (success) {
				finish();
				//THIS IS WHERE WE MOVE TO MAIN PAGE!
				moveToMainPage();
			} else {
				mPasswordView.setError(getString(R.string.error_incorrect_password));
				mPasswordView.requestFocus();
			}
		}

		@Override
		protected void onCancelled() {
			mAuthTask = null;
			showProgress(false);
		}
	}
}