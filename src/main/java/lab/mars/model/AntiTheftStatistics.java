package lab.mars.model;

/**
 * Author:yaoalong.
 * Date:2016/6/9.
 * Email:yaoalong@foxmail.com
 */
public class AntiTheftStatistics {

    private Double open;
    private Double closed;

    public Double getOpen() {
        return open;
    }

    public void setOpen(Double open) {
        this.open = open;
        this.closed = 100 - open;
    }

    public Double getClosed() {
        return closed;
    }
}
