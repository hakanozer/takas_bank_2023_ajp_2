package com.works;

import lombok.Data;

@Data
public class DummyProducts {
    private DummyProduct[] products;
    private long total;
    private long skip;
    private long limit;
}
