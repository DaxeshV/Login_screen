package com.login_signup_screendesign_demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Recyclerview extends AppCompatActivity {
    List<GetDataAdapter> GetDataAdapter1;

    RecyclerView recyclerView;

    RecyclerView.LayoutManager recyclerViewlayoutManager;

    RecyclerView.Adapter recyclerViewadapter;

    String GET_JSON_DATA_HTTP_URL = "http://www.spelectronics.esy.es/photo_upload/ImageJsonData.php";
    String JSON_IMAGE_TITLE_NAME = "user_name";
    String JSON_IMAGE_URL = "image";
    String Json_password = "password";

    JsonArrayRequest jsonArrayRequest ;

    RequestQueue requestQueue ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        GetDataAdapter1 = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.re1);

        recyclerView.setHasFixedSize(true);

        recyclerViewlayoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(recyclerViewlayoutManager);


        JSON_DATA_WEB_CALL();
    }

    private void JSON_DATA_WEB_CALL() {
        jsonArrayRequest = new JsonArrayRequest(GET_JSON_DATA_HTTP_URL,

                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        JSON_PARSE_DATA_AFTER_WEBCALL(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(jsonArrayRequest);
    }

    private void JSON_PARSE_DATA_AFTER_WEBCALL(JSONArray array) {
        for(int i = 0; i<array.length(); i++){
            GetDataAdapter GetDataAdapter2 = new GetDataAdapter();

            JSONObject json = null;

            try{
                json = array.getJSONObject(i);

                GetDataAdapter2.setUsername(json.getString(JSON_IMAGE_TITLE_NAME));
                GetDataAdapter2.setPassword(json.getString(Json_password));

                GetDataAdapter2.setImageServerUrl(json.getString(JSON_IMAGE_URL));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            GetDataAdapter1.add(GetDataAdapter2);
        }
        recyclerViewadapter = new RecyclerViewAdapter(GetDataAdapter1, this);

        recyclerView.setAdapter(recyclerViewadapter);

       recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
           @Override
           public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
               return false;
           }

           @Override
           public void onTouchEvent(RecyclerView rv, MotionEvent e) {
               Toast.makeText(Recyclerview.this, "Single Click on position        :",
                       Toast.LENGTH_SHORT).show();
           }



           @Override
           public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

           }
       });

    }
}
