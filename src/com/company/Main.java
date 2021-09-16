package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class Main {
    final static int THREADS_COUNT = 4;

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        task1();
        task2();
    }

    private static void task1() throws InterruptedException {
        ThreadGroup threadGroup = new ThreadGroup("группа");
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < THREADS_COUNT; i++) {
            threads.add(new MyThread(threadGroup, "поток "+ (i + 1)));
        }
        threads.forEach(Thread::start);

        Thread.sleep(10000);

        threadGroup.interrupt();
    }

    private static void task2() throws InterruptedException, ExecutionException {
        CallableThread callableThread1 = new CallableThread();
        final ExecutorService threadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        final Future<Integer> task = threadPool.submit(callableThread1);// Получаем результат
        final Integer resultOfTask = task.get();
        System.out.printf("Количество сообщений: %s\n", resultOfTask);

        System.out.println("Все задачи");

        List<Callable<Integer>> callables = Arrays.asList(new CallableThread(), new CallableThread(), new CallableThread(), new CallableThread());
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
