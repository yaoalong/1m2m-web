package lab.mars.model;

/**
 * Author:yaoalong.
 * Date:2016/6/18.
 * Email:yaoalong@foxmail.com
 */

/**
 * 设备的所在位置信息
 */
public class MachineBelongInformation {
    private int banId;
    private int floorId;
    private int apartmentId;
    private MachineTypeEnum machineType;

    private String resourceId;

    public MachineBelongInformation(int banId, int floorId, int apartmentId, MachineTypeEnum machineTypeEnum) {
        this.banId = banId;
        this.floorId = floorId;
        this.apartmentId = apartmentId;
        this.machineType = machineTypeEnum;
    }

    public int getBanId() {
        return banId;
    }


    public int getFloorId() {
        return floorId;
    }


    public int getApartmentId() {
        return apartmentId;
    }

    public MachineTypeEnum getMachineType() {
        return machineType;
    }


    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public void setMachineType(MachineTypeEnum machineType) {
        this.machineType = machineType;
    }
}
