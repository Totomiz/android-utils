package com.zt.android.utils.utils;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

/**
 * Created by Tony.Zhang at 2017/11/16.
 * Email: tonyzhang@tcl.com
 */

public final class NetWorkUtils {

    private static final int TYPE_NET_WORK_DISABLED = 99;
    private static final int TYPE_WIFI = 1;
    private static final int TYPE_UNKNOWN = 0;
    private static final int TYPE_3G = 3;
    private static final int TYPE_2G = 2;
    private static final int TYPE_4G = 4;
    private static final int TYPE_5G = 5;

    /**
     * 打开网络设置界面
     */
    public static void openWirelessSettings(Context context) {
        context.startActivity(new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }

    /**
     * 获取活动网络信息
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>}</p>
     *
     * @param context 最好是应用上下文
     * @return NetworkInfo
     */
    private static NetworkInfo getActiveNetworkInfo(Context context) {
        return ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
    }

    /**
     * 判断网络是否可用
     *
     * @param context 上下文
     * @return 网络状态是否可用
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isAvailable()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断网络是否连接
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>}</p>
     *
     * @return {@code true}: 是<br>{@code false}: 否
     */
    public static boolean isConnected(Context context) {
        NetworkInfo info = getActiveNetworkInfo(context);
        return info != null && info.isConnected();
    }

    /**
     * 获取当前网络类型
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>}</p>
     *
     * @return 网络类型
     * <ul>
     * <li>{@link NetWorkUtils#TYPE_WIFI             } </li>
     * <li>{@link NetWorkUtils#TYPE_4G               } </li>
     * <li>{@link NetWorkUtils#TYPE_3G               } </li>
     * <li>{@link NetWorkUtils#TYPE_2G               } </li>
     * <li>{@link NetWorkUtils#TYPE_NET_WORK_DISABLED} </li>
     * <li>{@link NetWorkUtils#TYPE_UNKNOWN          } </li>
     * </ul>
     */
    public static int getNetWorkType(Context context) {
        int netType = TYPE_UNKNOWN;

        NetworkInfo info = getActiveNetworkInfo(context);
        if (info != null && info.isAvailable()) {

            if (info.getType() == ConnectivityManager.TYPE_WIFI) {
                netType = TYPE_WIFI;
            } else if (info.getType() == ConnectivityManager.TYPE_MOBILE) {
                switch (info.getSubtype()) {

                    case TelephonyManager.NETWORK_TYPE_GPRS:
                    case TelephonyManager.NETWORK_TYPE_CDMA:
                    case TelephonyManager.NETWORK_TYPE_EDGE:
                    case TelephonyManager.NETWORK_TYPE_1xRTT:
                    case TelephonyManager.NETWORK_TYPE_IDEN:
                    case TelephonyManager.NETWORK_TYPE_GSM:
                        netType = TYPE_2G;
                        break;

                    case TelephonyManager.NETWORK_TYPE_TD_SCDMA:
                    case TelephonyManager.NETWORK_TYPE_EVDO_A:
                    case TelephonyManager.NETWORK_TYPE_UMTS:
                    case TelephonyManager.NETWORK_TYPE_EVDO_0:
                    case TelephonyManager.NETWORK_TYPE_HSDPA:
                    case TelephonyManager.NETWORK_TYPE_HSUPA:
                    case TelephonyManager.NETWORK_TYPE_HSPA:
                    case TelephonyManager.NETWORK_TYPE_EVDO_B:
                    case TelephonyManager.NETWORK_TYPE_EHRPD:
                    case TelephonyManager.NETWORK_TYPE_HSPAP:
                        netType = TYPE_3G;
                        break;

                    case TelephonyManager.NETWORK_TYPE_IWLAN:
                    case TelephonyManager.NETWORK_TYPE_LTE:
                        netType = TYPE_4G;
                        break;

                    default:
                        String subtypeName = info.getSubtypeName();
                        if (subtypeName.equalsIgnoreCase("TD-SCDMA")
                                || subtypeName.equalsIgnoreCase("WCDMA")
                                || subtypeName.equalsIgnoreCase("CDMA2000")) {
                            netType = TYPE_3G;
                        } else {
                            netType = TYPE_UNKNOWN;
                        }
                        break;
                }
            } else {
                netType = TYPE_NET_WORK_DISABLED;
            }
        }
        return netType;
    }

    /**
     * 获取网络可用状态
     *
     * @param context context
     * @return true网路类型未知 false 已知可用网路
     */
    public static boolean isUnknownNetWork(Context context) {
        int netWorkType = getNetWorkType(context);
        return netWorkType <= 0 || netWorkType >= 5;
    }
}
