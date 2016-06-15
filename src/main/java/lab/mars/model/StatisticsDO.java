package lab.mars.model;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Author:yaoalong.
 * Date:2016/6/15.
 * Email:yaoalong@foxmail.com
 */

/**
 *
 */
public class StatisticsDO {
    private ConcurrentHashMap<Integer, StatisticsMessage> statistics = new ConcurrentHashMap<>();

    public StatisticsDO() {
        for (int i = 0; i < 3; i++) {
            statistics.put(i, new StatisticsMessage());
        }
    }

    public ConcurrentHashMap<Integer, StatisticsMessage> getStatistics() {
        return statistics;
    }

    public static class StatisticsMessage {
        private AtomicLong used = new AtomicLong(0);
        private AtomicLong unUsed = new AtomicLong(0);

        public AtomicLong getUsed() {
            return used;
        }

        public AtomicLong getUnUsed() {
            return unUsed;
        }
    }
}
