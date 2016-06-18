package lab.mars.m2m.reality.pojo;

import lab.mars.data.DataGenerate;
import lab.mars.model.StatisticsDO;

import java.util.concurrent.atomic.AtomicInteger;

import static lab.mars.mapper.MachineMapper.*;
import static lab.mars.model.MachineTypeEnum.ANTITHEFT;
import static lab.msrs.web.util.NotificationUtils.parkingCondition;

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
    private boolean value;
    private int parkingFloor;
    private String resourceId;

    public LaserSensor(boolean value, String machineUri) {
        this.value = value;
        this.machineUri = machineUri;
    }

    public LaserSensor(boolean value, DataGenerate dataGenerate, String cntUri, String machineUri, int parkingFloor, String resourceId) {
        this.value = value;
        this.dataGenerate = dataGenerate;
        this.cntUri = cntUri;
        this.machineUri = machineUri;
        this.parkingFloor = parkingFloor;
        this.resourceId = resourceId;
        add();
    }

    @Override
    public void run() {
        value = !value;
        request(new LaserSensor(value, machineUri));
        if (value) {
            parkingFloorStatistics.get(parkingFloor).getStatistics().get(ANTITHEFT.getIndex()).getUsed().getAndDecrement();
            parkingStatistics.getStatistics().get(ANTITHEFT.getIndex()).getUsed().getAndDecrement();

        } else {

          parkingStatistics.getStatistics().get(ANTITHEFT.getIndex()).getUsed().getAndIncrement();
            parkingFloorStatistics.get(parkingFloor).getStatistics().get(ANTITHEFT.getIndex()).getUsed().getAndIncrement();
        }
        parkingCondition.put(resourceId, value);
    }

    private void add() {
        try{
            parkingCondition.put(cntUri, value);
            if (parkingFloor >= parkingFloorStatistics.size()) {
                int[] ints = new int[1];
                ints[0] = parkingPositionCount / parkingFloorCount;
                parkingFloorStatistics.add(parkingFloor, new StatisticsDO(1, ints));
            }
            if (!value) {
                 parkingFloorStatistics.get(parkingFloor).getStatistics().get(ANTITHEFT.getIndex()).getUsed().getAndIncrement();
                parkingStatistics.getStatistics().get(ANTITHEFT.getIndex()).getUsed().getAndIncrement();

            }
            parkingCondition.put(resourceId, value);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public int getValue() {
        return value ? 1 : 0;
    }

}
