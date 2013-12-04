package com.primerworldapps.seeker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockActivity;
import com.primerworldapps.seeker.entity.SeekerUser;
import com.primerworldapps.seeker.util.PlusClientAuthenticator;

public class WelcomeScreen extends SherlockActivity {

	private Button startButton;
	private TextView userNameText;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome_screen);
		getSupportActionBar().hide();

		initScreen();

		if (!SeekerUser.getInstance().isLoggedIn()) {
			startActivity(new Intent(WelcomeScreen.this, NewAccountHolderScreen.class));
		}
		//ImageView testImage = (ImageView) findViewById(R.id.testImage);
		//new DownloadImageTask(this, testImage).execute("https://plus.google.com/s2/photos/profile/me");
	}

	private void initScreen() {
		startButton = (Button) findViewById(R.id.startButton);
		startButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});

		userNameText = (TextView) findViewById(R.id.userName_welcomeText);
		userNameText.setText(SeekerUser.getInstance().getName());
	}

	@Override
	public void onResume() {
		super.onResume();
		if (!SeekerUser.getInstance().isLoggedIn() && PlusClientAuthenticator.getInstance().getPlusClient() != null) {
			Toast.makeText(this, getString(R.string.google_auth_error), Toast.LENGTH_SHORT).show();
			startActivity(new Intent(this, NewAccountHolderScreen.class));
		}
		userNameText.setText(SeekerUser.getInstance().getName());
	}

}
