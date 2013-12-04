package com.primerworldapps.seeker.util;

import android.app.Activity;
import android.content.Context;
import android.content.IntentSender;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.plus.PlusClient;
import com.primerworldapps.seeker.NewAccountHolderScreen;
import com.primerworldapps.seeker.R;
import com.primerworldapps.seeker.entity.SeekerUser;

public class PlusClientAuthenticator implements GooglePlayServicesClient.ConnectionCallbacks,
		GooglePlayServicesClient.OnConnectionFailedListener {

	public static final int REQUEST_CODE_RESOLVE_ERR = 9000;

	private static PlusClientAuthenticator instance;
	private PlusClient plusClient;
	private SeekerUser seekerUser;

	private static Context context;

	public static PlusClientAuthenticator getInstance() {
		if (instance == null) {
			instance = new PlusClientAuthenticator();
		}
		return instance;
	}

	public PlusClient init(Context context) {
		PlusClientAuthenticator.context = context;
		seekerUser = SeekerUser.getInstance();
		plusClient = new PlusClient.Builder(context, this, this).setScopes(Scopes.PLUS_LOGIN)
				.setVisibleActivities("http://schemas.google.com/AddActivity", "http://schemas.google.com/BuyActivity")
				.build();
		return plusClient;
	}

	@Override
	public void onConnectionFailed(ConnectionResult result) {
		if (result.hasResolution()) {
			try {
				result.startResolutionForResult((Activity) context, REQUEST_CODE_RESOLVE_ERR);
			} catch (IntentSender.SendIntentException e) {
				plusClient.connect();
			}
		} else {
			Toast.makeText(context, "Connection failed. Error code: " + result.getErrorCode(), Toast.LENGTH_SHORT)
					.show();
		}
	}

	@Override
	public void onConnected(Bundle arg0) {
		Toast.makeText(context, context.getString(R.string.google_connected), Toast.LENGTH_SHORT).show();

		if (!seekerUser.isLoggedIn()) {
			SeekerUser.getInstance().setLoggedIn(true).setEmail(plusClient.getAccountName())
					.setGoogleId(plusClient.getCurrentPerson().getId())
					.setName(plusClient.getCurrentPerson().getDisplayName())
					.setMale(plusClient.getCurrentPerson().getGender() == 0 ? true : false);
			PreferencesController.getInstance().savePreferences();
			
			//отправка данных о пользователе на сервер
			// new UserExecutor().checkUser(seekerUser.getName(),
			// seekerUser.getEmail());
		}
	}

	@Override
	public void onDisconnected() {
		Toast.makeText(context, context.getString(R.string.google_disconnected), Toast.LENGTH_SHORT).show();
		seekerUser.clear();
		PreferencesController.getInstance().savePreferences();
	}

	public PlusClient getPlusClient() {
		return plusClient;
	}

}
