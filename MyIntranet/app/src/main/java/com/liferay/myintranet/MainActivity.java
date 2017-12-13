package com.liferay.myintranet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

	private EditText emailView;
	private EditText passwordView;

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
	public void onClick(View v) {

		if ("nhpatt@gmail.com".equals(emailView.getText().toString()) && "pass".equals(
			passwordView.getText().toString())) {
			startActivity(new Intent(this, HomeActivity.class));
		} else {
			Toast.makeText(this, "Wrong user or password :(", Toast.LENGTH_LONG).show();
		}
	}
}
