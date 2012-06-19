package com.avalons.mast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class DownloadDataTask extends AsyncTask<Void, Void,String>{
	public static final String TAG = "SportWidget->DownloadTask"; 
	private  String result = "";
    private  InputStream is=null;
    //private  String city_name="";
    private ProgressDialog dialog;
    private String mUrl;
    private Context mContext;
    
   public  DownloadDataTask(Context context, String url){
    	mContext=context;
    	mUrl=url;
    };
  
    protected void onPreExecute() {
		dialog = new ProgressDialog(mContext, ProgressDialog.STYLE_SPINNER);
		dialog.setTitle("AndroidJSON");
		dialog.setMessage("Please, wait...");
		dialog.show();
	};
protected String doInBackground(Void... values) {
	try
	   {
	   // ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
	   // nameValuePairs.add(new BasicNameValuePair("Name",city_name));
	   HttpClient httpclient = new DefaultHttpClient();
	   HttpGet httppost = new HttpGet(mUrl);
	   //httppost.setEntity(new UrlEncodedFormEntity(""));
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
	Log.e(TAG, "result="+result);
	return result;
	 
}

protected void onProgressUpdate(Void... values) {
	super.onProgressUpdate(values);
	//progress.show();
}

protected void onPostExecute(String result) {
	super.onPostExecute(result);
	try{
	     JSONArray jArray = new JSONArray(result);
	     JSONObject json_data=null;
	     for(int i=0;i<jArray.length();i++)
	     {
	         json_data = jArray.getJSONObject(i);
	         int  population=json_data.getInt("City_Population");
	         
	     }
	     Log.e(TAG, "Succes! "+"");
	     }
	catch(JSONException e)
		{
	     Log.e(TAG, "Error parsing data "+e.toString());
	     }

	dialog.dismiss();
}
}
