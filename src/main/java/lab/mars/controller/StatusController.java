package lab.mars.controller;

import lab.mars.mapper.MachineMapper;
import lab.mars.model.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
    ParkingStatisticsStatus getParkingStatus(@RequestParam int floorId) {
        int flag = 1;
        ParkingStatisticsStatus parkingStatisticsStatus = new ParkingStatisticsStatus();
        long unUsed, used;
        try {
            if (flag == 0) {
                unUsed = MachineMapper.parkingStatistics.getStatistis().get(0).getUnUsed().get();
                used = MachineMapper.parkingStatistics.getStatistis().get(0).getUsed().get();
            } else {
                unUsed = MachineMapper.parkingFloorStatistics.get(floorId).getStatistis().get(0).getUnUsed().get();
                used = MachineMapper.parkingFloorStatistics.get(floorId).getStatistis().get(0).getUsed().get();
            }
            parkingStatisticsStatus.setUnUsed(Double.valueOf((unUsed) / (unUsed + used) * 100));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return parkingStatisticsStatus;
    }

    @RequestMapping(value = "/airConditionStatistics.do", method = RequestMethod.GET)
    public
    @ResponseBody
    AirConditionStatistics getAirConditionStatus(@RequestParam String key) {
        AirConditionStatistics airConditionStatistics = new AirConditionStatistics();
        StatisticsDO statisticsDO = judgePosition(key);
        long unUsed = statisticsDO.getStatistis().get(1).getUnUsed().get();
        long used = statisticsDO.getStatistis().get(1).getUsed().get();
        airConditionStatistics.setOpen(Double.valueOf((unUsed) / (unUsed + used) * 100));
        return airConditionStatistics;
    }

    @RequestMapping(value = "/lightStatusStatistics.do", method = RequestMethod.GET)
    public
    @ResponseBody
    LightStatusStatistics getLightStatus(@RequestParam String key) {
        StatisticsDO statisticsDO = judgePosition(key);
        long unUsed = statisticsDO.getStatistis().get(2).getUnUsed().get();
        long used = statisticsDO.getStatistis().get(2).getUsed().get();
        LightStatusStatistics lightStatusStatistics = new LightStatusStatistics();
        lightStatusStatistics.setOpen(Double.valueOf((unUsed) / (unUsed + used) * 100));
        return lightStatusStatistics;
    }

    @RequestMapping(value = "/antiTheftStatistics.do", method = RequestMethod.GET)
    public
    @ResponseBody
    AntiTheftStatistics getAntiTheftStatus(@RequestParam String key) {

        AntiTheftStatistics antiTheftStatistics = new AntiTheftStatistics();
        try{
            System.out.println("key:"+key);

            StatisticsDO statisticsDO = judgePosition(key);
            long unUsed = statisticsDO.getStatistis().get(0).getUnUsed().get();
            long used = statisticsDO.getStatistis().get(0).getUsed().get();
            antiTheftStatistics.setOpen(Double.valueOf((unUsed) / (unUsed + used) * 100));
        }catch (Exception e){
            e.printStackTrace();
        }

        return antiTheftStatistics;
    }

    public StatisticsDO judgePosition(String key) {
        String[] result = key.split("c");
        StatisticsDO statisticsDO = null;
        if (result.length == 1) {
            statisticsDO = MachineMapper.banStatistics.get(Integer.parseInt(result[0]) - 1);
        } else if (result.length == 2) {
            System.out.println("result"+result[0]);
            System.out.println("result:"+result[1]);
            statisticsDO = MachineMapper.floorStatistics.get((Integer.parseInt(result[0]) - 1) * 10 + Integer.parseInt(result[1]) - 1);
        } else if (result.length == 3) {
            statisticsDO = MachineMapper.apartmentStatistics.get((Integer.parseInt(result[0]) - 1) * 10 + (Integer.parseInt(result[1]) - 1) * 20 + Integer.parseInt(result[2]) - 1);
        }
        return statisticsDO;
    }
}
