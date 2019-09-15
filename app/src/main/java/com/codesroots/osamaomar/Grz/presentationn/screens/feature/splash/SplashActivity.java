package com.codesroots.osamaomar.grz.presentationn.screens.feature.splash;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.codesroots.osamaomar.grz.R;
import com.codesroots.osamaomar.grz.models.helper.ResourceUtil;
import com.codesroots.osamaomar.grz.presentationn.screens.feature.home.mainactivity.MainActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(() -> {

            if (ResourceUtil.getCurrentLanguage(SplashActivity.this).matches("ar"))
                ResourceUtil.changeLang("ar",SplashActivity.this);
            else
                ResourceUtil.changeLang("en",SplashActivity.this);

            Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(mainIntent);
            finish();
        }, 5000);
    }
}
