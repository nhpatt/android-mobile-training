package com.liferay.myapplication;

import android.util.Log;
import com.liferay.mobile.push.bus.BusUtil;
import com.liferay.mobile.screens.push.AbstractPushService;
import org.json.JSONException;
import org.json.JSONObject;

public class PushService extends AbstractPushService {
	@Override
	protected void processJSONNotification(JSONObject json) throws JSONException {
		Log.e("TAG", json.toString());
		BusUtil.post(json);
	}
}
