package com.azt.com.azt.http;

import com.azt.Utils.LogUtils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

/**
 * Created by Administrator on 2015/4/17.
 */
public class HttpClientConn {
    protected final HttpClient mClient;
    private final static String HEADER_CONTENT_TYPE = "Content-Type";

    public HttpClientConn(HttpClient client) {
        mClient = client;
    }

    public HttpResponse performRequest(Request<?> request, Map<String, String> additionalHeaders) throws IOException {
        HttpUriRequest httpRequest = createHttpRequest(request, additionalHeaders);
//        addHeaders(httpRequest, additionalHeaders);
//        addHeaders(httpRequest, request.getHeaders());
//        onPrepareRequest(httpRequest);
        HttpParams httpParams = httpRequest.getParams();
//        int timeoutMs = request.getTimeoutMs();
        // TODO: Reevaluate this connection timeout based on more wide-scale
        // data collection and possibly different for wifi vs. 3G.
        HttpConnectionParams.setConnectionTimeout(httpParams, 5000);
//        HttpConnectionParams.setSoTimeout(httpParams, timeoutMs);
        return mClient.execute(httpRequest);
    }


    /**
     * Creates the appropriate subclass of HttpUriRequest for passed in request.
     */
    /* protected */
    static HttpUriRequest createHttpRequest(Request<?> request,
                                            Map<String, String> additionalHeaders) {
        switch (request.getMethod()) {
            case Request.Method.DEPRECATED_GET_OR_POST:
                return null;
            case Request.Method.GET:
                return new HttpGet(request.getURL());
            case Request.Method.DELETE:
                return null;
            case Request.Method.POST: {
                HttpPost postRequest = new HttpPost(request.getURL());
//                postRequest.addHeader(HEADER_CONTENT_TYPE, request.getBodyContentType());
                return postRequest;
            }
            case Request.Method.PUT:
            case Request.Method.HEAD:
            case Request.Method.OPTIONS:
            case Request.Method.TRACE:
            case Request.Method.PATCH:
                return null;
            default:
                throw new IllegalStateException("Unknown request method.");
        }
    }
}
