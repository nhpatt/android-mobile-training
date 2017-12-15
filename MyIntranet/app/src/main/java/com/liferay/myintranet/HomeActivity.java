package com.liferay.myintranet;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.liferay.myintranet.adapter.Role;
import com.liferay.myintranet.adapter.RoleAdapter;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		TextView greetingView = findViewById(R.id.email);
		greetingView.setText(getIntent().getStringExtra("email"));

		Button takePictureView = findViewById(R.id.take_picture);
		takePictureView.setOnClickListener(this);

		Role[] roles = { new Role("admin"), new Role("power user") };
		ListView rolesView = findViewById(R.id.roles);
		rolesView.setAdapter(new RoleAdapter(this, roles));
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		startActivityForResult(intent, 1);
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		if (requestCode == 1) {
			if (resultCode == Activity.RESULT_OK) {
				Bundle extras = data.getExtras();
				Bitmap imageBitmap = (Bitmap) extras.get("data");

				ImageView imageView = findViewById(R.id.profile);
				imageView.setImageBitmap(imageBitmap);
			}
		}
	}
}
