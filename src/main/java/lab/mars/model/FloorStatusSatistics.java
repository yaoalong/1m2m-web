package lab.mars.model;

import java.util.List;

/**
 * Author:yaoalong.
 * Date:2016/6/18.
 * Email:yaoalong@foxmail.com
 */

/**
 * 楼层统计信息
 */
public class FloorStatusSatistics {
    private List<Boolean> antiTheftValues;

    public List<Boolean> getAntiTheftValues() {
        return antiTheftValues;
    }

    public void setAntiTheftValues(List<Boolean> antiTheftValues) {
        this.antiTheftValues = antiTheftValues;
    }
}
