package com.primerworldapps.seeker.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.NotificationManager;
import android.app.Service;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;
import com.primerworldapps.seeker.R;
import com.primerworldapps.seeker.SeekerHolderScreen;
import com.primerworldapps.seeker.entity.SeekerDetectedApplication;
import com.primerworldapps.seeker.services.ConfirmationService;

public class ApplicationDetectedFragment extends SherlockFragment {

	private View view;

	private TextView nameText;
	private TextView typeText;
	private TextView treatText;

	private TextView timerText;

	private CountDownTimer count;
	private NotificationManager notificationManager;

	private SeekerDetectedApplication seekerDetectedApplication;
	private Dialog confirmationProgressDialog;

	private int NOTI_CODE = 2;
	private long TWO_MINUTES = 120000;
	
	private Intent confirmationService;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.application_detected_fragment, container, false);

		confirmationService = new Intent(getActivity(), ConfirmationService.class);
		
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
					public void onClick(DialogInterface confirmationProgressDialog, int arg1) {
						confirmationProgressDialog.dismiss();
					}
				});
				builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface confirmationProgressDialog, int arg1) {
						confirmationProgressDialog.dismiss();
						backFragment();
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
					public void onClick(DialogInterface confirmationProgressDialog, int arg1) {
						confirmationProgressDialog.dismiss();
					}
				});
				builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface confirmationProgressDialog, int arg1) {
						confirmationProgressDialog.dismiss();
						count.cancel();
						// диалог ожидания реакции второго пользователя
						getActivity().startService(confirmationService);
						showConfirmationProgress();
					}
				});
				builder.show();
			}
		});

		return view;
	}

	public void initFragment() {
		seekerDetectedApplication = SeekerDetectedApplication.getInstance();

		nameText = (TextView) view.findViewById(R.id.application_nameText);
		typeText = (TextView) view.findViewById(R.id.application_themeText);
		treatText = (TextView) view.findViewById(R.id.application_treatText);

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

		count = new CountDownTimer(TWO_MINUTES, 1000) {
			public void onTick(long millisUntilFinished) {
				long minutes = millisUntilFinished / (60 * 1000);
				long seconds = millisUntilFinished % (60 * 1000);
				timerText.setText("[ " + String.valueOf(minutes) + ":" + String.valueOf(seconds / 1000) + " ]");
			}

			public void onFinish() {
				timerText.setText("00:00");
				backFragment();
			}
		};
		count.start();

	}

	private void showConfirmationProgress() {
		confirmationProgressDialog = new Dialog(getActivity());

		confirmationProgressDialog.setContentView(R.layout.confirm_progress_dialog);
		//confirmationProgressDialog.setCancelable(false);

		TextView nameText = (TextView) confirmationProgressDialog.findViewById(R.id.confirmProgress_nameText);
		nameText.setText(seekerDetectedApplication.getName());

		final TextView timeText = (TextView) confirmationProgressDialog.findViewById(R.id.confirmProgress_timeText);
		CountDownTimer count = new CountDownTimer(TWO_MINUTES, 1000) {
			public void onTick(long millisUntilFinished) {
				long minutes = millisUntilFinished / (60 * 1000);
				long seconds = millisUntilFinished % (60 * 1000);
				timeText.setText("[ " + String.valueOf(minutes) + ":" + String.valueOf(seconds / 1000) + " ]");
			}

			public void onFinish() {
				timeText.setText("00:00");
				confirmationProgressDialog.cancel();
				getActivity().stopService(confirmationService);
				backFragment();
			}
		};
		count.start();
		
		//кандидат подтвердил встречу
		confirmationProgressDialog.setOnCancelListener(new OnCancelListener() {
			
			@Override
			public void onCancel(DialogInterface dialog) {
				closeFragment();
			}
		});
		confirmationProgressDialog.show();
	}

	public void closeFragment() {
		confirmationProgressDialog.dismiss();
		getActivity().stopService(confirmationService);
	}
	
	private void backFragment() {
		count.cancel();
		SeekerHolderScreen seekerHolderScreen = (SeekerHolderScreen) getActivity();
		((ScanSeekerFragment) seekerHolderScreen.showFragment(0, false)).relaunchFragment();
	}
}
