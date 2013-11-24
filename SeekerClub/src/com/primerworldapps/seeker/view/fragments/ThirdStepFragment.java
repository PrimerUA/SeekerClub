package com.primerworldapps.seeker.view.fragments;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.RadioButton;

import com.actionbarsherlock.app.SherlockFragment;
<<<<<<< HEAD:SeekerClub/src/com/primerworldapps/seeker/fragments/ThirdStepFragment.java
import com.primerworldapps.seeker.ApplicationSummaryScreen;
import com.primerworldapps.seeker.R;
import com.primerworldapps.seeker.entity.SeekerApplication;
=======
import com.primerworldapps.seeker.entity.SeekerApplication;
import com.primerworldapps.seeker.entity.SeekerUser;
import com.primerworldapps.seeker.view.ApplicationSummaryScreen;
import com.primerworldapps.seeker.view.MeetingStepsHolderScreen;
import com.primerworldapps.seeker.R;
import com.primerworldapps.seeker.view.SeekerHolderScreen;
import com.primerworldapps.seeker.view.WelcomeScreen;
>>>>>>> 3fc692dc5ac95afeb8716dcfb98b13414a588ace:SeekerClub/src/com/primerworldapps/seeker/view/fragments/ThirdStepFragment.java

public class ThirdStepFragment extends SherlockFragment implements TextWatcher {

	private RadioButton emailRadioButton;
	private RadioButton phoneRadioButton;
	private RadioButton socialRadioButton;

	private EditText emailEdit;
	private EditText phoneEdit;
	private EditText socialEdit;

	private View view;
	private String contact;

	private Typeface typeface;

	private Button previewButton;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.third_step_fragment, container, false);

		initFragment();

		return view;
	}

	private void initFragment() {

		previewButton = (Button) view.findViewById(R.id.thirdStep_nextButton);
		previewButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				SeekerApplication.getInstance().setContact(contact);
				Intent seekerHolderIntent = new Intent(getActivity(), ApplicationSummaryScreen.class);
				startActivity(seekerHolderIntent);
			}
		});
		previewButton.setEnabled(false);

		emailRadioButton = (RadioButton) view.findViewById(R.id.radio_email);
		phoneRadioButton = (RadioButton) view.findViewById(R.id.radio_phone);
		socialRadioButton = (RadioButton) view.findViewById(R.id.radio_social);

		emailEdit = (EditText) view.findViewById(R.id.edit_email);
		phoneEdit = (EditText) view.findViewById(R.id.edit_phone);
		socialEdit = (EditText) view.findViewById(R.id.edit_social);

		typeface = socialRadioButton.getTypeface();

		emailEdit.setEnabled(false);
		phoneEdit.setEnabled(true);
		socialEdit.setEnabled(false);

		emailEdit.addTextChangedListener(this);
		phoneEdit.addTextChangedListener(this);
		socialEdit.addTextChangedListener(this);

		phoneRadioButton.setChecked(true);

		emailRadioButton.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					configureScreen();
					emailEdit.setEnabled(true);
					emailRadioButton.setTextColor(getResources().getColor(R.color.selected_text));
					emailRadioButton.setTypeface(null, Typeface.BOLD);
					emailRadioButton.setChecked(true);
				}
			}
		});

		phoneRadioButton.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					configureScreen();
					phoneEdit.setEnabled(true);
					phoneRadioButton.setTextColor(getResources().getColor(R.color.selected_text));
					phoneRadioButton.setTypeface(null, Typeface.BOLD);
					phoneRadioButton.setChecked(true);
				}
			}
		});

		socialRadioButton.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					configureScreen();
					socialEdit.setEnabled(true);
					socialRadioButton.setTextColor(getResources().getColor(R.color.selected_text));
					socialRadioButton.setTypeface(null, Typeface.BOLD);
					socialRadioButton.setChecked(true);
				}
			}
		});

	}

	protected void configureScreen() {
		emailRadioButton.setTypeface(typeface, Typeface.NORMAL);
		phoneRadioButton.setTypeface(typeface, Typeface.NORMAL);
		socialRadioButton.setTypeface(typeface, Typeface.NORMAL);

		emailRadioButton.setTextColor(getResources().getColor(android.R.color.white));
		phoneRadioButton.setTextColor(getResources().getColor(android.R.color.white));
		socialRadioButton.setTextColor(getResources().getColor(android.R.color.white));

		emailRadioButton.setChecked(false);
		phoneRadioButton.setChecked(false);
		socialRadioButton.setChecked(false);

		emailEdit.setEnabled(false);
		phoneEdit.setEnabled(false);
		socialEdit.setEnabled(false);
	}

	@Override
	public void afterTextChanged(Editable s) {
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count, int after) {

	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		if (!emailEdit.getText().toString().equals("")) {
			contact = emailEdit.getText().toString();
			previewButton.setEnabled(true);
		} else if (!phoneEdit.getText().toString().equals("")) {
			contact = phoneEdit.getText().toString();
			previewButton.setEnabled(true);
		} else if (!socialEdit.getText().toString().equals("")) {
			contact = socialEdit.getText().toString();
			previewButton.setEnabled(true);
		} else {
			previewButton.setEnabled(false);
		}
	}

}
