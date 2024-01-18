package org.example.concurrency._2_lack_of_coordination;

public interface IChannel {

    String take();

    void put(String message);
}
