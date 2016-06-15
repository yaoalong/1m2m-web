package lab.mars.m2m.reality.pojo;


/**
 * Author:yaoalong.
 * Date:2016/4/29.
 * Email:yaoalong@foxmail.com
 */

import lab.mars.m2m.test.resourcetest.ResourceTestBase;

/**
 * 防盗报警器
 */
public class AntitheftAlarm extends Machine {
    public AntitheftAlarm(boolean isClosed) {
        this.isClosed = isClosed;
    }

    public AntitheftAlarm(boolean isClosed, ResourceTestBase resourceTestBase, String cntUri) {
        this.isClosed = isClosed;
        this.resourceTestBase = resourceTestBase;
        this.cntUri = cntUri;
    }

    @Override
    public void create(int value) {
    }
}
