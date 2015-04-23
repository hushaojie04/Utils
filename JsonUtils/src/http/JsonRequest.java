package com.azt.com.azt.http;

import org.json.JSONObject;

/**
 * Created by Administrator on 2015/4/20.
 */
public abstract class JsonRequest<T> extends Request<T> {
    protected Response.Listener<T> mListener;

    public JsonRequest(int method, String url) {
        super(method, url);
    }

    public void setListener(Response.Listener<T> listener) {
        mListener = listener;
    }

}
