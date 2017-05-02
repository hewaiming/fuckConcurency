package com.oldratlee.fucking.concurrency;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author Jerry Lee (oldratlee at gmail dot com)
 * @see <a href="http://coolshell.cn/articles/9606.html">Java HashMap鐨勬寰幆</a>@<a href="http://weibo.com/haoel">宸﹁�虫湹鑰楀瓙</a>
 */
public class HashMapHangDemo {
    final Map<Integer, Object> holder = new HashMap<Integer, Object>();

    public static void main(String[] args) {
        HashMapHangDemo demo = new HashMapHangDemo();
        for (int i = 0; i < 100; i++) {
            demo.holder.put(i, null);
        }

        Thread thread = new Thread(demo.getConcurrencyCheckTask());
        thread.start();
        thread = new Thread(demo.getConcurrencyCheckTask());
        thread.start();

        System.out.println("Start get in main!");
        for (int i = 0; ; ++i) {
            for (int j = 0; j < 10000; ++j) {
                demo.holder.get(j);

                // 濡傛灉鍑虹幇hashmap鐨刧et hang浣忛棶棰橈紝鍒欎笅闈㈢殑杈撳嚭灏变笉浼氬啀鍑虹幇浜嗐��
                // 鍦ㄦ垜鐨勫紑鍙戞満涓婏紝寰堝鏄撳湪绗竴杞氨瑙傚療鍒拌繖涓棶棰樸��
                System.out.printf("Got key %s in round %s\n", j, i);
            }
        }
    }

    ConcurrencyTask getConcurrencyCheckTask() {
        return new ConcurrencyTask();
    }

    private class ConcurrencyTask implements Runnable {
        Random random = new Random();

        @Override
        public void run() {
            System.out.println("Add loop started in task!");
            while (true) {
                holder.put(random.nextInt() % (1024 * 1024 * 100), null);
            }
        }
    }
}