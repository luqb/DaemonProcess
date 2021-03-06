package com.yyh.activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.yyh.fork.NativeRuntime;
import com.yyh.utils.FileUtils;

import a.daemonprocess.R;

public class PhoneStatReceiver extends BroadcastReceiver {

	private String TAG = "tag";
	private int count = 0;

	@Override
	public void onReceive(Context context, Intent intent) {
		if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
			Log.i(TAG, "myProcess" +
					"开启启动了");
			NativeRuntime.getInstance().startService(context.getPackageName() + "/com.yyh.service.HostMonitor", FileUtils.createRootPath());
		} else if (Intent.ACTION_USER_PRESENT.equals(intent.getAction())) {
		}
	}

	@SuppressWarnings("deprecation")
	private void putNotification(Context context, String phoneNum) {
		count++;
		NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		Notification n = new Notification(R.mipmap.ic_launcher, "拦截短信ʾ", System.currentTimeMillis() + 1000);
		n.flags = Notification.FLAG_AUTO_CANCEL;
		n.number = count;
		Intent it = new Intent(context, MainActivity.class);
		it.putExtra("name", "name:" + count);
		PendingIntent pi = PendingIntent.getActivity(context, count, it, PendingIntent.FLAG_CANCEL_CURRENT);
		//n.setLatestEventInfo(context, "���ź�������", "����:" + phoneNum, pi);
		nm.notify(count, n);
	}
}
