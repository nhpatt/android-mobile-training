package com.liferay.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import com.liferay.mobile.push.Push;
import com.liferay.mobile.screens.auth.login.LoginListener;
import com.liferay.mobile.screens.auth.login.LoginScreenlet;
import com.liferay.mobile.screens.context.SessionContext;
import com.liferay.mobile.screens.context.User;
import com.liferay.mobile.screens.context.storage.CredentialsStorageBuilder;

public class LoginScreensActivity extends AppCompatActivity implements LoginListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login_screens);

		LoginScreenlet loginScreenlet = (LoginScreenlet) findViewById(R.id.login);
		loginScreenlet.setListener(this);

		SessionContext.loadStoredCredentials(CredentialsStorageBuilder.StorageType.SHARED_PREFERENCES);
		if (SessionContext.isLoggedIn()) {
			goToContent();
		}
	}

	@Override
	public void onLoginSuccess(User user) {
		Toast.makeText(this, user.getFullName(), Toast.LENGTH_SHORT).show();
		goToContent();
	}

	private void goToContent() {
		startActivity(new Intent(this, ContentActivity.class));
	}

	@Override
	public void onLoginFailure(Exception e) {
		Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
	}
}
