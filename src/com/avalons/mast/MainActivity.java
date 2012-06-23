package com.avalons.mast;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity 
{
	ProgressDialog progress;
    public static final String TAG = "SportWidget->MainActivity"; 
    public static final String getCitiesUrl = "http://test.epigrammi.net/api?act=getcities";
    public static final String getSheduleUrl = "http://test.epigrammi.net/api?act=getshedule&club_id=4";
	private final int IDD_DIALOG = 0;
    //private Button selected; 
    //private Button types_training; 
    //private Button types_program; 
    //private Button trainers;
    //private ImageButton prefs;
    private Cursor mCursor; 
    private ListAdapter mAdapter;
    private ListView lv;
    
    private String parameter;
    //private String day,time_start,type_program,duration,trainer,place;
    private static final String[] mContent = new String[] {
            DbHelper._ID, 
            DbHelper.CITY,
            DbHelper.CLUB,
            DbHelper.ROOM,
            DbHelper.TYPE_TRAINING,
            DbHelper.TYPE_PROGRAM,
            DbHelper.TRAINING,
            DbHelper.DAY,
            DbHelper.TIME_START,
            DbHelper.DURATION,
            DbHelper.TRAINER,
            DbHelper.PLACE,
            DbHelper.NOTES,
            DbHelper.DESCRIPTION,
            DbHelper.ISSELECTED,
            DbHelper.ADAPTER};

    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); 
        setContentView(R.layout.main);
        //new DownloadDataTask(getApplicationContext(), getCitiesUrl).execute();
        //new DownloadDataTask(getApplicationContext(),getSheduleUrl).execute();
        parameter=null;
        //selected = (Button)findViewById(R.id.button1); 
        //types_training = (Button)findViewById(R.id.button2); 
        //types_program = (Button)findViewById(R.id.button3); 
        //trainers = (Button)findViewById(R.id.button4);
        //prefs = (ImageButton)findViewById(R.id.imageButton1);
        mCursor = managedQuery(
                Provider.CONTENT_URI, mContent, parameter, null, null);
        
        mAdapter = new SportAdapter(getApplicationContext(), 
                R.layout.item, mCursor, 
                new String[] {DbHelper._ID,DbHelper.DAY, DbHelper.TIME_START, DbHelper.ADAPTER}, 
                new int[] {R.id.id,R.id.day, R.id.timetile, R.id.tile});           
        lv = (ListView)findViewById(R.id.lv);
        
        lv.setAdapter(mAdapter);
                
        
        lv.setOnItemClickListener(new OnItemClickListener() {
            
 			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
 					long arg3) { 	 				
 				
 				showDialog(IDD_DIALOG);
     			
 			}}
         ); 	
        
        
    }
    
    public void clickHandler(View view){
    	switch (view.getId()) {
					case R.id.button1:
						showOnlySelected(lv);
						break;
					case R.id.button2:
						Toast.makeText(getApplicationContext(), "Don't work yet!", Toast.LENGTH_SHORT).show();
						break;
					case R.id.button3:
						Toast.makeText(getApplicationContext(), "Don't work yet!", Toast.LENGTH_SHORT).show();
						break;
					case R.id.button4:
						Toast.makeText(getApplicationContext(), "Don't work yet!", Toast.LENGTH_SHORT).show();
						break;
					case R.id.imageButton1:
						Intent i = new Intent();
		                i.setClass(this, PreferencesActivity.class);
		                startActivity(i);
						break;
    	}
    };
    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences prefs = 
            PreferenceManager.getDefaultSharedPreferences(this);       
    }
   

   
    public void showOnlySelected(ListView lv){
    	parameter="isselected='+'";
    	mCursor = managedQuery(
                Provider.CONTENT_URI, mContent, parameter, null, null);
    	mAdapter = new SimpleCursorAdapter(this, 
                R.layout.item, mCursor, 
                new String[] {DbHelper.DAY, DbHelper.TIME_START, DbHelper.ADAPTER}, 
                new int[] {R.id.day, R.id.timetile, R.id.tile}); 
    	lv.setAdapter(mAdapter);
    	
    };
    
    public void selectByType(){
    	
    	
    };
    
    public void selectByProgram(){
    	
    	
    };
    
    public void selectByTrainer(){
    	
    	
    }

	
	@Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
        case IDD_DIALOG:
            LayoutInflater inflater = getLayoutInflater();
            View layout = inflater.inflate(
                R.layout.abouttile, (ViewGroup)findViewById(R.id.layout));
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setView(layout);
                                   
            return builder.create();
        default:
        return null;
        }
    }
	
	public void createNotif(String notif){
		
		Toast.makeText(getApplicationContext(), "Don't work yet!", Toast.LENGTH_SHORT).show();
		
	}
	
	
}
