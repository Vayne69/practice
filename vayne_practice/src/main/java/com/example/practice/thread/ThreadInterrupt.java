package com.example.practice.thread;

/**
 * @author : Yang Jian
 * @date : 2020/6/30 08:56
 */
public class ThreadInterrupt {
    private Thread thread;
    private boolean flag = false;

    public void execute(Runnable runnable) {
        thread = new Thread(() -> {
            Thread run = new Thread(runnable);
            run.setDaemon(true);
            run.start();
            try {
                run.join();
                flag = true;
            } catch (InterruptedException e) {
                System.out.println("执行任务结束了============");
                e.printStackTrace();
            }
        });
        thread.start();
    }

    public void shutdown(long mill) {
        long time = System.currentTimeMillis();
        while (!flag) {
            if (System.currentTimeMillis() - time >= mill) {
                System.out.println("任务超时，线程打断============");
                thread.interrupt();
                break;
            }
            try {
                thread.sleep(1);
            } catch (InterruptedException e) {
                System.out.println("执行线程被打断============");
                e.printStackTrace();
            }
        }
        flag = false;
    }


    public static void main(String[] args) {
        ThreadInterrupt threadInterrupt = new ThreadInterrupt();
        long start = System.currentTimeMillis();
        threadInterrupt.execute(() -> {
            // while (true){
            //
            // }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        threadInterrupt.shutdown(10000);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
