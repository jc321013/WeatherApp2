package com.example.jc321013.weatherapp;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by jc321013 on 8/04/2017.
 */

public class DownloadTask extends AsyncTask<String, Void,String> {




    @Override
    protected String doInBackground(String... urls) {

        String result = "";
        URL url;
        HttpURLConnection urlConnection = null;



        try {
            url = new URL(urls[0]);

            urlConnection = (HttpURLConnection) url.openConnection();

            InputStream in = urlConnection.getInputStream();

            InputStreamReader reader = new InputStreamReader(in);

            int data = reader.read();

            while (data != -1) {
                char current = (char) data;

                result += current;

                data = reader.read();
            }

            return result;


//        } catch (MalformedURLException e){
//            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        try {
            JSONObject jsonObject = new JSONObject(result);

            JSONObject weatherData = new JSONObject(jsonObject.getString("main"));

            Double temperature = Double.parseDouble(weatherData.getString("temp"));

            int temperatureInteger = (int) (temperature * 1.8-273.15);

            String placeName = jsonObject.getString("name");

            MainActivity.temperatureTextView.setText(String.valueOf(temperatureInteger));

            MainActivity.placeTextView.setText(placeName);





        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
