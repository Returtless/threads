package com.company;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        task1();
        task2();
    }

    private static void task1() throws InterruptedException {
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

    private static void task2() throws InterruptedException, ExecutionException {
        CallableThread callableThread1 = new CallableThread();
        final ExecutorService threadPool = Executors.newFixedThreadPool(4);
        final Future<Integer> task = threadPool.submit(callableThread1);// Получаем результат
        final Integer resultOfTask = task.get();
        System.out.printf("Количество сообщений: %s\n", resultOfTask);

        System.out.println("Все задачи");
        CallableThread callableThread2 = new CallableThread();
        CallableThread callableThread3 = new CallableThread();
        CallableThread callableThread4 = new CallableThread();

        List<Callable<Integer>> callables = Arrays.asList(callableThread1, callableThread2, callableThread3, callableThread4);
        List<Future<Integer>> list = threadPool.invokeAll(callables);
        list.forEach(g -> {
            try {
                System.out.printf("Количество сообщений от какой-то задачи: %s\n", g.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });

        System.out.printf("Количество сообщений от одной задачи: %s\n", threadPool.invokeAny(callables));

        threadPool.shutdown();
    }
}
