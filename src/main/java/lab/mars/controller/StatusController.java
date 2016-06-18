package lab.mars.controller;

import lab.mars.mapper.MachineMapper;
import lab.mars.model.ApartmentStatusStatistics;
import lab.mars.model.FloorStatusSatistics;
import lab.mars.model.MachineStatistics;
import lab.mars.model.StatisticsDO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

import static lab.mars.data.DataGenerate.*;
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
    /**
     * 获取停车场的统计信息
     *
     * @param floorId
     * @return
     */
    @RequestMapping(value = "/parkingStatistics.do", method = RequestMethod.GET)
    public
    @ResponseBody
    MachineStatistics getParkingStatus(@RequestParam int floorId) {
        int flag = 1;
        MachineStatistics machineStatistics = new MachineStatistics();
        long sum, used;
        if (flag == 0) {
            used = parkingStatistics.getStatistics().get(ANTITHEFT.getIndex()).getUsed().get();
            sum = parkingStatistics.getStatistics().get(ANTITHEFT.getIndex()).getSum();
        } else {
            sum = parkingFloorStatistics.get(floorId - 1).getStatistics().get(ANTITHEFT.getIndex()).getSum();
            used = parkingFloorStatistics.get(floorId - 1).getStatistics().get(ANTITHEFT.getIndex()).getUsed().get();
        }
        machineStatistics.setOpen(used, sum);
        return machineStatistics;
    }

    /**
     * 获取所有停车场的统计信息
     *
     * @return
     */
    @RequestMapping(value = "/allParkingStatistics.do", method = RequestMethod.GET)
    public
    @ResponseBody
    MachineStatistics getAllParkingStatus() {
        MachineStatistics machineStatistics = new MachineStatistics();
        long sum, used;
        used = parkingStatistics.getStatistics().get(ANTITHEFT.getIndex()).getUsed().get();
        sum = parkingStatistics.getStatistics().get(ANTITHEFT.getIndex()).getSum();
        machineStatistics.setOpen(used, sum);
        return machineStatistics;
    }

    /**
     * 获取所有空调的统计信息
     *
     * @return
     */
    @RequestMapping(value = "/allAirConditionStatistics.do", method = RequestMethod.GET)
    public
    @ResponseBody
    MachineStatistics getAllAirConditionStatus() {
        MachineStatistics machineStatisticsResult = new MachineStatistics();
        long sum = machineStatistics.getStatistics().get(AIRCONDIION.getIndex()).getSum();
        long used = machineStatistics.getStatistics().get(AIRCONDIION.getIndex()).getUsed().get();
        machineStatisticsResult.setOpen(used, sum);
        return machineStatisticsResult;
    }

    /**
     * 获取空调的统计信息
     *
     * @param key
     * @return
     */
    @RequestMapping(value = "/airConditionStatistics.do", method = RequestMethod.GET)
    public
    @ResponseBody
    MachineStatistics getAirConditionStatus(@RequestParam String key) {
        MachineStatistics machineStatistics = new MachineStatistics();
        StatisticsDO statisticsDO = judgePosition(key);
        long sum = statisticsDO.getStatistics().get(AIRCONDIION.getIndex()).getSum();
        long used = statisticsDO.getStatistics().get(AIRCONDIION.getIndex()).getUsed().get();
        machineStatistics.setOpen(used, sum);
        return machineStatistics;
    }

    /**
     * 获取所有灯的统计信息
     */
    @RequestMapping(value = "/allLightStatusStatistics.do", method = RequestMethod.GET)
    public
    @ResponseBody
    MachineStatistics getAllLightStatus() {
        long sum = machineStatistics.getStatistics().get(LIGHT.getIndex()).getSum();
        long used = machineStatistics.getStatistics().get(LIGHT.getIndex()).getUsed().get();
        MachineStatistics lightStatusStatistics = new MachineStatistics();
        lightStatusStatistics.setOpen(used, sum);
        return lightStatusStatistics;
    }

    /**
     * 获取灯的统计信息
     *
     * @param key
     * @return
     */
    @RequestMapping(value = "/lightStatusStatistics.do", method = RequestMethod.GET)
    public
    @ResponseBody
    MachineStatistics getLightStatus(@RequestParam String key) {
        StatisticsDO statisticsDO = judgePosition(key);
        long sum = statisticsDO.getStatistics().get(LIGHT.getIndex()).getSum();
        long used = statisticsDO.getStatistics().get(LIGHT.getIndex()).getUsed().get();
        MachineStatistics lightStatusStatistics = new MachineStatistics();
        lightStatusStatistics.setOpen(used, sum);
        return lightStatusStatistics;
    }

    /**
     * 获取所有警报器的信息
     *
     * @return
     */
    @RequestMapping(value = "/allAntiTheftStatistics.do", method = RequestMethod.GET)
    public
    @ResponseBody
    MachineStatistics getAllAntiTheftStatus() {
        MachineStatistics antiTheftStatistics = new MachineStatistics();
        long sum = machineStatistics.getStatistics().get(ANTITHEFT.getIndex()).getSum();
        long used = machineStatistics.getStatistics().get(ANTITHEFT.getIndex()).getUsed().get();
        antiTheftStatistics.setOpen(used, sum);
        return antiTheftStatistics;
    }

    /**
     * 获取警报器的信息
     *
     * @param key
     * @return
     */
    @RequestMapping(value = "/antiTheftStatistics.do", method = RequestMethod.GET)
    public
    @ResponseBody
    MachineStatistics getAntiTheftStatus(@RequestParam String key) {
        MachineStatistics machineStatistics = new MachineStatistics();
        StatisticsDO statisticsDO = judgePosition(key);
        long sum = statisticsDO.getStatistics().get(ANTITHEFT.getIndex()).getSum();
        long used = statisticsDO.getStatistics().get(ANTITHEFT.getIndex()).getUsed().get();
        machineStatistics.setOpen(used, sum);
        return machineStatistics;
    }

    /**
     * 获取一位业主所有的信息
     *
     * @param key
     * @return
     */
    @RequestMapping(value = "/getApartmentStatistics.do", method = RequestMethod.GET)
    public
    @ResponseBody
    ApartmentStatusStatistics getApartmentStatistics(@RequestParam String key) {
        String[] result = key.split("c");
        ApartmentStatusStatistics apartmentStatusStatistics = new ApartmentStatusStatistics();
        if (result.length != 3) {
            return apartmentStatusStatistics;
        }
        int banId = Integer.parseInt(result[0]) - 1;
        int floorId = Integer.parseInt(result[1]) - 1;
        int apartmentId = Integer.parseInt(result[2]) - 1;
        List<Integer> lightSensorValues = new ArrayList<>();
        List<Integer> temperatureSensorValues = new ArrayList<>();
        List<Boolean> lightStatuses = new ArrayList<>();
        List<Boolean> airConditionStatuses = new ArrayList<>();
        String index = banId + "/" + floorId + "/" + apartmentId + "/";
        for (int i = 0; i < roomNumber; i++) {
            lightStatuses.add(positionMapMachine.get(index + i + "/" + LIGHT_INDEX).isClosed);
            airConditionStatuses.add(positionMapMachine.get(index + i + "/" + AIRCONDITION_INDEX).isClosed);
            lightSensorValues.add(positionMapSensor.get(index + i + "/" + LIGHT_SENSOR_INDEX).getValue());
            temperatureSensorValues.add(positionMapSensor.get(index + i + "/" + TEMPERATURE_SENSOR_INDEX).getValue());
        }
        boolean antiTheft = positionMapAntiTheft.get(index + 1 + "/").isClosed;
        apartmentStatusStatistics.setAirConditionStatuses(airConditionStatuses);
        apartmentStatusStatistics.setLightStatuses(lightStatuses);
        apartmentStatusStatistics.setLightSensorValues(lightSensorValues);
        apartmentStatusStatistics.setTemperatureSensorValues(temperatureSensorValues);
        apartmentStatusStatistics.setAntiTheft(antiTheft);
        return apartmentStatusStatistics;
    }
    /**
     * 获取一位业主所有的信息
     *
     * @param key
     * @return
     */
    @RequestMapping(value = "/getFloorStatistics.do", method = RequestMethod.GET)
    public
    @ResponseBody
    FloorStatusSatistics getFloorStatistics(@RequestParam String key) {
        String[] result = key.split("c");
        FloorStatusSatistics floorStatusSatistics = new FloorStatusSatistics();
        if (result.length != 2) {
            return floorStatusSatistics;
        }
        int banId = Integer.parseInt(result[0]) - 1;
        int floorId = Integer.parseInt(result[1]) - 1;
        List<Boolean> antiTheftStatuses=new ArrayList<>();

        for(int i=0;i<apartmentNumber;i++){
            String index = banId + "/" + floorId + "/" + i + "/"+1+"/";
            antiTheftStatuses.add(positionMapAntiTheft.get(index).isClosed);
        }
       floorStatusSatistics.setAntiTheftValues(antiTheftStatuses);
        return floorStatusSatistics;
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
