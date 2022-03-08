package main;

import java.time.Clock;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    public static AtomicInteger sum = new AtomicInteger(0);
    public static AtomicInteger numb = new AtomicInteger(0);
    public static BlockingDeque<Student> queue = new LinkedBlockingDeque<>();
    public static List<Student> studentsList = new ArrayList<>();
    public static CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
    public static long start = 0;

    public static void main(String[] args) {

        //init students
        System.out.println("Uneti broj studenata: ");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        for(int i = 0; i < n; i++){
            Student s = new Student(i);
            studentsList.add(s);
        }

        Collections.sort(studentsList);

        for (int i = 0; i < n; i++){
            queue.add(studentsList.get(i));
        }

        //Threads init

        ExecutorService pool = Executors.newFixedThreadPool(3);
        start = System.currentTimeMillis();
        pool.submit(new Profesor(1));
        pool.submit(new Profesor(2));
        pool.submit(new Asistent(1));

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        pool.shutdownNow();
        System.out.println(sum.get() * 1.0 / numb.get());

    }
}
