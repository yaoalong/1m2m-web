package lab.msrs.web.util;

import lab.mars.m2m.reality.pojo.Machine;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Author:yaoalong.
 * Date:2016/6/16.
 * Email:yaoalong@foxmail.com
 */
public class NotificationUtils {
    public static AtomicLong zxid = new AtomicLong(0);

    public static ConcurrentHashMap<Long, Long> zxidMapStartTime = new ConcurrentHashMap<>();
    /**
     * 设备对应的containerURI
     */
    public static ConcurrentHashMap<String, Machine> cntMapMachine = new ConcurrentHashMap<>();
}
