package com.liferay.myintranet;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.CheckBox;
import com.liferay.mobile.screens.context.SessionContext;
import com.liferay.mobile.screens.context.User;
import com.liferay.mobile.screens.viewsets.material.auth.login.LoginView;

import static com.liferay.mobile.screens.context.storage.CredentialsStorageBuilder.StorageType.SHARED_PREFERENCES;

public class CustomLoginView extends LoginView {
	private CheckBox saveCredentialsView;

	public CustomLoginView(Context context) {
		super(context);
	}

	public CustomLoginView(Context context, AttributeSet attributes) {
		super(context, attributes);
	}

	public CustomLoginView(Context context, AttributeSet attributes, int defaultStyle) {
		super(context, attributes, defaultStyle);
	}

	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();

		saveCredentialsView = findViewById(R.id.save_credentials);
	}

	@Override
	public void showFinishOperation(User user) {
		if (saveCredentialsView.isChecked()) {
			SessionContext.storeCredentials(SHARED_PREFERENCES);
		} else {
			SessionContext.removeStoredCredentials(SHARED_PREFERENCES);
		}
	}
}
