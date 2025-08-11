package GitFolder.hrutwikCode.threadexamples;

public class ThreadClass extends Thread{
    @Override
    public void run() {
        System.out.println("run method");
    }


    public synchronized void start1() {
        super.start();
        System.out.println("start method");
    }

    public static void main(String[] args) {
        ThreadClass threadClass = new ThreadClass();
        threadClass.start1();
        System.out.println("main method");
    }
}

class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("running: " + Thread.currentThread().getName());
    }
}

 class ThreadExample1 {
    public static void main(String[] args) {
        MyThread t1 = new MyThread();
        MyThread t2 = new MyThread();

        t1.start();
        t2.start();

        System.out.println("Main thread: " + Thread.currentThread().getName());
    }
}

class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println(" running: " + Thread.currentThread().getName());
    }
}

class ThreadExample2 {
    public static void main(String[] args) {
        MyRunnable task = new MyRunnable();

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        t1.start();
        t2.start();

        System.out.println("Main thread: " + Thread.currentThread().getName());
    }
}
class MyThread2 extends Thread{
    @Override
    public void run() {
        System.out.println("Run Method");
    }
}
class ThreadEx{
    public static void main(String[] args) {
        MyThread2 myThread2=new MyThread2();
        myThread2.start();
        System.out.println("main Method");
    }
}

class RunnableEx implements Runnable {
    @Override
    public void run() {
        System.out.println("from Runnable run()");
        System.out.println(" Thread :"+Thread.currentThread().getName());

    }
}
class RunnableExampel{
    public static void main(String[] args) {
        RunnableEx runnableEx=new RunnableEx();
        RunnableEx runnableEx1=new RunnableEx();
        Thread thread1=new Thread(runnableEx1);
        Thread thread=new Thread(runnableEx);
        thread.start();
        thread1.start();
        System.out.println("from main method");
        System.out.println("Main Thread :"+Thread.currentThread().getName());
    }
}