package lab.msrs.web.util;

import lab.mars.m2m.reality.pojo.AbstractSensor;
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

    /**
     * 位置对应的machine
     */
    public static ConcurrentHashMap<String, Machine> positionMapMachine = new ConcurrentHashMap<>();

    public static ConcurrentHashMap<String, AbstractSensor> positionMapSensor = new ConcurrentHashMap<>();
    public static ConcurrentHashMap<String, Machine> positionMapAntiTheft = new ConcurrentHashMap<>();
}
