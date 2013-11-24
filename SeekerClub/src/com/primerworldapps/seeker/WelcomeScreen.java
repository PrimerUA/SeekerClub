package com.primerworldapps.seeker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockActivity;
import com.primerworldapps.seeker.entity.SeekerUser;
import com.primerworldapps.seeker.util.PreferencesController;
import com.primerworldapps.seeker.R;

public class WelcomeScreen extends SherlockActivity {

	private Button startButton;
	private TextView userNameText;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome_screen);
		getSupportActionBar().hide();
		
		initScreen();
		
		PreferencesController.getInstance().init(this);
		if (!SeekerUser.getInstance().isLoggedIn()) {
			startActivity(new Intent(WelcomeScreen.this, NewAccountHolderScreen.class));
		}
		// else show this screen
	}

	private void initScreen() {
		startButton = (Button) findViewById(R.id.startButton);
		startButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivityForResult(new Intent(WelcomeScreen.this, MeetingStepsHolderScreen.class), 0);
			}
		});

		userNameText = (TextView) findViewById(R.id.userName_welcomeText);
		userNameText.setText(SeekerUser.getInstance().getName());
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
	
	@Override
	public void onResume() {
		super.onResume();
		userNameText.setText(SeekerUser.getInstance().getName());
	}
}
