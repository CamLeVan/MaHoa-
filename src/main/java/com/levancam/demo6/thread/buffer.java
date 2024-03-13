package com.levancam.demo6.thread;

import java.util.ArrayList;

public class buffer {
    public buffer(int maxoder) {

        this.maxoder = maxoder;
    }

    private int maxoder;
    private ArrayList<Integer> products = new ArrayList<>();
    public void addProduct(int product, int productID){
        System.out.println("nhà sản xuất "+productID+"Tên sản phẩm "+product);
        products.add(product);
        System.out.println("còn lại "+ products.size());
    }
    public void removeProduct(int productID){
        System.out.println("Khách hàng "+productID+" Đã mua sản phẩm "+ products.get(0));
        System.out.println("còn lại "+ products.size());
        products.remove(0);
    }
    public int getMaxoder(){
        return this.maxoder;
    }
    public int getSize(){
        return products.size();
    }
}
