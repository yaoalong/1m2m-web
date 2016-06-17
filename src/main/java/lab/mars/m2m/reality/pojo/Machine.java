package lab.mars.m2m.reality.pojo;

import lab.mars.data.DataGenerate;
import lab.mars.m2m.protocol.primitive.m2m_primitiveContentType;
import lab.mars.m2m.protocol.resource.m2m_ContentInstance;
import lab.mars.m2m.reflection.ResourceReflection;
import lab.mars.model.MachineTypeEnum;
import lab.mars.model.StatisticsDO;

import javax.xml.bind.JAXBException;
import java.io.StringWriter;
import java.util.concurrent.atomic.AtomicInteger;

import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static lab.mars.m2m.protocol.enumeration.m2m_resourceType.contentInstance;
import static lab.mars.mapper.MachineMapper.*;
import static lab.mars.network.WebNetwork.ASYNC;
import static lab.msrs.web.util.NotificationUtils.INIT;

/**
 * Author:yaoalong.
 * Date:2016/4/29.
 * Email:yaoalong@foxmail.com
 */
public abstract class Machine {
    private static AtomicInteger atomicInteger = new AtomicInteger(0);
    public boolean isClosed;
    protected DataGenerate dataGenerate;
    protected String cntUri;

    public void request(Machine machine, int init, MachineTypeEnum machineTypeEnum) {
        int machineType = machineTypeEnum.getIndex();
        if (init == INIT) {
            int i = atomicInteger.getAndIncrement();
            int apartmentId = i % apartmentMachineNumber;
            if (apartmentId >= apartmentStatistics.size() || apartmentStatistics.get(apartmentId) == null) {
                apartmentStatistics.add(apartmentId, new StatisticsDO(3, new int[]{apartmentMachineNumber / 11, apartmentMachineNumber * 5 / 11, apartmentMachineNumber * 5 / 11}));
            }
            int floorId = i % floorNumber;
            if (floorId >= floorStatistics.size() || floorStatistics.get(floorId) == null) {
                floorStatistics.add(floorId, new StatisticsDO(3, new int[]{apartmentMachineNumber * 4 / 11, apartmentMachineNumber * 20 / 11, apartmentMachineNumber * 20 / 11}));
            }
            int banId = i % banNumber;
            if (banId >= banStatistics.size() || banStatistics.get(banId) == null) {
                banStatistics.add(banId, new StatisticsDO(3, new int[]{apartmentMachineNumber * 80 / 11, apartmentMachineNumber * 400 / 11, apartmentMachineNumber * 400 / 11}));
            }
            apartmentStatistics.get(apartmentId).getStatistics().get(machineType).getUsed().getAndIncrement();
            floorStatistics.get(floorId).getStatistics().get(machineType).getUsed().getAndIncrement();
            banStatistics.get(banId).getStatistics().get(machineType).getUsed().getAndIncrement();
            machineStatistics.getStatistics().get(machineType).getUsed().getAndIncrement();
            machineIdToURI.put(i, cntUri);
            machineURIToID.put(cntUri, i);
            urlTOType.put(cntUri, machineType);
            machineCondition.put(cntUri, machine.isClosed);
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
     * @param machineTypeEnum
     */
    public void update(String resourceURI, boolean value, MachineTypeEnum machineTypeEnum) {
        int machineType = machineTypeEnum.getIndex();
        try {
            if (value) {//当前的状态为关闭，已使用的减1
                apartmentStatistics.get(machineURIToID.get(resourceURI) % apartmentNumber).getStatistics().get(machineType).getUsed().getAndDecrement();
                floorStatistics.get(machineURIToID.get(resourceURI) % floorNumber).getStatistics().get(machineType).getUsed().getAndDecrement();
                banStatistics.get(machineURIToID.get(resourceURI) % banNumber).getStatistics().get(machineType).getUsed().getAndDecrement();
                machineStatistics.getStatistics().get(machineType).getUsed().getAndDecrement();
            } else {
                apartmentStatistics.get(machineURIToID.get(resourceURI) % apartmentNumber).getStatistics().get(machineType).getUsed().getAndIncrement();
                floorStatistics.get(machineURIToID.get(resourceURI) % floorNumber).getStatistics().get(machineType).getUsed().getAndIncrement();
                banStatistics.get(machineURIToID.get(resourceURI) % banNumber).getStatistics().get(machineType).getUsed().getAndIncrement();
                machineStatistics.getStatistics().get(machineType).getUsed().getAndIncrement();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        machineCondition.put(resourceURI, value);
    }

}
