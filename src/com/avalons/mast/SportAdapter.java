package com.avalons.mast;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class SportAdapter extends SimpleCursorAdapter{
	public static final String TAG = "SportWidget->SportAdapter"; 
	private CheckBox mCheckBox;
    private TextView mTextId;
	private Context mListContext;
	private Cursor mCursor;
     public SportAdapter(Context context, int layout, Cursor c, String[] from,
			int[] to) {
		super(context, layout, c, from, to);
		mListContext=context;
		mCursor=c;
	}

  @Override
  public View getView(int position, View converView, ViewGroup parent) {
   View List;
   Log.i(TAG, "Position="+position);
   mCursor.moveToPosition(position);
   if(converView==null){
	   	LayoutInflater inflater = (LayoutInflater)mListContext.getSystemService
			      (Context.LAYOUT_INFLATER_SERVICE);
	    List=newView(mListContext,mCursor,parent);	    
	    List=inflater.inflate(R.layout.item, parent, false);
	    
	   }
	   else{
	    List = (View)converView;
	   };
	   
   bindView(List, mListContext, mCursor);
    
    CheckBox cb=(CheckBox)List.findViewById(R.id.cb);
    	
    cb.setOnClickListener(new OnClickListener() {
   
		@Override
		public void onClick(View arg0) {
			
			Log.i(TAG, " Clicked on CheckBox");
			String id;
			mTextId=(TextView)((RelativeLayout)arg0.getParent()).findViewById(R.id.
					id);
		    id=mTextId.getText().toString(); 
		    Log.i(TAG, "Try  change to id="+id);
			ContentValues values = new ContentValues(2);
			if(((CheckBox)arg0).isChecked()){					 		              
					
		        values.put(DbHelper.ISSELECTED, "+");
        	    mListContext.getContentResolver().update( Provider.CONTENT_URI, values, "_ID=" + Integer.toString((Integer.parseInt(id))-1), null);
        	    mCursor.requery();
        	    Log.i(TAG, " Changed to '+' id="+id);
				  
			} else {
				
				values.put(DbHelper.ISSELECTED, "-"); 		              
				mListContext.getContentResolver().update( Provider.CONTENT_URI, values, "_ID=" + Integer.toString((Integer.parseInt(id))-1), null);
				mCursor.requery();
				Log.i(TAG, " Changed to '-' id="+id);
	              
			};	
			
		}
	}
		);
   
   
   return List;
  }
  
     
    }