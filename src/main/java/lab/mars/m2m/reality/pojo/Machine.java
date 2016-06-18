package lab.mars.m2m.reality.pojo;

import lab.mars.data.DataGenerate;
import lab.mars.m2m.protocol.primitive.m2m_primitiveContentType;
import lab.mars.m2m.protocol.resource.m2m_ContentInstance;
import lab.mars.m2m.reflection.ResourceReflection;
import lab.mars.model.MachineBelongInformation;
import lab.mars.model.StatisticsDO;

import javax.xml.bind.JAXBException;
import java.io.StringWriter;
import java.util.concurrent.atomic.AtomicInteger;

import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static lab.mars.m2m.protocol.enumeration.m2m_resourceType.contentInstance;
import static lab.mars.mapper.MachineMapper.*;
import static lab.mars.network.WebNetwork.ASYNC;
import static lab.msrs.web.util.NotificationUtils.INIT;
import static lab.msrs.web.util.NotificationUtils.positionMapMachine;

/**
 * Author:yaoalong.
 * Date:2016/4/29.
 * Email:yaoalong@foxmail.com
 */
public abstract class Machine {
    private static AtomicInteger atomicInteger = new AtomicInteger(0);
    public volatile boolean isClosed;
    protected transient DataGenerate dataGenerate;
    protected String cntUri;
    protected transient MachineBelongInformation machineBelongInformation;


    public void request(Machine machine, int init) {
        int machineType = machineBelongInformation.getMachineType().getIndex();
        if (init == INIT) {
            int i = atomicInteger.getAndIncrement();
            int apartmentId = machineBelongInformation.getApartmentId();
            if (apartmentId >= apartmentStatistics.size()) {
                apartmentStatistics.add(apartmentId, new StatisticsDO(3, new int[]{apartmentMachineNumber / 11, apartmentMachineNumber * 5 / 11, apartmentMachineNumber * 5 / 11}));
            }
            int floorId = machineBelongInformation.getFloorId();
            if (floorId >= floorStatistics.size()) {
                floorStatistics.add(floorId, new StatisticsDO(3, new int[]{apartmentMachineNumber * 4 / 11, apartmentMachineNumber * 20 / 11, apartmentMachineNumber * 20 / 11}));
            }
            int banId = machineBelongInformation.getBanId();
            if (banId >= banStatistics.size()) {
                banStatistics.add(banId, new StatisticsDO(3, new int[]{apartmentMachineNumber * 80 / 11, apartmentMachineNumber * 400 / 11, apartmentMachineNumber * 400 / 11}));
            }
            if (!isClosed) {
                apartmentStatistics.get(apartmentId).getStatistics().get(machineType).getUsed().getAndIncrement();
                floorStatistics.get(floorId).getStatistics().get(machineType).getUsed().getAndIncrement();
                banStatistics.get(banId).getStatistics().get(machineType).getUsed().getAndIncrement();
                machineStatistics.getStatistics().get(machineType).getUsed().getAndIncrement();
            }
            machineIdToURI.put(i, cntUri);
            machineURIToID.put(cntUri, i);
        }
        m2m_primitiveContentType m2m_primitiveContentType = new m2m_primitiveContentType();
        m2m_ContentInstance m2m_contentInstance = new m2m_ContentInstance();
        m2m_contentInstance.con = ResourceReflection.serializeKryo(machine);
        m2m_primitiveContentType.value = m2m_contentInstance;
        StringWriter sw = new StringWriter();
        try {
            dataGenerate.marshaller.get().marshal(m2m_primitiveContentType, sw);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        try {
            dataGenerate.testCreate(cntUri, contentInstance, sw.toString(), OK, ASYNC);//创建一个containerInstance
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public abstract void create(int value);

    /**
     * 更新统计信息
     *
     * @param resourceURI
     * @param value
     */
    //TODO 这里的统计信息有误
    public void update(String resourceURI, boolean value) {
        int machineType = machineBelongInformation.getMachineType().getIndex();
        try {
            if (value) {//当前的状态为关闭，已使用的减1
                apartmentStatistics.get(machineBelongInformation.getApartmentId()).getStatistics().get(machineType).getUsed().getAndDecrement();
                floorStatistics.get(machineBelongInformation.getFloorId()).getStatistics().get(machineType).getUsed().getAndDecrement();
                banStatistics.get(machineBelongInformation.getBanId()).getStatistics().get(machineType).getUsed().getAndDecrement();
                machineStatistics.getStatistics().get(machineType).getUsed().getAndDecrement();
            } else {
                apartmentStatistics.get(machineBelongInformation.getApartmentId()).getStatistics().get(machineType).getUsed().getAndIncrement();
                floorStatistics.get(machineBelongInformation.getFloorId()).getStatistics().get(machineType).getUsed().getAndIncrement();
                banStatistics.get(machineBelongInformation.getBanId()).getStatistics().get(machineType).getUsed().getAndIncrement();
                machineStatistics.getStatistics().get(machineType).getUsed().getAndIncrement();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        positionMapMachine.get(machineBelongInformation.getResourceId()).isClosed = value;
    }

}
