package com.justin.system.play;

public class MyThread extends Thread{

    private int count;

    public MyThread(String name) {
        super();
        this.setName(name);
    }

    @Override
    public void run() {
        super.run();
        while(count > 0){
            count--;
            System.out.println("由" + MyThread.currentThread().getName());
        }
    }

    private void test() {
        Runnable run =  () -> System.out.println("runnable");
        Thread thread = new Thread(run);
        thread.start();
    }
}
