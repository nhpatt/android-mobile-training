package com.liferay.myapplication;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.liferay.mobile.screens.base.BaseScreenlet;
import com.liferay.mobile.screens.context.User;
import com.liferay.mobile.screens.userportrait.UserPortraitScreenlet;

public class UserProfileView extends RelativeLayout implements UserProfileViewModel {

	private TextView emailView;
	private TextView userNameView;
	private UserPortraitScreenlet userPortraitView;

	public UserProfileView(Context context) {
		super(context);
	}

	public UserProfileView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public UserProfileView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
	public UserProfileView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
	}

	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();

		emailView = findViewById(R.id.email);
		userNameView = findViewById(R.id.user_name);
		userPortraitView = findViewById(R.id.user_portrait);
	}

	@Override
	public void showStartOperation(String defaultAction, User user) {
		emailView.setText(user.getEmail());
		userNameView.setText(user.getFullName());
		userPortraitView.setUserId(user.getId());
		userPortraitView.load();
	}

	@Override
	public void showStartOperation(String actionName) {

	}

	@Override
	public void showFinishOperation(String actionName) {

	}

	@Override
	public void showFailedOperation(String actionName, Exception e) {

	}

	@Override
	public BaseScreenlet getScreenlet() {
		return null;
	}

	@Override
	public void setScreenlet(BaseScreenlet screenlet) {

	}
}
