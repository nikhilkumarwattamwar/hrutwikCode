package GitFolder.hrutwikCode.threadexamples;

public class CountThread extends Thread{
    @Override
    public void run() {
//        super.run();
        for (int i=1;i<5;i++){
            System.out.println(i+" "+getName());
        }
    }
}
class Examples {
    public static void main(String[] args) {
        new CountThread().start();
        new CountThread().start();
        System.out.println("Main Method");
    }
}

class CountThreadUsingRunnable implements Runnable{
    @Override
    public void run() {
        for (int i=1;i<5;i++){
            System.out.println(i+" "+Thread.currentThread().getName());
        }
    }
}
class RunnableExamples {
    public static void main(String[] args) {
        Thread t1=new Thread(new CountThreadUsingRunnable());
        Thread t2=new Thread(new CountThreadUsingRunnable());

        t2.start();
        System.out.println("main thread");
        t1.start();

    }
}

class SleepThread extends Thread {
    public void run() {
        try {
            Thread.sleep(1000);
            System.out.println("Woke up: " + getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
class Example5 {
    public static void main(String[] args) {
        new SleepThread().start();
        System.out.println("main thread");
    }
}

class ThreadName extends Thread{
    ThreadName(String name){
        super(name);
    }

    @Override
    public void run() {
        System.out.println("run method "+getName());
    }
}
class Examples2 {
    public static void main(String[] args) {
        new ThreadName("Alice").start();
        ThreadName t1=new ThreadName("Jack");
        t1.start();
    }
}
class ThreadNameRunnable implements Runnable{

    @Override
    public void run() {
        System.out.println("Thread :"+Thread.currentThread().getName());
    }
}
class Exampl5 {
    public static void main(String[] args) {
        Thread t1=new Thread(new ThreadNameRunnable(),"Alice");
        Thread t2=new Thread(new ThreadNameRunnable(),"john");
        t1.start();
        t2.start();
        t1.setName("jack");
    }
}