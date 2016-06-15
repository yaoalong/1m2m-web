package lab.mars.mapper;

import io.netty.handler.codec.http.HttpMethod;
import lab.mars.m2m.protocol.common.m2m_childResourceRef;
import lab.mars.m2m.protocol.http.HeartBeat;
import lab.mars.m2m.protocol.resource.*;
import lab.mars.m2m.reality.pojo.Machine;
import lab.mars.m2m.reflection.ResourceReflection;
import lab.mars.model.MachineTypeEnum;
import lab.mars.model.StatisticsDO;
import lab.mars.network.Network;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static lab.mars.model.MachineTypeEnum.ANTITHEFT;

/**
 * Author:yaoalong.
 * Date:2016/6/10.
 * Email:yaoalong@foxmail.com
 */
public class MachineMapper {
    public static final ConcurrentHashMap<String, Boolean> machineCondition = new ConcurrentHashMap<>();
    public static final ConcurrentHashMap<String, Boolean> parkingCondition = new ConcurrentHashMap<>();
    public static final ConcurrentHashMap<Integer, String> machineIdToURI = new ConcurrentHashMap<>();
    public static final ConcurrentHashMap<Integer, String> parkingIdToURI = new ConcurrentHashMap<>();
    public static final ConcurrentHashMap<String, Integer> machineURIToID = new ConcurrentHashMap<>();
    public static final ConcurrentHashMap<String, Integer> parkingURIToID = new ConcurrentHashMap<>();
    public static final ConcurrentHashMap<String, Integer> urlTOType = new ConcurrentHashMap<>();
    public static final List<StatisticsDO> banStatistics = new ArrayList<>();
    public static final List<StatisticsDO> floorStatistics = new ArrayList<>();
    public static final List<StatisticsDO> apartmentStatistics = new ArrayList<>();
    public static final List<StatisticsDO> parkingFloorStatistics = new ArrayList<>();
    private static final String ROOT = "/csebase";
    public static StatisticsDO machineStatistics = new StatisticsDO();
    public static StatisticsDO parkingStatistics = new StatisticsDO();
    private static int machineCount = 8800;
    private static int parkingCount = 2000;
    private static Network network;
    //一户人家的设备的数量
    private static int apartmentNumber = 11;
    private static int floorNumber = 44;//一层的设备数量
    private static int banNumber = 880;//一栋楼的设备数量

