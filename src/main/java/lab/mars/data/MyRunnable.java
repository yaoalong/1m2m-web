package lab.mars.data;

import static lab.mars.mapper.MachineMapper.machineStatistics;
import static lab.mars.mapper.MachineMapper.parkingStatistics;
import static lab.mars.model.MachineTypeEnum.*;

/**
 * Author:yaoalong.
 * Date:2016/6/17.
 * Email:yaoalong@foxmail.com
 */
public class MyRunnable implements Runnable {
    @Override
    public void run() {
        while (true) {
            long used = parkingStatistics.getStatistics().get(ANTITHEFT.getIndex()).getUsed().get();
            long sum = parkingStatistics.getStatistics().get(ANTITHEFT.getIndex()).getSum();
            System.out.println("sum:" + sum + "used:" + used);
            used = machineStatistics.getStatistics().get(ANTITHEFT.getIndex()).getUsed().get();
            sum = machineStatistics.getStatistics().get(ANTITHEFT.getIndex()).getSum();
            System.out.println("machineStatistics:" + used + "sum:" + sum);
            used = machineStatistics.getStatistics().get(LIGHT.getIndex()).getUsed().get();
            sum = machineStatistics.getStatistics().get(LIGHT.getIndex()).getSum();
            System.out.println("lightStatistics:" + used + "sum:" + sum);
            used = machineStatistics.getStatistics().get(AIRCONDIION.getIndex()).getUsed().get();
            sum = machineStatistics.getStatistics().get(AIRCONDIION.getIndex()).getSum();
            System.out.println("AirconditionStatistics:" + used + "sum:" + sum);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
