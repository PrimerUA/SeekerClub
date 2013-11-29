package com.primerworldapps.seeker.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;
import com.primerworldapps.seeker.MeetingStepsHolderScreen;
import com.primerworldapps.seeker.R;
import com.primerworldapps.seeker.entity.SeekerUser;

public class MenuClubFragment extends SherlockFragment implements OnClickListener {

	private View view;

	private LinearLayout newMeetingLayout;

	private TextView contactsText;
	private TextView meetingsText;
	private TextView ratingsText;
	private TextView coinsText;

	private SeekerUser seekerUser;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.menu_club_fragment, container, false);

		seekerUser = SeekerUser.getInstance();
		initFragment();

		return view;
	}

	private void initFragment() {
		contactsText = (TextView) view.findViewById(R.id.menu_contactsNumber);
		meetingsText = (TextView) view.findViewById(R.id.menu_meetingsNumber);
		ratingsText = (TextView) view.findViewById(R.id.menu_ratingsNumber);
		coinsText = (TextView) view.findViewById(R.id.menu_coinsNumber);

		contactsText.setText(String.valueOf(seekerUser.getContacts()));
		meetingsText.setText(String.valueOf(seekerUser.getMeetings()));
		ratingsText.setText(String.valueOf(seekerUser.getRating()));
		coinsText.setText(String.valueOf(seekerUser.getCoins()));

		newMeetingLayout = (LinearLayout) view.findViewById(R.id.menuItem_newMeetingLayout);
		newMeetingLayout.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.menuItem_newMeetingLayout: {
			startActivityForResult(new Intent(getActivity(), MeetingStepsHolderScreen.class), 0);
			break;
		}
		}
	}
	
}
