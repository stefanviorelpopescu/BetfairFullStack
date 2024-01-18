package org.example.concurrency._1_contention;

public class MyThread extends Thread {

    private final Counter counter;

    public MyThread(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 1_000_000; i++) {
//            ContentionExample.increment();
            counter.increment();
        }
    }
}