package com.project.ayotest.utils;

import com.project.ayotest.model.Weather;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by chetan on 9/5/17.
 */

public class AppUtils {

    private static final String WEATHER = "weather";
    private static final String MAIN = "main";
    private static final String TEMP = "temp";
    private static final String HUMIDITY = "humidity";
    private static final String TEMP_MIN = "temp_min";
    private static final String TEMP_MAX = "temp_max";

    public static void createWeatherObjectFromResponse(Weather currentWeatherObject, String response) {

        try {

            JSONObject responseObject = new JSONObject(response);
            JSONObject mainObject = responseObject.getJSONObject(MAIN);
            currentWeatherObject.setTemperature(mainObject.getDouble(TEMP));
            currentWeatherObject.setMinTemperature(mainObject.getDouble(TEMP_MIN));
            currentWeatherObject.setMaxTemperature(mainObject.getDouble(TEMP_MAX));
            currentWeatherObject.setHumidity(mainObject.getDouble(HUMIDITY));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
