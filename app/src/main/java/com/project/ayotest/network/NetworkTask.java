package com.project.ayotest.network;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;


import com.project.ayotest.interfaces.ResponseHandler;

import java.io.IOException;
import java.net.URL;

public class NetworkTask extends AsyncTask<URL, Void, String> {

    private ResponseHandler mResponseHandler;
    private String mType;
    private ProgressBar mLoadingIndicator;

    public NetworkTask(ProgressBar loadingIndicator, ResponseHandler responseHandler) {
        mLoadingIndicator = loadingIndicator;
        mResponseHandler = responseHandler;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        if(mLoadingIndicator != null)
            mLoadingIndicator.setVisibility(View.VISIBLE);
    }

    @Override
    protected String doInBackground(URL... params) {
        URL url = params[0];
        String response = null;
        try {
            response = NetworkUtils.getResponseFromHttpURL(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    @Override
    protected void onPostExecute(String s) {
        if(mLoadingIndicator != null && mLoadingIndicator.isShown())
            mLoadingIndicator.setVisibility(View.GONE);

        if(s != null && !s.equals("")) {
            mResponseHandler.handleResponse(s);
        }
    }
}
