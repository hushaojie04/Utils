package com.azt.com.azt.http;

import android.os.Handler;

/**
 * Created by Administrator on 2015/4/20.
 */
public class NetworkDispatcher extends Thread{
    Request mRequest;
    MediatorDelivery mMediatorDelivery;
    public void dispatch(Request request,Handler handler)
    {
        mRequest = request;
        mMediatorDelivery = new MediatorDelivery(handler);
        start();
    }
    @Override
    public void run() {
        super.run();
        if(mRequest!=null)
        {
            mRequest.postRequest();
            mMediatorDelivery.postResponse(mRequest,mRequest.parseNetworkResponse(),null);
        }
    }

}
