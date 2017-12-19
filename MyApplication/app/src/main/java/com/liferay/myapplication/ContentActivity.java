package com.liferay.myapplication;

import android.os.Bundle;
import android.util.Log;
import com.liferay.mobile.android.service.Session;
import com.liferay.mobile.screens.context.SessionContext;
import com.liferay.mobile.screens.push.PushScreensActivity;
import com.liferay.mobile.screens.webcontent.display.WebContentDisplayScreenlet;
import org.json.JSONObject;

public class ContentActivity extends PushScreensActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_content);

		WebContentDisplayScreenlet screenlet = (WebContentDisplayScreenlet) findViewById(R.id.web_content_display);
		screenlet.setArticleId("30902");
		screenlet.load();

		UserProfileScreenlet viewById = (UserProfileScreenlet) findViewById(R.id.my_user_profile);
		viewById.performUserAction();
	}

	@Override
	protected Session getDefaultSession() {
		return SessionContext.createSessionFromCurrentSession();
	}

	@Override
	protected void onPushNotificationReceived(JSONObject jsonObject) {
		Log.e("ajksdjkasdf", jsonObject.toString());
	}

	@Override
	protected void onErrorRegisteringPush(String message, Exception e) {
		Log.e("ajksdjkasdf", e.getMessage());
	}

	@Override
	protected String getSenderId() {
		return "176310287574";
	}
}
