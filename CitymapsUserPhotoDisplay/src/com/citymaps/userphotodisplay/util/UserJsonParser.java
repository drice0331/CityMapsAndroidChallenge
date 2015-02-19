package com.citymaps.userphotodisplay.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.citymaps.userphotodisplay.model.UserPhoto;

import android.util.Log;

public class UserJsonParser {
	public static final String TAG = "UserJsonParser";
	
	private static final String ENDPOINT = "http://ndev-coreapi.citymaps.com/v2/activity/";
	private static final String USER = "user/";
	private static final String USER_ID = "8ea239c4-c648-4009-a252-a220e018dc4b/";
	private static final String LIMIT = "20";
	
	private static final String ACTIVITIES_KEY = "activities";
	private static final String ID_KEY = "activity_id";
	private static final String IMAGE_KEY = "image_url";
	private static final String DIRECT_OBJECT_KEY = "direct_object";
	private static final String NAME_KEY = "name";
	
	byte[] getUrlBytes(String urlSpec) throws IOException {
		URL url = new URL(urlSpec);
		HttpURLConnection connection = (HttpURLConnection)url.openConnection();
		
		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			InputStream in = connection.getInputStream();
			
			if(connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
				return null;
			}
			
			int bytesRead = 0;
			byte[] buffer = new byte[1024];
			while((bytesRead = in.read(buffer)) > 0) {
				out.write(buffer, 0, bytesRead);
			}
			out.close();
			return out.toByteArray();
		} finally {
			connection.disconnect();
		}
	}
	
	public String getFromHttp(String url) {
		String responseString = null;
		try {
		DefaultHttpClient client = new DefaultHttpClient();
		HttpResponse response = null; // some response object
		HttpGet get = new HttpGet(url);
		HttpEntity httpEntity;
		
		response = client.execute(get);
		
		httpEntity = response.getEntity();
        responseString = EntityUtils.toString(httpEntity);
		}
		catch(UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		Log.i("jsonparser", responseString);
        return responseString;
	}
	
    public ArrayList<UserPhoto> downloadPhotos(String url) {
        ArrayList<UserPhoto> items = new ArrayList<UserPhoto>();
        parseJson(getFromHttp(url), items);
        Log.i("parseJSON", "items length is " + items.size());
        return items;
    }
    
    public void parseJson(String jsonStr, ArrayList<UserPhoto> items) {
    	if(jsonStr != null) {
    		try {
				JSONObject jsonobject = new JSONObject(jsonStr);
				JSONArray jsonArray = jsonobject.getJSONArray(ACTIVITIES_KEY);
				
				for(int ind = 0; ind < jsonArray.length(); ind++) {
					JSONObject obj = jsonArray.getJSONObject(ind);
					String id = obj.getString(ID_KEY);
					
					JSONArray imageURLArray = obj.getJSONArray(IMAGE_KEY);
					String imageUrl = imageURLArray.getString(0);
					
					JSONObject directObj = obj.getJSONObject(DIRECT_OBJECT_KEY);
					String name = directObj.getString(NAME_KEY);
					
					//Log.i("parser", "id: " + id + " caption(name): " + name + " url: " + imageUrl);
					
					
					UserPhoto photo = new UserPhoto();
					photo.setUrl(imageUrl);
					photo.setCaption(name);
					photo.setId(id);
					items.add(photo);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
    	}
    }
    
    /**
     * Called by GetUserPhotosTask to fetch from api, returns an arrayList of Photo objects
     * @return
     */
    public ArrayList<UserPhoto> fetchItems(int offset) {

    	String url = ENDPOINT;
    	url = url.concat(USER + USER_ID)
    			.concat("images?offset=" + String.valueOf(offset))
    			.concat("&limit=" + LIMIT);
        Log.i("TAG", "URL is " + url);
        return downloadPhotos(url);        

    }
    
    /**
     * A Little hardcodey, but gets the avatar url for main activity
     * @return
     */
    public String fetchAvatarUrl() {
    	String avatarUrl= "";
    	String url = ENDPOINT;
    	url = url.concat(USER + USER_ID)
    			.concat("images?offset=" + String.valueOf(0))
    			.concat("&limit=" + String.valueOf(2));
    	try {
			JSONObject jsonobject = new JSONObject(getFromHttp(url));
			JSONArray jsonArray = jsonobject.getJSONArray(ACTIVITIES_KEY);
			JSONObject obj = jsonArray.getJSONObject(0);
			JSONObject user = obj.getJSONObject("user");
			avatarUrl = user.getString("avatar_url");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return avatarUrl;
    }

}