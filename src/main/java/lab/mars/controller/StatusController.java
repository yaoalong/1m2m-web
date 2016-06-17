package lab.mars.controller;

import lab.mars.mapper.MachineMapper;
import lab.mars.model.ApartmentStatusStatistics;
import lab.mars.model.MachineStatistics;
import lab.mars.model.StatisticsDO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

import static lab.mars.mapper.MachineMapper.*;
import static lab.mars.model.MachineTypeEnum.*;
import static lab.msrs.web.util.NotificationUtils.*;

/**
 * Author:yaoalong.
 * Date:2016/6/9.
 * Email:yaoalong@foxmail.com
 */

/**
 * 设备信息状态统计
 */
@Controller
public class StatusController {

    @RequestMapping(value = "/parkingStatistics.do", method = RequestMethod.GET)
    public
    @ResponseBody
    MachineStatistics getParkingStatus(@RequestParam int floorId) {
        int flag = 1;
        MachineStatistics machineStatistics = new MachineStatistics();
        long sum, used;
        if (flag == 0) {
            used = parkingStatistics.getStatistics().get(ANTITHEFT.getIndex()).getUsed().get();
            sum = parkingStatistics.getStatistics().get(ANTITHEFT.getIndex()).getUsed().get();
        } else {
            sum = parkingFloorStatistics.get(floorId - 1).getStatistics().get(ANTITHEFT.getIndex()).getSum();
            used = parkingFloorStatistics.get(floorId - 1).getStatistics().get(0).getUsed().get();
        }
        System.out.println("used:" + used + " sum:" + sum);
        machineStatistics.setOpen(used * 100 / sum);
        return machineStatistics;
    }

    @RequestMapping(value = "/airConditionStatistics.do", method = RequestMethod.GET)
    public
    @ResponseBody
    MachineStatistics getAirConditionStatus(@RequestParam String key) {
        MachineStatistics machineStatistics = new MachineStatistics();
        StatisticsDO statisticsDO = judgePosition(key);
        long sum = statisticsDO.getStatistics().get(ANTITHEFT.getIndex()).getSum();
        long used = statisticsDO.getStatistics().get(AIRCONDIION.getIndex()).getUsed().get();
        machineStatistics.setOpen(used * 100 / sum);
        return machineStatistics;
    }

    @RequestMapping(value = "/lightStatusStatistics.do", method = RequestMethod.GET)
    public
    @ResponseBody
    MachineStatistics getLightStatus(@RequestParam String key) {
        StatisticsDO statisticsDO = judgePosition(key);
        long sum = statisticsDO.getStatistics().get(ANTITHEFT.getIndex()).getSum();
        long used = statisticsDO.getStatistics().get(LIGHT.getIndex()).getUsed().get();
        MachineStatistics lightStatusStatistics = new MachineStatistics();
        lightStatusStatistics.setOpen(used * 100 / sum);
        return lightStatusStatistics;
    }

    @RequestMapping(value = "/antiTheftStatistics.do", method = RequestMethod.GET)
    public
    @ResponseBody
    MachineStatistics getAntiTheftStatus(@RequestParam String key) {
        MachineStatistics machineStatistics = new MachineStatistics();
        StatisticsDO statisticsDO = judgePosition(key);
        long sum = statisticsDO.getStatistics().get(ANTITHEFT.getIndex()).getSum();
        long used = statisticsDO.getStatistics().get(ANTITHEFT.getIndex()).getUsed().get();
        machineStatistics.setOpen(used * 100 / sum);
        return machineStatistics;
    }

    @RequestMapping(value = "/getApartmentStatistics.do", method = RequestMethod.GET)
    public
    @ResponseBody
    ApartmentStatusStatistics getApartmentStatistics(@RequestParam String key) {
        String[] result = key.split("c");
        ApartmentStatusStatistics apartmentStatusStatistics = new ApartmentStatusStatistics();
        if (result.length != 3) {
            return apartmentStatusStatistics;
        }
        int ban = Integer.parseInt(result[0]) - 1;
        int floor = Integer.parseInt(result[1]) - 1;
        int apartment = Integer.parseInt(result[2]) - 1;
        List<Integer> lightSensorValues = new ArrayList<>();
        List<Integer> temperatureSensorValues = new ArrayList<>();
        List<Boolean> lightStatuses = new ArrayList<>();
        List<Boolean> airConditionStatuses = new ArrayList<>();
        for (int i = 0; i < roomNumber; i++) {
            lightStatuses.add(positionMapMachine.get(ban + "/" + floor + "/" + apartment + "/" + i + "/" + 0).isClosed);
            airConditionStatuses.add(positionMapMachine.get(ban + "/" + floor + "/" + apartment + "/" + i + "/" + 1).isClosed);
            lightSensorValues.add(positionMapSensor.get(ban + "/" + floor + "/" + apartment + "/" + i + "/" + 2).getValue());
            temperatureSensorValues.add(positionMapSensor.get(ban + "/" + floor + "/" + apartment + "/" + i + "/" + 3).getValue());
        }
        boolean antiTheft = positionMapAntiTheft.get(ban + "/" + floor + "/" + apartment + "/" + 1 + "/").isClosed;
        apartmentStatusStatistics.setAirConditionStatuses(airConditionStatuses);
        apartmentStatusStatistics.setLightStatuses(lightStatuses);
        apartmentStatusStatistics.setTemperatureSensorValues(lightSensorValues);
        apartmentStatusStatistics.setTemperatureSensorValues(temperatureSensorValues);
        apartmentStatusStatistics.setAntiTheft(antiTheft);
        return apartmentStatusStatistics;
    }

    private StatisticsDO judgePosition(String key) {
        String[] result = key.split("c");
        StatisticsDO statisticsDO = null;
        if (result.length == 1) {
            statisticsDO = banStatistics.get(Integer.parseInt(result[0]) - 1);
        } else if (result.length == 2) {
            statisticsDO = MachineMapper.floorStatistics.get((Integer.parseInt(result[0]) - 1) * 10 + Integer.parseInt(result[1]) - 1);
        } else if (result.length == 3) {
            statisticsDO = MachineMapper.apartmentStatistics.get((Integer.parseInt(result[0]) - 1) * 10 + (Integer.parseInt(result[1]) - 1) * 20 + Integer.parseInt(result[2]) - 1);
        }
        return statisticsDO;
    }
}
