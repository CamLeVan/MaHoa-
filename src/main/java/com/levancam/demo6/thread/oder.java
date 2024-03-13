package com.levancam.demo6.thread;

public class oder implements Runnable{
    @Override
    public void run() {
        while (true){
            System.out.println("oder");
        }
    }
}
