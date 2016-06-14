package lab.mars.controller;

import lab.mars.mapper.MachineMapper;
import lab.mars.model.MachineStatus;
import lab.mars.model.ParkingStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;

import static lab.mars.mapper.MachineMapper.*;

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
    MachineStatus retrieve(@RequestParam int key) {
        MachineStatus machineStatus = new MachineStatus();
        try {
            System.out.println("key" + key);
             machineStatus.setClosed(machineCondition.get(machineIdToURI.get(key)));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return machineStatus;
    }

    @RequestMapping(value = "/parkingRetrieve.do", method = RequestMethod.GET)
    public
    @ResponseBody
    ParkingStatus retrieveParking(@RequestParam int key) {
        ParkingStatus parkingStatus = new ParkingStatus();
        parkingStatus.setUnUsed(parkingCondition.get(parkingIdToURI.get(key)));
        return parkingStatus;
    }

    @PostConstruct
    public void init() {
        MachineMapper.init();
    }
}
