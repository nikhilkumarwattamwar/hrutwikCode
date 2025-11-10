package lambda.thread;

public class Thread1 {

    public static void main(String[] args) {
   Object lock = new Object();

    Runnable obj =()->{
        synchronized (lock){
        System.out.println("running a task of thread "+Thread.currentThread().getName());
        for(int i=0;i<5;i++) {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(i);
        }
        }
    };

        Thread t1= new Thread(obj);
        t1.start();
        Thread t2= new Thread(obj);
        t2.start();
    }
}
