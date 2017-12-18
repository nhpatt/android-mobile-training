package com.liferay.myintranet.userprofile;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.liferay.mobile.screens.base.BaseScreenlet;
import com.liferay.mobile.screens.context.User;
import com.liferay.mobile.screens.userportrait.UserPortraitScreenlet;
import com.liferay.myintranet.R;

public class UserProfileView extends RelativeLayout implements UserProfileViewModel {

	private BaseScreenlet screenlet;
	private TextView userEmailView;
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

		userEmailView = findViewById(R.id.user_email);
		userPortraitView = findViewById(R.id.user_portrait);
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
		return screenlet;
	}

	@Override
	public void setScreenlet(BaseScreenlet screenlet) {
		this.screenlet = screenlet;
	}

	@Override
	public void showFinishOperation(String userAction, User user) {
		userEmailView.setText(user.getEmail());
		userPortraitView.setUserId(user.getId());
		userPortraitView.load();
	}
}
