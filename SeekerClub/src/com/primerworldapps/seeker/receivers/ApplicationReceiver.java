package com.primerworldapps.seeker.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.primerworldapps.seeker.SeekerHolderScreen;
import com.primerworldapps.seeker.entity.SeekerDetectedApplication;
import com.primerworldapps.seeker.services.ScannerService;

public class ApplicationReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// подгружаем полученную заявку от сервера и отображаем уведомление
		SeekerDetectedApplication.getInstance().setName("Жанна Фриске").setType(3).setMyTreat(false)
				.setContact("+380631234567");
		// //

		context.stopService(new Intent(context, ScannerService.class));

		// ScanSeekerFragment scanSeekerFragment = new ScanSeekerFragment();
		// scanSeekerFragment.stopTimer();

		SeekerHolderScreen seekerHolderScreen = new SeekerHolderScreen();
		seekerHolderScreen.initScreen();
		seekerHolderScreen.showFragment(1, true);

	}

}
