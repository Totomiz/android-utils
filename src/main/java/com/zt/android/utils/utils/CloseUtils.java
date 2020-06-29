package com.zt.android.utils.utils;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by Tony.Zhang at 2017/11/16.
 *
 */

public class CloseUtils {

    /**
     * 关闭IO
     *
     * @param closeables closeables
     */
    public static void closeIO(final Closeable... closeables) {
        if (closeables == null) return;
        for (Closeable closeable : closeables) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