    /**
     * 加载所有的资源信息
     */
    public static void init() {
        network = new Network();
        network.init();
        int i = 0;
        try {
            m2m_resource m2m_resource = network.testRetrieve(ROOT);
            List<m2m_childResourceRef> resourceList = ((m2m_CSEBase) m2m_resource).ch;
            for (m2m_childResourceRef aeURI : resourceList) {
                m2m_resource m2mResource = network.testRetrieve(aeURI.v);
                if (m2mResource instanceof m2m_AE) {//检索所有的AE
                    List<m2m_childResourceRef> containers = ((m2m_AE) m2mResource).ch;
                    for (m2m_childResourceRef container : containers) {//检索所有的Container
                        m2mResource = network.testRetrieve(container.v);
                        if (m2mResource instanceof m2m_Container) {
                            if (((m2m_Container) m2mResource).la == null) {
                                continue;
                            }
                            m2mResource = network.testRetrieve(((m2m_Container) m2mResource).la);//获取最新的instance
                            if (m2mResource instanceof m2m_ContentInstance) {
                                Object object = ResourceReflection.deserializeKryo(((m2m_ContentInstance) m2mResource).con);
                                if (object instanceof Machine) {
                                    if (i < machineCount) {
                                        int machineType = i % apartmentNumber;
                                        if (machineType == 0) {
                                        } else if (machineType % 2 == 1) {
                                            machineType = MachineTypeEnum.AIRCONDIION.getIndex();
                                        } else {
                                            machineType = MachineTypeEnum.LIGHT.getIndex();
                                        }
                                        int apartmentId = i / apartmentNumber;
                                        if (apartmentId >= apartmentStatistics.size() || apartmentStatistics.get(apartmentId) == null) {
                                            apartmentStatistics.add(apartmentId, new StatisticsDO());
                                        }
                                        int floorId = i / floorNumber;
                                        if (floorId >= floorStatistics.size() || floorStatistics.get(floorId) == null) {
                                            floorStatistics.add(floorId, new StatisticsDO());
                                        }
                                        int banId = i / banNumber;
                                        if (banId >= banStatistics.size() || banStatistics.get(banId) == null) {
                                            banStatistics.add(banId, new StatisticsDO());
                                        }
                                        if (((Machine) object).isClosed) {
                                            System.out.println("开");
                                            apartmentStatistics.get(apartmentId).getStatistics().get(machineType).getUnUsed().getAndIncrement();
                                            floorStatistics.get(floorId).getStatistics().get(machineType).getUnUsed().getAndIncrement();
                                            banStatistics.get(banId).getStatistics().get(machineType).getUnUsed().getAndIncrement();
                                            machineStatistics.getStatistics().get(machineType).getUnUsed().getAndIncrement();
                                        } else {
                                            System.out.println("关闭");
                                            apartmentStatistics.get(apartmentId).getStatistics().get(machineType).getUsed().getAndIncrement();
                                            floorStatistics.get(floorId).getStatistics().get(machineType).getUsed().getAndIncrement();
                                            banStatistics.get(banId).getStatistics().get(machineType).getUsed().getAndIncrement();
                                            machineStatistics.getStatistics().get(machineType).getUsed().getAndIncrement();
                                        }

                                        machineCondition.put(container.v, ((Machine) object).isClosed);
                                        machineIdToURI.put(i, container.v);
                                        machineURIToID.put(container.v, i);
                                        urlTOType.put(container.v, machineType);
                                    } else if (i < machineCount + parkingCount) {
                                        parkingCondition.put(container.v, ((Machine) object).isClosed);
                                        parkingIdToURI.put(i - machineCount, container.v);
                                        parkingURIToID.put(container.v, i - machineCount);
                                        int floorId = (i - machineCount) % 2;
                                        if (floorId >= parkingFloorStatistics.size() || parkingFloorStatistics.get(floorId) == null) {
                                            parkingFloorStatistics.add(floorId, new StatisticsDO());
                                        }
                                        if (((Machine) object).isClosed) {
                                            parkingFloorStatistics.get(floorId).getStatistics().get(ANTITHEFT.getIndex()).getUnUsed().getAndIncrement();
                                            parkingStatistics.getStatistics().get(ANTITHEFT.getIndex()).getUnUsed().getAndIncrement();
                                        } else {
                                            parkingFloorStatistics.get(floorId).getStatistics().get(ANTITHEFT.getIndex()).getUsed().getAndIncrement();
                                            parkingStatistics.getStatistics().get(ANTITHEFT.getIndex()).getUsed().getAndIncrement();
                                        }
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取系统性能信息
     *
     * @return
     * @throws Exception
     */
    public static HeartBeat getConnection() throws Exception {
        return network.test2Request(HttpMethod.GET, ROOT, OK, null);
    }

    /**
     * 更新所有的统计信息
     *
     * @param resourceURI
     */
    public static void update(String resourceURI) {
        if (machineCondition.containsKey(resourceURI)) {
            int machineType = urlTOType.get(urlTOType);
            if (machineCondition.get(resourceURI)) {
                apartmentStatistics.get(machineURIToID.get(resourceURI) % apartmentNumber).getStatistics().get(machineType).getUnUsed().getAndIncrement();
                apartmentStatistics.get(machineURIToID.get(resourceURI) % apartmentNumber).getStatistics().get(machineType).getUsed().getAndDecrement();
                floorStatistics.get(machineURIToID.get(resourceURI) % floorNumber).getStatistics().get(machineType).getUnUsed().getAndIncrement();
                floorStatistics.get(machineURIToID.get(resourceURI) % floorNumber).getStatistics().get(machineType).getUsed().getAndDecrement();
                MachineMapper.banStatistics.get(machineURIToID.get(resourceURI) % banNumber).getStatistics().get(machineType).getUnUsed().getAndIncrement();
                MachineMapper.banStatistics.get(machineURIToID.get(resourceURI) % banNumber).getStatistics().get(machineType).getUsed().getAndDecrement();
                machineStatistics.getStatistics().get(machineType).getUnUsed().getAndIncrement();
                machineStatistics.getStatistics().get(machineType).getUnUsed().getAndDecrement();
            } else {
                apartmentStatistics.get(machineURIToID.get(resourceURI) % apartmentNumber).getStatistics().get(machineType).getUsed().getAndIncrement();
                apartmentStatistics.get(machineURIToID.get(resourceURI) % apartmentNumber).getStatistics().get(machineType).getUnUsed().getAndDecrement();
                floorStatistics.get(machineURIToID.get(resourceURI) % floorNumber).getStatistics().get(machineType).getUsed().getAndIncrement();
                floorStatistics.get(machineURIToID.get(resourceURI) % floorNumber).getStatistics().get(machineType).getUnUsed().getAndDecrement();
                banStatistics.get(machineURIToID.get(resourceURI) % banNumber).getStatistics().get(machineType).getUsed().getAndIncrement();
                banStatistics.get(machineURIToID.get(resourceURI) % banNumber).getStatistics().get(machineType).getUnUsed().getAndDecrement();
                machineStatistics.getStatistics().get(machineType).getUnUsed().getAndDecrement();
                machineStatistics.getStatistics().get(machineType).getUnUsed().getAndIncrement();
            }
            machineCondition.put(resourceURI, !machineCondition.get(resourceURI));

        } else if (parkingCondition.containsKey(resourceURI)) {
            if (parkingCondition.get(resourceURI)) {
                parkingFloorStatistics.get(parkingURIToID.get(resourceURI) % 2).getStatistics().get(ANTITHEFT.getIndex()).getUnUsed().getAndIncrement();
                parkingFloorStatistics.get(parkingURIToID.get(resourceURI) % 2).getStatistics().get(ANTITHEFT.getIndex()).getUsed().getAndDecrement();
                parkingStatistics.getStatistics().get(ANTITHEFT.getIndex()).getUnUsed().getAndIncrement();
            } else {
                parkingFloorStatistics.get(parkingURIToID.get(resourceURI) % 2).getStatistics().get(ANTITHEFT.getIndex()).getUsed().getAndIncrement();
                parkingFloorStatistics.get(parkingURIToID.get(resourceURI) % 2).getStatistics().get(ANTITHEFT.getIndex()).getUnUsed().getAndDecrement();
                machineStatistics.getStatistics().get(ANTITHEFT.getIndex()).getUnUsed().getAndDecrement();
            }
            parkingCondition.put(resourceURI, !MachineMapper.parkingCondition.get(resourceURI));
        }
    }


    public static void main(String args[]) {
        MachineMapper.init();
        System.out.println("sieze:" + MachineMapper.machineCondition.size());
        System.out.println("sieze:" + MachineMapper.parkingCondition.size());
    }

}
