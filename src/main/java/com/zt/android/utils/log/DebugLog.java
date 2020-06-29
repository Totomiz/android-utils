package com.zt.android.utils.log;

/**
 * Created on 2017/12/28.
 * Email: tonyzhang@tcl.com
 * <p>
 * Generally, use the DebugLog.v() DebugLog.d() DebugLog.i() DebugLog.w() and DebugLog.e()
 * methods.
 * For some reason,You may want to see the log output regardless of the current build type of apk,use the DebugLog.rv()
 * DebugLog.rd() DebugLog.ri() DebugLog.rw() and DebugLog.re() methods.
 * </p>
 *
 * @author Tony zhang
 */

public class DebugLog {


    /**
     * this is an proxy has default implementation {@link DefaultLogProxy}
     */
    private static ILogProxy debugLog    = new DefaultLogProxy();
    /**
     * init this on your application if needed
     */
    private static boolean   isLogEnable = false;

    public static ILogProxy setLogEnable(boolean enable) {
        isLogEnable = enable;
        return debugLog;
    }

    public static ILogProxy setShowPlace(boolean isShow) {
        debugLog.setShowPlace(isShow);
        return debugLog;
    }

    public static void setLogProxy(ILogProxy logProxy) {
        debugLog = logProxy;
    }

    public static void i(String tag, Object... objs) {
        if (isLogEnable) {
            debugLog.i(tag, objs);
        }
    }

    public static void v(String tag, Object... objs) {
        if (isLogEnable) {
            debugLog.v(tag, objs);
        }
    }

    public static void d(String tag, Object... objs) {
        if (isLogEnable) {
            debugLog.d(tag, objs);
        }
    }

    public static void w(String tag, Object... objs) {
        if (isLogEnable) {
            debugLog.w(tag, objs);
        }
    }

    public static void e(String tag, Object... objs) {
        if (isLogEnable) {
            debugLog.e(tag, objs);
        }
    }

    public static void ri(String tag, Object... objs) {
        debugLog.i(tag, objs);
    }

    public static void rv(String tag, Object... objs) {
        debugLog.v(tag, objs);
    }

    public static void rd(String tag, Object... objs) {
        debugLog.d(tag, objs);
    }

    public static void rw(String tag, Object... objs) {
        debugLog.w(tag, objs);
    }

    public static void re(String tag, Object... objs) {
        debugLog.e(tag, objs);
    }
}
