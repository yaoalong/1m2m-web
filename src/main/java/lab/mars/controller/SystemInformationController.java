package lab.mars.controller;

import io.netty.handler.codec.http.HttpMethod;
import lab.mars.m2m.protocol.http.HeartBeat;
import lab.mars.model.ConnectionsStatistics;
import lab.mars.network.Network;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import static io.netty.handler.codec.http.HttpResponseStatus.OK;

/**
 * Author:yaoalong.
 * Date:2016/6/9.
 * Email:yaoalong@foxmail.com
 */

/**
 * 系统负载信息的获取
 */
@Controller
@RequestMapping("/connection")
public class SystemInformationController {
    @RequestMapping(value = "/tps.do", method = RequestMethod.GET)
    public
    @ResponseBody
    ConnectionsStatistics getConnection() {
        HeartBeat heartBeat = null;
        try {
            heartBeat = Network.test2Request(HttpMethod.GET, "/csebase", OK, null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        ConnectionsStatistics connectionsStatistics = new ConnectionsStatistics();
        connectionsStatistics.setConnectionNumber(heartBeat.getConnections());
        connectionsStatistics.setAvgResonseTime(new Double(heartBeat.getAvgHandlingTime()).longValue());
        return connectionsStatistics;
    }
}
