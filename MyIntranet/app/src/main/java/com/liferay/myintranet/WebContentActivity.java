package com.liferay.myintranet;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import com.liferay.mobile.android.auth.basic.BasicAuthentication;
import com.liferay.mobile.android.service.Session;
import com.liferay.mobile.android.service.SessionImpl;
import com.liferay.mobile.android.v7.journalarticle.JournalArticleService;
import org.json.JSONObject;

public class WebContentActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_web_content);
		Toolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					BasicAuthentication basicAuthentication = new BasicAuthentication("test@liferay.com", "test");
					Session session = new SessionImpl("http://10.0.3.2:8080", basicAuthentication);

					JournalArticleService journalArticleService = new JournalArticleService(session);

					JSONObject article = journalArticleService.getArticle(44492);
					System.out.println(article);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
}
