package lab.mars.controller;

import lab.mars.mapper.MachineMapper;
import lab.mars.model.MachineStatistics;
import lab.mars.model.StatisticsDO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import static lab.mars.model.MachineTypeEnum.*;

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
        long unUsed, used;
        if (flag == 0) {
            unUsed = MachineMapper.parkingStatistics.getStatistis().get(ANTITHEFT.getIndex()).getUnUsed().get();
            used = MachineMapper.parkingStatistics.getStatistis().get(ANTITHEFT.getIndex()).getUsed().get();
        } else {
            unUsed = MachineMapper.parkingFloorStatistics.get(floorId).getStatistis().get(0).getUnUsed().get();
            used = MachineMapper.parkingFloorStatistics.get(floorId).getStatistis().get(0).getUsed().get();
        }
        machineStatistics.setOpen(used / (unUsed + used) * 100);
        return machineStatistics;
    }

    @RequestMapping(value = "/airConditionStatistics.do", method = RequestMethod.GET)
    public
    @ResponseBody
    MachineStatistics getAirConditionStatus(@RequestParam String key) {
        MachineStatistics machineStatistics = new MachineStatistics();
        StatisticsDO statisticsDO = judgePosition(key);
        long unUsed = statisticsDO.getStatistis().get(AIRCONDIION.getIndex()).getUnUsed().get();
        long used = statisticsDO.getStatistis().get(AIRCONDIION.getIndex()).getUsed().get();
        machineStatistics.setOpen(used / (unUsed + used) * 100);
        return machineStatistics;
    }

    @RequestMapping(value = "/lightStatusStatistics.do", method = RequestMethod.GET)
    public
    @ResponseBody
    MachineStatistics getLightStatus(@RequestParam String key) {
        StatisticsDO statisticsDO = judgePosition(key);
        long unUsed = statisticsDO.getStatistis().get(LIGHT.getIndex()).getUnUsed().get();
        long used = statisticsDO.getStatistis().get(LIGHT.getIndex()).getUsed().get();
        MachineStatistics lightStatusStatistics = new MachineStatistics();
        lightStatusStatistics.setOpen(unUsed / (unUsed + used) * 100);
        return lightStatusStatistics;
    }

    @RequestMapping(value = "/antiTheftStatistics.do", method = RequestMethod.GET)
    public
    @ResponseBody
    MachineStatistics getAntiTheftStatus(@RequestParam String key) {
        MachineStatistics machineStatistics = new MachineStatistics();
        StatisticsDO statisticsDO = judgePosition(key);
        long unUsed = statisticsDO.getStatistis().get(ANTITHEFT.getIndex()).getUnUsed().get();
        long used = statisticsDO.getStatistis().get(ANTITHEFT.getIndex()).getUsed().get();
        machineStatistics.setOpen(used / (unUsed + used) * 100);
        return machineStatistics;
    }

    public StatisticsDO judgePosition(String key) {
        String[] result = key.split("c");
        StatisticsDO statisticsDO = null;
        if (result.length == 1) {
            statisticsDO = MachineMapper.banStatistics.get(Integer.parseInt(result[0]) - 1);
        } else if (result.length == 2) {
            statisticsDO = MachineMapper.floorStatistics.get((Integer.parseInt(result[0]) - 1) * 10 + Integer.parseInt(result[1]) - 1);
        } else if (result.length == 3) {
            statisticsDO = MachineMapper.apartmentStatistics.get((Integer.parseInt(result[0]) - 1) * 10 + (Integer.parseInt(result[1]) - 1) * 20 + Integer.parseInt(result[2]) - 1);
        }
        return statisticsDO;
    }
}
