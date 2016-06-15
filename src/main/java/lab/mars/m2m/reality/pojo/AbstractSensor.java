package lab.mars.m2m.reality.pojo;

import lab.mars.m2m.test.resourcetest.ResourceTestBase;

import java.io.Serializable;

/**
 * Author:yaoalong.
 * Date:2016/4/25.
 * Email:yaoalong@foxmail.com
 */
public abstract class AbstractSensor implements SensorObject, Serializable {

    protected ResourceTestBase resourceTestBase;
    protected String cntUri;
    protected String machineUri;
    private long id;

    public void request(AbstractSensor sensor) {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
