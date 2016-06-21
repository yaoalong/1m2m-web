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

    public StatisticsDO(int length, int[] lengths) {
        for (int i = 0; i < length; i++) {
            statistics.put(i, new StatisticsMessage(lengths[i]));
        }
    }

    public ConcurrentHashMap<Integer, StatisticsMessage> getStatistics() {
        return statistics;
    }

    public static class StatisticsMessage {
        private AtomicLong used = new AtomicLong(0);
        private long sum;

        public StatisticsMessage(long sum) {
            this.sum = sum;
        }

        public AtomicLong getUsed() {
            return used;
        }

        public long getSum() {
            return sum;
        }
    }
}
