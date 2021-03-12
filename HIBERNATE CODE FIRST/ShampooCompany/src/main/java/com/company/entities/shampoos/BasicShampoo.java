package com.company.entities.shampoos;

import com.company.entities.ingredients.BasicIngredient;
import com.company.entities.labels.BasicLabel;
import com.company.interfaces.Shampoo;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "shampoos")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "shampoo_type", discriminatorType = DiscriminatorType.STRING)
public abstract class BasicShampoo implements Shampoo {

    @Id
    private long id;

    @Basic
    private BigDecimal price;

    @Basic
    private String brand;

    @Enumerated
    private Size size;

    @OneToOne(optional = true, cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private BasicLabel label;


    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "shampoos_ingredients",
            joinColumns = @JoinColumn(name = "shampoo_id",
                    referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id",
                    referencedColumnName = "id"))
    private Set<BasicIngredient> ingredients;

    protected BasicShampoo() {
        this.setIngredients(new HashSet<>());
    }

    public BasicShampoo(BigDecimal price, String brand, Size size, BasicLabel label) {
        this.price = price;
        this.brand = brand;
        this.size = size;
        this.label = label;
        this.setIngredients(new HashSet<>());
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String getBrand() {
        return brand;
    }

    @Override
    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public Size getSize() {
        return size;
    }

    @Override
    public void setSize(Size size) {
        this.size = size;
    }

    @Override
    public BasicLabel getLabel() {
        return label;
    }

    @Override
    public void setLabel(BasicLabel label) {
        this.label = label;
    }

    @Override
    public Set<BasicIngredient> getIngredients() {
        return ingredients;
    }

    @Override
    public void setIngredients(Set<BasicIngredient> ingredients) {
        this.ingredients = ingredients;
    }
}
