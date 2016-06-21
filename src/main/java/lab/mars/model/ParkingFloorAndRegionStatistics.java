package lab.mars.model;

/**
 * Author:yaoalong.
 * Date:2016/6/19.
 * Email:yaoalong@foxmail.com
 */

/**
 * 停车场某区域的统计信息
 */
public class ParkingFloorAndRegionStatistics {
    private Long sum;
    private Long unUsed;
    private Boolean free;

    public Long getSum() {
        return sum;
    }

    public void setSum(Long sum) {
        this.sum = sum;
    }

    public Long getUnUsed() {
        return unUsed;
    }

    public void setUnUsed(Long unUsed) {
        this.unUsed = unUsed;
    }

    public Boolean getFree() {
        return free;
    }

    public void setFree(Boolean free) {
        this.free = free;
    }
}
