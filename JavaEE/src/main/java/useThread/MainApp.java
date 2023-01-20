package useThread;

public class MainApp {
    public static void main(String[] args) {

        Action ac1 = new Action("jpg-1");
        Thread th1 = new Thread(ac1);

        Action ac2 = new Action("jpg-2");
        Thread th2 = new Thread(ac2);

        Action ac3 = new Action("jpg-3");
        Thread th3 = new Thread(ac3);

        Runnable rn = () -> {
            try {
                th1.setPriority(10);
                th1.start();
                th1.join();

                th2.setPriority(8);
                th2.start();
                th2.join();

                th3.setPriority(9);
                th3.start();
                th3.join();
            }catch (Exception ex) {
                System.err.println("Error : " + ex);
            }
        };
        new Thread(rn).start();

        System.out.println("This Line Call");

    }
}
