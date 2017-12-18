package com.liferay.myintranet.userprofile;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import com.liferay.mobile.screens.base.BaseScreenlet;
import com.liferay.mobile.screens.cache.CachePolicy;
import com.liferay.mobile.screens.context.User;
import com.liferay.myintranet.R;

public class UserProfileScreenlet extends BaseScreenlet<UserProfileViewModel, UserProfileInteractor>
	implements UserProfileListener {

	public UserProfileScreenlet(Context context) {
		super(context);
	}

	public UserProfileScreenlet(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public UserProfileScreenlet(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public UserProfileScreenlet(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
	}

	@Override
	protected View createScreenletView(Context context, AttributeSet attributes) {

		TypedArray typedArray =
			context.getTheme().obtainStyledAttributes(attributes, R.styleable.UserProfileScreenlet, 0, 0);
		userId = typedArray.getInt(R.styleable.UserProfileScreenlet_userId, 0);
		int layoutId = typedArray.getResourceId(R.styleable.UserProfileScreenlet_layoutId, getDefaultLayoutId());
		typedArray.recycle();
		return LayoutInflater.from(context).inflate(layoutId, null);
	}

	@Override
	protected UserProfileInteractor createInteractor(String actionName) {
		return new UserProfileInteractor();
	}

	@Override
	protected void onUserAction(String userActionName, UserProfileInteractor interactor, Object... args) {
		setCachePolicy(CachePolicy.CACHE_FIRST);
		interactor.start(userId);
	}

	@Override
	public void onUserLoaded(User user) {
		getViewModel().showFinishOperation(BaseScreenlet.DEFAULT_ACTION, user);
	}

	@Override
	public void error(Exception e, String action) {
		getViewModel().showFailedOperation(action, e);
	}
}
