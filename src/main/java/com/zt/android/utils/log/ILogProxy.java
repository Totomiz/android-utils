package com.zt.android.utils.log;

/**
 * Created on 2017/12/28.
 * Email: tonyzhang@tcl.com
 *
 * @author Tony zhang
 */

public interface ILogProxy {
    void i(String tag, Object... objs);
    void v(String tag, Object... objs);
    void d(String tag, Object... objs);
    void w(String tag, Object... objs);
    void e(String tag, Object... objs);
    void setShowPlace(boolean isShow);
}
