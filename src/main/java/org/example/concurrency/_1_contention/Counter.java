package org.example.concurrency._1_contention;

import lombok.Getter;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;

@Getter
public class Counter {
    AtomicLong count = new AtomicLong(0);
    long count2 = 0;

    private final ReentrantLock lock = new ReentrantLock();
    private final Object lock2 = new Object();

    public void increment() {
//        synchronized (lock) {
            count.incrementAndGet();
//        }
//        System.out.println(count);
    }
    public void increment2() {
        synchronized (lock2) {
            count2++;
        }
//        System.out.println(count);
    }

}
