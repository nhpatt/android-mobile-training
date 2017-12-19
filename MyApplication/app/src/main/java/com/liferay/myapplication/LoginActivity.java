package com.liferay.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.liferay.mobile.screens.context.SessionContext;
import com.liferay.mobile.screens.context.storage.CredentialsStorageBuilder;

public class LoginActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
	}
}
