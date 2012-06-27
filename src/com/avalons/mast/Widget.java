/*
 * Copyright (C) 2009 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.avalons.mast;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.IBinder;
import android.util.Log;
import android.widget.RemoteViews;

/**
 * Define a simple widget that shows the sport schedule. 
 */
public class Widget extends AppWidgetProvider {
	
	public static final String TAG = "SportWidget->Widget";
	
		Cursor mCursor;
		static final String[] mContent = new String[] { DbHelper._ID, DbHelper.ADAPTER };
	
	
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager,
            int[] appWidgetIds) {
        
    	// Get all ids
    			ComponentName thisWidget = new ComponentName(context,
    					AppWidgetProvider.class);
    			int[] allWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);
    			for (int widgetId : allWidgetIds) {
    				//Read data from database 				
    				mCursor = context.getContentResolver().query(Provider.CONTENT_URI, mContent, null, null,
    						null);
    				mCursor.moveToPosition(0); String tv1=mCursor.getString(14);
    				mCursor.moveToPosition(1); String tv2=mCursor.getString(14);
    				mCursor.moveToPosition(2); String tv3=mCursor.getString(14);
    				mCursor.moveToPosition(3); String tv4=mCursor.getString(14);
    				
    				RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
    						R.layout.widget);
    				Log.i(TAG, tv1);
    				Log.i(TAG, tv2);
    				Log.i(TAG, tv3);
    				Log.i(TAG, tv4);
    				// Set the text
    				
    				
    				remoteViews.setTextViewText(R.id.tv1, tv1);
    				remoteViews.setTextViewText(R.id.tv2, tv2);
    				remoteViews.setTextViewText(R.id.tv3, tv3);
    				remoteViews.setTextViewText(R.id.tv4, tv4);
    				
    				// Register an onClickListener
    				Intent intent = new Intent(context, MainActivity.class);

    				intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
    				intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);

    				PendingIntent pendingIntent = PendingIntent.getBroadcast(context,
    						0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    				remoteViews.setOnClickPendingIntent(R.id.tv1, pendingIntent);
    				appWidgetManager.updateAppWidget(widgetId, remoteViews);
    			}
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        Log.d("ARH","CLICKK");
    }
        
}
