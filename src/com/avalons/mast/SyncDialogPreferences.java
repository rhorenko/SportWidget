package com.avalons.mast;

import java.util.Calendar;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.EditTextPreference;
import android.preference.PreferenceManager;
import android.util.AttributeSet;
import android.util.Log;

public class SyncDialogPreferences  extends EditTextPreference {
	public static final String TAG = "SportWidget->SyncDialogPreferences";
	public static final String getSheduleUrl = "http://test.epigrammi.net/api?act=getshedule&club_id=4";
	Context mContext;
    public SyncDialogPreferences(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.i(TAG, "From prefs");
        mContext=context;
    }

    @Override
    protected void onDialogClosed(boolean positiveResult) {
        super.onDialogClosed(positiveResult);
        sync(mContext);    //synhronization with server   
    }
    public void sync(Context context){
		String time;
		new DownloadDataTask(context,getSheduleUrl).execute();
		time=getTime();
		//prefs = 
	            //PreferenceManager.getDefaultSharedPreferences(this);
		//String syncTime=prefs.getString(getString(R.string.pr_sync),getString(R.string.pr_def_val_sync));
		
		SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefEditor = sharedPref.edit(); // Get preference in editor mode
        prefEditor.putString(context.getString(R.string.pr_sync), time); // set your default value here (could be empty as well)
        prefEditor.commit(); // finally save changes
	};
	
	public String getTime(){
		StringBuilder time;
		final Calendar c = Calendar.getInstance();
		int mYear = c.get(Calendar.YEAR);
		int mMonth = c.get(Calendar.MONTH)+1;
		int mDay = c.get(Calendar.DAY_OF_MONTH);
        int mHour = c.get(Calendar.HOUR_OF_DAY);
        int mMinute = c.get(Calendar.MINUTE);
        int mSecond = c.get(Calendar.SECOND);
		time = new StringBuilder()
        	.append(mYear)
        	.append("/")
        	.append(mMonth)
        	.append("/")
        	.append(mDay)
        	.append(" ")
        	.append(mHour)
        	.append(":")
        	.append(mMinute)
        	.append(":")
        	.append(mSecond);
		return time.toString();
	};
}