package com.login_signup_screendesign_demo;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.LruCache;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Viewdata extends AppCompatActivity {
    String url = "http://www.spelectronics.esy.es/photo_upload/fetch.php";
    final String tag = this.getClass().getSimpleName();

    ArrayList<HashMap<String, String>> arrayList;
    ListAdapter adapter;
    ListView listview = null;
    ImageView iv;
    private NetworkImageView mNetworkImageView;
    private ImageLoader mImageLoader;

    public static final String user_name = "user_name";
    public static final String password = "password";
    public static final String image = "image";
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewdata);
         final String iurl = "http://goo.gl/0rkaBz";

         listview = (ListView) findViewById(R.id.list_item);
        iv=(ImageView)findViewById(R.id.imageView);

        readdata();

    }


    private void readdata() {


        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try{
                    {
                        JSONArray ja =response.getJSONArray("mydata");
                        for(int i = 0; i < ja.length(); i++){
                            JSONObject jobj = ja.getJSONObject(i);
                            HashMap<String, String> item = new HashMap<>();
                            item.put(user_name, jobj.getString(user_name));
                            item.put(password, jobj.getString(password));
                            item.put(image, jobj.getString(image));

                            arrayList.add(item);

                        }
                        String[] from = {user_name, password,image};
                        int[] to = {R.id.name, R.id.password,R.id.imageView};
                        adapter = new SimpleAdapter(getApplicationContext(), arrayList, R.layout.customlist, from, to);
                        listview.setAdapter(adapter);



                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(tag, String.valueOf(error));
            }
        });
        RequestQueue rq = Volley.newRequestQueue(this);
        rq.add(jor);
    }
    }

