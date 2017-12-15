package com.liferay.myintranet;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		TextView greetingView = findViewById(R.id.email);
		greetingView.setText(getIntent().getStringExtra("email"));

		Button takePictureView = findViewById(R.id.take_picture);
		takePictureView.setOnClickListener(this);

		String[] roles = { "admin", "power user" };
		ListView rolesView = findViewById(R.id.roles);
		rolesView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, roles));
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
