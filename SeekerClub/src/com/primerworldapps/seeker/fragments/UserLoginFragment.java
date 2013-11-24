package com.primerworldapps.seeker.fragments;

import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.app.SherlockFragment;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks;
import com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.plus.PlusClient;
import com.primerworldapps.seeker.NewAccountHolderScreen;
import com.primerworldapps.seeker.R;
import com.primerworldapps.seeker.entity.SeekerUser;

/**
 * Activity which displays a login screen to the user, offering registration as
 * well.
 */
public class UserLoginFragment extends SherlockFragment implements ConnectionCallbacks, OnConnectionFailedListener {
	private static final int REQUEST_CODE_RESOLVE_ERR = 9000;

	private PlusClient mPlusClient;

	private SignInButton loginButton;

	private View view;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		view = inflater.inflate(R.layout.user_login_fragment, container, false);

		initFragment();

		mPlusClient = new PlusClient.Builder(getSherlockActivity(), this, this).setScopes(Scopes.PLUS_LOGIN).build();

		return view;
	}

	private void initFragment() {
		loginButton = (SignInButton) view.findViewById(R.id.login_button);
		loginButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!mPlusClient.isConnected()) {
					mPlusClient.connect();
				}
			}
		});
	}

	@Override
	public void onConnectionFailed(ConnectionResult result) {
		if (result.hasResolution()) {
			try {
				result.startResolutionForResult(getActivity(), REQUEST_CODE_RESOLVE_ERR);
			} catch (SendIntentException e) {
				mPlusClient.connect();
			}
		}
	}

	@Override
	public void onActivityResult(int requestCode, int responseCode, Intent intent) {
		if (requestCode == REQUEST_CODE_RESOLVE_ERR && responseCode == SherlockActivity.RESULT_OK) {
			mPlusClient.connect();
		}
	}

	@Override
	public void onStart() {
		super.onStart();
		// mPlusClient.connect();
	}

	@Override
	public void onStop() {
		super.onStop();
		mPlusClient.disconnect();
	}

	@Override
	public void onConnected(Bundle connectionHint) {
		SeekerUser.getInstance().setEmail(mPlusClient.getAccountName())
				.setName(mPlusClient.getCurrentPerson().getDisplayName())
				.setMale(mPlusClient.getCurrentPerson().getGender() == 0 ? true : false);
		Toast.makeText(getActivity(), mPlusClient.getCurrentPerson().getDisplayName() + " вход выполнен",
				Toast.LENGTH_SHORT).show();
		NewAccountHolderScreen newAccountHolderScreen = (NewAccountHolderScreen) getActivity();
		newAccountHolderScreen.showFragment(1, false);
	}

	@Override
	public void onDisconnected() {
		Toast.makeText(getActivity(), mPlusClient.getCurrentPerson().getDisplayName() + " - вышел",
				Toast.LENGTH_SHORT).show();
	}

}
