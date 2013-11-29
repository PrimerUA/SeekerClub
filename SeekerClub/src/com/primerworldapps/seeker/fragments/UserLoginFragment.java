package com.primerworldapps.seeker.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.actionbarsherlock.app.SherlockFragment;
import com.google.android.gms.common.SignInButton;
import com.primerworldapps.seeker.R;
import com.primerworldapps.seeker.util.PlusClientAuthenticator;

/**
 * Activity which displays a login screen to the user, offering registration as
 * well.
 */
public class UserLoginFragment extends SherlockFragment {

	private SignInButton loginButton;

	private View view;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		view = inflater.inflate(R.layout.user_login_fragment, container, false);

		initFragment();

		return view;
	}

	private void initFragment() {
		loginButton = (SignInButton) view.findViewById(R.id.login_button);
		loginButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				PlusClientAuthenticator.getInstance().init(getActivity()).connect();
			}
		});
	}

}
