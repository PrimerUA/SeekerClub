package com.primerworldapps.seeker.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Spinner;

import com.actionbarsherlock.app.SherlockFragment;
import com.primerworldapps.seeker.R;
import com.primerworldapps.seeker.entity.SeekerUser;
import com.primerworldapps.seeker.util.PreferencesController;

public class CreateAccountFragment extends SherlockFragment {

	private View view;
	
	private Button enterButton;
	private Spinner ageSpinner;
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
			public void onClick(View v) {
				SeekerUser.getInstance().setAge(ageSpinner.getSelectedItemPosition());
				PreferencesController.getInstance().savePreferences();
				//отправка данных о пользователе на сервер
				getActivity().finish();
			}
		});
		
		checkBox = (CheckBox) view.findViewById(R.id.createAccount_checkBox);
		checkBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				enterButton.setEnabled(isChecked);
			}
		});
		
		ageSpinner = (Spinner) view.findViewById(R.id.createAccount_ageSpinner);
	}
}
