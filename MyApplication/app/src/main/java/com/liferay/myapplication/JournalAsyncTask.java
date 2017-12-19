package com.liferay.myapplication;

import android.os.AsyncTask;
import android.widget.TextView;
import com.liferay.mobile.android.auth.basic.BasicAuthentication;
import com.liferay.mobile.android.service.Session;
import com.liferay.mobile.android.service.SessionImpl;
import com.liferay.mobile.android.v7.journalarticle.JournalArticleService;
import java.lang.ref.WeakReference;
import org.json.JSONObject;

class JournalAsyncTask extends AsyncTask<Void, Void, String> {

	private final WeakReference weakReference;

	public JournalAsyncTask(WeakReference weakReference) {
		this.weakReference = weakReference;
	}

	@Override
	protected String doInBackground(Void... voids) {
		Session session = new SessionImpl("http://10.0.3.2:8080", new BasicAuthentication("test@liferay.com", "test"));

		JournalArticleService journalArticleService = new JournalArticleService(session);
		try {
			JSONObject article = journalArticleService.getArticle(30903);

			return article.getString("content");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected void onPostExecute(String s) {
		super.onPostExecute(s);

		if (weakReference.get() != null) {
			((TextView) weakReference.get()).setText(s);
		}
	}
}
