package ninja.paranoidandroid.volleytest;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by anton on 17.01.17.
 */

public class VolleySingelton {

    private static VolleySingelton mInstance;
    private RequestQueue mRequestQueue;
    private Context mContext;

    private VolleySingelton(Context context){
        mContext = context;
        getRequestQueue();
    }


    public RequestQueue getRequestQueue(){


        if(mRequestQueue == null){
            mRequestQueue = Volley.newRequestQueue(mContext);
        }

        return mRequestQueue;
    }

    public static synchronized VolleySingelton getInstance(Context context){

        if(mInstance == null){
            mInstance = new VolleySingelton(context);
        }

        return mInstance;
    }

    public <T> void addToRequestQueue(Request<T> request){
        mRequestQueue.add(request);
    }

}
