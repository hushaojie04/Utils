package com.azt.com.azt.http;

import android.os.Handler;

import java.util.concurrent.Executor;

/**
 * Created by Administrator on 2015/4/17.
 */
public class MediatorDelivery {
    /**
     * 中介者
     */
    private final Executor mResponsePoster;

    public MediatorDelivery(final Handler handler) {
        mResponsePoster = new Executor() {
            @Override
            public void execute(Runnable run) {
                handler.post(run);
            }
        };
    }

    public void postResponse(Request<?> request, Response<?> response, Runnable runnable) {
        mResponsePoster.execute(new ResponseDeliveryRunnable(request, response, runnable));
    }

    private class ResponseDeliveryRunnable implements Runnable {
        private Response response;
        private Request request;
        private Runnable runnable;

        public ResponseDeliveryRunnable(Request request, Response response, Runnable runnable) {
            this.request = request;
            this.response = response;
            this.runnable = runnable;
        }

        @Override
        public void run() {
            request.deliverResponse(response.result);
            if (runnable != null)
                runnable.run();
        }
    }

}
