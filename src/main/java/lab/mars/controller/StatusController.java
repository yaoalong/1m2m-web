package lab.mars.controller;

import lab.mars.model.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Random;

/**
 * Author:yaoalong.
 * Date:2016/6/9.
 * Email:yaoalong@foxmail.com
 */
@Controller
public class StatusController {

    @RequestMapping(value = "/parkingStatistics.do", method = RequestMethod.GET)
    public
    @ResponseBody
    ParkingStatisticsStatus getParkingStatus() {
        ParkingStatisticsStatus parkingStatisticsStatus = new ParkingStatisticsStatus();
        parkingStatisticsStatus.setUnUsed(Double.valueOf(new Random().nextInt(50)));
        return parkingStatisticsStatus;
    }
    @RequestMapping(value = "/airConditionStatistics.do", method = RequestMethod.GET)
    public
    @ResponseBody
    AirConditionStatistics getAirConditionStatus() {
        AirConditionStatistics airConditionStatistics = new AirConditionStatistics();
        airConditionStatistics.setOpen(Double.valueOf(new Random().nextInt(50)));
        return airConditionStatistics;
    }
    @RequestMapping(value = "/lightStatusStatistics.do", method = RequestMethod.GET)
    public
    @ResponseBody
    LightStatusStatistics getLightStatus() {
        LightStatusStatistics lightStatusStatistics = new LightStatusStatistics();
        lightStatusStatistics.setOpen(Double.valueOf(new Random().nextInt(50)));
        return lightStatusStatistics;
    }
    @RequestMapping(value = "/antiTheftStatistics.do", method = RequestMethod.GET)
    public
    @ResponseBody
    AntiTheftStatistics getAntiTheftStatus() {
        AntiTheftStatistics antiTheftStatistics = new AntiTheftStatistics();
        antiTheftStatistics.setOpen(Double.valueOf(new Random().nextInt(50)));
        return antiTheftStatistics;
    }
}
