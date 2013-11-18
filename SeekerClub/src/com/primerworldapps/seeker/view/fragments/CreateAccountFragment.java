package com.primerworldapps.seeker.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.actionbarsherlock.app.SherlockFragment;
import com.primerworldapps.seeker.R;

public class CreateAccountFragment extends SherlockFragment {

	private View view;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.create_account_fragment, container, false);
		initFragment();
		Button nextButton = (Button) view.findViewById(R.id.createAccount_loginButton);
		nextButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				getActivity().finish();
			}
		});
		return view;
	}
	private void initFragment() {
		
	}
}
