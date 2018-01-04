package com.login_signup_screendesign_demo;

/**
 * Created by Mr.Daxesh on 12/25/2017.
 */

public class GetDataAdapter {
    public String ImageServerUrl;
    public String username;
    public String password;



    public String getImageServerUrl() {
        return ImageServerUrl;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setImageServerUrl(String imageServerUrl) {
        ImageServerUrl = imageServerUrl;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
