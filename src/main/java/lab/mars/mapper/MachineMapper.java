package lab.mars.mapper;

import io.netty.handler.codec.http.HttpMethod;
import lab.mars.m2m.protocol.common.m2m_childResourceRef;
import lab.mars.m2m.protocol.http.HeartBeat;
import lab.mars.m2m.protocol.resource.*;
import lab.mars.m2m.reality.pojo.Machine;
import lab.mars.m2m.reflection.ResourceReflection;
import lab.mars.network.Network;
import lab.mars.util.network.HttpClient;

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
    public static ConcurrentHashMap<Integer, String> machineIdToURI = new ConcurrentHashMap<>();
    public static ConcurrentHashMap<Integer, String> parkingIdToURI = new ConcurrentHashMap<>();
    public static HttpClient client = new HttpClient();
    private static int machineCount = 8800;
    private static int parkingCount = 2000;
    private static Network network;

    public static void init() {
        network = new Network();
        network.init();
        int i = 0;
        try {
            m2m_resource m2m_resource = network.testRetrieve(ROOT, null, OK);
            if (m2m_resource instanceof m2m_CSEBase) {
                List<m2m_childResourceRef> resourceList = ((m2m_CSEBase) m2m_resource).ch;
                for (m2m_childResourceRef aeURI : resourceList) {
                    m2m_resource m2mResource = network.testRetrieve(aeURI.v, null, OK);
                    if (m2mResource instanceof m2m_AE) {
                        List<m2m_childResourceRef> containers = ((m2m_AE)m2mResource).ch;
                        for (m2m_childResourceRef container : containers) {
                            m2mResource = network.testRetrieve(container.v, null, OK);
                            System.out.println("查询完成"+m2mResource+" containerURI:"+container.v);
                            if (m2mResource instanceof m2m_Container) {
                                if(((m2m_Container)m2mResource).la==null){
                                    continue;
                                }
                                m2mResource = network.testRetrieve(((m2m_Container)m2mResource).la, null, OK);
                                System.out.println("开始");
                                if (m2mResource instanceof m2m_ContentInstance) {
                                    Object object = ResourceReflection.deserializeKryo(((m2m_ContentInstance) m2mResource).con);
                                    if (object instanceof Machine) {
                                        if (i < machineCount) {
                                            machineCondition.put(container.v, ((Machine) object).isClosed);
                                            machineIdToURI.put(i, container.v);
                                        } else if (i < machineCount + parkingCount) {
                                            parkingCondition.put(container.v, ((Machine) object).isClosed);
                                            parkingIdToURI.put(i - machineCount, container.v);
                                        } else {
                                            return;
                                        }
                                        i++;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static HeartBeat getConnection() throws Exception {
        return network.test2Request(HttpMethod.GET, "/csebase", OK, null);
    }
    public static void main(String args[]){
        MachineMapper.init();
        System.out.println("sieze:"+MachineMapper.machineCondition.size());
        System.out.println("sieze:"+MachineMapper.parkingCondition.size());
    }
}
