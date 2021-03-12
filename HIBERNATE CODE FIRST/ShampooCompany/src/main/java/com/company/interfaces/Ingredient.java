package com.company.interfaces;

import com.company.entities.shampoos.BasicShampoo;

import java.util.*;
import java.io.Serializable;
import java.math.BigDecimal;

public interface Ingredient extends Serializable {

    String getName();

    void setName(String name);

    int getId();

    void setId(int id);

    BigDecimal getPrice();

    void setPrice(BigDecimal price);

    List<BasicShampoo> getShampoos();

    void setShampoos(List<BasicShampoo> shampoos);
}
