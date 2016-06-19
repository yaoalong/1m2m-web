package lab.mars.m2m.reality.pojo;

/**
 * Author:yaoalong.
 * Date:2016/4/29.
 * Email:yaoalong@foxmail.com
 */


import lab.mars.data.DataGenerate;
import lab.mars.model.MachineBelongInformation;

import static lab.msrs.web.util.NotificationUtils.INIT;
import static lab.msrs.web.util.NotificationUtils.IS_NOT_INIT;

/**
 * 灯设备
 */
public class Light extends Machine {

    private int low;

    public Light(boolean isClosed) {
        this.isClosed = isClosed;
    }

    public Light(int low, boolean isClosed, DataGenerate dataGenerate, String cntUri, MachineBelongInformation machineBelongInformation) {
        this.low = low;
        this.isClosed = isClosed;
        this.dataGenerate = dataGenerate;
        this.cntUri = cntUri;
        this.machineBelongInformation = machineBelongInformation;
        request(new Light(isClosed), INIT);
    }

    @Override
    public void create(int value) {
        if (value < low && isClosed) {
            isClosed = false;
            request(new Light(isClosed), IS_NOT_INIT);
            update(isClosed);
        } else if (!isClosed) {
            //  System.out.println("光线传感器感应到光强度始终，关闭灯");
            isClosed = true;
            request(new Light(isClosed), IS_NOT_INIT);
            update(isClosed);
        }


    }

}
