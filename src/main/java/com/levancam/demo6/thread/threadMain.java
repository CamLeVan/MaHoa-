package com.levancam.demo6.thread;

public class threadMain {
    public static void main(String[] args) {
        buffer buffer = new buffer(50);
        producer p1 = new producer(333,buffer);
        consumer c1 = new consumer(001,buffer);

        c1.start();
        p1.start();
    }
}
