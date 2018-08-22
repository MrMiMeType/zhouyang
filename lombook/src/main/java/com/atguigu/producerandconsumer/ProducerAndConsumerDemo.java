package com.atguigu.producerandconsumer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//模拟生产一个消费一个的情形，共进行10轮
//线程 操纵 资源类 高内聚 低耦合 判断 干活 通知 虚假唤醒用while
//资源类
class Cake{
    private int number = 0;
    private Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();


    /*public synchronized void consumCake() throws InterruptedException {
       //判断
       while (number == 0) {
           this.wait();
       }
       //干活
       --number;
       System.out.println(Thread.currentThread().getName()+"消费了一块蛋糕！还剩"+number+"块蛋糕！");
       //通知
       this.notifyAll();
   }

  public synchronized void productCake() throws InterruptedException {
           //判断
           while (number != 0) {
               this.wait();
           }
           //干活
           ++number;
           System.out.println(Thread.currentThread().getName()+"生产了一块蛋糕！还剩"+number+"块蛋糕！");
           //通知
           this.notifyAll();
   }*/
    //这个为什么不行呢？因为是一套的！
    //生产的方法
    public void productCake() throws InterruptedException {
       lock.lock();
       try {
            //判断
            while ( number != 0){
                condition.await();
            }
            //干活
            ++number;
            System.out.println(Thread.currentThread().getName()+"生产了一个蛋糕！还剩"+number+"块蛋糕！");
            //通知
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void consumerCake() throws InterruptedException {
        lock.lock();
        try {
            //判断
            while (number == 0){
                condition.await();
            }
            //干活
            --number;
            System.out.println(Thread.currentThread().getName()+"消费了一块蛋糕！还剩"+number+"块蛋糕！");
            //通知
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            condition.signalAll();
        }
    }
}
public class ProducerAndConsumerDemo {
    public static void main(String[] args) {
        Cake cake = new Cake();
        new Thread(() -> {
            for (int i = 0; i <= 10; i++) {
                try {
                    cake.productCake();
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();

        new Thread(() -> {
            for (int i = 0; i <= 10; i++) {
                try {
                    cake.productCake();
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();

        new Thread(() -> {
            for (int i = 1; i <= 10 ; i++) {
                try {
                    cake.consumerCake();
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } },"C").start();

        new Thread(() -> {
            for (int i = 1; i <= 10 ; i++) {
                try {
                    cake.consumerCake();
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } },"D").start();

        /*new Thread(() -> {
            for (int i = 1; i <= 10 ; i++) {
                try {
                    cake.productCake();
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } },"C").start();

        new Thread(() -> {
            for (int i = 1; i <= 10 ; i++) {
                try {
                    cake.productCake();
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } },"D").start();*/
    }
}
