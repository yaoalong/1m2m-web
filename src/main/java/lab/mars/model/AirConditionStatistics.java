package lab.mars.model;

/**
 * Author:yaoalong.
 * Date:2016/6/9.
 * Email:yaoalong@foxmail.com
 */
public class AirConditionStatistics {
    private Double open;
    private Double closed;

    public Double getOpen() {
        return open;
    }

    public void setOpen(Double open) {
        this.closed = 100 - open;
        this.open = open;

    }

    public Double getClosed() {
        return closed;
    }
}
