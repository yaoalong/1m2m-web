package lab.mars.m2m.reality.pojo;

/**
 * Author:yaoalong.
 * Date:2016/4/21.
 * Email:yaoalong@foxmail.com
 */


import lab.mars.data.DataGenerate;

import static lab.msrs.web.util.NotificationUtils.positionMapSensor;

/**
 * 防盗传感器
 */
public class AntiTheftSensor extends AbstractSensor {
    private static final long serialVersionUID = -3092906479136585494L;
    private boolean value;

    public AntiTheftSensor(boolean value, String machineUri) {
        this.value = value;
        this.machineUri = machineUri;
    }

    public AntiTheftSensor(boolean value, DataGenerate dataGenerate, String cntUri, String machineUri, String resourceId) {
        this.value = value;
        this.dataGenerate = dataGenerate;
        this.cntUri = cntUri;
        this.machineUri = machineUri;
        this.resourceId = resourceId;
        positionMapSensor.put(resourceId, this);

    }

    @Override
    public void run() {
        try {
            value = !value;
            request(new AntiTheftSensor(value, machineUri));
            positionMapSensor.get(resourceId).setValue(value ? 1 : 0);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getValue() {
        return value ? 1 : 0;
    }

}
