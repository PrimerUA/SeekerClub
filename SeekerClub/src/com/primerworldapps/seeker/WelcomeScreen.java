package com.primerworldapps.seeker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockActivity;

public class WelcomeScreen extends SherlockActivity {

	private Button startButton;
	private TextView userNameText;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome_screen);

		getSupportActionBar().hide();
		initScreen();
		//if user not logined show login screen
		startActivity(new Intent(WelcomeScreen.this, NewAccountHolderScreen.class));
		//else show this screen
	}

	private void initScreen() {
		startButton = (Button) findViewById(R.id.startButton);
		startButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(WelcomeScreen.this, MeetingStepsHolderScreen.class));
			}
		});
		
		userNameText = (TextView) findViewById(R.id.userName_welcomeText);
		userNameText.setText("Михаил");
	}
}
