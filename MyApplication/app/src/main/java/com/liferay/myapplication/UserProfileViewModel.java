package com.liferay.myapplication;

import com.liferay.mobile.screens.base.view.BaseViewModel;
import com.liferay.mobile.screens.context.User;

public interface UserProfileViewModel extends BaseViewModel {
	void showStartOperation(String defaultAction, User user);
}
