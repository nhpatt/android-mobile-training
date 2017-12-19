package com.liferay.myapplication;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.CheckBox;
import com.liferay.mobile.screens.context.SessionContext;
import com.liferay.mobile.screens.context.User;
import com.liferay.mobile.screens.context.storage.CredentialsStorageBuilder;
import com.liferay.mobile.screens.viewsets.material.auth.login.LoginView;

public class MyLogin extends LoginView {

	private CheckBox saveCredentials;

	public MyLogin(Context context) {
		super(context);
	}

	public MyLogin(Context context, AttributeSet attributes) {
		super(context, attributes);
	}

	public MyLogin(Context context, AttributeSet attributes, int defaultStyle) {
		super(context, attributes, defaultStyle);
	}

	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();

		saveCredentials = findViewById(R.id.guardar_credenciales);
	}

	@Override
	public void showFinishOperation(User user) {
		super.showFinishOperation(user);

		if (saveCredentials.isChecked()) {
			SessionContext.storeCredentials(CredentialsStorageBuilder.StorageType.SHARED_PREFERENCES);
		}
	}
}
