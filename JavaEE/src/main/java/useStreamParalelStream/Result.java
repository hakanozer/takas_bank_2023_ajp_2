package useStreamParalelStream;

import java.util.ArrayList;
import java.util.List;

public class Result {

    public List<Customer> data( int count ) {
        List<Customer> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Customer c = new Customer();
            c.setCid(i);
            c.setName("Name : " + i);
            list.add(c);
        }
        return list;
    }

}
