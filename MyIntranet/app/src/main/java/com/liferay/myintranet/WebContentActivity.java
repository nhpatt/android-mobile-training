package com.liferay.myintranet;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import com.liferay.mobile.android.auth.basic.BasicAuthentication;
import com.liferay.mobile.android.callback.BatchCallback;
import com.liferay.mobile.android.callback.typed.JSONArrayCallback;
import com.liferay.mobile.android.service.BatchSessionImpl;
import com.liferay.mobile.android.service.Session;
import com.liferay.mobile.android.v7.journalarticle.JournalArticleService;
import org.json.JSONArray;
import org.json.JSONException;

public class WebContentActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_web_content);
		Toolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		BasicAuthentication basicAuthentication = new BasicAuthentication("test@liferay.com", "test");
		BatchSessionImpl session = new BatchSessionImpl("http://10.0.3.2:8080", basicAuthentication);

		session.setCallback(new BatchCallback() {
			@Override
			public void onFailure(Exception exception) {

			}

			@Override
			public void onSuccess(JSONArray articles) {
				TextView content = findViewById(R.id.web_content);
				try {
					String content0 = articles.getJSONObject(0).getString("content");
					String content1 = articles.getJSONObject(1).getString("content");
					content.setText(content0 + content1);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});

		JournalArticleService journalArticleService = new JournalArticleService(session);

		try {
			journalArticleService.getArticle(44492);
			journalArticleService.getArticle(44500);
			session.invoke();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
