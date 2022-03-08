package main;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;

public class Profesor implements Runnable{

    private int id;

    public Profesor(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        while(true){
            try {
                Student s = Main.queue.take();
                if(s.getArrival() + Main.start - System.currentTimeMillis() > 0)
                    Thread.sleep(s.getArrival() + Main.start - System.currentTimeMillis());

                Main.cyclicBarrier.await();
                long pocetakOdbrane = System.currentTimeMillis() - Main.start;

                int grade = new Random().nextInt(5) + 5;
                System.out.println(
                        " Thread: " + s.getId() +
                        " Arrival: " + s.getArrival() +
                        " Profesor: " + this.id +
                        " TTC: " + s.getLen() + " : " + pocetakOdbrane +
                        " Score: " + grade
                );
                Thread.sleep(s.getLen());
                Main.sum.addAndGet(grade);
                Main.numb.addAndGet(1);
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
                break;
            }

        }
    }
}
