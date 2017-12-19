package com.liferay.myapplication;

import com.liferay.mobile.screens.base.interactor.listener.BaseCacheListener;
import com.liferay.mobile.screens.context.User;

public interface UserProfileListener extends BaseCacheListener {

	void onUserProfileLoaded(User user);
}
