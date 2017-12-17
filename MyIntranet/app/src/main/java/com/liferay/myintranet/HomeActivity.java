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
import com.liferay.mobile.android.auth.basic.BasicAuthentication;
import com.liferay.mobile.android.callback.file.FileProgressCallback;
import com.liferay.mobile.android.http.file.UploadData;
import com.liferay.mobile.android.service.Session;
import com.liferay.mobile.android.service.SessionImpl;
import com.liferay.mobile.android.v7.dlapp.DLAppService;
import com.liferay.myintranet.adapter.Role;
import com.liferay.myintranet.adapter.RoleAdapter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
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
				final Bitmap imageBitmap = (Bitmap) extras.get("data");

				ImageView imageView = findViewById(R.id.profile);
				imageView.setImageBitmap(imageBitmap);

				new Thread(new Runnable() {
					@Override
					public void run() {
						Session session = new SessionImpl("http://10.0.3.2:8080",
							new BasicAuthentication("test@liferay.com", "test"));

						try {
							DLAppService dlAppService = new DLAppService(session);

							ByteArrayOutputStream bos = new ByteArrayOutputStream();
							imageBitmap.compress(Bitmap.CompressFormat.JPEG, 70, bos);
							byte[] bitmapdata = bos.toByteArray();
							ByteArrayInputStream bs = new ByteArrayInputStream(bitmapdata);

							String fileName = "name1";
							UploadData uploadData = new UploadData(bs, fileName, new FileProgressCallback() {
								@Override
								public void onProgress(int totalBytes) {
									System.out.println(totalBytes);
								}
							});
							long repositoryId = 20143L;
							long folderId = 42599L;
							dlAppService.addFileEntry(repositoryId, folderId, fileName, "image/jpeg", fileName,
								"description", "changeLog", uploadData, null);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}).start();
			}
		}
	}
}
