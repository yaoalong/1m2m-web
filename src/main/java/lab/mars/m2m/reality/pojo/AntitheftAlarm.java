package lab.mars.m2m.reality.pojo;


/**
 * Author:yaoalong.
 * Date:2016/4/29.
 * Email:yaoalong@foxmail.com
 */

import lab.mars.data.DataGenerate;
import lab.mars.model.MachineBelongInformation;

/**
 * 防盗报警器
 */
public class AntitheftAlarm extends Machine {
    public AntitheftAlarm(boolean isClosed) {
        this.isClosed = isClosed;
    }

    public AntitheftAlarm(boolean isClosed, DataGenerate dataGenerate, String cntUri, MachineBelongInformation machineBelongInformation) {
        this.isClosed = isClosed;
        this.dataGenerate = dataGenerate;
        this.cntUri = cntUri;
        this.machineBelongInformation = machineBelongInformation;
        request(new AntitheftAlarm(isClosed), 0);
    }

    @Override
    public void create(int value) {
        boolean pre = value == 1 ? true : false;
        if (pre && !isClosed) {
            // System.out.println("防盗传感器感应到门关了，防盗报警器关闭");
            isClosed = true;
            request(new AntitheftAlarm(isClosed), 1);
            update(cntUri, isClosed);
        } else if (!pre && isClosed) {
            // System.out.println("防盗传感器感应到门没关,防盗报警器开启");
            isClosed = false;
            request(new AntitheftAlarm(isClosed), 1);
            update(cntUri, isClosed);
        } else {
            // System.out.println("防盗" + cntUri + "状态:" + (isClosed ? "关闭" : "开启") + "门:" + (pre ? "关了" : "没关"));
        }
    }
}
