package com.zt.android.utils.utils;


import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;

import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created on 2018/2/1.
 * Email: tonyzhang@tcl.com
 *
 * @author Tony zhang
 */

public class Utils {
    private static          android.content.Context mApplicationContext;
    static WeakReference<Activity> sTopActivityWeakRef;
    static List<Activity> sActivityList = new LinkedList<>();
    private static volatile int                     mNumActivities;

    private Utils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    private static Application.ActivityLifecycleCallbacks mCallbacks = new Application.ActivityLifecycleCallbacks() {
        @Override
        public void onActivityCreated(Activity activity, Bundle bundle) {
            sActivityList.add(activity);
            setTopActivityWeakRef(activity);
        }

        @Override
        public void onActivityStarted(Activity activity) {
            mNumActivities++;
            setTopActivityWeakRef(activity);
        }

        @Override
        public void onActivityResumed(Activity activity) {
            setTopActivityWeakRef(activity);
        }

        @Override
        public void onActivityPaused(Activity activity) {

        }

        @Override
        public void onActivityStopped(Activity activity) {
            mNumActivities--;
            if (mNumActivities < 0) mNumActivities = 0;
        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            sActivityList.remove(activity);
        }
    };

    public static void init(@NonNull final Application context) {
        mApplicationContext = context;
        context.registerActivityLifecycleCallbacks(mCallbacks);
    }

    public static android.content.Context getAppContext() {
        if (mApplicationContext == null) {
            throw new NullPointerException("Please init context in you Application");
        }
        return mApplicationContext;
    }

    private static void setTopActivityWeakRef(Activity activity) {
        if (sTopActivityWeakRef == null || !activity.equals(sTopActivityWeakRef.get())) {
            sTopActivityWeakRef = new WeakReference<>(activity);
        }
    }

    public static boolean isAppInBackground() {
        return 0 == mNumActivities;
    }

    private static Boolean isDebug = null;

    public static boolean isDebug() {
        return isDebug == null ? false : isDebug.booleanValue();
    }

    /**
     * Sync lib debug with app's debug value. Should be called in module Application
     *
     * @param context
     */
    public static void syncIsDebug(Context context) {
        if (isDebug == null) {
            isDebug = context.getApplicationInfo() != null &&
                    (context.getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        }
    }

    public static void quitApp() {
        Iterator<Activity> iterator = sActivityList.iterator();
        while (iterator.hasNext()) {
            iterator.next().finish();
        }
    }


}
