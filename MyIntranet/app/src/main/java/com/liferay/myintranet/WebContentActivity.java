package com.liferay.myintranet;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import com.liferay.mobile.android.auth.basic.BasicAuthentication;
import com.liferay.mobile.android.service.Session;
import com.liferay.mobile.android.service.SessionImpl;
import com.liferay.mobile.android.v7.journalarticle.JournalArticleService;
import org.json.JSONException;
import org.json.JSONObject;

public class WebContentActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_web_content);
		Toolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		new WebContentAsyncTask().execute();
	}

	class WebContentAsyncTask extends AsyncTask<Void, Void, JSONObject> {

		@Override
		protected JSONObject doInBackground(Void... voids) {
			BasicAuthentication basicAuthentication = new BasicAuthentication("test@liferay.com", "test");
			Session session = new SessionImpl("http://10.0.3.2:8080", basicAuthentication);

			JournalArticleService journalArticleService = new JournalArticleService(session);

			try {
				return journalArticleService.getArticle(44492);
			} catch (Exception e) {
				return null;
			}
		}

		@Override
		protected void onPostExecute(JSONObject article) {
			super.onPostExecute(article);

			TextView content = findViewById(R.id.web_content);
			try {
				content.setText(article.getString("content"));
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
	}
}
