package org.example.concurrency;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        String name = "Ion";
        String ref = name;

        System.out.println(name == ref);

        name += "el";
        System.out.println(name == ref);

        String name2 = "Ion";
        System.out.println(name2 == ref);

        String name3 = new String("Ion");
        System.out.println(name3 == ref);

        Integer int1 = 1234;
        Integer int2 = 1234;
        System.out.println(int1 == int2);

        List<String> names = new ArrayList<>();
        List<String> synchronizedList = Collections.synchronizedList(names);

        ExecutorService executorService = new ThreadPoolExecutor(2,
                8,
                10,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10),
                new ThreadPoolExecutor.DiscardOldestPolicy());

        executorService.submit(() -> System.out.println(Thread.currentThread().getName() + " test"));
        executorService.submit(() -> System.out.println(Thread.currentThread().getName() + " test"));
        executorService.submit(() -> System.out.println(Thread.currentThread().getName() + " test"));
        executorService.submit(() -> System.out.println(Thread.currentThread().getName() + " test"));
        executorService.submit(() -> System.out.println(Thread.currentThread().getName() + " test"));
    }
}