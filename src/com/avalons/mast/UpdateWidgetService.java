package com.avalons.mast;

import java.util.ArrayList;
import java.util.List;

import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.database.Cursor;
import android.os.IBinder;
import android.util.Log;
import android.widget.RemoteViews;

public class UpdateWidgetService extends Service {
	
	public static final String TAG = "SportWidget->UdateWidgetService";
	
		Cursor mCursor;
		static final String[] mContent = new String[] { DbHelper._ID, DbHelper.ADAPTER };
		
		AppWidgetManager appWidgetManager;
		int[] appWidgetIds;
		
		// list of widget's ids
		static List<int[]> widgets = new ArrayList<int[]>();	
		
		@Override
		public void onStart(Intent intent, int startId) {
			
			// read id for widget that added
			appWidgetManager = AppWidgetManager.getInstance(getApplicationContext());
			appWidgetIds = intent.getIntArrayExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS);
			String operation = intent.getStringExtra(Widget.SERVICE_PARAM);
			
			// if widget added
			if(operation.equals(Widget.SERVICE_ADD)){
				// add current id to list				
				widgets.add(appWidgetIds);
				if(widgets.size() > 0){
					updateWidget();
					
					}	
				super.onStart(intent, startId);				
			} 
			
			// if widget deleted		
			if(operation.equals(Widget.SERVICE_DEL)){
				// delete this widget's id from list				
				widgets.remove(appWidgetIds);
				// if all widgets is deleted - stop service
				if(widgets.size() == 0){							
					stopSelf();
				}				
			}
			
		}
		
		@Override
		public void onDestroy(){

		}
		
		@Override
		public IBinder onBind(Intent intent) {
			return null;		
		}	
		
		void updateWidget(){
			
			// create link for layout			
			RemoteViews remoteViews = new RemoteViews(getPackageName(),	R.layout.widget);    			
			for (int j = 0; j < widgets.size(); j++){
				int[] current = widgets.get(j);	
				
				// count widgets that visible
		        int widget_max = current.length;
		        
		        // update each widget
		        for (int i=0; i < widget_max; i++) {	        	
		        	mCursor = this.getContentResolver().query(Provider.CONTENT_URI, mContent, null, null,
							null);
					mCursor.moveToPosition(0); String tv1=mCursor.getString(1);
					mCursor.moveToPosition(1); String tv2=mCursor.getString(1);
					mCursor.moveToPosition(2); String tv3=mCursor.getString(1);
					mCursor.moveToPosition(3); String tv4=mCursor.getString(1);
					//Log output			
					Log.e(TAG, tv1);
					Log.e(TAG, tv2);
					Log.e(TAG, tv3);
					Log.e(TAG, tv4);
					//Set text
					remoteViews.setTextViewText(R.id.tv1, tv1);//Should delete ""
					remoteViews.setTextViewText(R.id.tv2, tv2);
					remoteViews.setTextViewText(R.id.tv3, tv3);
					remoteViews.setTextViewText(R.id.tv4, tv4);
					// Register an onClickListener
		        	 		        	
		        	
		            int widgetId = current[i];
					
					appWidgetManager.updateAppWidget(widgetId, remoteViews);  	            
		        }
			}		
		}


	
	
	
	/* 
	
	public AppWidgetManager appWidgetManager;
	@Override
	public void onStart(Intent intent, int startId) {
		Log.i(TAG, "Called");
		
		appWidgetManager = AppWidgetManager.getInstance(this
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
	*/
}
