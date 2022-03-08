package main;

import java.util.Random;

public class Asistent  implements Runnable{

    private int id;

    public Asistent(int id){
        this.id = id;
    }

    @Override
    public void run() {
        while(true){
            try {
                Student s = Main.queue.take();
                if(s.getArrival() + Main.start - System.currentTimeMillis() > 0)
                    Thread.sleep(s.getArrival() + Main.start - System.currentTimeMillis());
                long pocetakOdbrane = System.currentTimeMillis() - Main.start;
                int grade = new Random().nextInt(5) + 5;

                System.out.println(
                        " Thread: " + s.getId() +
                        " Arrival: " + s.getArrival() +
                        " Asistent: " + this.id +
                        " TTC: " + s.getLen() + " : " + pocetakOdbrane +
                        " Score: " + grade
                );
                Thread.sleep(s.getLen());
                Main.sum.addAndGet(grade);
                Main.numb.addAndGet(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
