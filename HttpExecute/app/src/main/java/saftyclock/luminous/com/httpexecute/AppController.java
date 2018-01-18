package saftyclock.luminous.com.httpexecute;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.RetryPolicy;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;



public class AppController extends Application {

    public static final String TAG = AppController.class
            .getSimpleName();

    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;

    private static AppController mInstance;


    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

    }

    public static synchronized AppController getInstance() {
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }


    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
       /* req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);*/
        RequestQueue mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        int socketTimeout =20000;//30 seconds - change to what you want
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        req.setRetryPolicy(policy);
        System.out.println("mRequestQueue "+mRequestQueue.toString());
        mRequestQueue.add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }



    public <T> void addToRequestQueue10(Request<T> req, String tag)
    {
        RequestQueue mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        int socketTimeout =20000;//30 seconds - change to what you want
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        req.setRetryPolicy(policy);
        System.out.println("mRequestQueue "+mRequestQueue.toString());
        mRequestQueue.add(req);
    }

    public <T> void addToRequestQueue15(Request<T> req, String tag)
    {
        RequestQueue mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        int socketTimeout =25000;//30 seconds - change to what you want
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        req.setRetryPolicy(policy);
        System.out.println("mRequestQueue "+mRequestQueue.toString());
        mRequestQueue.add(req);
    }

    public <T> void addToRequestQueue20(Request<T> req, String tag)
    {
        RequestQueue mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        int socketTimeout =30000;//30 seconds - change to what you want
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        req.setRetryPolicy(policy);
        System.out.println("mRequestQueue "+mRequestQueue.toString());
        mRequestQueue.add(req);
    }
}
