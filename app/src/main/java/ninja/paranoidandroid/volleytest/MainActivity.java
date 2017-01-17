package ninja.paranoidandroid.volleytest;

import android.app.DownloadManager;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private ImageView mImageView;
    private Button mButton;
    private Button mGetImgButton;
    private Button mGetJsonButton;
    private Button mPostinfoButton;
    String serverUrl = "http://192.168.0.103/volley/test.php";
    String imgUrl = "http://192.168.0.103/volley/android.png";
    String jsonUrl = "http://192.168.0.103/volley/json.txt";
    String postParametersUrl = "http://192.168.0.103/volley/post_parameters.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initUI();
        //setmButtonOnClickListener();
        //setmGetImgButtonOnClickListener();
        //setmGetJsonButtonOnClickListener();
        setmPostinfoButtonOnClickListener();

    }

    private void initUI(){

        mButton = (Button) findViewById(R.id.b_activity_main_button);
        mGetImgButton = (Button) findViewById(R.id.b_activity_main_get_img);
        mGetJsonButton = (Button) findViewById(R.id.b_activity_main_get_json);
        mPostinfoButton = (Button) findViewById(R.id.b_activity_main_post_info);
        mImageView = (ImageView) findViewById(R.id.iv_activity_main_image);
    }

    private void setmButtonOnClickListener(){
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //final RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);

                StringRequest stringRequest =  new StringRequest(Request.Method.POST, serverUrl,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                Log.i("MainActivity", "onResponse(), response is: " + response);
                                //requestQueue.stop();


                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {



                    }
                });
                //requestQueue.add(stringRequest);
                VolleySingelton.getInstance(MainActivity.this).addToRequestQueue(stringRequest);
            }
        });
    }


    private void setmGetImgButtonOnClickListener(){

        mGetImgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //final RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
                ImageRequest imageRequest = new ImageRequest(imgUrl, new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {

                        if(response != null){
                            Log.i("MainActivity", "onResponse(), response is not null");
                            mImageView.setImageBitmap(response);
                        }else{
                            Log.i("MainActivity", "onResponse(), response is null");
                        }


                    }
                }, 0, 0, ImageView.ScaleType.CENTER_CROP, null, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                //requestQueue.add(imageRequest);
                VolleySingelton.getInstance(MainActivity.this).addToRequestQueue(imageRequest);
            }
        });
    }


    private void setmGetJsonButtonOnClickListener(){

        mGetJsonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, jsonUrl,
                        null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            String name = response.getString("name");
                            Log.i("MainActivity", "onResponse(), name is: " + name);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                VolleySingelton.getInstance(MainActivity.this).addToRequestQueue(jsonObjectRequest);
            }
        });
    }


    private void setmPostinfoButtonOnClickListener(){

        mPostinfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StringRequest stringRequest = new StringRequest(Request.Method.POST, postParametersUrl,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Log.i("MainActivity", "onResponce(), result is: " + response);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {

                        Map<String, String> params = new HashMap<String, String>();
                        params.put("name", "Buster");

                        return params;
                    }
                };
                VolleySingelton.getInstance(MainActivity.this).addToRequestQueue(stringRequest);
            }
        });

    }
}
