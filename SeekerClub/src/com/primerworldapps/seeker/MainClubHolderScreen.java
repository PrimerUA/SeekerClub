package com.primerworldapps.seeker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentManager.OnBackStackChangedListener;
import android.support.v4.app.FragmentTransaction;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.plus.PlusClient;
import com.google.android.gms.plus.PlusClient.OnAccessRevokedListener;
import com.primerworldapps.seeker.entity.SeekerUser;
import com.primerworldapps.seeker.fragments.MenuClubFragment;
import com.primerworldapps.seeker.util.PlusClientAuthenticator;
import com.primerworldapps.seeker.util.PreferencesController;

public class MainClubHolderScreen extends SherlockFragmentActivity {

	private final int STEPS = 1;
	private Fragment[] fragments = new Fragment[STEPS];

	private int currentFragment;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_club_holder_screen);

		PreferencesController.getInstance().init(this);
		if (!SeekerUser.getInstance().isLoggedIn()) {
			startActivity(new Intent(this, WelcomeScreen.class));
		} else {
			PlusClientAuthenticator.getInstance().init(this).connect();
		}

		FragmentManager fm = getSupportFragmentManager();
		MenuClubFragment startFragment = (MenuClubFragment) fm.findFragmentById(R.id.menuFragment);
		fragments[0] = startFragment;
		// fragments[1] = (SecondStepFragment)
		// fm.findFragmentById(R.id.secondFragment);
		// fragments[2] = (ThirdStepFragment)
		// fm.findFragmentById(R.id.thirdFragment);
		// fragments[3] = (ApplicationSummaryFragment)
		// fm.findFragmentById(R.id.summaryFragment);

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
			getSupportActionBar().setTitle(getString(R.string.club_1));
		} else if (fragmentIndex == 1) {
			getSupportActionBar().setTitle(getString(R.string.club_2));
		} else if (fragmentIndex == 2) {
			getSupportActionBar().setTitle(getString(R.string.club_3));
		} else {
			getSupportActionBar().setTitle(getString(R.string.club_4));
		}
		currentFragment = fragmentIndex;
		transaction.commit();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_logout: {
			PlusClient mPlusClient = PlusClientAuthenticator.getInstance().getPlusClient();
			if (mPlusClient.isConnected()) {
				mPlusClient.clearDefaultAccount();

			    mPlusClient.revokeAccessAndDisconnect(new OnAccessRevokedListener() {
			       @Override
			       public void onAccessRevoked(ConnectionResult status) {
			    	   
			       }
			    });
				mPlusClient.disconnect();
				startActivity(new Intent(this, WelcomeScreen.class));
			}
			break;
		}
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
	public boolean onCreateOptionsMenu(Menu menu) {
		getSupportMenuInflater().inflate(R.menu.main_menu, menu);
		return true;
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
