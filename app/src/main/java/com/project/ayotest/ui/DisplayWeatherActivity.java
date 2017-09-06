package com.project.ayotest.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;
import com.project.ayotest.R;
import com.project.ayotest.interfaces.ResponseHandler;
import com.project.ayotest.model.Weather;
import com.project.ayotest.network.NetworkTask;
import com.project.ayotest.network.NetworkUtils;
import com.project.ayotest.utils.AppUtils;

import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DisplayWeatherActivity extends AppCompatActivity implements ResponseHandler {

    private static final String LOG_TAG = DisplayWeatherActivity.class.getSimpleName();

    @BindView(R.id.tv_current_temp_value)
    TextView mCurrentTempTextView;

    @BindView(R.id.tv_min_temp_value)
    TextView mMinTempTextView;

    @BindView(R.id.tv_max_temp_value)
    TextView mMaxTempTextView;

    @BindView(R.id.tv_humidity_value)
    TextView mHumidityTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_weather);

        ButterKnife.bind(this);

        if(getIntent() != null && getIntent().hasExtra(getString(R.string.latlng))) {
            LatLng latLng = getIntent().getParcelableExtra(getString(R.string.latlng));
            Log.d(LOG_TAG, "Latitude is " + latLng.latitude + " Longitude is " + latLng.longitude);
            fetchWeatherDetails(latLng);
        }
    }

    private void fetchWeatherDetails(LatLng latLng) {
        URL url = NetworkUtils.buildGetWeatherURL(latLng);
        Log.d(LOG_TAG, url.toString());
        NetworkTask networkTask = new NetworkTask(null, this);
        networkTask.execute(url);
    }

    @Override
    public void handleResponse(String response) {
        Weather weather = new Weather();
        AppUtils.createWeatherObjectFromResponse(weather, response);
        mCurrentTempTextView.setText(String.valueOf(weather.getTemperature()));
        mMinTempTextView.setText(String.valueOf(weather.getMinTemperature()));
        mMaxTempTextView.setText(String.valueOf(weather.getMaxTemperature()));
        mHumidityTextView.setText(String.valueOf(weather.getHumidity()));
    }
}
