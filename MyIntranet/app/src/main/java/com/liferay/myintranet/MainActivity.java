package com.liferay.myintranet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.liferay.mobile.android.auth.SignIn;
import com.liferay.mobile.android.auth.basic.BasicAuthentication;
import com.liferay.mobile.android.callback.typed.JSONObjectCallback;
import com.liferay.mobile.android.service.Session;
import com.liferay.mobile.android.service.SessionImpl;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

	private static final String TAG = "MyIntranet";
	private EditText emailView;
	private EditText passwordView;
	private boolean value;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button loginView = findViewById(R.id.login_view);
		loginView.setOnClickListener(this);

		emailView = findViewById(R.id.email_view);
		passwordView = findViewById(R.id.password_view);
	}

	@Override
	protected void onResume() {
		super.onResume();

		Log.e(TAG, String.valueOf(value));
	}

	@Override
	public void onClick(View v) {

		this.value = true;

		final String email = emailView.getText().toString();
		final String password = passwordView.getText().toString();

		BasicAuthentication basicAuthentication = new BasicAuthentication(email, password);
		Session session = new SessionImpl("http://10.0.3.2:8080", basicAuthentication);

		SignIn.signIn(session, new JSONObjectCallback() {
			@Override
			public void onFailure(Exception exception) {
				Toast.makeText(MainActivity.this, "Wrong user or password :(", Toast.LENGTH_LONG).show();
			}

			@Override
			public void onSuccess(JSONObject result) {
				Intent intent = new Intent(MainActivity.this, WebContentActivity.class);
				intent.putExtra("email", email);
				startActivity(intent);
			}
		});
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);

		outState.putBoolean("key", value);
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		value = savedInstanceState.getBoolean("key");

		super.onRestoreInstanceState(savedInstanceState);
	}
}
