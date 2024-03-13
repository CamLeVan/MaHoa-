package com.levancam.demo6.thread;

public class producer extends Thread {
    private int id;
    private buffer buffer;

    public producer(int id, com.levancam.demo6.thread.buffer buffer) {
        this.id = id;
        this.buffer = buffer;
    }
    int i=0;
    @Override
    public void run() {
       while (true){
           if (buffer.getMaxoder()>buffer.getSize()){
               try{
                   buffer.addProduct(i,this.id);
               } catch (Exception e) {
                   throw new RuntimeException(e);
               }
           }
       }
    }
}
