package com.example.spring.school.student.service.concrete;

public class Singleton {


    public static void main(String[] args) throws InterruptedException {


        Thread thread = new Thread(Student::getInstance);
        Thread thread2 = new Thread(Student::getInstance);
        Thread thread3 = new Thread(Student::getInstance);

        thread.start();
        thread2.start();
        thread3.start();

        thread.join();
        thread2.join();
        thread3.join();

    }


    private static class Student {

        private static Student studentInstance;

        public static synchronized Student getInstance(){

            if (studentInstance != null) {
                System.out.println("Returning exiting instance...");
            } else {
                System.out.println("Creating new instance...");
                studentInstance = new Student("com.example.spring.school.student.controller.Test");
            }
            return studentInstance;
        }

        private String name;

        private Student(String name) {
            this.name = name;
        }

    }

}
