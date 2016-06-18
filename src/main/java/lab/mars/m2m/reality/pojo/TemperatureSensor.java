package lab.mars.m2m.reality.pojo;


import lab.mars.data.DataGenerate;

import static lab.msrs.web.util.NotificationUtils.positionMapSensor;

/**
 * Author:yaoalong.
 * Date:2016/4/21.
 * Email:yaoalong@foxmail.com
 */
public class TemperatureSensor extends AbstractSensor {
    private int incrementNum;
    private int low;
    private int high;

    public TemperatureSensor(int value, String machineUri) {
        this.value = value;
        this.machineUri = machineUri;
    }

    public TemperatureSensor(int value, int incrementNum, int low, int high, DataGenerate dataGenerate, String cntUri, String machineUri, String resourceId) {
        this.incrementNum = incrementNum;
        this.low = low;
        this.high = high;
        this.value = value;
        this.dataGenerate = dataGenerate;
        this.cntUri = cntUri;
        this.machineUri = machineUri;
        this.resourceId = resourceId;
    }

    @Override
    public void run() {
        if (value >= low && value <= high) {
            value += incrementNum;
            request(new TemperatureSensor(value, machineUri));
        } else {
            value = low;
        }
        positionMapSensor.get(resourceId).setValue(value);
    }


}
