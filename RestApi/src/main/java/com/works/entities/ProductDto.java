package com.works.entities;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class ProductDto implements Serializable {
    private final Long pid;
    @Length(min = 2, max = 100)
    @NotEmpty
    @NotNull
    private final String title;
    private final String detail;
    private final Integer price;
}
