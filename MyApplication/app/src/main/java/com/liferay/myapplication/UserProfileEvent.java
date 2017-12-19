package com.liferay.myapplication;

import com.liferay.mobile.screens.base.interactor.event.CacheEvent;
import org.json.JSONObject;

class UserProfileEvent extends CacheEvent {

	public UserProfileEvent() {
		super();
	}

	public UserProfileEvent(JSONObject userById) {
		super(userById);
	}
}
