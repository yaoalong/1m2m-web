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
     * 位置对应的停车位
     */
    public static final ConcurrentHashMap<String, Boolean> parkingCondition = new ConcurrentHashMap<>();
    /**
     * 设备对应的containerURI
     */
    public static final ConcurrentHashMap<String, Machine> cntMapMachine = new ConcurrentHashMap<>();
    /**
     * 位置对应的machine
     */
    public static final ConcurrentHashMap<String, Machine> positionMapMachine = new ConcurrentHashMap<>();
    /**
     * 位置对应的传感器
     */
    public static final ConcurrentHashMap<String, AbstractSensor> positionMapSensor = new ConcurrentHashMap<>();
    //位置对应的防盗器
    public static final ConcurrentHashMap<String, Machine> positionMapAntiTheft = new ConcurrentHashMap<>();
}
