package lab.mars.controller;

import lab.mars.mapper.MachineMapper;
import lab.mars.model.MachineStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
        System.out.println("key"+key);
        MachineStatus machineStatus = new MachineStatus();

        machineStatus.setClosed(MachineMapper.result.get(key));
        return machineStatus;
    }
}
