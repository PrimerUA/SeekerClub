package com.primerworldapps.seeker.fragments;

import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.view.MenuItem;
import com.primerworldapps.seeker.R;
import com.primerworldapps.seeker.SeekerHolderScreen;
import com.primerworldapps.seeker.entity.SeekerApplication;

public class ApplicationSummaryFragment extends SherlockFragment implements LocationListener {

	private static final long LOCATION_REFRESH_TIME = 0;
	private static final float LOCATION_REFRESH_DISTANCE = 0;

	private LocationManager mLocationManager;

	private ProgressBar mProgressBar;
	private TextView summaryStatusText;

	private View view;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.application_summary_fragment, container, false);

		initFragment();

		return view;
	}

	private void initFragment() {
		SeekerApplication seekerApplication = SeekerApplication.getInstance();

		TextView themeText = (TextView) view.findViewById(R.id.summaryThemeText);
		TextView treatText = (TextView) view.findViewById(R.id.summaryTreatText);
		TextView genderText = (TextView) view.findViewById(R.id.summaryGenderText);
		TextView ageText = (TextView) view.findViewById(R.id.summaryAgeText);
		TextView callbackText = (TextView) view.findViewById(R.id.summaryCallbackText);

		switch (seekerApplication.getType()) {
		case 1: {
			themeText.setText(R.string.type_1);
			break;
		}
		case 2: {
			themeText.setText(R.string.type_2);
			break;
		}
		case 3: {
			themeText.setText(R.string.type_3);
			break;
		}
		case 4: {
			themeText.setText(R.string.type_4);
			break;
		}
		case 5: {
			themeText.setText(R.string.type_5);
			break;
		}
		case 6: {
			themeText.setText(R.string.type_6);
			break;
		}
		case 7: {
			themeText.setText(R.string.type_7);
			break;
		}
		}
		if (seekerApplication.isMyTreat()) {
			treatText.setText(R.string.treat_yes);
		} else {
			treatText.setText(R.string.treat_no);
		}
		if (seekerApplication.isMale()) {
			genderText.setText(R.string.male);
		} else {
			genderText.setText(R.string.female);
		}
		switch (seekerApplication.getAge()) {
		case 0: {
			ageText.setText(getString(R.string.age_1));
			break;
		}
		case 1: {
			ageText.setText(getString(R.string.age_2));
			break;
		}
		case 2: {
			ageText.setText(getString(R.string.age_3));
			break;
		}
		case 3: {
			ageText.setText(getString(R.string.age_4));
			break;
		}
		case 4: {
			ageText.setText(getString(R.string.age_5));
			break;
		}
		}

		callbackText.setText(seekerApplication.getContact());

		mProgressBar = (ProgressBar) view.findViewById(R.id.summary_progressBar);
		mProgressBar.setVisibility(View.GONE);

		summaryStatusText = (TextView) view.findViewById(R.id.summary_statusText);
		summaryStatusText.setVisibility(View.GONE);

		final Button postApplicationButton = (Button) view.findViewById(R.id.postApplication_Button);
		postApplicationButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				postApplicationButton.setEnabled(false);
				mProgressBar.setVisibility(View.VISIBLE);
				summaryStatusText.setVisibility(View.VISIBLE);
				summaryStatusText.setText(getString(R.string.connection_check));
				locationSearchTask();
			}

		});
	}

	protected void locationSearchTask() {
		mLocationManager = (LocationManager) getActivity().getSystemService(Service.LOCATION_SERVICE);
		Location location = mLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
		if (location == null) {
			location = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		}
		if (location == null) {
			checkLocationProviders(mLocationManager);
		} else {
			showNextActivity(location);
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		default: {
			getActivity().finish();
		}
		}
		return true;
	}

	@Override
	public void onLocationChanged(Location location) {
		showNextActivity(location);
	}

	@Override
	public void onProviderDisabled(String provider) {
		Toast.makeText(getActivity(), "Provider disbled", Toast.LENGTH_SHORT)
		.show();
	}

	@Override
	public void onProviderEnabled(String provider) {
		Toast.makeText(getActivity(), "Provider enabled", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		checkLocationProviders(mLocationManager);
	}

	private void checkLocationProviders(LocationManager mLocationManager) {
		summaryStatusText.setText(getString(R.string.location_check));
		if (mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
			mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, LOCATION_REFRESH_TIME,
					LOCATION_REFRESH_DISTANCE, this);
		} else if (mLocationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
			mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, LOCATION_REFRESH_TIME,
					LOCATION_REFRESH_DISTANCE, this);
		} else {
			mProgressBar.setVisibility(View.GONE);
			summaryStatusText.setVisibility(View.GONE);
			Intent wirelessSettingsIntent = new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
			startActivity(wirelessSettingsIntent);
		}
	}

	@Override
	public void onResume() {
		super.onResume();

		initFragment();
	}

	private void showNextActivity(Location location) {
		mLocationManager.removeUpdates(this);
		
		SeekerApplication.getInstance().setLatitude(location.getLatitude()).setLongitude(location.getLongitude());
		
		summaryStatusText.setText(getString(R.string.posting_to_server));
		mProgressBar.setVisibility(View.GONE);
		summaryStatusText.setVisibility(View.GONE);

		Toast.makeText(getActivity(), location.getLatitude() + ":" + location.getLongitude(), Toast.LENGTH_SHORT)
				.show();
		startActivityForResult(
				new Intent(getActivity(), SeekerHolderScreen.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP), 0);
	}
}
