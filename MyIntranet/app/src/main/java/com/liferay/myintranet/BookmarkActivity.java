package com.liferay.myintranet;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.liferay.mobile.android.auth.basic.BasicAuthentication;
import com.liferay.mobile.android.callback.typed.JSONArrayCallback;
import com.liferay.mobile.android.service.JSONObjectWrapper;
import com.liferay.mobile.android.service.Session;
import com.liferay.mobile.android.service.SessionImpl;
import com.liferay.mobile.android.v7.bookmarksentry.BookmarksEntryService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BookmarkActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bookmark);

		Session session = new SessionImpl("http://10.0.3.2:8080", new BasicAuthentication("test@liferay.com", "test"));

		session.setCallback(new JSONArrayCallback() {
			@Override
			public void onFailure(Exception exception) {
				System.err.println(exception.getMessage());
			}

			@Override
			public void onSuccess(JSONArray result) {
				try {
					for (int i = 0; i < result.length(); i++) {
						System.out.println(result.getJSONObject(i).getString("name"));
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});
		BookmarksEntryService bookmarksEntryService = new BookmarksEntryService(session);

		String className = "com.liferay.bookmarks.util.comparator.EntryNameComparator";

		JSONObjectWrapper orderByComparator = new JSONObjectWrapper(className, new JSONObject());

		try {
			bookmarksEntryService.getEntries(20143, 0, 0, 10, orderByComparator);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
