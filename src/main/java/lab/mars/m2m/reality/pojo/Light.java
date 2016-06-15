package lab.mars.m2m.reality.pojo;

/**
 * Author:yaoalong.
 * Date:2016/4/29.
 * Email:yaoalong@foxmail.com
 */


import lab.mars.m2m.test.resourcetest.ResourceTestBase;

/**
 * 灯设备
 */
public class Light extends Machine {

    private int low;
    private int high;

    public Light(boolean isClosed) {
        this.isClosed = isClosed;
    }

    public Light(int low, int high, boolean isClosed, ResourceTestBase resourceTestBase, String cntUri) {
        this.low = low;
        this.high = high;
        this.isClosed = isClosed;
        this.resourceTestBase = resourceTestBase;
        this.cntUri = cntUri;
    }

    @Override
    public void create(int value) {
    }

}
