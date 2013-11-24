package com.primerworldapps.seeker.receiver;

import com.primerworldapps.seeker.services.ScannerService;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ApplicationReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// ���������� ���������� ������ �� ������� � ���������� �����������
		context.stopService(new Intent(context, ScannerService.class));
	}

}
