package lab.mars.m2m.reality.pojo;

import lab.mars.data.DataGenerate;
import lab.mars.model.StatisticsDO;

import java.util.concurrent.atomic.AtomicInteger;

import static lab.mars.mapper.MachineMapper.*;
import static lab.mars.model.MachineTypeEnum.ANTITHEFT;

/**
 * Author:yaoalong.
 * Date:2016/4/29.
 * Email:yaoalong@foxmail.com
 */

/**
 * 停车场红外线
 */
public class LaserSensor extends AbstractSensor {
    private static final long serialVersionUID = 6021429766370514584L;
    static AtomicInteger atomicInteger = new AtomicInteger(0);
    private boolean value;
    private int period;

    public LaserSensor(boolean value, String machineUri) {
        this.value = value;
        this.machineUri = machineUri;
    }

    public LaserSensor(boolean value, int period, DataGenerate dataGenerate, String cntUri, String machineUri) {
        this.value = value;
        this.period = period;
        this.dataGenerate = dataGenerate;
        this.cntUri = cntUri;
        this.machineUri = machineUri;
        add();
    }

    @Override
    public void run() {
        value = !value;
        request(new LaserSensor(value, machineUri));
        int id = parkingURIToID.get(cntUri);
        if (value) {
            parkingFloorStatistics.get(id % 2).getStatistics().get(ANTITHEFT.getIndex()).getUsed().getAndIncrement();
            //System.out.println("增加了");
            synchronized (parkingStatistics){
                parkingStatistics.getStatistics().get(ANTITHEFT.getIndex()).getUsed().getAndIncrement();
            }
        } else {
            parkingStatistics.getStatistics().get(ANTITHEFT.getIndex()).getUsed().getAndDecrement();
           // System.out.println("减少");
            synchronized (parkingFloorStatistics){
                parkingFloorStatistics.get(id % 2).getStatistics().get(ANTITHEFT.getIndex()).getUsed().getAndDecrement();
            }

        }
        parkingCondition.put(cntUri, value);
    }

    public void add() {
        int i = atomicInteger.getAndIncrement();
        parkingCondition.put(cntUri, value);
        parkingIdToURI.put(i, cntUri);
        parkingURIToID.put(cntUri, i);
        int floorId = (i) % 2;
        System.out.println("laser sensor");
        synchronized (parkingFloorStatistics) {
            if (floorId >= parkingFloorStatistics.size()) {
                int[] ints = new int[1];
                ints[0] = parkingCount / 2;
                System.out.println("添加了对应的");
                parkingFloorStatistics.add(floorId, new StatisticsDO(1, ints));
            }
            if (value) {
                parkingFloorStatistics.get(floorId).getStatistics().get(ANTITHEFT.getIndex()).getUsed().getAndIncrement();
            }
            else{
                parkingFloorStatistics.get(floorId).getStatistics().get(ANTITHEFT.getIndex()).getUsed().getAndDecrement();
            }
        }
    }

    @Override
    public int getValue() {
        return value ? 1 : 0;
    }

}
