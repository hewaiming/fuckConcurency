package com.oldratlee.fucking.concurrency;

import com.oldratlee.fucking.concurrency.util.Utils;

/**
 * <a href="http://hllvm.group.iteye.com/group/topic/34932">请问R�? 有没有什么工具可以查看正在运行的类的c/汇编代码</a>提到�?<b>代码提升</b>的问题�??
 *
 * @author Jerry Lee (oldratlee at gmail dot com)
 * @see <a href="http://hllvm.group.iteye.com/group/topic/34932">请问R�? 有没有什么工具可以查看正在运行的类的c/汇编代码</a>
 */
public class NoPublishDemo {
    boolean stop = false;

    public static void main(String[] args) {
        // LoadMaker.makeLoad();

        NoPublishDemo demo = new NoPublishDemo();

        Thread thread = new Thread(demo.getConcurrencyCheckTask());
        thread.start();

        Utils.sleep(1000);
        System.out.println("Set stop to true in main!");
        demo.stop = true;
        System.out.println("Exit main.");
    }

    ConcurrencyCheckTask getConcurrencyCheckTask() {
        return new ConcurrencyCheckTask();
    }

    private class ConcurrencyCheckTask implements Runnable {
        @Override
        public void run() {
            System.out.println("ConcurrencyCheckTask started!");
            // 如果主线中stop的�?�可见，则循环会�?出�??
            // 在我的开发机上，几乎必现循环不�??出！（简单安全的解法：在running属�?�上加上volatile�?
            while (!stop) {
            }
            System.out.println("ConcurrencyCheckTask stopped!");
        }
    }
}
