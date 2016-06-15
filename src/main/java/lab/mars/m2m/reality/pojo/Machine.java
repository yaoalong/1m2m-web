package lab.mars.m2m.reality.pojo;

import lab.mars.m2m.test.resourcetest.ResourceTestBase;

/**
 * Author:yaoalong.
 * Date:2016/4/29.
 * Email:yaoalong@foxmail.com
 */
public abstract class Machine {
    public boolean isClosed;
    protected ResourceTestBase resourceTestBase;
    protected String cntUri;

    public void request(Machine machine) {
    }


    public abstract void create(int value);

}
