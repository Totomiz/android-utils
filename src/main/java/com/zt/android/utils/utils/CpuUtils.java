package com.zt.android.utils.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ZT at 2017/11/16.
 *
 */

public class CpuUtils {


    //内核总数
    public static final String KEY_CPU_CORE_NUMBER = "CPU architecture";

    //cpu处理器架构 AArch64 Processor rev 2 (aarch64)
    public static final String KEY_CPU_PROCESSOR = "Processor";

    //硬件型号
    public static final String KEY_CPU_HARDWARE = "Hardware";

    /**
     * 获取/proc/cpuinfo 文件的CPU信息，按照键值存储到Map
     * <pre>
     * CPU architecture  : 8
     * Processor         : AArch64 Processor rev 2 (aarch64)
     * processor	     : 7
     * CPU revision	     : 2
     * CPU implementer	 : 0x41
     * Features	         : fp asimd aes pmull sha1 sha2 crc32 wp half thumb fastmult vfp edsp neon vfpv3 tlsi vfpv4 idiva idivt
     * Hardware	         : MT6755M
     * CPU part	         : 0xd03
     * CPU variant	     : 0x0
     * BogoMIPS	         : 26.00
     * </pre>
     *
     * @return key-value map
     */
    public static Map<String, String> getCpuInfoMap() {
        HashMap<String, String> map = new HashMap<>();
        String path = File.separator + "proc" + File.separator + "cpuinfo";
        FileReader fileReader = null;
        BufferedReader br = null;
        try {
            fileReader = new FileReader(path);
            br = new BufferedReader(fileReader);
            String line = null;
            while ((line = br.readLine()) != null) {
                if (line.contains(":")) {
                    String[] split = line.split(":");
                    if(split.length>1){
                        map.put(split[0].trim(), split[1].trim());
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            CloseUtils.closeIO(br, fileReader);
        }
        return map;
    }

    public static String getCpuInfoValue(Map<String, String> cpuMap, String key) {
        return cpuMap.get(key);
    }
}
