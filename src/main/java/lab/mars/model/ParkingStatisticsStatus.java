package lab.mars.model;

/**
 * Author:yaoalong.
 * Date:2016/6/10.
 * Email:yaoalong@foxmail.com
 */
public class ParkingStatisticsStatus {

    private Double used;
    private Double unUsed;


    public void setUnUsed(Double unUsed) {
        this.unUsed = unUsed;
        this.used=100-unUsed;
    }

    public Double getUnUsed() {
        return unUsed;
    }

    public Double getUsed() {
        return used;
    }
}
