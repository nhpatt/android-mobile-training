package com.liferay.myintranet.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.liferay.myintranet.R;

public class RoleAdapter extends ArrayAdapter<Role> {

	public RoleAdapter(@NonNull Context context, Role[] roles) {
		super(context, R.layout.role_edit, roles);
	}

	@NonNull
	@Override
	public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

		if (convertView == null) {
			convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.role_edit, parent, false);
		}

		TextView roleView = convertView.findViewById(R.id.name);
		roleView.setText(getItem(position).getName());

		return convertView;
	}
}
