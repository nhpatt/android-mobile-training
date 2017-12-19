package com.liferay.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.liferay.mobile.android.auth.SignIn;
import com.liferay.mobile.android.auth.basic.BasicAuthentication;
import com.liferay.mobile.android.callback.typed.JSONObjectCallback;
import com.liferay.mobile.android.service.Session;
import com.liferay.mobile.android.service.SessionImpl;
import com.liferay.mobile.android.v7.journalarticle.JournalArticleService;
import com.liferay.mobile.push.Push;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

	private boolean valor;
	private TextView contentView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		//ButterKnife.injectViews(this);

		setContentView(R.layout.activity_main);

		contentView = (TextView) findViewById(R.id.content);

		Button signInView = (Button) findViewById(R.id.sign_in);
		signInView.setBackgroundColor(getResources().getColor(R.color.red));

		signInView.setOnClickListener(this);

		Session session = new SessionImpl("http://10.0.3.2:8080", new BasicAuthentication("test@liferay.com", "test"));

		CustomblogService customblogService = new CustomblogService(session);

		session.setCallback(new JSONObjectCallback() {
			@Override
			public void onFailure(Exception exception) {
			}

			@Override
			public void onSuccess(JSONObject result) {
				try {
					contentView.setText(result.getString("content"));
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});

		JournalArticleService journalArticleService = new JournalArticleService(session);
		try {
			journalArticleService.getArticle(30903);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onClick(View v) {

		valor = true;
		System.out.println(valor);

		EditText password = (EditText) findViewById(R.id.password);
		EditText login = (EditText) findViewById(R.id.login);

		BasicAuthentication authentication =
			new BasicAuthentication(login.getText().toString(), password.getText().toString());

		SignIn.signIn(new SessionImpl("http://10.0.3.2:8080", authentication), new JSONObjectCallback() {
			@Override
			public void onFailure(Exception exception) {
				int i = 0;
			}

			@Override
			public void onSuccess(JSONObject result) {
				Log.d("TAG", result.toString());
			}
		});

		if ("test".equals(password.getText().toString())) {
			Toast.makeText(this, "Login!", Toast.LENGTH_SHORT).show();

			Intent intent = new Intent(this, HomeActivity.class);

			intent.putExtra("user", login.getText().toString());
			startActivity(intent);
		}
	}
}
