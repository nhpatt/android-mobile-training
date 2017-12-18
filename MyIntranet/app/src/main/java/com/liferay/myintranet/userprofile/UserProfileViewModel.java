package com.liferay.myintranet.userprofile;

import com.liferay.mobile.screens.base.view.BaseViewModel;
import com.liferay.mobile.screens.context.User;

public interface UserProfileViewModel extends BaseViewModel {
	void showFinishOperation(String userAction, User user);
}
