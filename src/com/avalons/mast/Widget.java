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

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;

/**
 * Define a simple widget that shows the sport schedule. 
 */
public class Widget extends AppWidgetProvider {
	public static final String TAG = "SportWidget->Widget";
	
	//for service management
		public static final String SERVICE_PARAM = "parameters";
		public static final String SERVICE_ADD = "add";
		public static final String SERVICE_DEL = "del";
		
		//it is called when widget is updating
		@Override
		public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
			// add widget
			Intent intent = new Intent(context, UpdateWidgetService.class);
	        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds); 
	        intent.putExtra(SERVICE_PARAM, SERVICE_ADD); 
			context.startService(intent);  
		}
		//it is called when widget is deleting
	    @Override
	    public void onDeleted(Context context, int[] appWidgetIds) {
	    	// delete widget
			Intent intent = new Intent(context.getApplicationContext(), UpdateWidgetService.class);
			intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds); 
	        intent.putExtra(SERVICE_PARAM, SERVICE_DEL); 
			context.startService(intent);  		   	
	    }	
	    
	 
	
	/**
	public static String FORCE_WIDGET_UPDATE = "com.avalons.mast.FORCE_WIDGET_UPDATE";
	
	
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
    				remoteViews.setTextViewText(R.id.tv1, "tv1");
    				remoteViews.setTextViewText(R.id.tv2, "tv2");
    				remoteViews.setTextViewText(R.id.tv3, "tv3");
    				remoteViews.setTextViewText(R.id.tv4, "tv4");
    				
    				
    				// Register an onClickListener
    				//Intent intent = new Intent(context, MainActivity.class);
    				Intent intent = new Intent("com.avalons.mast.ACTION_WIDGET_CLICK");
    				intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
    				intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);

    				PendingIntent pendingIntent = PendingIntent.getBroadcast(context,
    						0, intent, 0);
    				remoteViews.setOnClickPendingIntent(R.id.widget, pendingIntent);
    				appWidgetManager.updateAppWidget(widgetId, remoteViews);
    				
    				Intent intent1 = new Intent(context, UpdateWidgetService.class);
    				context.startService(intent1);  
    			}
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        Log.d(TAG,"CLICKK");
        if (FORCE_WIDGET_UPDATE.equals(intent.getAction())) {
			
        	Log.d("ARH","CLICKK--------------->");
			}
		
    }
    */    
}
