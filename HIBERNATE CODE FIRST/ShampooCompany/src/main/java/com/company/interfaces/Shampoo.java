package com.company.interfaces;

import com.company.entities.ingredients.BasicIngredient;
import com.company.entities.labels.BasicLabel;
import com.company.entities.shampoos.Size;

import java.math.BigDecimal;
import java.util.Set;

public interface Shampoo {

    long getId();

    void setId(long id);

    String getBrand();

    void setBrand(String brand);

    BigDecimal getPrice();

    void setPrice(BigDecimal price);

    BasicLabel getLabel();

    void setLabel(BasicLabel label);

    Size getSize();

    void setSize(Size size);

    Set<BasicIngredient> getIngredients();

    void setIngredients(Set<BasicIngredient> ingredients);


}
