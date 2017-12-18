package com.liferay.myintranet.userprofile;

import com.liferay.mobile.android.v7.user.UserService;
import com.liferay.mobile.screens.base.interactor.BaseRemoteInteractor;
import com.liferay.mobile.screens.base.interactor.event.BasicEvent;
import com.liferay.mobile.screens.context.User;
import org.json.JSONObject;

public class UserProfileInteractor extends BaseRemoteInteractor<UserProfileListener, BasicEvent> {

	@Override
	public BasicEvent execute(Object... args) throws Exception {

		UserService userService = new UserService(getSession());
		JSONObject jsonObject = userService.getUserById((Long) args[0]);
		return new BasicEvent(jsonObject);
	}

	@Override
	public void onSuccess(BasicEvent event) {
		getListener().onUserLoaded(new User(event.getJSONObject()));
	}

	@Override
	public void onFailure(BasicEvent event) {
		getListener().onError(event.getException());
	}
}
