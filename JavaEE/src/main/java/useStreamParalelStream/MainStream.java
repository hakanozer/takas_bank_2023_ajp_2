package useStreamParalelStream;

import java.util.List;

public class MainStream {
    public static void main(String[] args) {

        Result result = new Result();
        List<Customer> ls = result.data(1000);

        long start = System.currentTimeMillis();
        ls
        .parallelStream()
        .filter( item -> item.getCid() > 5  )
        .forEach( item -> {
            try {
                Thread.sleep(100);
            }catch (Exception ex) {}
            System.out.println( item.getName() );
        });
        long end = System.currentTimeMillis();
        long bettween = end - start;
        System.out.println("bettween : " + bettween);

    }
}

/*
stream bettween : 103.184
paralel bettween : 13.233
 */
