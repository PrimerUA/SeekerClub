package com.primerworldapps.seeker.services;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationCompat.Builder;
import android.widget.RemoteViews;

import com.primerworldapps.seeker.R;
import com.primerworldapps.seeker.SeekerHolderScreen;
import com.primerworldapps.seeker.receiver.ApplicationReceiver;

public class ScannerService extends Service {

	private ApplicationReceiver applicationReceiver = new ApplicationReceiver();
	private NotificationManager notificationManager;

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

		return Service.START_STICKY;
	}

	private void showNotification(Intent intent) {
		int NOTI_CODE = 1;
		notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		Builder notificationBuilder = new NotificationCompat.Builder(this);

		Intent notificationIntent = new Intent(this, SeekerHolderScreen.class);
		notificationIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		PendingIntent contentIntent = PendingIntent.getActivity(this, NOTI_CODE, notificationIntent,
				PendingIntent.FLAG_CANCEL_CURRENT);
		notificationBuilder.setAutoCancel(false)
				.setTicker(getString(R.string.noti_scanner_ticker))
				.setContentIntent(contentIntent).setSmallIcon(R.drawable.ic_launcher)
				.setWhen(System.currentTimeMillis());

		Notification notification = notificationBuilder.build();

		RemoteViews contentView = new RemoteViews(getPackageName(), R.layout.noti_spinner);
		contentView.setProgressBar(R.id.noti_scannerProgress, 0, 0, true);
		contentView.setTextViewText(R.id.noti_titleText, getString(R.string.noti_scanner_title));
		contentView.setTextViewText(R.id.noti_subTitleText, getString(R.string.noti_scanner_text));

		notification.contentView = contentView;
		notification.flags = Notification.FLAG_ONGOING_EVENT;
		notification.flags = Notification.FLAG_NO_CLEAR;
		notificationManager.notify(NOTI_CODE, notification);
	}

	@Override
	public void onDestroy() {
		unregisterReceiver(applicationReceiver);
		super.onDestroy();
		notificationManager.cancelAll();
	}

	@Override
	public boolean onUnbind(Intent intent) {
		unregisterReceiver(applicationReceiver);
		return super.onUnbind(intent);
	}

}
