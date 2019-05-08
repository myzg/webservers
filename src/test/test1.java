package test;


import datamodel.StudentModel;


public class test1 {
    public static void main(String [] args) {
        Thread [] threads = new Thread[10];
        Runnable runnable = new Thread(new test2());
        for(int i = 0 ; i < 10 ; i++) {
            threads[i] = new Thread(runnable);
            threads[i].start();
        }
    }
}
class test2 implements Runnable {
    private volatile int anInt = 0;
    @Override
    public void run() {
        while(anInt<100) {
            synchronized (this){
                StudentModel model = new StudentModel();
                model.setStu_id(anInt+1);
                System.out.println(Thread.currentThread().getName()+"现在的stu_id 是 ："+model.getStu_id());
                anInt++;
            }

        }
    }

    private  StudentModel get() {
        StudentModel model = new StudentModel();
        model.setStu_id(anInt+1);
        return model;
    }
}