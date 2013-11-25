package com.primerworldapps.seeker.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;
import com.primerworldapps.seeker.R;
import com.primerworldapps.seeker.SeekerHolderScreen;
import com.primerworldapps.seeker.entity.SeekerDetectedApplication;

public class ApplicationDetectedFragment extends SherlockFragment {

	private View view;
	
	private TextView nameText;
	private TextView typeText;
	private TextView treatText;
	
	private TextView timerText;

	private long TWO_MINUTES = 120000;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.application_detected_fragment, container, false);
		initFragment();

		Button cancelButton = (Button) view.findViewById(R.id.application_noButton);
		cancelButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
				builder.setIcon(R.drawable.ic_launcher);
				builder.setTitle(R.string.cancel_title);
				builder.setMessage(R.string.cancel_text);
				builder.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int arg1) {
						dialog.dismiss();
					}
				});
				builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int arg1) {
						dialog.dismiss();
					}
				});
				builder.show();
			}
		});

		Button agreeButton = (Button) view.findViewById(R.id.application_yesButton);
		agreeButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
				builder.setIcon(R.drawable.ic_launcher);
				builder.setTitle(R.string.agree_title);
				builder.setMessage(R.string.agree_text);
				builder.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int arg1) {
						dialog.dismiss();
						SeekerHolderScreen seekerHolderScreen = new SeekerHolderScreen();
						seekerHolderScreen.showFragment(1, false);
					}
				});
				builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int arg1) {
						dialog.dismiss();
						//диалог ожидания реакции второго пользователя
					}
				});
				builder.show();
			}
		});
		
		return view;
	}

	private void initFragment() {
		SeekerDetectedApplication seekerDetectedApplication = SeekerDetectedApplication.getInstance();
		
		nameText = (TextView) view.findViewById(R.id.application_timerText);
		typeText = (TextView) view.findViewById(R.id.application_timerText);
		treatText = (TextView) view.findViewById(R.id.application_timerText);
		
		nameText.setText(seekerDetectedApplication.getName());
		switch (seekerDetectedApplication.getType()) {
		case 1: {
			typeText.setText(R.string.type_1);
			break;
		}
		case 2: {
			typeText.setText(R.string.type_2);
			break;
		}
		case 3: {
			typeText.setText(R.string.type_3);
			break;
		}
		case 4: {
			typeText.setText(R.string.type_4);
			break;
		}
		case 5: {
			typeText.setText(R.string.type_5);
			break;
		}
		case 6: {
			typeText.setText(R.string.type_6);
			break;
		}
		case 7: {
			typeText.setText(R.string.type_7);
			break;
		}
		}
		
		treatText.setText(seekerDetectedApplication.isMyTreat() ? R.string.treat_yes : R.string.treat_no);
		
		timerText = (TextView) view.findViewById(R.id.application_timerText);

		CountDownTimer count = new CountDownTimer(TWO_MINUTES, 1000) {
			public void onTick(long millisUntilFinished) {
				long minutes = millisUntilFinished / (60 * 1000);
				long seconds = millisUntilFinished % (60 * 1000);
				timerText.setText("[ " + String.valueOf(minutes) + ":" + String.valueOf(seconds / 1000) + " ]");
			}

			public void onFinish() {
				timerText.setText("00:00");
				SeekerHolderScreen seekerHolderScreen = new SeekerHolderScreen();
				seekerHolderScreen.showFragment(0, false);
			}
		};
		count.start();
		
	}
}
