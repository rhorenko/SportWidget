package com.avalons.mast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Toast;

public class DownloadDataTask extends AsyncTask<Void, Void,String>{
	public static final String TAG = "SportWidget->DownloadTask"; 
	private  String result = "";
    private  InputStream is=null;
    //private  String city_name="";
    ProgressDialog dialog;
    private String mUrl;
    private Context mContext;
     
   public DownloadDataTask(Context context, String url){
    	mContext=context;
    	mUrl=url;
    	//dialog.getWindow().setLayout(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
    	dialog = new ProgressDialog(mContext, ProgressDialog.STYLE_SPINNER);
    	//dialog.setTitle("Обновление");
    	//dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); 
    	//dialog.addContentView(new View(mContext), (new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT)));
        
		
    	dialog.setMessage("Секундочку...");
		dialog.show();
    };
  
    
    protected String doInBackground(Void... values) {
	try
	   {
	   HttpClient httpclient = new DefaultHttpClient();
	   HttpGet httppost = new HttpGet(mUrl);
	   HttpResponse response = httpclient.execute(httppost);
	   HttpEntity entity = response.getEntity();
	   is = entity.getContent();
	       }
	catch(Exception e)
	    {
	        Log.e(TAG, "Error in http connection "+e.toString());
	    }
	
	try{
		BufferedReader reader = new BufferedReader(new InputStreamReader(is,"utf-8"),8);
		StringBuilder sb = new StringBuilder();
		String line = null;
		while ((line = reader.readLine()) != null) {
		sb.append(line + "\n");
		}
		 is.close();
		 result=sb.toString();
		 	}
		catch(Exception e)
		{
		    Log.e(TAG, "Error converting result "+e.toString());
		 }
	//Log.e(TAG, "result="+result);
	return result;
	 
	}

	protected void onProgressUpdate(Void... values) {
		super.onProgressUpdate(values);
		dialog.show();
	}

	protected void onPostExecute(String result) {
		super.onPostExecute(result);
		JSONParser mJSONParser = new JSONParser(mContext,result);	
		dialog.dismiss();
		Toast toast = Toast.makeText(mContext, "Данные успешно обновлены", Toast.LENGTH_SHORT);
		toast.show();
	}


}
