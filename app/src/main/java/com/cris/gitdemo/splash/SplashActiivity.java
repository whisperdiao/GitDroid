package com.cris.gitdemo.splash;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.cris.gitdemo.R;
import com.cris.gitdemo.commons.ActivityUtils;

import butterknife.ButterKnife;

public class SplashActiivity extends AppCompatActivity {

    private ActivityUtils activityUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityUtils = new ActivityUtils(this);
        //        CurrentUser.clear();
        setContentView(R.layout.activity_splash);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
    }


}
