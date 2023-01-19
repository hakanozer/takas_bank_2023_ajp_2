
package com.works.props;

import lombok.Data;

import java.util.List;

@Data
public class Product {

    private Integer id;
    private String title;
    private String description;
    private Integer price;
    private Double discountPercentage;
    private Double rating;
    private Integer stock;
    private String brand;
    private String category;
    private String thumbnail;
    private List<String> images = null;


}
