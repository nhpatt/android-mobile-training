package com.liferay.myintranet;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import com.liferay.mobile.android.callback.typed.JSONObjectCallback;
import com.liferay.mobile.android.service.Session;
import com.liferay.mobile.android.v7.journalarticle.JournalArticleService;
import com.liferay.mobile.screens.context.SessionContext;
import org.json.JSONException;
import org.json.JSONObject;

public class WebContentActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_web_content);
		Toolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
	}
}
