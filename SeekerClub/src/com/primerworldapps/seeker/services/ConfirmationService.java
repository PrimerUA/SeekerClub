package com.primerworldapps.seeker.services;

import com.primerworldapps.seeker.R;
import com.primerworldapps.seeker.SeekerHolderScreen;
import com.primerworldapps.seeker.entity.SeekerDetectedApplication;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationCompat.Builder;
import android.widget.RemoteViews;

public class ConfirmationService extends Service {

	private NotificationManager notificationManager;

	private int NOTI_CONFIRMATION_CODE = 1;
	private int NOTI_CONFIRMED_CODE = 2;

	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TO-DO: отправка подтверждения на сервер
		// запуск процедуры оиждания подтверждения на сервере
		// каждуые 20 секунд посылать запрос на сервер и проверять состояние
		// подтверждения от второго пользователя

		notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		showConfirmationNotification();

		return Service.START_STICKY;
	}

	private void showConfirmationNotification() {
		notificationManager.cancelAll();
		Builder notificationBuilder = new NotificationCompat.Builder(this);

		Intent notificationIntent = new Intent(this, SeekerHolderScreen.class);
		notificationIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		PendingIntent contentIntent = PendingIntent.getActivity(this, NOTI_CONFIRMATION_CODE, notificationIntent,
				PendingIntent.FLAG_CANCEL_CURRENT);
		notificationBuilder.setAutoCancel(false).setTicker(getString(R.string.cnoti_confirmation_ticker))
				.setContentIntent(contentIntent).setSmallIcon(R.drawable.ic_launcher)
				.setWhen(System.currentTimeMillis());

		Notification notification = notificationBuilder.build();

		RemoteViews contentView = new RemoteViews(getPackageName(), R.layout.noti_spinner);
		contentView.setProgressBar(R.id.noti_scannerProgress, 0, 0, true);
		contentView.setTextViewText(R.id.noti_titleText, getString(R.string.cnoti_confirmation_title));
		contentView.setTextViewText(R.id.noti_subTitleText, getString(R.string.cnoti_confirmation_text));

		notification.contentView = contentView;
		notification.flags = Notification.FLAG_ONGOING_EVENT;
		notification.flags = Notification.FLAG_NO_CLEAR;
		notificationManager.notify(NOTI_CONFIRMATION_CODE, notification);
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
		PendingIntent contentIntent = PendingIntent.getActivity(this, NOTI_CONFIRMED_CODE, notificationIntent,
				PendingIntent.FLAG_CANCEL_CURRENT);
		notificationBuilder.setContentTitle(getString(R.string.cnoti_confirmed_title))
				.setContentText(getString(R.string.cnoti_confirmed_text)).setAutoCancel(false)
				.setTicker(getString(R.string.cnoti_confirmed_ticker)).setContentIntent(contentIntent)
				.setSmallIcon(R.drawable.ic_launcher).setWhen(System.currentTimeMillis());

		Notification notification = notificationBuilder.build();
		notification.flags = Notification.FLAG_ONGOING_EVENT;
		notification.flags = Notification.FLAG_NO_CLEAR;
		notificationManager.notify(NOTI_CONFIRMED_CODE, notification);
	}
}
