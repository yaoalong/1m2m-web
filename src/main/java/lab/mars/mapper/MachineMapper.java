package lab.mars.mapper;

import lab.mars.m2m.protocol.common.m2m_childResourceRef;
import lab.mars.m2m.protocol.resource.m2m_AE;
import lab.mars.m2m.protocol.resource.m2m_CSEBase;
import lab.mars.m2m.protocol.resource.m2m_resource;
import lab.mars.network.Network;
import lab.mars.util.network.HttpClient;
import lab.mars.util.network.HttpServer;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import static io.netty.handler.codec.http.HttpResponseStatus.OK;

/**
 * Author:yaoalong.
 * Date:2016/6/10.
 * Email:yaoalong@foxmail.com
 */
public class MachineMapper {
    private static final String ROOT = "/csebase";
    public static ConcurrentHashMap<String, Boolean> machineCondition = new ConcurrentHashMap<>();
    public static ConcurrentHashMap<String, Boolean> parkingCondition = new ConcurrentHashMap<>();
    public static HttpClient client = new HttpClient();
    private static int machineCount = 8800;
    private static int parkingCount = 2000;
    private static HttpServer httpServer = new HttpServer();

    public static void init() {
        Network network = new Network();
        network.init();
        int i = 0;
        try {
            m2m_resource m2m_resource = network.testRetrieve(ROOT, null, OK);
            if (m2m_resource instanceof m2m_CSEBase) {
                List<m2m_childResourceRef> resourceList = ((m2m_CSEBase) m2m_resource).ch;
                for (m2m_childResourceRef aeURI : resourceList) {
                    m2m_resource m2mResource = network.testRetrieve(aeURI.v, null, OK);
                    if (m2mResource instanceof m2m_AE) {
                        List<m2m_childResourceRef> containers = ((m2m_CSEBase) m2m_resource).ch;
                        for (m2m_childResourceRef container : containers) {
                            if (i < machineCount) {
                                machineCondition.put(container.v, false);
                            } else if (i < machineCount + parkingCount) {
                                parkingCondition.put(container.v, false);
                            } else {
                                return;
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
