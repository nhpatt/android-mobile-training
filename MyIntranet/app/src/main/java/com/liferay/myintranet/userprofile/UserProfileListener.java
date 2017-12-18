package com.liferay.myintranet.userprofile;

import com.liferay.mobile.screens.context.User;

public interface UserProfileListener {

	void onUserLoaded(User user);

	void onError(Exception e);
}
