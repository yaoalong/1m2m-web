package lab.mars.model;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Author:yaoalong.
 * Date:2016/6/15.
 * Email:yaoalong@foxmail.com
 */
public class StatisticsDO {
    private ConcurrentHashMap<Integer, StatisticsMessage> statistis = new ConcurrentHashMap<>();

    public StatisticsDO() {
        for (int i = 0; i < 3; i++) {
            statistis.put(i, new StatisticsMessage());
        }
    }

    public ConcurrentHashMap<Integer, StatisticsMessage> getStatistis() {
        return statistis;
    }

    public void setStatistis(ConcurrentHashMap<Integer, StatisticsMessage> statistis) {
        this.statistis = statistis;
    }

    public static class StatisticsMessage {
        private AtomicLong used;
        private AtomicLong unUsed;

        public AtomicLong getUsed() {
            return used;
        }

        public void setUsed(AtomicLong used) {
            this.used = used;
        }

        public AtomicLong getUnUsed() {
            return unUsed;
        }

        public void setUnUsed(AtomicLong unUsed) {
            this.unUsed = unUsed;
        }
    }
}
