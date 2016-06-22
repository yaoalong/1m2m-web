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

/**
 * 这里是统计信息
 */
public class MachineMapper {
    public static final List<StatisticsDO> banStatistics = new ArrayList<>();
    public static final List<StatisticsDO> floorStatistics = new ArrayList<>();
    public static final List<StatisticsDO> apartmentStatistics = new ArrayList<>();

    public static final ConcurrentHashMap<String, StatisticsDO> parkingFloorAndRegionStatistics = new ConcurrentHashMap<>();


    public static final DataGenerate network = new DataGenerate();
    //停车场的层数
    public static final int parkingFloorCount = 3;
    //停车场区域数量
    public static final int parkingRegionCount = 5;

    //小区区数量
    public static final int regionNumer = 2;
    //楼栋数
    public static final int banNumber = 5;
    //层数
    public static final int floorNumber = 5;
    //户数
    public static final int apartmentNumber = 1;
    //房间数量
    public static final int roomNumber = 4;

    //一户人家的设备的数量
    public static final int apartmentMachineNumber = 11;
    public static final int machineCount = regionNumer * banNumber * floorNumber * apartmentNumber * apartmentMachineNumber;
    //停车位的总数量
    public static final int parkingPositionCount = 495;
    public static final String CSEBASE = "/csebase";
    public static StatisticsDO machineStatistics = new StatisticsDO(3, new int[]{machineCount / 11, machineCount * 5 / 11, machineCount * 5 / 11});
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

    }

}
