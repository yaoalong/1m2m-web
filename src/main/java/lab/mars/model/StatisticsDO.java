package lab.mars.model;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Author:yaoalong.
 * Date:2016/6/15.
 * Email:yaoalong@foxmail.com
 */
public class StatisticsDO {

    private AtomicLong used;
    private AtomicLong unUsed;

    public AtomicLong getUsed() {
        return used;
    }

    public void setUsed(AtomicLong used) {
        this.used = used;
    }

    public AtomicLong getUnUsed() {
        return unUsed;
    }

    public void setUnUsed(AtomicLong unUsed) {
        this.unUsed = unUsed;
    }
}
