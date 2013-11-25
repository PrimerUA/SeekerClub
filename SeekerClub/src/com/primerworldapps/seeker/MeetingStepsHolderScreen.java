package com.primerworldapps.seeker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentManager.OnBackStackChangedListener;
import android.support.v4.app.FragmentTransaction;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;
import com.primerworldapps.seeker.fragments.ApplicationSummaryFragment;
import com.primerworldapps.seeker.fragments.FirstStepFragment;
import com.primerworldapps.seeker.fragments.SecondStepFragment;
import com.primerworldapps.seeker.fragments.ThirdStepFragment;

public class MeetingStepsHolderScreen extends SherlockFragmentActivity {

	private final int STEPS = 4;
	private Fragment[] fragments = new Fragment[STEPS];

	private int currentFragment;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.meeting_holder_screen);

		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		FragmentManager fm = getSupportFragmentManager();
		FirstStepFragment startFragment = (FirstStepFragment) fm.findFragmentById(R.id.firstFragment);
		fragments[0] = startFragment;
		fragments[1] = (SecondStepFragment) fm.findFragmentById(R.id.secondFragment);
		fragments[2] = (ThirdStepFragment) fm.findFragmentById(R.id.thirdFragment);
		fragments[3] = (ApplicationSummaryFragment) fm.findFragmentById(R.id.summaryFragment);

		FragmentTransaction transaction = fm.beginTransaction();
		for (int i = 0; i < fragments.length; i++) {
			transaction.hide(fragments[i]);
		}
		transaction.commit();
		showFragment(currentFragment = 0, true);

		fm.addOnBackStackChangedListener(new OnBackStackChangedListener() {

			@Override
			public void onBackStackChanged() {
				if (getSupportFragmentManager().getBackStackEntryCount() == 0)
					finish();
			}
		});
	}

	public void showFragment(int fragmentIndex, boolean addToBackStack) {
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction transaction = fm.beginTransaction();
		for (int i = 0; i < fragments.length; i++) {
			if (i == fragmentIndex) {
				transaction.show(fragments[i]);
			} else {
				transaction.hide(fragments[i]);
			}
		}

		if (addToBackStack) {
			transaction.addToBackStack(null);
		}
		if (fragmentIndex == 0) {
			getSupportActionBar().setTitle(getString(R.string.step_1));
		} else if (fragmentIndex == 1) {
			getSupportActionBar().setTitle(getString(R.string.step_2));
		} else if (fragmentIndex == 2) {
			getSupportActionBar().setTitle(getString(R.string.step_3));
		} else {
			((ApplicationSummaryFragment) fragments[fragmentIndex]).relaunchFragment();
			getSupportActionBar().setTitle(getString(R.string.application_summary_check));
		}
		currentFragment = fragmentIndex;
		transaction.commit();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		default: {
			backButton();
		}
		}
		return true;
	}

	private void backButton() {
		if (currentFragment != 0) {
			currentFragment--;
			showFragment(currentFragment, false);
		} else {
			finish();
		}
	}

	@Override
	public void onBackPressed() {
		backButton();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (resultCode) {
		case 0:
			setResult(0);
			finish();
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

}
