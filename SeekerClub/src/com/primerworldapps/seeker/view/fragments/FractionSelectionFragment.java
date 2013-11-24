package com.primerworldapps.seeker.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.actionbarsherlock.app.SherlockFragment;
import com.primerworldapps.seeker.view.NewAccountHolderScreen;
import com.primerworldapps.seeker.R;

public class FractionSelectionFragment extends SherlockFragment {

	private View view;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fraction_selection_fragment, container, false);
		initFragment();
//		Button nextButton = (Button) view.findViewById(R.id.newAccount_nextButton);
//		nextButton.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				NewAccountHolderScreen newAccountHolderScreen = (NewAccountHolderScreen) getActivity();
//				newAccountHolderScreen.showFragment(1, true);
//			}
//		});
		return view;
	}
	private void initFragment() {
		
	}

}
