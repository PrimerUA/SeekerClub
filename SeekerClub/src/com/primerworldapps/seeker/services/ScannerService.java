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
import com.primerworldapps.seeker.entity.SeekerDetectedApplication;
import com.primerworldapps.seeker.util.Constants;

public class ScannerService extends Service {

	private NotificationManager notificationManager;
	
	private int NOTI_SCAN_CODE = 1;
	private int NOTI_DETECT_CODE = 2;

	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TO-DO: отправка заявки на сервер
		// запуск процедуры сканирования на сервере
		// каждую минуту посылать запрос на сервер и проверять есть ли там новые
		// заявки по указанным критериям

		notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		showScannerNotification();

		return Service.START_STICKY;
	}

	private void showScannerNotification() {
		notificationManager.cancelAll();
		Builder notificationBuilder = new NotificationCompat.Builder(this);

		Intent notificationIntent = new Intent(this, SeekerHolderScreen.class);
		notificationIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		PendingIntent contentIntent = PendingIntent.getActivity(this, NOTI_SCAN_CODE, notificationIntent,
				PendingIntent.FLAG_CANCEL_CURRENT);
		notificationBuilder.setAutoCancel(false).setTicker(getString(R.string.noti_scanner_ticker))
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
		notificationManager.notify(NOTI_SCAN_CODE, notification);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		if (SeekerDetectedApplication.getInstance().getId() == 0) {
			notificationManager.cancelAll();
		} else {
			showDetectedNotification();
		}
	}

	@Override
	public boolean onUnbind(Intent intent) {
		return super.onUnbind(intent);
	}

	public void showDetectedNotification() {
		notificationManager.cancelAll();
		Builder notificationBuilder = new NotificationCompat.Builder(this);

		Intent notificationIntent = new Intent(this, SeekerHolderScreen.class);
		notificationIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		PendingIntent contentIntent = PendingIntent.getActivity(this, NOTI_DETECT_CODE, notificationIntent,
				PendingIntent.FLAG_CANCEL_CURRENT);
		notificationBuilder.setContentTitle(getString(R.string.noti_detected_title))
				.setContentText(getString(R.string.noti_detected_text)).setAutoCancel(false)
				.setTicker(getString(R.string.noti_detected_ticker)).setContentIntent(contentIntent)
				.setSmallIcon(R.drawable.ic_launcher).setWhen(System.currentTimeMillis());

		Notification notification = notificationBuilder.build();
		notification.flags = Notification.FLAG_ONGOING_EVENT;
		notification.flags = Notification.FLAG_NO_CLEAR;
		notificationManager.notify(NOTI_DETECT_CODE, notification);
	}
}
