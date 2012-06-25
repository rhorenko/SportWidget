package com.avalons.mast;

import android.content.Context;
import android.preference.EditTextPreference;
import android.util.AttributeSet;
import android.util.Log;

public class SyncDialogPreferences  extends EditTextPreference {
	public static final String TAG = "SportWidget->SyncDialogPreferences";
    public SyncDialogPreferences(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.i(TAG, "From prefs");
    }

    @Override
    protected void onDialogClosed(boolean positiveResult) {
        super.onDialogClosed(positiveResult);
        
        
    }

}