package com.primerworldapps.seeker.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

import com.actionbarsherlock.app.SherlockFragment;
import com.primerworldapps.seeker.R;
import com.primerworldapps.seeker.util.ConnectionTask;

public class CreateAccountFragment extends SherlockFragment {

	private View view;

	private Button enterButton;
	private CheckBox checkBox;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.create_account_fragment, container, false);

		initFragment();

		return view;
	}

	private void initFragment() {
		enterButton = (Button) view.findViewById(R.id.createAccount_loginButton);
		enterButton.setEnabled(false);
		enterButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(final View v) {
				new ConnectionTask(getActivity()).execute();
			}
		});

		checkBox = (CheckBox) view.findViewById(R.id.createAccount_checkBox);
		checkBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				enterButton.setEnabled(isChecked);
			}
		});
	}
}
