package com.primerworldapps.seeker.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.actionbarsherlock.app.SherlockFragment;
import com.primerworldapps.entity.SeekerApplication;
import com.primerworldapps.seeker.MeetingStepsHolderScreen;
import com.primerworldapps.seeker.R;

public class SecondStepFragment extends SherlockFragment {

	private Spinner ageSpinner;
	private RadioButton genderMale;
	private RadioButton genderFemale;

	private View view;
	private int age;
	private boolean isMale = true;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.second_step_fragment, container, false);

		initFragment();
		Button nextButton = (Button) view.findViewById(R.id.secondStep_nextButton);
		nextButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				SeekerApplication.getInstance().setAge(age).setMale(isMale);
				MeetingStepsHolderScreen meetingStepsHolderScreen = (MeetingStepsHolderScreen) getActivity();
				meetingStepsHolderScreen.showFragment(2, true);
			}
		});

		return view;
	}

	private void initFragment() {
		ageSpinner = (Spinner) view.findViewById(R.id.age_spinner);
		genderMale = (RadioButton) view.findViewById(R.id.radio_male);
		genderFemale = (RadioButton) view.findViewById(R.id.radio_female);

		ageSpinner.setSelection(1);
		ageSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int selectedAge, long arg3) {
				switch (selectedAge) {
				case 0: {
					age = 0;
					break;
				}
				case 1: {
					age = 1;
					break;
				}
				case 2: {
					age = 2;
					break;
				}
				case 3: {
					age = 3;
					break;
				}
				case 4: {
					age = 4;
					break;
				}
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				age = 1;
			}
		});

		genderFemale.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					isMale = false;
					genderMale.setChecked(isMale);
				}
			}
		});

		genderMale.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					isMale = true;
					genderFemale.setChecked(!isMale);
				}
			}
		});
	}
	
}
