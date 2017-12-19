package com.liferay.myapplication;

import com.liferay.mobile.android.v7.user.UserService;
import com.liferay.mobile.screens.base.BaseScreenlet;
import com.liferay.mobile.screens.base.interactor.BaseCacheReadInteractor;
import com.liferay.mobile.screens.context.User;
import org.json.JSONObject;

public class UserProfileInteractor extends BaseCacheReadInteractor<UserProfileListener, UserProfileEvent> {

	@Override
	public UserProfileEvent execute(Object... args) throws Exception {

		UserService userService = new UserService(getSession());
		JSONObject userById = userService.getUserById((Integer) args[0]);

		return new UserProfileEvent(userById);
	}

	@Override
	public void onSuccess(UserProfileEvent event) {
		getListener().onUserProfileLoaded(new User(event.getJSONObject()));
	}

	@Override
	public void onFailure(UserProfileEvent event) {
		getListener().error(event.getException(), BaseScreenlet.DEFAULT_ACTION);
	}

	@Override
	protected String getIdFromArgs(Object... args) {
		return String.valueOf(args[0]);
	}
}
