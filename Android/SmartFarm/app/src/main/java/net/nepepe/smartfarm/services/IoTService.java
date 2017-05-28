package net.nepepe.smartfarm.services;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import net.nepepe.smartfarm.core.SensorData;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class IoTService extends SQLiteOpenHelper implements DataService {

    private static IoTService sInstance;
    private static final String DATABASE_NAME = "smartfarmdb";
    private static final int DATABASE_VERSION = 1;
    private String serviceBaseUrl;

    private IoTService(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        serviceBaseUrl = "http://192.168.1.207/SmartFarm_Backend/";
    }

    @Override
    public DataService getInstanse(Context context) {

       if (sInstance == null) {
            sInstance = new IoTService(context.getApplicationContext());
        }
        return sInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /*
    * getSensorData
    *
    * @return list of sensor data
    */
    public List<SensorData> getSensorData(String sensorType){
        List<SensorData> sensorDataList = new ArrayList<SensorData>();
        InputStream inputStream = null;
        String result = "";
        try {

            // create HttpClient
            HttpClient httpclient = new DefaultHttpClient();

            // make GET request
            String url = String.format("%s%s", serviceBaseUrl,"receive_signal");
            HttpResponse httpResponse = httpclient.execute(new HttpGet(url));

            // receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();

            // convert inputstream to string
            if(inputStream != null) {
                result = convertInputStreamToString(inputStream);

                // convert String to JSONObject
                JSONObject json = new JSONObject(result);

                JSONArray sensorDataJSONArray = json.getJSONArray("signals"); // get articles array

                //"outputValue": "2215522",
                //"sensorType": "Light diode",
                // "timeStamp": 1234567789

                if(sensorDataJSONArray != null){
                    for(int i=0, size = sensorDataJSONArray.length(); i < size; i++){
                       //sensorDataList.add(sensorDataJSONArray.getString(i));
                    }
                }

            }
            else
                return null;

        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }

        return null;
    }


    public boolean sendComand(String command){
        // Creating HTTP client
        HttpClient httpClient = new DefaultHttpClient();
        // Creating HTTP Post
        String url = String.format("%s%s", serviceBaseUrl,"ouput_command");
        HttpPost httpPost = new HttpPost(url);

        // Building post parameters
        // key and value pair
        List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(1);
        nameValuePair.add(new BasicNameValuePair("command", command));


        // Url Encoding the POST parameters
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));
        } catch (UnsupportedEncodingException e) {
            // writing error to Log
            e.printStackTrace();
        }

        // Making HTTP Request
        try {
            HttpResponse response = httpClient.execute(httpPost);

            // writing response to log
            Log.d("Http Response:", response.toString());
        } catch (ClientProtocolException e) {
            // writing exception to log
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            // writing exception to log
            e.printStackTrace();
            return false;
        }
        return true;
    }


    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;
    }

}
