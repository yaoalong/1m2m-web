package lab.mars.model;

/**
 * Author:yaoalong.
 * Date:2016/6/9.
 * Email:yaoalong@foxmail.com
 */
public class ConnectionsStatistics {

    private Integer connectionNumber;

    private Long avgResonseTime;

    public Integer getConnectionNumber() {
        return connectionNumber;
    }

    public void setConnectionNumber(Integer connectionNumber) {
        this.connectionNumber = connectionNumber;
    }

    public Long getAvgResonseTime() {
        return avgResonseTime;
    }

    public void setAvgResonseTime(Long avgResonseTime) {
        this.avgResonseTime = avgResonseTime;
    }
}
