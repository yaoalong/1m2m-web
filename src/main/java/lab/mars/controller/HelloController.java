package lab.mars.controller;

import lab.mars.model.ConnectionsStatistics;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Random;

/**
 * Author:yaoalong.
 * Date:2016/6/9.
 * Email:yaoalong@foxmail.com
 */
@Controller
@RequestMapping("/connection")
public class HelloController {
    @RequestMapping(value="/tps.do",method = RequestMethod.GET)
    public
    @ResponseBody
    ConnectionsStatistics getConnection() {

        ConnectionsStatistics connectionsStatistics = new ConnectionsStatistics();
        connectionsStatistics.setConnectionNumber(new Random().nextInt(100));
        connectionsStatistics.setAvgResonseTime(Long.valueOf(new Random().nextInt(100)));
        return connectionsStatistics;

    }
}
