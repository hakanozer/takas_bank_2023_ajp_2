package useThread;

public class Account implements Runnable {

    int total = 0;
    public Account (int total) {
        this.total = total;
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        MainAccount.call(threadName, total);
    }



}
