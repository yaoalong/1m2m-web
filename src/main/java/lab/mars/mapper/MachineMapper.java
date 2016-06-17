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
    //停车场的层数
    public static final int parkingFloor = 2;
    //楼栋数
    public static final int banNumber = 5;
    //层数
    public static final int floorNumber = 1;
    //户数
    public static final int apartmentNumber = 4;
    //房间数量
    public static final int roomNumber = 5;

    //一户人家的设备的数量
    public static final int apartmentMachineNumber = 11;
    public static final int machineCount = banNumber * floorNumber * apartmentNumber * apartmentMachineNumber;
    //停车位的总数量
    public static final int parkingPositionCount = 40;
    public static final String CSEBASE = "/csebase";
    public static StatisticsDO machineStatistics = new StatisticsDO(3, new int[]{machineCount / 5, machineCount * 2 / 5, machineCount * 2 / 5});
    public static StatisticsDO parkingStatistics = new StatisticsDO(1, new int[]{parkingPositionCount});

//

    /**
     * 获取系统性能信息
     *
     * @return
     * @throws Exception
     */
    public static HeartBeat getConnection() throws Exception {
        return network.test2Request(HttpMethod.GET, CSEBASE, OK, null);
    }

    public static void main(String args[]) {
        System.out.println("sieze:" + MachineMapper.machineCondition.size());
        System.out.println("sieze:" + MachineMapper.parkingCondition.size());
    }

}
