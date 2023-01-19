
package com.works.props;

import lombok.Data;

import java.util.List;

@Data
public class DummyProducts {

    private List<Product> products = null;
    private Integer total;
    private Integer skip;
    private Integer limit;

}
