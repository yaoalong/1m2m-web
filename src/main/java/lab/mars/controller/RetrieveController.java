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

import static lab.mars.mapper.MachineMapper.network;
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
    /**
     * 检索设备的状态
     *
     * @param key
     * @return
     */
    @RequestMapping(value = "/retrieveMachine.do", method = RequestMethod.GET)
    public
    @ResponseBody
    MachineStatus retrieveMachine(@RequestParam String key) {
        MachineStatus machineStatus = new MachineStatus();
        String[] result = key.split("c");

        int banId = Integer.parseInt(result[0]) - 1;
        int floorId = Integer.parseInt(result[1]) - 1;
        int apartmentId = Integer.parseInt(result[2]) - 1;
        boolean isClosed;
        String index = banId + "/" + floorId + "/" + apartmentId + "/";
        if (result.length == 4) {
            isClosed = positionMapAntiTheft.get(index + 1 + "/").isClosed;
        } else {
            int roomId = Integer.parseInt(result[2]) - 1;
            if (Integer.parseInt(result[4]) == 0) {
                isClosed = positionMapMachine.get(index + roomId + "/" + 2).isClosed;
            } else {
                isClosed = positionMapMachine.get(index + roomId + "/" + 3).isClosed;
            }
        }
        machineStatus.setClosed(isClosed);
        return machineStatus;
    }

    @RequestMapping(value = "/parkingRetrieve.do", method = RequestMethod.GET)
    public
    @ResponseBody
    ParkingStatus retrieveParking(@RequestParam String key) {
        String result = key.replaceAll("c", "/");
        ParkingStatus parkingStatus = new ParkingStatus();
        parkingStatus.setUnUsed(parkingCondition.get(result));
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
        Sensor sensor = new Sensor();
        if (result.length == 4) {
            sensor.setValue(positionMapSensor.get(ban + "/" + floor + "/" + apartment + "/" + "/" + 0).getValue());
            return sensor;
        } else {
            int roomNumber = Integer.parseInt(result[3]) - 1;
            if (Integer.parseInt(result[4]) == 0) {
                sensor.setValue(positionMapSensor.get(ban + "/" + floor + "/" + apartment + "/" + roomNumber + "/" + 0).getValue());
            } else {
                sensor.setValue(positionMapSensor.get(ban + "/" + floor + "/" + apartment + "/" + roomNumber + "/" + 1).getValue());
            }
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
