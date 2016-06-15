package lab.mars.model;

/**
 * Author:yaoalong.
 * Date:2016/6/9.
 * Email:yaoalong@foxmail.com
 */
public class MachineStatistics {
    private Integer open;
    private Integer closed;

    public Integer getOpen() {
        return open;
    }

    public void setOpen(long open) {
        int opened = (int) open;
        this.closed = 100 - opened;
        this.open = opened;

    }

    public Integer getClosed() {
        return closed;
    }
}
