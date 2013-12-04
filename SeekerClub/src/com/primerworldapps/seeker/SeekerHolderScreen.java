package com.primerworldapps.seeker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentManager.OnBackStackChangedListener;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.primerworldapps.seeker.entity.SeekerDetectedApplication;
import com.primerworldapps.seeker.fragments.ApplicationDetectedFragment;
import com.primerworldapps.seeker.fragments.ScanSeekerFragment;
import com.primerworldapps.seeker.services.ScannerService;

public class SeekerHolderScreen extends SherlockFragmentActivity {

	private final int STEPS = 2; // change
	private Fragment[] fragments = new Fragment[STEPS];

	private Intent scannerService;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		scannerService = new Intent(this, ScannerService.class);
		setContentView(R.layout.seeker_holder_screen);

		initScreen();
		showFragment(0, true);
	}

	public void initScreen() {

		FragmentManager fm = getSupportFragmentManager();
		ScanSeekerFragment startFragment = (ScanSeekerFragment) fm.findFragmentById(R.id.scanFragment);
		fragments[0] = startFragment;
		fragments[1] = (ApplicationDetectedFragment) fm.findFragmentById(R.id.detectedFragment);
		// fragments[2] = (FractionSelectionFragment)
		// fm.findFragmentById(R.id.fractionFragment);

		FragmentTransaction transaction = fm.beginTransaction();
		for (int i = 0; i < fragments.length; i++) {
			transaction.hide(fragments[i]);
		}
		transaction.commit();

		fm.addOnBackStackChangedListener(new OnBackStackChangedListener() {

			@Override
			public void onBackStackChanged() {
				if (getSupportFragmentManager().getBackStackEntryCount() == 0)
					finish();
			}
		});
	}

	public void startScannerService() {
		startService(scannerService);
	}

	public void stopScannerService() {
		stopService(scannerService);
	}

	public Fragment showFragment(int fragmentIndex, boolean addToBackStack) {
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
			getSherlock().setTitle(getString(R.string.scan_1));
		} else if (fragmentIndex == 1) {
			getSherlock().setTitle(getString(R.string.scan_2));
		} else {
			getSherlock().setTitle(getString(R.string.scan_3));
		}
		transaction.commitAllowingStateLoss();
		return fragments[fragmentIndex];
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
	    outState.putString("WORKAROUND_FOR_BUG_19917_KEY", "WORKAROUND_FOR_BUG_19917_VALUE");
	    super.onSaveInstanceState(outState);
	}

	@Override
	public void onBackPressed() {
		Intent backtoHome = new Intent(Intent.ACTION_MAIN);
		backtoHome.addCategory(Intent.CATEGORY_HOME);
		backtoHome.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(backtoHome);
	}

	@Override
	public boolean onKeyDown(int keycode, KeyEvent e) {
		switch (keycode) {
		case KeyEvent.KEYCODE_MENU: { // найдена подходящая заявка на сервере
			SeekerDetectedApplication.getInstance().setId(1).setName("Жанна Фриске").setType(3).setMyTreat(false)
					.setContact("+380631234567");
			((ScanSeekerFragment) fragments[0]).stopTimer();
			((ApplicationDetectedFragment) fragments[1]).initFragment();
			stopScannerService();
			showFragment(1, false);
		}
			return true;
		}

		return super.onKeyDown(keycode, e);
	}

}
