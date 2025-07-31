package oops.abstaction;

public class Example {
    public static void main(String[] args) {
        Printer p= new Printer();
        p.start();
        p.print();
        p.stop();
    }
}

interface Printable{
    void print();  //abstract method
}

abstract class Machine{                     // abstact class
    void start(){
        System.out.println("machine starting...");
    }

    abstract void stop();      //abstact method
}

class Printer extends Machine implements Printable{
    public void print(){
        System.out.println("Printing document...");
    }

    void stop(){
        System.out.println(" printer stopped");
    }
}