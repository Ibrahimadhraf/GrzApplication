package com.codesroots.osamaomar.grz.models.helper;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;


public class MyApplication extends Application implements  AppLifeCycleHandler.AppLifeCycleCallback{

    public static final String TAG = MyApplication.class.getSimpleName();
    private static MyApplication mInstance;
    public static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        context = getApplicationContext();
        mInstance = this;
        PreferenceHelper preferenceHelper =new PreferenceHelper(context);

        AppLifeCycleHandler appLifeCycleHandler = new AppLifeCycleHandler(this);
        registerActivityLifecycleCallbacks(appLifeCycleHandler);
        registerComponentCallbacks(appLifeCycleHandler);

//        PreferenceHelper preferenceHelper=new PreferenceHelper(mInstance);
//        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
//                .setDefaultFontPath("fonts/JF-Flat-Regular.ttf")
//                .setFontAttrId(R.attr.fontPath)
//                .build()
//        );
    }

    public static synchronized MyApplication getInstance() {
        return mInstance;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }


    @Override
    public void onAppBackground() {

    }

    @Override
    public void onAppForeground() {

    }
}

