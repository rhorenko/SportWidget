package com.avalons.mast;

import java.util.Random;

import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.database.Cursor;
import android.os.IBinder;
import android.util.Log;
import android.widget.RemoteViews;

public class UpdateWidgetService extends Service {
	public static final String TAG = "SportWidget->UdateWidgetService"; 
	Cursor mCursor;
	static final String[] mContent = new String[] { DbHelper._ID, DbHelper.ADAPTER };
	@Override
	public void onStart(Intent intent, int startId) {
		Log.i(TAG, "Called");
		
		AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this
				.getApplicationContext());

		int[] allWidgetIds = intent
				.getIntArrayExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS);

		ComponentName thisWidget = new ComponentName(getApplicationContext(),
				Widget.class);
		int[] allWidgetIds2 = appWidgetManager.getAppWidgetIds(thisWidget);
		Log.e(TAG, "From Intent" + allWidgetIds.length);
		Log.e(TAG, "Direct" + allWidgetIds2.length);

		for (int widgetId : allWidgetIds) {
			// Create some random data
			int number = (new Random().nextInt(100));

			RemoteViews remoteViews = new RemoteViews(this
					.getApplicationContext().getPackageName(),
					R.layout.widget);
			Log.e("WidgetExample", ""+number);		
			mCursor = this.getContentResolver().query(Provider.CONTENT_URI, mContent, null, null,
					null);
			mCursor.moveToPosition(0); String tv1=mCursor.getString(14);
			mCursor.moveToPosition(1); String tv2=mCursor.getString(14);
			mCursor.moveToPosition(2); String tv3=mCursor.getString(14);
			mCursor.moveToPosition(3); String tv4=mCursor.getString(14);
			//Log output			
			Log.e(TAG, tv1);
			Log.e(TAG, tv2);
			Log.e(TAG, tv3);
			Log.e(TAG, tv4);
			//Set text
			remoteViews.setTextViewText(R.id.tv1, "tv1");//Should delete ""
			remoteViews.setTextViewText(R.id.tv2, "tv2");
			remoteViews.setTextViewText(R.id.tv3, "tv3");
			remoteViews.setTextViewText(R.id.tv4, "tv4");
			// Register an onClickListener
			Intent clickIntent = new Intent(this.getApplicationContext(),
					MainActivity.class);

			clickIntent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
			clickIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS,
					allWidgetIds);

			PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, clickIntent,
					PendingIntent.FLAG_UPDATE_CURRENT);
			remoteViews.setOnClickPendingIntent(R.id.tv1, pendingIntent);
			appWidgetManager.updateAppWidget(widgetId, remoteViews);
		}
		//stopSelf();

		super.onStart(intent, startId);
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
}
