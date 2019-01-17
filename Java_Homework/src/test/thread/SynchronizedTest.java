package test.thread;

public class SynchronizedTest {
    static class Callme {
        void call(String msg) {
            System.out.println("[" + msg + "]");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(")");
        }
    }

    static class Caller implements Runnable {
        String msg;
        Callme target;
        Thread t;

        public Caller(Callme target, String msg) {
            this.msg = msg;
            this.target = target;
            t = new Thread(this);
            t.start();
        }

        @Override
        public void run() {
            synchronized (target) {
                target.call(msg);
            }
        }
    }

    public static void main(String[] args) {
        Callme target = new Callme();
        Caller ob1 = new Caller(target, "Hello");
        Caller ob2 = new Caller(target, "Synchronized");
        Caller ob3 = new Caller(target, "World");
        try {
            ob1.t.join();
            ob2.t.join();
            ob3.t.join();
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }
    }

}