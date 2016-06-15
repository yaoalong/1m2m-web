package lab.mars.model;

/**
 * Author:yaoalong.
 * Date:2016/6/15.
 * Email:yaoalong@foxmail.com
 */
public enum MachineTypeEnum {

    ANTITHEFT(0), AIRCONDIION(1), LIGHT(2);
    private int index;

    MachineTypeEnum(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

}
