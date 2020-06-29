package com.zt.android.utils.log;

import android.util.Log;

/**
 * Created by ZT on 2017/12/28.
 */

/*package*/class DefaultLogProxy implements ILogProxy {
    private static final int           STACK_LAYER    = 4;
    public static final  int           ASSERT         = Log.ASSERT;
    private static final String NULL           = "null";
    private static final StringBuilder STRING_BUILDER = new StringBuilder();
    private static final int           VERBOSE        = Log.VERBOSE;
    private static final int           DEBUG          = Log.DEBUG;
    private static final int           INFO           = Log.INFO;
    private static final int           WARN           = Log.WARN;
    private static final int           ERROR          = Log.ERROR;
    private static       boolean       SHOW_PLACE     = true;

    private static String msgToString(Object msg) {
        String str = NULL;
        if (msg != null) {
            if (msg instanceof Throwable) {
                str = Log.getStackTraceString((Throwable) msg);
            } else {
                str = msg.toString();
            }
        }
        return str;
    }

    private static String buildMsg(Object... msgs) {
        String str = NULL;
        if (msgs != null) {
            int len = msgs.length;
            if (len == 1) {
                if (msgs[0] != null) {
                    str = msgToString(msgs[0]);
                }
            } else if (len > 1) {
                synchronized (STRING_BUILDER) {
                    STRING_BUILDER.setLength(0);
                    for (Object s : msgs) {
                        STRING_BUILDER.append(msgToString(s));
                    }
                    str = STRING_BUILDER.toString();
                }
            }
        }
        if (SHOW_PLACE) {
            return str + getPlace();
        }
        return str;
    }

    private static String getPlace() {
        StackTraceElement[] stacks = new Throwable().getStackTrace();
        if (stacks.length <= STACK_LAYER) {
            return "";
        }
        return " at " +
                stacks[STACK_LAYER].getClassName() + "." +
                stacks[STACK_LAYER].getMethodName() + "(" +
                stacks[STACK_LAYER].getFileName() + ":" +
                stacks[STACK_LAYER].getLineNumber() + ")\n";
    }

    public void i(String tag, Object... msg) {
        Log.println(INFO, tag, buildMsg(msg));
    }

    public void v(String tag, Object... msg) {
        Log.println(VERBOSE, tag, buildMsg(msg));
    }

    public void d(String tag, Object... msg) {
        Log.println(DEBUG, tag, buildMsg(msg));
    }

    public void w(String tag, Object... msg) {
        Log.println(WARN, tag, buildMsg(msg));
    }

    public void e(String tag, Object... msg) {
        Log.println(ERROR, tag, buildMsg(msg));
    }

    @Override
    public void setShowPlace(boolean isShow) {
        DefaultLogProxy.SHOW_PLACE = isShow;
    }
}
