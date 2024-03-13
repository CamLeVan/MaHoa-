package com.levancam.demo6.thread;

public class consumer extends Thread{
    private int id;
    private buffer buffer;

    public consumer(int id, com.levancam.demo6.thread.buffer buffer) {
        this.id = id;
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (true){
            if (buffer.getSize()>0){
                try {
                    buffer.removeProduct(this.id);
                    sleep((long)Math.random()*100);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
