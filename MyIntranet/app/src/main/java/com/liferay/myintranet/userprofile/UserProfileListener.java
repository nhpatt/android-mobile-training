package com.liferay.myintranet.userprofile;

import com.liferay.mobile.screens.base.interactor.listener.BaseCacheListener;
import com.liferay.mobile.screens.context.User;

public interface UserProfileListener extends BaseCacheListener {

	void onUserLoaded(User user);
}
