package com.example.spring.school.student.service.concrete;

public class Volatile {

    private volatile static int counter;


    public static void main(String[] args) throws InterruptedException {


        Thread plus = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    counter++;
                }
            }
        });

        Thread minus = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    counter--;
                }
            }
        });



        plus.start();
        minus.start();

        plus.join();
        minus.join();

        System.out.println(counter);



    }


}
