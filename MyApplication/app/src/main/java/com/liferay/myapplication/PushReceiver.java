package com.liferay.myapplication;

import android.support.annotation.NonNull;
import com.liferay.mobile.screens.push.AbstractPushReceiver;

public class PushReceiver extends AbstractPushReceiver<PushService> {
	@NonNull
	@Override
	protected Class<PushService> getPushServiceClass() {
		return PushService.class;
	}
}
