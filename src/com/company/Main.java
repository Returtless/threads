package com.company;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        ThreadGroup threadGroup = new ThreadGroup("группа");

        Thread thread1 = new MyThread(threadGroup, "поток 1");
        Thread thread2 = new MyThread(threadGroup, "поток 2");
        Thread thread3 = new MyThread(threadGroup, "поток 3");
        Thread thread4 = new MyThread(threadGroup, "поток 4");

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

        Thread.sleep(10000);

        threadGroup.interrupt();
    }
}
