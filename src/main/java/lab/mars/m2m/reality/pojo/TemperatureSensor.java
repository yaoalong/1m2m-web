package lab.mars.m2m.reality.pojo;


import lab.mars.m2m.test.resourcetest.ResourceTestBase;

/**
 * Author:yaoalong.
 * Date:2016/4/21.
 * Email:yaoalong@foxmail.com
 */
public class TemperatureSensor extends AbstractSensor {
    private int incrementNum;
    private int low;
    private int high;
    private int period;
    private int value;

    public TemperatureSensor(int value, String machineUri) {
        this.value = value;
        this.machineUri = machineUri;
    }

    public TemperatureSensor(int value, int incrementNum, int low, int high, int period, ResourceTestBase resourceTestBase, String cntUri, String machineUri) {
        this.incrementNum = incrementNum;
        this.low = low;
        this.high = high;
        this.period = period;
        this.value = value;
        this.resourceTestBase = resourceTestBase;
        this.cntUri = cntUri;
        this.machineUri = machineUri;
    }

    @Override
    public void run() {
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

}
