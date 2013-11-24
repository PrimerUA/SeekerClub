package com.primerworldapps.seeker.view;

import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuItem;
import com.primerworldapps.seeker.R;
import com.primerworldapps.seeker.entity.SeekerApplication;
import com.primerworldapps.seeker.view.services.ScannerService;

public class ApplicationSummaryScreen extends SherlockActivity implements LocationListener {

	private static final long LOCATION_REFRESH_TIME = 60000;
	private static final float LOCATION_REFRESH_DISTANCE = 50;

	private LocationManager mLocationManager;

	private ProgressBar mProgressBar;
	private TextView summaryStatusText;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.application_summary_screen);

		getSupportActionBar().setTitle(R.string.application_summary_check);
		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		initScreen();

	}

	private void initScreen() {
		SeekerApplication seekerApplication = SeekerApplication.getInstance();

		TextView themeText = (TextView) findViewById(R.id.summaryThemeText);
		TextView treatText = (TextView) findViewById(R.id.summaryTreatText);
		TextView genderText = (TextView) findViewById(R.id.summaryGenderText);
		TextView ageText = (TextView) findViewById(R.id.summaryAgeText);
		TextView callbackText = (TextView) findViewById(R.id.summaryCallbackText);

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

		mProgressBar = (ProgressBar) findViewById(R.id.summary_progressBar);
		mProgressBar.setVisibility(View.GONE);

		summaryStatusText = (TextView) findViewById(R.id.summary_statusText);
		summaryStatusText.setVisibility(View.GONE);

		final Button postApplicationButton = (Button) findViewById(R.id.postApplication_Button);
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
		mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
		checkLocationProviders(mLocationManager);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		default: {
			finish();
		}
		}
		return true;
	}

	@Override
	public void onLocationChanged(Location location) {
		SeekerApplication.getInstance().setLatitude(location.getLatitude()).setLongitude(location.getLongitude());
		summaryStatusText.setText(getString(R.string.posting_to_server));
		mProgressBar.setVisibility(View.GONE);
		summaryStatusText.setVisibility(View.GONE);
		
		Toast.makeText(this, location.getLatitude() + ":" + location.getLongitude(), Toast.LENGTH_SHORT).show();
		//startActivity(new Intent(this, SeekerHolderScreen.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
		startActivityForResult(new Intent(this, SeekerHolderScreen.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP), 0);
	}

	@Override
	public void onProviderDisabled(String provider) {
	}

	@Override
	public void onProviderEnabled(String provider) {
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		checkLocationProviders(mLocationManager);
	}

	private void checkLocationProviders(LocationManager mLocationManager) {
		summaryStatusText.setText(getString(R.string.location_check));
		if (mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
			mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, LOCATION_REFRESH_TIME, LOCATION_REFRESH_DISTANCE, this);
		} else if (mLocationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
			mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, LOCATION_REFRESH_TIME, LOCATION_REFRESH_DISTANCE, this);
		} else {
			mProgressBar.setVisibility(View.GONE);
			summaryStatusText.setVisibility(View.GONE);
			Intent wirelessSettingsIntent = new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
			startActivity(wirelessSettingsIntent);
		}
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
