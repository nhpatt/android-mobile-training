package com.liferay.myintranet.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.liferay.myintranet.R;
import java.util.List;

public class RoleAdapter extends RecyclerView.Adapter<RoleAdapter.ViewHolder> {

	private final List<Role> roles;
	private final View.OnClickListener onClickListener;

	public RoleAdapter(List<Role> roles, View.OnClickListener onClickListener) {
		this.roles = roles;
		this.onClickListener = onClickListener;
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.role_edit, parent, false));
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {
		holder.paint(roles.get(position));
	}

	@Override
	public int getItemCount() {
		return roles.size();
	}

	class ViewHolder extends RecyclerView.ViewHolder {

		private final TextView nameView;
		private final ImageView editView;

		ViewHolder(View itemView) {
			super(itemView);

			nameView = itemView.findViewById(R.id.name);
			editView = itemView.findViewById(R.id.edit);
			editView.setOnClickListener(onClickListener);
		}

		void paint(Role role) {
			nameView.setText(role.getName());
		}
	}
}
