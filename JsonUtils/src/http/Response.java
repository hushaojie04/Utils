package com.azt.com.azt.http;

/**
 * Created by Administrator on 2015/4/17.
 */
public class Response<T> {
    //回调网络数据给用户
    public interface Listener<T>
    {
        void onResponse(T response);
    }
    //数据
    public T result;
}
