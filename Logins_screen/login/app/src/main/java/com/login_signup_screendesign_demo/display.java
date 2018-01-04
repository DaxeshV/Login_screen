package com.login_signup_screendesign_demo;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.login_signup_screendesign_demo.R;

public class display extends AppCompatActivity {
TextView dis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        SharedPreferences preferences = getSharedPreferences("MYPREF",MODE_PRIVATE);
        String display  = preferences.getString("display","");

        dis=(TextView)findViewById(R.id.hello);
        dis.setText(display);

    }
}
