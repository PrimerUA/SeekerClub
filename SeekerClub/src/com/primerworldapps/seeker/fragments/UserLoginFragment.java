package com.primerworldapps.seeker.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Spinner;

import com.actionbarsherlock.app.SherlockFragment;
import com.google.android.gms.common.SignInButton;
import com.primerworldapps.seeker.NewAccountHolderScreen;
import com.primerworldapps.seeker.R;
import com.primerworldapps.seeker.entity.SeekerUser;
import com.primerworldapps.seeker.util.PlusClientAuthenticator;

public class UserLoginFragment extends SherlockFragment {

	private SignInButton loginButton;
	private Spinner ageSpinner;

	private View view;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		view = inflater.inflate(R.layout.user_login_fragment, container, false);

		initFragment();

		return view;
	}

	private void initFragment() {
		
		ageSpinner = (Spinner) view.findViewById(R.id.createAccount_ageSpinner);
		ageSpinner.setSelection(1);
		
		loginButton = (SignInButton) view.findViewById(R.id.login_button);
		loginButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				SeekerUser.getInstance().setAge(ageSpinner.getSelectedItemPosition());
				PlusClientAuthenticator.getInstance().init(getActivity()).connect();
				NewAccountHolderScreen newAccountHolderScreen = (NewAccountHolderScreen) getActivity();
				newAccountHolderScreen.showFragment(1, false);
			}
		});
	}

}
