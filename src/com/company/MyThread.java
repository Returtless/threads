package com.company;

class MyThread extends Thread {

    public MyThread(ThreadGroup group, String name) {
        super(group, name);
    }
    @Override
    public void run() {
        try {
            while(!isInterrupted()) {
                System.out.printf("Я %s. Всем привет!\n", getName());
                Thread.sleep(2500);
            }
        } catch (InterruptedException err) {

        } finally{
            currentThread().interrupt();
            System.out.printf("%s завершен\n", getName());
        }
    }

}