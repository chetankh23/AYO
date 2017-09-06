package com.project.ayotest.network;

import android.net.Uri;

import com.google.android.gms.maps.model.LatLng;
import com.project.ayotest.BuildConfig;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class NetworkUtils {

    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/weather";
    private static final String LAT = "lat";
    private static final String LONG = "lon";
    private static final String UNITS = "units";
    private static final String IMPERIAL = "imperial";
    private static final String APPID = "appid";

    // Build URL to retrieve list of users.
    public static URL buildGetWeatherURL(LatLng latLng) {
        Uri builtUri = Uri.parse(BASE_URL).buildUpon()
                .appendQueryParameter(LAT, String.valueOf(latLng.latitude))
                .appendQueryParameter(LONG, String.valueOf(latLng.longitude))
                .appendQueryParameter(UNITS, IMPERIAL)
                .appendQueryParameter(APPID, BuildConfig.APP_ID)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    public static String getResponseFromHttpURL(URL url) throws IOException {
        URLConnection connection = url.openConnection();
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(10000);
        BufferedReader inputStream = null;
        try {
            inputStream = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            Scanner scanner = new Scanner(inputStream);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput)
                return scanner.next();
            else
                return null;
        } finally {
            if(inputStream != null)
                inputStream.close();
        }
    }
}
