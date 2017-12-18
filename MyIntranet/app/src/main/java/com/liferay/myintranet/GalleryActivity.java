package com.liferay.myintranet;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.liferay.myintranet.userprofile.UserProfileScreenlet;

public class GalleryActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gallery);

		UserProfileScreenlet userProfileScreenlet = findViewById(R.id.user_profile);
		userProfileScreenlet.performUserAction();
	}
}
