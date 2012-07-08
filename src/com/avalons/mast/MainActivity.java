package com.avalons.mast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	ProgressDialog progress;
	public static final String TAG = "SportWidget->MainActivity";
	public static final String getCitiesUrl = "http://test.epigrammi.net/api?act=getcities";
	public static String FORCE_WIDGET_UPDATE = "com.paad.chapter9.FORCE_WIDGET_UPDATE";
	int mPosition;
	private final int IDD_DIALOG = 0;
	// private Button selected;
	// private Button types_training;
	// private Button types_program;
	// private Button trainers;
	// private ImageButton prefs;
	Vector<String> trainers;
	Vector<String> typesProgram;
	Vector<String> typesTraining;
	private Cursor mCursor;
	private ListAdapter mAdapter;
	private ListView lv;
	private boolean showOnlySelected;
	private String parameter;
	// private String day,time_start,type_program,duration,trainer,place;
	static final String[] mContent = new String[] { DbHelper._ID,
			DbHelper.CITY, DbHelper.CLUB, DbHelper.ROOM,
			DbHelper.TYPE_TRAINING, DbHelper.TYPE_PROGRAM, DbHelper.TRAINING,
			DbHelper.DAY, DbHelper.TIME_START, DbHelper.DURATION,
			DbHelper.TRAINER, DbHelper.PLACE, DbHelper.NOTES,
			DbHelper.DESCRIPTION, DbHelper.ISSELECTED, DbHelper.ADAPTER };
	SharedPreferences prefs;

	public void onResume() {
		super.onResume();
		// prefs =
		// PreferenceManager.getDefaultSharedPreferences(this);

	}

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		showOnlySelected = false;
		// new DownloadDataTask(getApplicationContext(),
		// getCitiesUrl).execute();
		// new
		// DownloadDataTask(getApplicationContext(),getSheduleUrl).execute();
		parameter = null;
		// selected = (Button)findViewById(R.id.button1);
		// types_training = (Button)findViewById(R.id.button2);
		// types_program = (Button)findViewById(R.id.button3);
		// trainers = (Button)findViewById(R.id.button4);
		// prefs = (ImageButton)findViewById(R.id.imageButton1);
		// SharedPreferences settings =
		// PreferenceManager.getDefaultSharedPreferences(this);
		// settings.getString("Type", defValue);
		mCursor = managedQuery(Provider.CONTENT_URI, mContent, parameter, null,
				null);
		trainers= new Vector();
		typesProgram= new Vector();
		typesTraining= new Vector();
		
		//getApplicationContext().sendBroadcast(new Intent(FORCE_WIDGET_UPDATE));
		
		mAdapter = new SportAdapter(getApplicationContext(), R.layout.item,
				mCursor, new String[] { DbHelper._ID, DbHelper.DAY,
						DbHelper.TIME_START, DbHelper.ADAPTER }, new int[] {
						R.id.id, R.id.day, R.id.timetile, R.id.tile });
		lv = (ListView) findViewById(R.id.lv);

		lv.setAdapter(mAdapter);

		lv.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				mPosition = position;
				
				showDialog(IDD_DIALOG);

			}
		});
		

	}

	public void clickHandler(View view) {
		switch (view.getId()) {
		case R.id.button1:
			showOnlySelected=!showOnlySelected;
			if (showOnlySelected) {
				showOnlySelected(lv);
				((Button)  view).setText(R.string.menu_all);
			} else {
				showAll(lv);
				((Button)  view).setText(R.string.menu_selected);
			};

			break;
		
		case R.id.button2:
			createTypeTrainings(typesTraining);
			
			// create the grid item mapping
			String[] from3 = new String[] {"rowid", "col_1"};
			int[] to3 = new int[] { R.id.idoneitem, R.id.oneitem };
			// prepare the list of all records
			List<HashMap<String, String>> fillMaps3 = new ArrayList<HashMap<String, String>>();
						
			for(int i = 0; i < typesTraining.size(); i++){
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("rowid", ""+i);
				map.put("col_1", typesTraining.get(i));
						
				fillMaps3.add(map);
			}
			// fill in the grid_item layout
			SimpleAdapter adapter3 = new SimpleAdapter(this, fillMaps3, R.layout.oneitem, from3, to3);
			lv.setAdapter(adapter3);			
			break;
		case R.id.button3:			
			createTypePrograms(typesProgram);
			
			// create the grid item mapping
			String[] from2 = new String[] {"rowid", "col_1"};
			int[] to2 = new int[] { R.id.idoneitem, R.id.oneitem };
			// prepare the list of all records
			List<HashMap<String, String>> fillMaps2 = new ArrayList<HashMap<String, String>>();
									
			for(int i = 0; i < typesProgram.size(); i++){
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("rowid", ""+i);
				map.put("col_1", typesProgram.get(i));
									
				fillMaps2.add(map);
			}
			// fill in the grid_item layout
			SimpleAdapter adapter2 = new SimpleAdapter(this, fillMaps2, R.layout.oneitem, from2, to2);
			lv.setAdapter(adapter2);	
			
			break;
		case R.id.button4:
			createTrainers(trainers);
									
			// create the grid item mapping
			String[] from4 = new String[] {"rowid", "col_1"};
			int[] to4 = new int[] { R.id.idoneitem, R.id.oneitem };
			// prepare the list of all records
			List<HashMap<String, String>> fillMaps4 = new ArrayList<HashMap<String, String>>();
			
			for(int i = 0; i < trainers.size(); i++){
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("rowid", ""+i);
			map.put("col_1", trainers.get(i));
			
			fillMaps4.add(map);
			}
			// fill in the grid_item layout
			SimpleAdapter adapter4 = new SimpleAdapter(this, fillMaps4, R.layout.oneitem, from4, to4);
			lv.setAdapter(adapter4);

			break;
		case R.id.imageButton1:
			Intent i = new Intent();
			i.setClass(this, PreferencesActivity.class);
			startActivity(i);
			break;
		}
	};

	public void showAll(ListView lv) {
		parameter = null;
		mCursor = managedQuery(Provider.CONTENT_URI, mContent, parameter, null,
				null);

		mAdapter = new SportAdapter(getApplicationContext(), R.layout.item,
				mCursor, new String[] { DbHelper._ID, DbHelper.DAY,
						DbHelper.TIME_START, DbHelper.ADAPTER }, new int[] {
						R.id.id, R.id.day, R.id.timetile, R.id.tile });

		lv.setAdapter(mAdapter);
	};

	public void showOnlySelected(ListView lv) {
		parameter = "isselected='+'";
		mCursor = managedQuery(Provider.CONTENT_URI, mContent, parameter, null,
				null);
		mAdapter = new SportAdapter(getApplicationContext(), R.layout.item,
				mCursor, new String[] { DbHelper._ID, DbHelper.DAY,
						DbHelper.TIME_START, DbHelper.ADAPTER }, new int[] {
						R.id.id, R.id.day, R.id.timetile, R.id.tile });
		lv.setAdapter(mAdapter);

	};

	public void selectByType(String type) {
		parameter = "type='" + type + "'";
		mCursor = managedQuery(Provider.CONTENT_URI, mContent, parameter, null,
				null);
		mAdapter = new SportAdapter(getApplicationContext(), R.layout.item,
				mCursor, new String[] { DbHelper._ID, DbHelper.DAY,
						DbHelper.TIME_START, DbHelper.ADAPTER }, new int[] {
						R.id.id, R.id.day, R.id.timetile, R.id.tile });
		lv.setAdapter(mAdapter);

	};

	public void selectByProgram(String program) {
		parameter = "program='" + program + "'";
		mCursor = managedQuery(Provider.CONTENT_URI, mContent, parameter, null,
				null);
		mAdapter = new SportAdapter(getApplicationContext(), R.layout.item,
				mCursor, new String[] { DbHelper._ID, DbHelper.DAY,
						DbHelper.TIME_START, DbHelper.ADAPTER }, new int[] {
						R.id.id, R.id.day, R.id.timetile, R.id.tile });
		lv.setAdapter(mAdapter);

	};

	public void selectByTrainer(String trainer) {
		parameter = "trainer='" + trainer + "'";
		mCursor = managedQuery(Provider.CONTENT_URI, mContent, parameter, null,
				null);
		mAdapter = new SportAdapter(getApplicationContext(), R.layout.item,
				mCursor, new String[] { DbHelper._ID, DbHelper.DAY,
						DbHelper.TIME_START, DbHelper.ADAPTER }, new int[] {
						R.id.id, R.id.day, R.id.timetile, R.id.tile });
		lv.setAdapter(mAdapter);

	}
	
	
	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case IDD_DIALOG:
			
			LayoutInflater inflater = getLayoutInflater();
			View layout = inflater.inflate(R.layout.abouttile,
					(ViewGroup) findViewById(R.id.layout));
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setView(layout);

			

			return builder.create();
		default:
			return null;
		}
	}
	protected void onPrepareDialog(int id, Dialog dialog) {
		
	      super.onPrepareDialog(id, dialog);
	      Log.d(TAG, "onPrepareDialog");
	      if (id == IDD_DIALOG) {
	    	  Log.i(TAG, "Position="+mPosition);  
	    	  
	    	  Cursor mCursor;
				TextView trainingTile = (TextView) dialog
						.findViewById(R.id.trainingtile);
				TextView timeStart = (TextView) dialog
						.findViewById(R.id.time_start_tile);
				TextView durationTile = (TextView) dialog
						.findViewById(R.id.duration_tile);
				TextView trainerTile = (TextView) dialog
						.findViewById(R.id.trainer_tile);
				TextView placeTile = (TextView) dialog
						.findViewById(R.id.place_tile);
				TextView notesTile = (TextView) dialog
						.findViewById(R.id.notes_tile);
				TextView descriptionTile = (TextView) dialog
						.findViewById(R.id.description_tile);

				mCursor = managedQuery(Provider.CONTENT_URI, mContent, null, null,
						null);
				mCursor.moveToPosition(mPosition);

				
				
				trainingTile.setText(mCursor.getString(6));
				timeStart.setText(mCursor.getString(8));
				durationTile.setText(mCursor.getString(9));
				trainerTile.setText(mCursor.getString(10));
				placeTile.setText(mCursor.getString(11));
				notesTile.setText(mCursor.getString(12));
				descriptionTile.setText(mCursor.getString(13));
				
	      }
	    }
	public void createNotif(String notif) {

		Toast.makeText(getApplicationContext(), "Don't work yet!",
				Toast.LENGTH_SHORT).show();

	};
	
	public void createTrainers(Vector<String> trainers){
		Cursor mCursor;
		String trainer;
		mCursor = managedQuery(Provider.CONTENT_URI, mContent, null, null,
				null);
		for (int i = 0; i < mCursor.getCount(); i++) {
			mCursor.moveToPosition(i);
			trainer=mCursor.getString(10);
			
			if(!(trainers.contains(trainer))){
			trainers.add(trainer);
			Log.i(TAG, trainer);
			}
			
		}
		
		
	};
	
	
	
	public void createTypeTrainings(Vector<String> typesTraining){
		Cursor mCursor;
		String typeTraining;
		mCursor = managedQuery(Provider.CONTENT_URI, mContent, null, null,
				null);
		for (int i = 0; i < mCursor.getCount(); i++) {
			mCursor.moveToPosition(i);
			typeTraining=mCursor.getString(4);
			
			if(!(typesTraining.contains(typeTraining))){
				typesTraining.add(typeTraining);
			Log.i(TAG, typeTraining);
			}
			
		}
		
		
	};
	public void createTypePrograms(Vector<String> typesProgram){
		Cursor mCursor;
		String typeProgram;
		mCursor = managedQuery(Provider.CONTENT_URI, mContent, null, null,
				null);
		for (int i = 0; i < mCursor.getCount(); i++) {
			mCursor.moveToPosition(i);
			typeProgram=mCursor.getString(5);
			
			if(!(typesProgram.contains(typeProgram))){
				typesProgram.add(typeProgram);
			Log.i(TAG, typeProgram);
			}
			
		}
		
		
	};
}
