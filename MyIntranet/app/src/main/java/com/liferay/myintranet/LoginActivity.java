package com.liferay.myintranet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import com.liferay.mobile.screens.auth.login.LoginListener;
import com.liferay.mobile.screens.auth.login.LoginScreenlet;
import com.liferay.mobile.screens.context.User;

public class LoginActivity extends AppCompatActivity implements LoginListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		LoginScreenlet loginScreenlet = findViewById(R.id.login);
		loginScreenlet.setListener(this);
	}

	@Override
	public void onLoginSuccess(User user) {
		Toast.makeText(this, user.getFirstName(), Toast.LENGTH_SHORT).show();
		startActivity(new Intent(this, WebContentActivity.class));
	}

	@Override
	public void onLoginFailure(Exception e) {
		Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
	}
}
