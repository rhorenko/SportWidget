package com.avalons.mast;

import java.util.Vector;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class JSONParser {
	public static final String TAG = "SportWidget->JSONParser"; 
	
	int    city_id;
	String city_name="";
	
	int    club_id;
	String club_name;
	String club_addr;
	
	Vector ids=new Vector();
	Vector days=new Vector();
	Vector titles=new Vector();
	Vector trainers=new Vector();
	Vector places=new Vector();
	Vector times=new Vector();
	Vector abouts=new Vector();
	Vector lengths=new Vector();
	//Constructor
	public JSONParser(String res){
		
		if ((res.contains("0")|
			   res.contains("1")|
			   res.contains("2")|
			   res.contains("3")|
			   res.contains("4")|
			   res.contains("5")|
			   res.contains("6")|
			   res.contains("7")|
			   res.contains("8")|
			   res.contains("9")
				))
		{
			parseShedule(res);
		} else 
		{
			parseCities(res);			
		};
	};
	
	public void parseCities(String result){
		try{
		     
		    
			JSONArray jArray = new JSONArray(result);
			String jClubs=jArray.getJSONObject(0).getString("clubs");
			JSONObject jArrayClubs = new JSONObject(jClubs);
			
			city_id=jArray.getJSONObject(0).getInt("city_id"); 
			city_name=jArray.getJSONObject(0).getString("city_name"); 
			club_id=jArrayClubs.getInt("club_id");
			club_name=jArrayClubs.getString("club_name");
			club_addr=jArrayClubs.getString("club_addr");
		     
		     Log.i(TAG, "city_id="+city_id);
		     Log.i(TAG, "city_name="+city_name);
		     Log.i(TAG, "club_id="+club_id);
		     Log.i(TAG, "club_name="+club_name);
		     Log.i(TAG, "club_addr="+club_addr);
		     Log.i(TAG, "Succes! "+"");
		     }
		catch(JSONException e)
			{
		     Log.e(TAG, "Error parsing data "+e.toString());
		     }
	};
	
	public void parseShedule(String shedule){
		try {
			
			JSONObject jObject = new JSONObject(shedule);
			JSONArray jTiles=jObject.getJSONArray("1");
			JSONObject jTile;
			for (int i = 0; i < jTiles.length(); i++) {
				jTile  = jTiles.getJSONObject(i);
				ids.add(      jTile.getInt("id"));
				days.add(     jTile.getInt("day"));
				titles.add(   jTile.getString("title"));
				trainers.add( jTile.getString("trainer"));
				places.add(   jTile.getString("place"));
				times.add(    jTile.getString("time"));
				abouts.add(   jTile.getString("about"));
				lengths.add(  jTile.getInt("length"));
				
				/**Log.i(TAG,"ids["+i+"]="+ids.get(i)+
							 "days["+i+"]="+days.get(i)+
							 "titles["+i+"]="+titles.get(i)+
							 "trainers["+i+"]="+trainers.get(i)+
							 "places["+i+"]="+places.get(i)+
							 "times["+i+"]="+times.get(i)+
							 "abouts["+i+"]="+abouts.get(i)+
							 "lengths["+i+"]="+lengths.get(i)+"\n");*/
			}
			
		} catch (JSONException e) {
			
		}
	};

}
