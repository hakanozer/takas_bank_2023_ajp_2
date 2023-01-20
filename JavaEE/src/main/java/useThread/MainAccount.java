package useThread;

public class MainAccount {

    static int total = 0;

    public static void main(String[] args) {

        Account ac1 = new Account(1000);
        Account ac2 = new Account(-200);
        Account ac3 = new Account(500);
        Account ac4 = new Account(-100);

        Thread th1 = new Thread(ac1);
        Thread th2 = new Thread(ac2);
        Thread th3 = new Thread(ac3);
        Thread th4 = new Thread(ac4);

        th1.start();
        th2.start();
        th3.start();
        th4.start();

    }

    public synchronized static void call(String threadName, int val) {
        total = total + val;
        System.out.println(threadName + ": " +"Total : " + total);
    }

}
