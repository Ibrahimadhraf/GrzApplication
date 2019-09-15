package com.codesroots.osamaomar.grz.models.helper;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks2;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.Window;
import android.view.WindowManager;

import com.codesroots.osamaomar.grz.R;

public class AppLifeCycleHandler implements Application.ActivityLifecycleCallbacks, ComponentCallbacks2 {

    private AppLifeCycleCallback appLifeCycleCallback;

    private boolean appInForeground;

    AppLifeCycleHandler(AppLifeCycleCallback appLifeCycleCallback) {
        this.appLifeCycleCallback = appLifeCycleCallback;
    }

    @Override
    public void onActivityResumed(Activity activity) {
        if (!appInForeground) {
            appInForeground = true;
            appLifeCycleCallback.onAppForeground();
        }
    }

    @Override
    public void onTrimMemory(int i) {
        if (i == ComponentCallbacks2.TRIM_MEMORY_UI_HIDDEN) {
            appInForeground = false;
            appLifeCycleCallback.onAppBackground();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onActivityCreated(Activity activity, Bundle bundle) {
        Window window = activity.getWindow();
// clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
// finally change the color
        window.setStatusBarColor(activity.getResources().getColor(R.color.colorPrimary));

    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }

    @Override
    public void onConfigurationChanged(Configuration configuration) {

    }

    @Override
    public void onLowMemory() {

    }

    interface AppLifeCycleCallback {

        void onAppBackground();

        void onAppForeground();
    }
}