package com.primerworldapps.seeker.fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;
import com.primerworldapps.seeker.MeetingStepsHolderScreen;
import com.primerworldapps.seeker.R;
import com.primerworldapps.seeker.entity.SeekerApplication;

public class FirstStepFragment extends SherlockFragment {

	private LinearLayout layoutTypeOne;
	private LinearLayout layoutTypeTwo;
	private LinearLayout layoutTypeThree;
	private LinearLayout layoutTypeFour;
	private LinearLayout layoutTypeFive;
	private LinearLayout layoutTypeSix;
	private LinearLayout layoutTypeSeven;

	private TextView textTypeOne;
	private TextView textTypeTwo;
	private TextView textTypeThree;
	private TextView textTypeFour;
	private TextView textTypeFive;
	private TextView textTypeSix;
	private TextView textTypeSeven;
	
	private CheckBox myTreatCheckBox;

	private View view;
	private Typeface typeface;

	private int type = 1;
	private boolean myTreat = false;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.first_step_fragment, container, false);
		initFragment();
		Button nextButton = (Button) view.findViewById(R.id.firstStep_nextButton);
		nextButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				SeekerApplication.getInstance().setType(type).setMyTreat(myTreat);
				MeetingStepsHolderScreen meetingStepsHolderScreen = (MeetingStepsHolderScreen) getActivity();
				meetingStepsHolderScreen.showFragment(1, true);
			}
		});
		return view;
	}

	private void initFragment() {
		getSherlockActivity().getSupportActionBar().setHomeButtonEnabled(false);
		getSherlockActivity().getSupportActionBar().setDisplayHomeAsUpEnabled(false);

		myTreatCheckBox = (CheckBox) view.findViewById(R.id.myTreat_checkBox);

		layoutTypeOne = (LinearLayout) view.findViewById(R.id.layout_type_1);
		layoutTypeTwo = (LinearLayout) view.findViewById(R.id.layout_type_2);
		layoutTypeThree = (LinearLayout) view.findViewById(R.id.layout_type_3);
		layoutTypeFour = (LinearLayout) view.findViewById(R.id.layout_type_4);
		layoutTypeFive = (LinearLayout) view.findViewById(R.id.layout_type_5);
		layoutTypeSix = (LinearLayout) view.findViewById(R.id.layout_type_6);
		layoutTypeSeven = (LinearLayout) view.findViewById(R.id.layout_type_7);

		textTypeOne = (TextView) view.findViewById(R.id.text_type_1);
		textTypeTwo = (TextView) view.findViewById(R.id.text_type_2);
		textTypeThree = (TextView) view.findViewById(R.id.text_type_3);
		textTypeFour = (TextView) view.findViewById(R.id.text_type_4);
		textTypeFive = (TextView) view.findViewById(R.id.text_type_5);
		textTypeSix = (TextView) view.findViewById(R.id.text_type_6);
		textTypeSeven = (TextView) view.findViewById(R.id.text_type_7);

		myTreatCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				myTreat = isChecked;
			}
		});

		typeface = textTypeSeven.getTypeface();
		textTypeOne.setText("[ " + textTypeOne.getText() + " ]");
		
		layoutTypeOne.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				configureScreen();
				textTypeOne.setTypeface(null, Typeface.BOLD);
				textTypeOne.setTextColor(getResources().getColor(R.color.selected_text));
				textTypeOne.setText("[ " + textTypeOne.getText() + " ]");
				type = 1;
			}
		});

		layoutTypeTwo.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				configureScreen();
				textTypeTwo.setTypeface(null, Typeface.BOLD);
				textTypeTwo.setTextColor(getResources().getColor(R.color.selected_text));
				textTypeTwo.setText("[ " + textTypeTwo.getText() + " ]");
				type = 2;
			}
		});

		layoutTypeThree.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				configureScreen();
				textTypeThree.setTypeface(null, Typeface.BOLD);
				textTypeThree.setTextColor(getResources().getColor(R.color.selected_text));
				textTypeThree.setText("[ " + textTypeThree.getText() + " ]");
				type = 3;
			}
		});

		layoutTypeFour.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				configureScreen();
				textTypeFour.setTypeface(null, Typeface.BOLD);
				textTypeFour.setTextColor(getResources().getColor(R.color.selected_text));
				textTypeFour.setText("[ " + textTypeFour.getText() + " ]");
				type = 4;
			}
		});

		layoutTypeFive.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				configureScreen();
				textTypeFive.setTypeface(null, Typeface.BOLD);
				textTypeFive.setTextColor(getResources().getColor(R.color.selected_text));
				textTypeFive.setText("[ " + textTypeFive.getText() + " ]");
				type = 5;
			}
		});

		layoutTypeSix.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				configureScreen();
				textTypeSix.setTypeface(null, Typeface.BOLD);
				textTypeSix.setTextColor(getResources().getColor(R.color.selected_text));
				textTypeSix.setText("[ " + textTypeSix.getText() + " ]");
				type = 6;
			}
		});

		layoutTypeSeven.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				configureScreen();
				textTypeSeven.setTypeface(null, Typeface.BOLD);
				textTypeSeven.setTextColor(getResources().getColor(R.color.selected_text));
				textTypeSeven.setText("[ " + textTypeSeven.getText() + " ]");
				type = 7;
			}
		});
	}

	protected void configureScreen() {
		textTypeOne.setText(getString(R.string.type_1));
		textTypeTwo.setText(getString(R.string.type_2));
		textTypeThree.setText(getString(R.string.type_3));
		textTypeFour.setText(getString(R.string.type_4));
		textTypeFive.setText(getString(R.string.type_5));
		textTypeSix.setText(getString(R.string.type_6));
		textTypeSeven.setText(getString(R.string.type_7));
		
		textTypeOne.setTypeface(typeface, Typeface.NORMAL);
		textTypeTwo.setTypeface(typeface, Typeface.NORMAL);
		textTypeThree.setTypeface(typeface, Typeface.NORMAL);
		textTypeFour.setTypeface(typeface, Typeface.NORMAL);
		textTypeFive.setTypeface(typeface, Typeface.NORMAL);
		textTypeSix.setTypeface(typeface, Typeface.NORMAL);
		textTypeSeven.setTypeface(typeface, Typeface.NORMAL);

		textTypeOne.setTextColor(getResources().getColor(android.R.color.white));
		textTypeTwo.setTextColor(getResources().getColor(android.R.color.white));
		textTypeThree.setTextColor(getResources().getColor(android.R.color.white));
		textTypeFour.setTextColor(getResources().getColor(android.R.color.white));
		textTypeFive.setTextColor(getResources().getColor(android.R.color.white));
		textTypeSix.setTextColor(getResources().getColor(android.R.color.white));
		textTypeSeven.setTextColor(getResources().getColor(android.R.color.white));
	}

}
