package com.liferay.myintranet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

		String email = emailView.getText().toString();
		if ("nhpatt@gmail.com".equals(email) && "pass".equals(
			passwordView.getText().toString())) {
			Intent intent = new Intent(this, HomeActivity.class);
			intent.putExtra("email", email);
			startActivity(intent);
		} else {
			Toast.makeText(this, "Wrong user or password :(", Toast.LENGTH_LONG).show();
		}
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
