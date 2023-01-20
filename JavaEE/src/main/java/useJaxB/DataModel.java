package useJaxB;

import java.util.ArrayList;
import java.util.List;

public class DataModel {

    public List<Product> result() {
        List<Product> ls = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Product p = new Product();
            p.setPid(i);
            p.setPrice(i*10);
            p.setTitle("Title: " + i);
            Category c = new Category();
            c.setCid(i+1);
            c.setTitle("Category: " + i);
            p.setCategory(c);
            ls.add(p);
        }
        return ls;
    }

}
