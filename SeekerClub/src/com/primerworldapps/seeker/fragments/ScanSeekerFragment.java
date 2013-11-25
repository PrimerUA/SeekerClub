package com.primerworldapps.seeker.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;
import com.primerworldapps.seeker.R;
import com.primerworldapps.seeker.services.ScannerService;

public class ScanSeekerFragment extends SherlockFragment {

	private View view;
	private Intent service;

	private TextView timerText;

	private long ONE_HOUR = 3600000;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.scan_seeker_fragment, container, false);
		initFragment();

		Button abortButton = (Button) view.findViewById(R.id.scanner_abortButton);
		abortButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
				builder.setIcon(R.drawable.ic_launcher);
				builder.setTitle(R.string.stop_title);
				builder.setMessage(R.string.stop_text);
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
						stopServiceAndFinish();
					}
				});
				builder.show();
			}
		});

		service = new Intent(getActivity(), ScannerService.class);
		getActivity().startService(service);

		return view;
	}

	private void initFragment() {
		timerText = (TextView) view.findViewById(R.id.scanner_timerText);

		CountDownTimer count = new CountDownTimer(ONE_HOUR, 1000) {
			public void onTick(long millisUntilFinished) {
				long minutes = millisUntilFinished / (60 * 1000);
				long seconds = millisUntilFinished % (60 * 1000);
				timerText.setText("[ " + String.valueOf(minutes) + ":" + String.valueOf(seconds / 1000) + " ]");
			}

			public void onFinish() {
				timerText.setText("00:00");
				stopServiceAndFinish();
			}
		};

		count.start();
	}

	private void stopServiceAndFinish() {
		getActivity().stopService(service);
		getActivity().finish();
	}
}
