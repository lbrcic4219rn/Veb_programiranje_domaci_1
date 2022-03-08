package main;

import java.util.Random;

public class Student implements Comparable{
    private long arrival;
    private long len;
    private int id;

    public Student(int id) {
        this.id = id;
        long arrivalRand = new Random().nextInt(1000);
        this.arrival = arrivalRand;
        this.len = new Random().nextInt(500) + 500;
    }


    public long getArrival() {
        return arrival;
    }

    public int getId() {
        return id;
    }

    public long getLen() {
        return len;
    }

    @Override
    public int compareTo(Object o) {
        Student s = (Student) o;
        if(s.arrival > this.arrival)
            return -1;
        if(s.arrival == this.arrival)
            return 0;
        return 1;
    }

    @Override
    public String toString() {
        return "Student{" +
                ", arrival=" + arrival +
                ", len=" + len +
                ", id=" + id +
                '}';
    }
}
