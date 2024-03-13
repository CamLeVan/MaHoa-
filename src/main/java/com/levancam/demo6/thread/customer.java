package com.levancam.demo6.thread;

public class customer extends Thread {
    public customer() {
        super();
    }

    @Override
    public void run() {
        while (true){
            System.out.println("customer");

        }
    }
}
