package com.liferay.myintranet;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.liferay.myintranet.adapter.Role;
import com.liferay.myintranet.adapter.RoleAdapter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

	private List<Role> roles;
	private RoleAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		TextView greetingView = findViewById(R.id.email);
		greetingView.setText(getIntent().getStringExtra("email"));

		Button takePictureView = findViewById(R.id.take_picture);
		takePictureView.setOnClickListener(this);

		roles = new ArrayList<>(Arrays.asList(new Role("admin"), new Role("power user")));

		RecyclerView listView = findViewById(R.id.roles);
		listView.setLayoutManager(new LinearLayoutManager(this));
		adapter = new RoleAdapter(roles, this);
		listView.setAdapter(adapter);
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.take_picture) {
			Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			startActivityForResult(intent, 1);
		} else {
			Toast.makeText(this, "Try to edit", Toast.LENGTH_SHORT).show();
			roles.add(new Role("User"));
			adapter.notifyDataSetChanged();
		}
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
