package lab.mars.model;

/**
 * Author:yaoalong.
 * Date:2016/6/9.
 * Email:yaoalong@foxmail.com
 */
public class StatusStatistics {

    private AirConditionStatistics airConditionStatistics;
    private AntiTheftStatistics antiTheftStatistics;

    private LightStatusStatistics lightStatusStatistics;

    public AirConditionStatistics getAirConditionStatistics() {
        return airConditionStatistics;
    }

    public void setAirConditionStatistics(AirConditionStatistics airConditionStatistics) {
        this.airConditionStatistics = airConditionStatistics;
    }

    public AntiTheftStatistics getAntiTheftStatistics() {
        return antiTheftStatistics;
    }

    public void setAntiTheftStatistics(AntiTheftStatistics antiTheftStatistics) {
        this.antiTheftStatistics = antiTheftStatistics;
    }

    public LightStatusStatistics getLightStatusStatistics() {
        return lightStatusStatistics;
    }

    public void setLightStatusStatistics(LightStatusStatistics lightStatusStatistics) {
        this.lightStatusStatistics = lightStatusStatistics;
    }
}
