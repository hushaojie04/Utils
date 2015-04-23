package com.azt.com.azt.http;

import java.io.IOException;

/**
 * Created by Administrator on 2015/4/17.
 */
public abstract class Request<T> implements Network{
    /**
     * Supported request methods.
     */
    public interface Method {
        int DEPRECATED_GET_OR_POST = -1;
        int GET = 0;
        int POST = 1;
        int PUT = 2;
        int DELETE = 3;
        int HEAD = 4;
        int OPTIONS = 5;
        int TRACE = 6;
        int PATCH = 7;
    }

    /**
     * 请求URL
     */
    private String mURL ;
    /**
     * 请求方式
     */
    private int mMethod;
    private int mTimeoutMs;
    public Request(int method,String url)
    {
        mURL = url;
    }
    public String getURL()
    {
        return mURL;
    }
    public int getMethod()
    {
        return mMethod;
    }
//    public int getTimeoutMs()
//    {
//        return mTimeoutMs;
//    }
    abstract void deliverResponse(T response);
    abstract Response<T> parseNetworkResponse();

}
