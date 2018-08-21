package com.atguigu.saletickets;
//线程  操作  资源类  高内聚  低耦合

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Ticket{
    //模拟票的张数
    private int number = 30;
    private Lock lock = new ReentrantLock();

    //卖票的方法
    public void saleTicket(){
        lock.lock();
        try {
            if(number > 0){
                --number;
                System.out.println("线程"+Thread.currentThread().getName()+"卖出1张票，还剩"+number+"张票！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
public class SaleTickets {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        new Thread(() -> {for (int i = 1; i <=30 ; i++) ticket.saleTicket();},"A").start();
        new Thread(() -> {for (int i = 1; i <=30 ; i++) ticket.saleTicket();},"B").start();
        new Thread(() -> {for (int i = 1; i <=30 ; i++) ticket.saleTicket();},"C").start();
    }
}
