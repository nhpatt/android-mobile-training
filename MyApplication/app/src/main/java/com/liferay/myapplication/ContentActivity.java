package com.liferay.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import com.liferay.mobile.push.Push;
import com.liferay.mobile.screens.context.SessionContext;
import com.liferay.mobile.screens.webcontent.display.WebContentDisplayScreenlet;
import org.json.JSONObject;

public class ContentActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_content);

		//try {
		//	Push.with(SessionContext.createSessionFromCurrentSession())
		//		.withPortalVersion(70)
		//		.register(this, "176310287574");
		//} catch (Exception e) {
		//	e.printStackTrace();
		//}

		Push.with(SessionContext.createSessionFromCurrentSession()).onPushNotification(
			new Push.OnPushNotification() {

				@Override
				public void onPushNotification(JSONObject jsonObject) {
					Log.e("ajksdjkasdf", jsonObject.toString());
				}
			}
		);


		WebContentDisplayScreenlet screenlet = (WebContentDisplayScreenlet) findViewById(R.id.web_content_display);
		screenlet.setArticleId("30902");
		screenlet.load();

		UserProfileScreenlet viewById = (UserProfileScreenlet) findViewById(R.id.my_user_profile);
		viewById.performUserAction();
	}
}
