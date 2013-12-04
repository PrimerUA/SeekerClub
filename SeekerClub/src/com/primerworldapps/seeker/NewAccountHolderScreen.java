package com.primerworldapps.seeker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentManager.OnBackStackChangedListener;
import android.support.v4.app.FragmentTransaction;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;
import com.primerworldapps.seeker.fragments.CreateAccountFragment;
import com.primerworldapps.seeker.fragments.UserLoginFragment;

public class NewAccountHolderScreen extends SherlockFragmentActivity {

	private final int STEPS = 2;
	private Fragment[] fragments = new Fragment[STEPS];

	private int currentFragment;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_account_holder_screen);

		// getSupportActionBar().setHomeButtonEnabled(true);
		// getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		FragmentManager fm = getSupportFragmentManager();
		UserLoginFragment startFragment = (UserLoginFragment) fm.findFragmentById(R.id.loginFragment);
		fragments[0] = startFragment;
		fragments[1] = (CreateAccountFragment) fm.findFragmentById(R.id.creationFragment);
		// fragments[2] = (FractionSelectionFragment)
		// fm.findFragmentById(R.id.fractionFragment);

		FragmentTransaction transaction = fm.beginTransaction();
		for (int i = 0; i < fragments.length; i++) {
			transaction.hide(fragments[i]);
		}
		transaction.commit();
		showFragment(currentFragment = 0, false);

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
			getSupportActionBar().setTitle(getString(R.string.reg_step_1));
		} else if (fragmentIndex == 1) {
			getSupportActionBar().setTitle(getString(R.string.reg_step_2));
		} else {
			getSupportActionBar().setTitle(getString(R.string.reg_step_3));
		}
		currentFragment = fragmentIndex;
		transaction.commit();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		default: {
			// backButton();
		}
		}
		return true;
	}

	public void onBackPressed() {
		Intent backtoHome = new Intent(Intent.ACTION_MAIN);
		backtoHome.addCategory(Intent.CATEGORY_HOME);
		backtoHome.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(backtoHome);
	}

}
