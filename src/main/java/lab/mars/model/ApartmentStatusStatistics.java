package lab.mars.model;

import java.util.List;

/**
 * Author:yaoalong.
 * Date:2016/6/17.
 * Email:yaoalong@foxmail.com
 */

/**
 * 每个业主的统计信息
 */

public class ApartmentStatusStatistics {

    private List<Integer> lightSensorValues;
    private List<Integer> temperatureSensorValues;
    private List<Boolean> lightStatuses;
    private List<Boolean> airConditionStatuses;
    private boolean antiTheft;

    public List<Integer> getLightSensorValues() {
        return lightSensorValues;
    }

    public void setLightSensorValues(List<Integer> lightSensorValues) {
        this.lightSensorValues = lightSensorValues;
    }

    public List<Integer> getTemperatureSensorValues() {
        return temperatureSensorValues;
    }

    public void setTemperatureSensorValues(List<Integer> temperatureSensorValues) {
        this.temperatureSensorValues = temperatureSensorValues;
    }

    public List<Boolean> getLightStatuses() {
        return lightStatuses;
    }

    public void setLightStatuses(List<Boolean> lightStatuses) {
        this.lightStatuses = lightStatuses;
    }

    public List<Boolean> getAirConditionStatuses() {
        return airConditionStatuses;
    }

    public void setAirConditionStatuses(List<Boolean> airConditionStatuses) {
        this.airConditionStatuses = airConditionStatuses;
    }

    public boolean isAntiTheft() {
        return antiTheft;
    }

    public void setAntiTheft(boolean antiTheft) {
        this.antiTheft = antiTheft;
    }
}
