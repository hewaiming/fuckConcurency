package com.oldratlee.fucking.concurrency;

import java.util.Random;

/**
 * @author Jerry Lee (oldratlee at gmail dot com)
 */
public class InvalidCombinationStateDemo {
    public static void main(String[] args) {
        CombinationStatTask task = new CombinationStatTask();
        Thread thread = new Thread(task);
        thread.start();

        Random random = new Random();
        while (true) {
            int rand = random.nextInt(1000);
            task.state1 = rand;
            task.state2 = rand * 2;
        }
    }

    private static class CombinationStatTask implements Runnable {
        // 对于组合状�?�，�? volatile 不能解决问题
        volatile int state1;
        volatile int state2;

        @Override
        public void run() {
            int c = 0;
            for (long i = 0; ; i++) {
                int i1 = state1;
                int i2 = state2;
                if (i1 * 2 != i2) {
                    c++;
                    System.err.printf("Fuck! Got invalid CombinationStat!! check time=%s, happen time=%s(%s%%), count value=%s|%s\n",
                            i + 1, c, (float) c / (i + 1) * 100, i1, i2);
                } else {
                    // 如果去掉这个输出，则在我的开发机上，发生无效组合的概率由 ~5% 降到 ~0.1%
                    System.out.printf("Emm... %s|%s\n", i1, i2);
                }
            }
        }
    }

}
