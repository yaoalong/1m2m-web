package lab.msrs.web.util;

import lab.mars.m2m.reality.pojo.Machine;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Author:yaoalong.
 * Date:2016/6/16.
 * Email:yaoalong@foxmail.com
 */
public class NotificationUtils {
    public static final int INIT = 0;
    public static final int IS_NOT_INIT = 1;

    /**
     * 设备对应的containerURI
     */
    public static ConcurrentHashMap<String, Machine> cntMapMachine = new ConcurrentHashMap<>();
}
