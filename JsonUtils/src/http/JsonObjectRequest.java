package com.azt.com.azt.http;

import android.os.Handler;
import android.os.Message;

import com.azt.Utils.LogUtils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/4/20.
 */
public class JsonObjectRequest extends JsonRequest<JSONObject> implements Network {
    HttpClientConn mHttpClientConn;
    JSONObject mJSONObject;

    public JsonObjectRequest(int method, String url) {
        super(method, url);
        mHttpClientConn = new HttpClientConn(new DefaultHttpClient());
    }
    public void postRequest() {
        try {
            HttpResponse mHttpResponse = mHttpClientConn.performRequest(this, null);
            HttpEntity mHttpEntity = mHttpResponse.getEntity();

            mJSONObject = new JSONObject(EntityUtils.toString(mHttpEntity));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    @Override
    void deliverResponse(JSONObject response) {
        if (mListener != null)
            mListener.onResponse(response);

    }

    @Override
    Response<JSONObject> parseNetworkResponse() {
        Response response = new Response();
        response.result = mJSONObject;
        return response;
    }
}