package lab.mars.controller;

import lab.mars.mapper.MachineMapper;
import lab.mars.model.MachineStatus;
import lab.mars.model.ParkingStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Author:yaoalong.
 * Date:2016/6/10.
 * Email:yaoalong@foxmail.com
 */
@Controller
public class RetrieveController {
    @RequestMapping(value = "/retrieve.do", method = RequestMethod.GET)
    public
    @ResponseBody
    MachineStatus retrieve(@RequestParam String key) {
        MachineStatus machineStatus = new MachineStatus();
        try {
System.out.println("key"+key);
          //  machineStatus.setClosed(MachineMapper.result.get(key));
        }catch (Exception e){
e.printStackTrace();
        }

        return machineStatus;
    }

    @RequestMapping(value = "/parkingRetrieve.do", method = RequestMethod.GET)
    public
    @ResponseBody
    ParkingStatus retrieveParking(@RequestParam String key) {
        ParkingStatus parkingStatus = new ParkingStatus();
        parkingStatus.setUnUsed(MachineMapper.parkingCondition.get(key));
        return parkingStatus;
    }
}
