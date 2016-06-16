package lab.mars.mapper;

import io.netty.handler.codec.http.HttpMethod;
import lab.mars.data.DataGenerate;
import lab.mars.m2m.protocol.http.HeartBeat;
import lab.mars.model.StatisticsDO;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import static io.netty.handler.codec.http.HttpResponseStatus.OK;

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

    /**
     * 传感器的值
     */
    public static final ConcurrentHashMap<String, Integer> sensorMap = new ConcurrentHashMap<>();


    public static final ConcurrentHashMap<String, Integer> urlTOType = new ConcurrentHashMap<>();
    public static final List<StatisticsDO> banStatistics = new ArrayList<>();
    public static final List<StatisticsDO> floorStatistics = new ArrayList<>();
    public static final List<StatisticsDO> apartmentStatistics = new ArrayList<>();
    public static final List<StatisticsDO> parkingFloorStatistics = new ArrayList<>();
    public static final DataGenerate network = new DataGenerate();
    private static final String ROOT = "/csebase";
    //一户人家的设备的数量
    public static int apartmentNumber = 11;
    public static int floorNumber = 44;//一层的设备数量
    public static int banNumber = 880;//一栋楼的设备数量
    public static int machineCount = 8800;
    public static int parkingCount = 2000;
    public static StatisticsDO machineStatistics = new StatisticsDO(3, new int[]{1760, 3520, 3520});
    public static StatisticsDO parkingStatistics = new StatisticsDO(1, new int[]{2000});

//

    /**
     * 获取系统性能信息
     *
     * @return
     * @throws Exception
     */
    public static HeartBeat getConnection() throws Exception {
        return network.test2Request(HttpMethod.GET, ROOT, OK, null);
    }
//
//    /**
//     * 更新所有的统计信息
//     *
//     * @param resourceURI
//     */
//    public static void update(String resourceURI) {
//        if (machineCondition.containsKey(resourceURI)) {
//            int machineType = urlTOType.get(urlTOType);
//            if (machineCondition.get(resourceURI)) {
//                apartmentStatistics.get(machineURIToID.get(resourceURI) % apartmentNumber).getStatistics().get(machineType).getUnUsed().getAndIncrement();
//                apartmentStatistics.get(machineURIToID.get(resourceURI) % apartmentNumber).getStatistics().get(machineType).getUsed().getAndDecrement();
//                floorStatistics.get(machineURIToID.get(resourceURI) % floorNumber).getStatistics().get(machineType).getUnUsed().getAndIncrement();
//                floorStatistics.get(machineURIToID.get(resourceURI) % floorNumber).getStatistics().get(machineType).getUsed().getAndDecrement();
//                MachineMapper.banStatistics.get(machineURIToID.get(resourceURI) % banNumber).getStatistics().get(machineType).getUnUsed().getAndIncrement();
//                MachineMapper.banStatistics.get(machineURIToID.get(resourceURI) % banNumber).getStatistics().get(machineType).getUsed().getAndDecrement();
//                machineStatistics.getStatistics().get(machineType).getUnUsed().getAndIncrement();
//                machineStatistics.getStatistics().get(machineType).getUnUsed().getAndDecrement();
//            } else {
//                apartmentStatistics.get(machineURIToID.get(resourceURI) % apartmentNumber).getStatistics().get(machineType).getUsed().getAndIncrement();
//                apartmentStatistics.get(machineURIToID.get(resourceURI) % apartmentNumber).getStatistics().get(machineType).getUnUsed().getAndDecrement();
//                floorStatistics.get(machineURIToID.get(resourceURI) % floorNumber).getStatistics().get(machineType).getUsed().getAndIncrement();
//                floorStatistics.get(machineURIToID.get(resourceURI) % floorNumber).getStatistics().get(machineType).getUnUsed().getAndDecrement();
//                banStatistics.get(machineURIToID.get(resourceURI) % banNumber).getStatistics().get(machineType).getUsed().getAndIncrement();
//                banStatistics.get(machineURIToID.get(resourceURI) % banNumber).getStatistics().get(machineType).getUnUsed().getAndDecrement();
//                machineStatistics.getStatistics().get(machineType).getUnUsed().getAndDecrement();
//                machineStatistics.getStatistics().get(machineType).getUnUsed().getAndIncrement();
//            }
//            machineCondition.put(resourceURI, !machineCondition.get(resourceURI));
//
//        } else if (parkingCondition.containsKey(resourceURI)) {
//            if (parkingCondition.get(resourceURI)) {
//                parkingFloorStatistics.get(parkingURIToID.get(resourceURI) % 2).getStatistics().get(ANTITHEFT.getIndex()).getUnUsed().getAndIncrement();
//                parkingFloorStatistics.get(parkingURIToID.get(resourceURI) % 2).getStatistics().get(ANTITHEFT.getIndex()).getUsed().getAndDecrement();
//                parkingStatistics.getStatistics().get(ANTITHEFT.getIndex()).getUnUsed().getAndIncrement();
//            } else {
//                parkingFloorStatistics.get(parkingURIToID.get(resourceURI) % 2).getStatistics().get(ANTITHEFT.getIndex()).getUsed().getAndIncrement();
//                parkingFloorStatistics.get(parkingURIToID.get(resourceURI) % 2).getStatistics().get(ANTITHEFT.getIndex()).getUnUsed().getAndDecrement();
//                machineStatistics.getStatistics().get(ANTITHEFT.getIndex()).getUnUsed().getAndDecrement();
//            }
//            parkingCondition.put(resourceURI, !MachineMapper.parkingCondition.get(resourceURI));
//        }
//    }


    public static void main(String args[]) {
        //   MachineMapper.init();
        System.out.println("sieze:" + MachineMapper.machineCondition.size());
        System.out.println("sieze:" + MachineMapper.parkingCondition.size());
    }

}
