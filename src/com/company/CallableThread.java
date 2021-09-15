package com.company;

import java.util.Random;
import java.util.concurrent.Callable;

public class CallableThread implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        int countMessages = new Random().nextInt(10);
        for (int i = 0; i < countMessages; i++) {
            System.out.printf("Я %s. Всем привет!\n", Thread.currentThread().getName());
            Thread.sleep(2500);
        }
        return countMessages;
    }
}
