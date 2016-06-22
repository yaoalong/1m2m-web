package lab.mars.data;

import lab.mars.m2m.protocol.common.m2m_childResourceRef;
import lab.mars.m2m.protocol.resource.m2m_ContentInstance;
import lab.mars.m2m.protocol.resource.m2m_resource;
import lab.mars.m2m.reality.pojo.*;
import lab.mars.m2m.reflection.ResourceReflection;
import lab.mars.model.MachineBelongInformation;
import lab.mars.network.WebNetwork;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static lab.mars.mapper.MachineMapper.*;
import static lab.mars.model.MachineTypeEnum.*;
import static lab.msrs.web.util.NotificationUtils.*;

/**
 * Author:yaoalong.
 * Date:2016/6/16.
 * Email:yaoalong@foxmail.com
 */
public class DataGenerate extends WebNetwork {
    public static final int LIGHT_SENSOR_INDEX = 0;
    public static final int TEMPERATURE_SENSOR_INDEX = 1;
    public static final int LIGHT_INDEX = 2;
    public static final int AIRCONDITION_INDEX = 3;
    /**
     * 光线传感器
     */
    private static final int light_current_value = 0;
    private static final int light_low_value = 80;
    private static final int light_lowest_value = 0;
    private static final int light_high_value = 140;
    private static final int light_highest_value = 200;
    private static final int light_period = 2;
    private static final int light_increment_num = 20;
    /**
     * 温度传感器
     */
    private static final int temperature_current_value = 0;
    private static final int temperature_lowest_value = -20;
    private static final int temperature_low_value = 0;
    private static final int temperature_high_value = 25;
    private static final int temperature_highest_value = 40;
    private static final int temperature_period = 1;
    private static final int temperature_increment_num = 20;
    /**
     * 防盗传感器
     */
    private static final boolean antitheft_sensor_value = true;
    private static final int antitheft_sensor_period = 1;
    /**
     * 传感器
     */
    private static final boolean laser_sensor_value = true;
    private static final int laser_sensor_period = 5;
    //光线传感器0
    //空调传感器1
    //灯2
    //空调3
    Random random = new Random();

    public static void main(String args[]) throws Exception {
        long startTime = System.currentTimeMillis();
        new DataGenerate().generateData();
        System.out.println(System.currentTimeMillis() - startTime);
        new Thread(new MyRunnable()).start();
        System.in.read();
    }

    @Override
    public void handleNotify(m2m_childResourceRef ref) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(() -> {
            m2m_resource response = null;
            try {
                response = testRetrieve(ref.v, null, OK, SYNC);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            if (response instanceof m2m_ContentInstance) {
                m2m_ContentInstance cin = (m2m_ContentInstance) response;
                Object object = ResourceReflection.deserializeKryo(cin.con);
                if (object instanceof LightSensor) {
                    LightSensor lightSensor = (LightSensor) object;
                    cntMapMachine.get(lightSensor.getMachineUri()).create(lightSensor.getValue());
                } else if (object instanceof AntiTheftSensor) {
                    AntiTheftSensor antiTheftSensor = (AntiTheftSensor) object;
                    cntMapMachine.get(antiTheftSensor.getMachineUri()).create(antiTheftSensor.getValue());
                } else if (object instanceof TemperatureSensor) {
                    TemperatureSensor temperatureSensor = (TemperatureSensor) object;
                    cntMapMachine.get(temperatureSensor.getMachineUri()).create(temperatureSensor.getValue());
                }
            } else {
                //  System.out.println("classs:" + response.getClass());
            }
        });
    }

