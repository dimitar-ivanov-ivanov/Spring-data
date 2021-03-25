package productShop.shop.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private long id;

    @Size(min = 3)
    private String name;

    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "buyer_id")
    private User buyer;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private User seller;

    @ManyToMany
    @JoinTable(
            name = "category_products",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories;

    public Product() {
        this.categories = new HashSet<>();
    }

    public Product(@Size(min = 3) String name, BigDecimal price, User buyer, User seller) {
        this.name = name;
        this.price = price;
        this.buyer = buyer;
        this.seller = seller;
        this.categories = new HashSet<>();
    }
}

