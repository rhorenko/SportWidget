package com.avalons.mast;

import java.util.HashMap;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

public class Provider extends ContentProvider {
	public static final String TAG = "SportWidget->Provider"; 
	
    public static final String DB_SCHEDULE = "schedule.db";
    
    public static final Uri CONTENT_URI = Uri.parse(
			"content://com.avalons.mast.provider/schedule");//?
    public static final int URI_CODE = 1;
    public static final int URI_CODE_ID = 2;

    private static final UriMatcher mUriMatcher;
    private static HashMap<String, String> mContactMap;
    
    private SQLiteDatabase db;

    static {
        mUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        mUriMatcher.addURI("com.avalons.mast.provider", 
                DbHelper.TABLE_NAME, URI_CODE);
        mUriMatcher.addURI("com.avalons.mast.provider", 
                DbHelper.TABLE_NAME + "/#", URI_CODE_ID);

        mContactMap = new HashMap<String, String>();
        mContactMap.put(DbHelper._ID, DbHelper._ID);
        mContactMap.put(DbHelper.CITY, DbHelper.CITY);
        mContactMap.put(DbHelper.CLUB, DbHelper.CLUB);
        mContactMap.put(DbHelper.ROOM, DbHelper.ROOM);
        mContactMap.put(DbHelper.TYPE_TRAINING, DbHelper.TYPE_TRAINING);
        mContactMap.put(DbHelper.TYPE_PROGRAM, DbHelper.TYPE_PROGRAM);
        mContactMap.put(DbHelper.TRAINING, DbHelper.TRAINING);
        mContactMap.put(DbHelper.DAY, DbHelper.DAY);
        mContactMap.put(DbHelper.TIME_START, DbHelper.TIME_START);
        mContactMap.put(DbHelper.DURATION, DbHelper.DURATION);
        mContactMap.put(DbHelper.TRAINER, DbHelper.TRAINER);
        mContactMap.put(DbHelper.PLACE, DbHelper.PLACE);
        mContactMap.put(DbHelper.NOTES, DbHelper.NOTES);
        mContactMap.put(DbHelper.DESCRIPTION, DbHelper.DESCRIPTION);
        mContactMap.put(DbHelper.ADAPTER, DbHelper.ADAPTER);
        mContactMap.put(DbHelper.ISSELECTED, DbHelper.ISSELECTED);
    }

    public String getDbName() {
        return(DB_SCHEDULE);
    }
    
    @Override
    public boolean onCreate() {

        db = (new DbHelper(getContext())).getWritableDatabase();
        return (db == null) ? false : true;
    }
   
    @Override
    public Cursor query(Uri url, String[] projection, 
            String selection, String[] selectionArgs, String sort) {
      
        String orderBy;       
        if (TextUtils.isEmpty(sort)) {
            orderBy = DbHelper._ID;
        } 
        else {
            orderBy = sort;
        }

        Cursor c = db.query(DbHelper.TABLE_NAME, projection, selection, selectionArgs, 
                null, null, orderBy);
        c.setNotificationUri(getContext().getContentResolver(), url);
        return c;
    }

    @Override
    public Uri insert(Uri url, ContentValues inValues) {

        ContentValues values = new ContentValues(inValues);
        
        long rowId = db.insert(DbHelper.TABLE_NAME, DbHelper._ID, values);
        
        if (rowId > 0) {
            Uri uri = ContentUris.withAppendedId(CONTENT_URI, rowId);
            getContext().getContentResolver().notifyChange(uri, null);
            return uri;
        }
        else {
        	throw new SQLException("Failed to insert row into " + url);
        }
    }

    @Override
    public int delete(Uri url, String where, String[] whereArgs) {
        int retVal = db.delete(DbHelper.TABLE_NAME, where, whereArgs);

        getContext().getContentResolver().notifyChange(url, null);
        return retVal;
    }

    @Override
    public int update(Uri url, ContentValues values, 
            String where, String[] whereArgs) {
        int retVal = db.update(DbHelper.TABLE_NAME, values, where, whereArgs);
    
        getContext().getContentResolver().notifyChange(url, null);
        return retVal;
    }

    @Override
    public String getType(Uri uri) {       
        return null;
    }  
}