    public void generateData() throws Exception {
        init();
        Map<String, String> containerURI = new HashMap<>();
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(2);
        for (int q = 0; q < regionNumer; q++) {
            for (int i = 0; i < banNumber; i++) {
                for (int j = 0; j < floorNumber; j++) {
                    String aeUri = createSyncAEResource();//现在是每层楼是一个AE
                    for (int z = 0; z < apartmentNumber; z++) {

                        for (int y = 0; y < roomNumber; y++) {
                            for (int w = 0; w < 4; w++) {
                                String cntUri = createSyncContainer(aeUri);//创建一个container
                                String resourceId = q + "/" + i + "/" + j + "/" + z + "/" + y + "/" + w;
                                containerURI.put(resourceId, cntUri);
                                if (w == 2) {
                                    MachineBelongInformation machineBelongInformation = new MachineBelongInformation(q, i, j, z, LIGHT);
                                    machineBelongInformation.setResourceId(resourceId);
                                    Light light = new Light(light_low_value, false, this, cntUri, machineBelongInformation);//空调
                                    cntMapMachine.put(cntUri, light);//存储container和设备的映射关系
                                    positionMapMachine.put(resourceId, light);
                                }
                                if (w == 3) {
                                    MachineBelongInformation machineBelongInformation = new MachineBelongInformation(q, i, j, z, AIRCONDIION);
                                    machineBelongInformation.setResourceId(resourceId);
                                    AirConditioning airConditioning = new AirConditioning(temperature_low_value, temperature_high_value, false, this, cntUri, machineBelongInformation);//灯
                                    cntMapMachine.put(cntUri, airConditioning);
                                    positionMapMachine.put(resourceId, airConditioning);
                                }
                            }
                        }
                        //防盗传感器
                        String cntUri = createSyncContainer(aeUri);//创建一个container
                        containerURI.put(q + "/" + i + "/" + j + "/" + z + "/" + 0 + "/", cntUri);
                        //防盗报警器
                        cntUri = createSyncContainer(aeUri);//创建一个container
                        String resourceId = q + "/" + i + "/" + j + "/" + z + "/" + 1 + "/";
                        containerURI.put(resourceId, cntUri);
                        MachineBelongInformation machineBelongInformation = new MachineBelongInformation(q, i, j, z, ANTITHEFT);
                        machineBelongInformation.setResourceId(resourceId);
                        AntitheftAlarm antitheftAlarm = new AntitheftAlarm(false, this, cntUri, machineBelongInformation);
                        positionMapAntiTheft.put(resourceId, antitheftAlarm);
                        cntMapMachine.put(cntUri, antitheftAlarm);

                    }
                }
            }
        }
        for (int q = 0; q < regionNumer; q++) {
            for (int i = 0; i < banNumber; i++) {
                for (int j = 0; j < floorNumber; j++) {
                    for (int z = 0; z < apartmentNumber; z++) {
                        String containerURL = containerURI.get(q + "/" + i + "/" + j + "/" + z + "/" + 0 + "/");
                        createAsyncSubScriptions(containerURL);
                        for (int y = 0; y < roomNumber; y++) {
                            /**
                             * 创建不同的subscription
                             */
                            String resourceURI = q + "/" + i + "/" + j + "/" + z + "/" + y + "/";
                            for (int w = 0; w < 2; w++) {
                                containerURL = containerURI.get(resourceURI + w);
                                createAsyncSubScriptions(containerURL);
                            }
                            LightSensor lightSensor = new LightSensor(light_current_value, light_increment_num, light_lowest_value, light_highest_value, this, containerURI.get(resourceURI + 0), containerURI.get(resourceURI + 2), resourceURI + 0);
                            TemperatureSensor temperatureSensor = new TemperatureSensor(temperature_current_value, temperature_increment_num, temperature_lowest_value, temperature_highest_value, this, containerURI.get(resourceURI + 1), containerURI.get(resourceURI + 3), resourceURI + 1);
                            positionMapSensor.put(resourceURI + LIGHT_SENSOR_INDEX, lightSensor);
                            positionMapSensor.put(resourceURI + TEMPERATURE_SENSOR_INDEX, temperatureSensor);
                            executorService.scheduleAtFixedRate(lightSensor, 0
                                    , light_period * getRandom(), TimeUnit.SECONDS);
                            executorService.scheduleAtFixedRate(temperatureSensor, 1
                                    , light_period * getRandom(), TimeUnit.SECONDS);
                        }
                        executorService.scheduleAtFixedRate(new AntiTheftSensor(antitheft_sensor_value, this, containerURI.get(q + "/" + i + "/" + j + "/" + z + "/" + 0 + "/"), containerURI.get(q + "/" + i + "/" + j + "/" + z + "/" + 1 + "/"), q + "/" + i + "/" + j + "/" + z + "/" + 0 + "/"), 1
                                , antitheft_sensor_period * getRandom(), TimeUnit.SECONDS);


                    }
                }
            }
        }
        String aeUri = createSyncAEResource();
        for (int w = 0; w < regionNumer; w++) {
            for (int i = 0; i < parkingFloorCount; i++) {
                for (int j = 0; j < parkingRegionCount; j++) {
                    for (int z = 0; z < parkingPositionCount / parkingRegionCount / parkingFloorCount; z++) {
                        String cntUri = createSyncContainer(aeUri);//创建一个container
                        containerURI.put(w + "/" + i + "/" + j + "/" + z, cntUri);
                    }
                }
            }
        }
        for (int w = 0; w < regionNumer; w++) {
            for (int i = 0; i < parkingFloorCount; i++) {
                for (int j = 0; j < parkingRegionCount; j++) {
                    for (int z = 0; z < parkingPositionCount / parkingRegionCount / parkingFloorCount; z++) {
                        executorService.scheduleAtFixedRate(new LaserSensor(laser_sensor_value, this, containerURI.get(w + "/" + i + "/" + j + "/" + z), null, w + "/" + i + "/" + j, w + "/" + i + "/" + j + "/" + z), 1, laser_sensor_period * getRandom(), TimeUnit.SECONDS);

                    }
                }
            }
        }

    }

    private int getRandom() {
        int result = random.nextInt(10);
        if (result <= 1) {
            result = 1;
        }
        return result;
    }
}
