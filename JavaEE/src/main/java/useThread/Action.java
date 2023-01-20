package useThread;

public class Action implements Runnable{

    String path = "";
    public Action( String path ) {
        this.path = path;
    }

    @Override
    public void run() {
        try {
            long start = System.currentTimeMillis();
            System.out.println("Start: " + start);
            Thread.sleep(5000);
            System.out.println("Thread Call Finish :" + path );
            long end = System.currentTimeMillis();
            System.out.println("End: " + end);
        }catch (Exception ex) {}
    }

}
