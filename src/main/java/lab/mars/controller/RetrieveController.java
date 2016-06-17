package lab.mars.controller;

import lab.mars.model.MachineStatus;
import lab.mars.model.ParkingStatus;
import lab.mars.model.Sensor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;

import static lab.mars.mapper.MachineMapper.*;
import static lab.msrs.web.util.NotificationUtils.*;

/**
 * Author:yaoalong.
 * Date:2016/6/10.
 * Email:yaoalong@foxmail.com
 */

/**
 * 检索某个特定的资源的信息
 */
@Controller
public class RetrieveController {
    @RequestMapping(value = "/retrieveMachine.do", method = RequestMethod.GET)
    public
    @ResponseBody
    MachineStatus retrieve(@RequestParam String key) {
        MachineStatus machineStatus = new MachineStatus();
        String[] result = key.split("c");
        if (result.length != 5) {
            return machineStatus;
        }
        int ban = Integer.parseInt(result[0]) - 1;
        int floor = Integer.parseInt(result[1]) - 1;
        int apartment = Integer.parseInt(result[2]) - 1;
        int roomNumber = Integer.parseInt(result[3]) - 1;
        boolean isClosed = false;
        if (Integer.parseInt(result[4]) == 0) {
            isClosed = positionMapAntiTheft.get(ban + "/" + floor + "/" + apartment + "/" + 1 + "/").isClosed;
        } else if (Integer.parseInt(result[4]) == 1) {
            isClosed = positionMapMachine.get(ban + "/" + floor + "/" + apartment + "/" + roomNumber + "/" + 1).isClosed;
        } else if (Integer.parseInt(result[4]) == 2) {
            isClosed = positionMapMachine.get(ban + "/" + floor + "/" + apartment + "/" + roomNumber + "/" + 0).isClosed;
        }
        machineStatus.setClosed(isClosed);
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

    @RequestMapping(value = "/retrieveSensor.do", method = RequestMethod.GET)
    public
    @ResponseBody
    Sensor retrieveSensor(@RequestParam String key) {
        String[] result = key.split("c");
        int ban = Integer.parseInt(result[0]) - 1;
        int floor = Integer.parseInt(result[1]) - 1;
        int apartment = Integer.parseInt(result[2]) - 1;
        int roomNumber = Integer.parseInt(result[3]) - 1;
        Sensor sensor = new Sensor();
        if (Integer.parseInt(result[4]) == 0) {
            sensor.setValue(positionMapSensor.get(ban + "/" + floor + "/" + apartment + "/" + roomNumber + "/" + 2).getValue());
        } else if (Integer.parseInt(result[4]) == 1) {
            sensor.setValue(positionMapSensor.get(ban + "/" + floor + "/" + apartment + "/" + roomNumber + "/" + 1).getValue());
        } else if (Integer.parseInt(result[4]) == 2) {
            sensor.setValue(positionMapSensor.get(ban + "/" + floor + "/" + apartment + "/" + roomNumber + "/" + 2).getValue());
        }
        return sensor;
    }

    @PostConstruct
    public void init() {
        try {
            network.generateData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
