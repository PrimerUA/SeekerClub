package com.primerworldapps.seeker.view.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.actionbarsherlock.app.SherlockFragment;
import com.primerworldapps.seeker.R;
import com.primerworldapps.seeker.services.ScannerService;

public class ScanSeekerFragment extends SherlockFragment {

	private View view;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.scan_seeker_fragment, container, false);
		initFragment();
		Button abortButton = (Button) view.findViewById(R.id.scanner_abortButton);
		abortButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				getActivity().finish();
			}
		});
		
		Intent service = new Intent(getActivity(), ScannerService.class);
		service.putExtra("noti_title", getString(R.string.noti_scanner_title));
		service.putExtra("noti_text", getString(R.string.noti_scanner_text));
	    getActivity().startService(service);
	    
		return view;
	}

	private void initFragment() {
		
	}
}
