package com.liferay.myintranet.userprofile;

import com.liferay.mobile.android.v7.user.UserService;
import com.liferay.mobile.screens.base.BaseScreenlet;
import com.liferay.mobile.screens.base.interactor.BaseCacheReadInteractor;
import com.liferay.mobile.screens.base.interactor.event.CacheEvent;
import com.liferay.mobile.screens.context.User;
import org.json.JSONObject;

public class UserProfileInteractor extends BaseCacheReadInteractor<UserProfileListener, CacheEvent> {

	@Override
	public CacheEvent execute(Object... args) throws Exception {

		UserService userService = new UserService(getSession());
		JSONObject jsonObject = userService.getUserById((Long) args[0]);
		return new UserProfileEvent(jsonObject);
	}

	@Override
	public void onSuccess(CacheEvent event) {
		getListener().onUserLoaded(new User(event.getJSONObject()));
	}

	@Override
	public void onFailure(CacheEvent event) {
		getListener().error(event.getException(), BaseScreenlet.DEFAULT_ACTION);
	}

	@Override
	protected String getIdFromArgs(Object... args) {
		return String.valueOf(args[0]);
	}

	public class UserProfileEvent extends CacheEvent {

		public UserProfileEvent() {
			super();
		}

		public UserProfileEvent(JSONObject jsonObject) {
			super(jsonObject);
		}
	}
}
