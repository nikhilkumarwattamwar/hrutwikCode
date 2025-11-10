package lambda.thread;

import java.util.LinkedList;
import java.util.Queue;

public class ProducerConsumer {
        Queue<Integer> queue = new LinkedList<>();
        int item = 0;
        Runnable produce = () -> {
            while (true) {
                synchronized (queue) {
                    while (queue.size() == 5) {
                        try {
                            System.out.println("queue is FULL");
                            queue.wait();
                        } catch (InterruptedException e) {
                            System.out.println(e);
                        }
                    }
                    queue.add(item);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        System.out.println(e);
                    }
                    item++;
                    System.out.println(item + " are added");
                    queue.notifyAll();
                }
            }
        };

        Runnable consumer = () -> {
            while (true) {
                synchronized (queue) {
                    while (queue.isEmpty()) {
                        try {
                            System.out.println("queue is EMPTY");
                            queue.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    int items = queue.poll();
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println(items + " is removed");
                    queue.notifyAll();
                }
            }
        };

    public static void main(String[] args) {
        ProducerConsumer p = new ProducerConsumer();
        Thread t1= new Thread(p.produce);
        t1.start();
        Thread t2= new Thread(p.consumer);
        t2.start();
    }

    }
