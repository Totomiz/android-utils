package com.zt.android.utils.log;

/**
 * Created by ZT on 2017/12/28.
 */

public interface ILogProxy {
    void i(String tag, Object... objs);
    void v(String tag, Object... objs);
    void d(String tag, Object... objs);
    void w(String tag, Object... objs);
    void e(String tag, Object... objs);
    void setShowPlace(boolean isShow);
}
