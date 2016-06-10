package lab.mars.mapper;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Author:yaoalong.
 * Date:2016/6/10.
 * Email:yaoalong@foxmail.com
 */
public class MachineMapper {
    public static ConcurrentHashMap<String, Boolean> result = new ConcurrentHashMap<String, Boolean>();

    static {
        for (int i = 0; i < 10; i++) {//栋
            for (int j = 0; j < 10; j++) {//楼
                for (int w = 0; w < 10; w++) {//层
                    for (int x = 0; x < 10; x++) {//户

                        for (int y = 0; y < 10; y++) {//房间
                            result.put("/"+i + "/" + j + "/" + w + "/" + x + "/" + y + "/" + 0, new Random().nextBoolean());
                            result.put("/"+i + "/" + j + "/" + w + "/" + x + "/" + y + "/" + 1, new Random().nextBoolean());
                        }
                    }
                }
            }

        }
    }
}
