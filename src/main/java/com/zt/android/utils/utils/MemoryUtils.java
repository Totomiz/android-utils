package com.zt.android.utils.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.text.format.Formatter;

import java.io.File;

/**
 * Created by Tony.Zhang at 2017/11/16.
 * Email: tonyzhang@tcl.com
 */

public class MemoryUtils {

    /**
     * @param context 上下文
     * @return 总共的内存（已转换大小）以M, G为单位的容量
     */
    public static String getRAMTotalSize(Context context) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        manager.getMemoryInfo(memoryInfo);
        return Formatter.formatFileSize(context, memoryInfo.totalMem);
    }

    /**
     * @param context 上下文
     * @return 总共的内存 （未转换大小）
     */
    public static long getRAMTotalSizeLong(Context context) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        manager.getMemoryInfo(memoryInfo);
        return memoryInfo.totalMem;
    }

    /**
     * @param context 上下文
     * @return 可用的内存（已转换大小）以M, G为单位的容量
     */
    public static String getAvailableRAMSize(Context context) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        manager.getMemoryInfo(memoryInfo);
        return Formatter.formatFileSize(context, memoryInfo.availMem);
    }


    /**
     * @param context 上下文
     * @return 总共的内存（未转换大小）
     */
    public static long getAvailableRAMSizeLong(Context context) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        manager.getMemoryInfo(memoryInfo);
        return memoryInfo.availMem;
    }


    /**
     * 判断sd卡是否可用
     */
    public static boolean isExternalStorageAvailable() {
        return Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
    }

    /**
     * 获取手机内部存储空间
     *
     * @param context
     * @return 以M, G为单位的容量
     */
    public static String getInternalMemorySize(Context context) {
        File file = Environment.getDataDirectory();
        StatFs statFs = new StatFs(file.getPath());
        long blockSizeLong = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR2) {
            blockSizeLong = statFs.getBlockSizeLong();
        } else {
            blockSizeLong = statFs.getBlockSize();
        }
        long blockCountLong = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR2) {
            blockCountLong = statFs.getBlockCountLong();
        } else {
            blockCountLong = statFs.getBlockCount();
        }
        long size = blockCountLong * blockSizeLong;
        return Formatter.formatFileSize(context, size);
    }

    /**
     * 获取手机内部存储空间
     *
     * @return 未转换单位的容量byte
     */
    public static long getInternalMemorySizeLong() {
        File file = Environment.getDataDirectory();
        StatFs statFs = new StatFs(file.getPath());
        long blockSizeLong = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR2) {
            blockSizeLong = statFs.getBlockSizeLong();
        } else {
            blockSizeLong = statFs.getBlockSize();
        }
        long blockCountLong = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR2) {
            blockCountLong = statFs.getBlockCountLong();
        } else {
            blockCountLong = statFs.getBlockCount();
        }
        return blockCountLong * blockSizeLong;
    }



    /**
     * 获取手机内部可用存储空间
     *
     * @param context
     * @return 以M, G为单位的容量
     */
    public static String getAvailableInternalMemorySize(Context context) {
        File file = Environment.getDataDirectory();
        StatFs statFs = new StatFs(file.getPath());
        long availableBlocksLong = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR2) {
            availableBlocksLong = statFs.getAvailableBlocksLong();
        } else {
            availableBlocksLong = statFs.getAvailableBlocks();
        }
        long blockSizeLong = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR2) {
            blockSizeLong = statFs.getBlockSizeLong();
        } else {
            blockSizeLong = statFs.getBlockSize();
        }
        return Formatter.formatFileSize(context, availableBlocksLong
                * blockSizeLong);
    }

    /**
     * 获取手机内部可用存储空间
     *
     * @return 未转换单位的容量byte
     */
    public static long getAvailableInternalMemorySizeLong() {
        File file = Environment.getDataDirectory();
        StatFs statFs = new StatFs(file.getPath());
        long availableBlocksLong = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR2) {
            availableBlocksLong = statFs.getAvailableBlocksLong();
        } else {
            availableBlocksLong = statFs.getAvailableBlocks();
        }
        long blockSizeLong = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR2) {
            blockSizeLong = statFs.getBlockSizeLong();
        } else {
            blockSizeLong = statFs.getBlockSize();
        }
        return availableBlocksLong * blockSizeLong;
    }

    /**
     * 获取手机外部存储空间
     *
     * @param context
     * @return 以M, G为单位的容量
     */
    public static String getExternalMemorySize(Context context) {
        File file = Environment.getExternalStorageDirectory();
        StatFs statFs = new StatFs(file.getPath());
        long blockSizeLong = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR2) {
            blockSizeLong = statFs.getBlockSizeLong();
        } else {
            blockSizeLong = statFs.getBlockSize();
        }
        long blockCountLong = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR2) {
            blockCountLong = statFs.getBlockCountLong();
        } else {
            blockCountLong = statFs.getBlockCount();
        }
        return Formatter
                .formatFileSize(context, blockCountLong * blockSizeLong);
    }


    /**
     * 获取手机外部存储空间
     *
     * @return 未转换单位的容量byte
     */
    public static long getExternalMemorySizeLong() {
        File file = Environment.getExternalStorageDirectory();
        StatFs statFs = new StatFs(file.getPath());
        long blockSizeLong = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR2) {
            blockSizeLong = statFs.getBlockSizeLong();
        } else {
            blockSizeLong = statFs.getBlockSize();
        }
        long blockCountLong = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR2) {
            blockCountLong = statFs.getBlockCountLong();
        } else {
            blockCountLong = statFs.getBlockCount();
        }
        return  blockCountLong * blockSizeLong;
    }


    /**
     * 获取手机外部可用存储空间
     *
     * @param context
     * @return 以M, G为单位的容量
     */
    public static String getAvailableExternalMemorySize(Context context) {
        File file = Environment.getExternalStorageDirectory();
        StatFs statFs = new StatFs(file.getPath());
        long availableBlocksLong = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR2) {
            availableBlocksLong = statFs.getAvailableBlocksLong();
        } else {
            availableBlocksLong = statFs.getAvailableBlocks();
        }
        long blockSizeLong = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR2) {
            blockSizeLong = statFs.getBlockSizeLong();
        } else {
            blockSizeLong = statFs.getBlockSize();
        }
        return Formatter.formatFileSize(context, availableBlocksLong
                * blockSizeLong);
    }


    /**
     * 获取手机外部可用存储空间
     *
     * @return 未转换单位的容量byte
     */
    public static long getAvailableExternalMemorySizeLong() {
        File file = Environment.getExternalStorageDirectory();
        StatFs statFs = new StatFs(file.getPath());
        long availableBlocksLong = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR2) {
            availableBlocksLong = statFs.getAvailableBlocksLong();
        } else {
            availableBlocksLong = statFs.getAvailableBlocks();
        }
        long blockSizeLong = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR2) {
            blockSizeLong = statFs.getBlockSizeLong();
        } else {
            blockSizeLong = statFs.getBlockSize();
        }
        return availableBlocksLong * blockSizeLong;
    }
}
