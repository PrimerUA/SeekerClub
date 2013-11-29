package com.primerworldapps.seeker.util;

import com.primerworldapps.seeker.entity.SeekerUser;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesController {
	private static PreferencesController instance = null;

	private SharedPreferences sharedPrefs;
	private SeekerUser seekerUser;

	protected PreferencesController() {
		// Exists only to defeat instantiation.
	}

	public static PreferencesController getInstance() {
		if (instance == null) {
			instance = new PreferencesController();
		}
		return instance;
	}

	public void init(Context context) {
		this.sharedPrefs = context.getSharedPreferences(Constants.SHARED_PREFERENCES, 0);
		this.seekerUser = SeekerUser.getInstance();

		loadPreferences();
	}

	public void loadPreferences() {
		seekerUser.setName(sharedPrefs.getString(Constants.USER_NAME, null));
		seekerUser.setEmail(sharedPrefs.getString(Constants.USER_EMAIL, null));
		seekerUser.setMale(sharedPrefs.getBoolean(Constants.USER_GENDER, false));
		seekerUser.setAge(sharedPrefs.getInt(Constants.USER_AGE, 0));
		seekerUser.setLoggedIn(sharedPrefs.getBoolean(Constants.USER_STATE, false));
	}

	public void savePreferences() {
		SharedPreferences.Editor editor = sharedPrefs.edit();
		editor.putString(Constants.USER_NAME, seekerUser.getName());
		editor.putString(Constants.USER_EMAIL, seekerUser.getEmail());
		editor.putBoolean(Constants.USER_GENDER, seekerUser.isMale());
		editor.putInt(Constants.USER_AGE, seekerUser.getAge());
		editor.putBoolean(Constants.USER_STATE, seekerUser.isLoggedIn());
		editor.commit();
	}

}
