package lab.mars.controller;

import lab.mars.m2m.protocol.http.HeartBeat;
import lab.mars.mapper.MachineMapper;
import lab.mars.model.ConnectionsStatistics;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Author:yaoalong.
 * Date:2016/6/9.
 * Email:yaoalong@foxmail.com
 */

/**
 * 系统负载信息的获取
 */
@Controller
public class SystemInformationController {
    @RequestMapping(value = "/tps.do", method = RequestMethod.GET)
    public
    @ResponseBody
    ConnectionsStatistics getConnection() {
        HeartBeat heartBeat;
        try {
            heartBeat = MachineMapper.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        ConnectionsStatistics connectionsStatistics = new ConnectionsStatistics();
        connectionsStatistics.setConnectionNumber(heartBeat.getConnections());
        connectionsStatistics.setAvgResonseTime((long) (heartBeat.getAvgHandlingTime()*1000));
        return connectionsStatistics;
    }
}
