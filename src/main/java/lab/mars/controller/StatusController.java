package lab.mars.controller;

import lab.mars.mapper.MachineMapper;
import lab.mars.model.MachineStatistics;
import lab.mars.model.StatisticsDO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import static lab.mars.mapper.MachineMapper.parkingStatistics;
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
        long sum, used;
        sum = parkingStatistics.getStatistics().get(ANTITHEFT.getIndex()).getSum();
        if (flag == 0) {
            used = parkingStatistics.getStatistics().get(ANTITHEFT.getIndex()).getUsed().get();
        } else {
            used = MachineMapper.parkingFloorStatistics.get(floorId).getStatistics().get(0).getUsed().get();
        }
        machineStatistics.setOpen(used * 100 / sum);
        return machineStatistics;
    }

    @RequestMapping(value = "/airConditionStatistics.do", method = RequestMethod.GET)
    public
    @ResponseBody
    MachineStatistics getAirConditionStatus(@RequestParam String key) {
        MachineStatistics machineStatistics = new MachineStatistics();
        StatisticsDO statisticsDO = judgePosition(key);
        long sum = parkingStatistics.getStatistics().get(ANTITHEFT.getIndex()).getSum();
        long used = statisticsDO.getStatistics().get(AIRCONDIION.getIndex()).getUsed().get();
        machineStatistics.setOpen(used * 100 / sum);
        return machineStatistics;
    }

    @RequestMapping(value = "/lightStatusStatistics.do", method = RequestMethod.GET)
    public
    @ResponseBody
    MachineStatistics getLightStatus(@RequestParam String key) {
        StatisticsDO statisticsDO = judgePosition(key);
        long sum = parkingStatistics.getStatistics().get(ANTITHEFT.getIndex()).getSum();
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
        long sum = parkingStatistics.getStatistics().get(ANTITHEFT.getIndex()).getSum();
        long used = statisticsDO.getStatistics().get(ANTITHEFT.getIndex()).getUsed().get();
        System.out.println("sum:"+sum);
        machineStatistics.setOpen(used * 100 / sum);
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
