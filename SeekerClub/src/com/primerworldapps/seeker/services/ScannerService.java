package com.primerworldapps.seeker.services;

import android.R;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationCompat.Builder;

import com.primerworldapps.seeker.SeekerHolderScreen;
import com.primerworldapps.seeker.receiver.ApplicationReceiver;

public class ScannerService extends Service {

	private ApplicationReceiver applicationReceiver = new ApplicationReceiver();

	@Override
	public IBinder onBind(Intent arg0) {
		registerReceiver(applicationReceiver, new IntentFilter());
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TO-DO: отправка заявки на сервер
		// запуск процедуры сканирования на сервере
		registerReceiver(applicationReceiver, new IntentFilter());

		showNotification(intent);

		startActivity(new Intent(this, SeekerHolderScreen.class));
		return Service.START_STICKY;
	}

	private void showNotification(Intent intent) {
		int NOTI_CODE = 1;
		NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		Builder notificationBuilder = new NotificationCompat.Builder(this);

		Intent notificationIntent = new Intent(this, SeekerHolderScreen.class);
		PendingIntent contentIntent = PendingIntent.getActivity(this,
				NOTI_CODE, notificationIntent, 0);
		notificationBuilder.setAutoCancel(false)
				.setContentText(intent.getExtras().getString("noti_title"))
				.setContentTitle(intent.getExtras().getString("noti_text"))
				.setContentIntent(contentIntent);
		Notification notification = notificationBuilder.build();
		notification.flags = Notification.FLAG_NO_CLEAR ;
		notificationManager.notify(NOTI_CODE, notification);
	}

	@Override
	public void onDestroy() {
		unregisterReceiver(applicationReceiver);
		super.onDestroy();
	}

	@Override
	public boolean onUnbind(Intent intent) {
		unregisterReceiver(applicationReceiver);
		return super.onUnbind(intent);
	}

}
