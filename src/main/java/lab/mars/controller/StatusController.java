package lab.mars.controller;

import lab.mars.model.AirConditionStatistics;
import lab.mars.model.AntiTheftStatistics;
import lab.mars.model.LightStatusStatistics;
import lab.mars.model.StatusStatistics;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Author:yaoalong.
 * Date:2016/6/9.
 * Email:yaoalong@foxmail.com
 */
@Controller
public class StatusController {
    @RequestMapping(value = "/statistics.do", method = RequestMethod.GET)
    public
    @ResponseBody
    StatusStatistics getConnection() {
        StatusStatistics statusStatistics = new StatusStatistics();
        AirConditionStatistics airConditionStatistics = new AirConditionStatistics();
        airConditionStatistics.setClosed(40.0);
        airConditionStatistics.setOpen(100 - 40.0);
        AntiTheftStatistics antiThefiStatistics = new AntiTheftStatistics();
        antiThefiStatistics.setClosed(0.5);
        antiThefiStatistics.setOpen(0.5);
        LightStatusStatistics lightStatusStatistics = new LightStatusStatistics();
        lightStatusStatistics.setOpen(0.7);
        lightStatusStatistics.setClosed(0.3);
        statusStatistics.setAirConditionStatistics(airConditionStatistics);
        statusStatistics.setAntiTheftStatistics(antiThefiStatistics);
        statusStatistics.setLightStatusStatistics(lightStatusStatistics);
        return statusStatistics;
    }
}
