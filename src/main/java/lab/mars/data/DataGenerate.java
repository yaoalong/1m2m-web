package lab.mars.data;

import lab.mars.m2m.protocol.common.m2m_childResourceRef;
import lab.mars.m2m.protocol.resource.m2m_ContentInstance;
import lab.mars.m2m.protocol.resource.m2m_resource;
import lab.mars.m2m.reality.pojo.*;
import lab.mars.m2m.reflection.ResourceReflection;
import lab.mars.network.WebNetwork;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static lab.msrs.web.util.NotificationUtils.cntMapMachine;

/**
 * Author:yaoalong.
 * Date:2016/6/16.
 * Email:yaoalong@foxmail.com
 */
public class DataGenerate extends WebNetwork {
    /**
     * 光线传感器
     */
    private static final int light_current_value = 0;
    private static final int light_low_value = 10;
    private static final int light_lowest_value = 0;
    private static final int light_high_value = 100;
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
    private static final boolean antitheft_sensor_value = false;
    private static final int antitheft_sensor_period = 5000;

    /**
     * 楼层
     */
    private static final int garageFloors = 2;
    /**
     * 停车场
     */
    private static final int number_parking_number = 10;
    /**
     * 传感器
     */
    private static final boolean laser_sensor_value = false;
    private static final int laser_sensor_period = 5000;


    //光线传感器0
    //空调传感器1
    //灯2
    //空调3

    @Override
    public void handleNotify(m2m_childResourceRef ref) {
        System.out.println("接收到了notify");
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
                System.out.println("classs:" + response.getClass());
            }
        });
    }

    public void generateData() throws Exception {
        init();
        Map<String, String> containerURI = new HashMap<>();
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(2);
        //栋数
        int banNum = 1;
        //层数
        int floor = 2;
        //户数
        int houseHold = 1;
        //房间
        int roomsNums = 1;

        for (int i = 0; i < banNum; i++) {
            for (int j = 0; j < floor; j++) {
                String aeUri = createSyncAEResource();//现在是每层楼是一个AE
                for (int z = 0; z < houseHold; z++) {
                    for (int y = 0; y < roomsNums; y++) {
                        for (int w = 0; w < 4; w++) {
                            String cntUri = createSyncContainer(aeUri);//创建一个container
                            containerURI.put(i + "/" + j + "/" + z + "/" + y + "/" + w, cntUri);
                            if (w == 2) {
                                Light light = new Light(light_low_value, light_high_value, false, this, cntUri);//空调
                                cntMapMachine.put(cntUri, light);
                            }
                            if (w == 3) {
                                AirConditioning airConditioning = new AirConditioning(temperature_low_value, temperature_high_value, false, this, cntUri);//灯
                                cntMapMachine.put(cntUri, airConditioning);
                            }
                        }
                    }
                    //防盗传感器
                    String cntUri = createSyncContainer(aeUri);//创建一个container
                    containerURI.put(i + "/" + j + "/" + z + "/" + 0 + "/", cntUri);
                    //防盗报警器
                    cntUri = createSyncContainer(aeUri);//创建一个container
                    containerURI.put(i + "/" + j + "/" + z + "/" + 1 + "/", cntUri);
                    AntitheftAlarm antitheftAlarm = new AntitheftAlarm(false, this, cntUri);
                    cntMapMachine.put(cntUri, antitheftAlarm);

                }
            }
        }
        System.out.println("contr"+"完成");
        for (int i = 0; i < banNum; i++) {
            for (int j = 0; j < floor; j++) {
                for (int z = 0; z < houseHold; z++) {
                    String containerURL = containerURI.get(i + "/" + j + "/" + z + "/" + 0 + "/");
                    createAsyncSubScriptions(containerURL);
                    for (int y = 0; y < roomsNums; y++) {
                        /**
                         * 创建不同的subscription
                         */
                        for (int w = 0; w < 2; w++) {
                            containerURL = containerURI.get(i + "/" + j + "/" + z + "/" + y + "/" + w);
                            createAsyncSubScriptions(containerURL);
                        }
                        executorService.scheduleAtFixedRate(new LightSensor(light_current_value, light_increment_num, light_lowest_value, light_highest_value, light_period, this, containerURI.get(i + "/" + j + "/" + z + "/" + y + "/" + 0), containerURI.get(i + "/" + j + "/" + z + "/" + y + "/" + 2)), 0
                                , light_period, TimeUnit.SECONDS);
                        executorService.scheduleAtFixedRate(new TemperatureSensor(temperature_current_value, temperature_increment_num, temperature_lowest_value, temperature_highest_value, temperature_period, this, containerURI.get(i + "/" + j + "/" + z + "/" + y + "/" + 1), containerURI.get(i + "/" + j + "/" + z + "/" + y + "/" + 3)), 1
                                , light_period, TimeUnit.SECONDS);
                    }
                    executorService.scheduleAtFixedRate(new AntiTheftSensor(antitheft_sensor_value, antitheft_sensor_period, this, containerURI.get(i + "/" + j + "/" + z + "/" + 0 + "/"), containerURI.get(i + "/" + j + "/" + z + "/" + 1 + "/")), 1
                            , antitheft_sensor_period, TimeUnit.SECONDS);


                }
            }
        }
        String aeUri = createSyncAEResource();
        for (int i = 0; i < garageFloors; i++) {
            for (int j = 0; j < number_parking_number; j++) {
                String cntUri = createSyncContainer(aeUri);//创建一个container
                containerURI.put(i + "/" + j, cntUri);
            }
        }
        for (int i = 0; i < garageFloors; i++) {
            for (int j = 0; j < number_parking_number; j++) {
                executorService.scheduleAtFixedRate(new LaserSensor(laser_sensor_value, laser_sensor_period, this, containerURI.get(i + "/" + j), null), 1, laser_sensor_period, TimeUnit.SECONDS);

            }
        }
        System.out.println("创建完毕");
        Thread.sleep(1000);
    }
    public static void main(String args[]) throws Exception {
        long startTime=System.currentTimeMillis();
        new DataGenerate().generateData();
        System.out.println(System.currentTimeMillis()-startTime);
    }
}
