package productShop.shop.models;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private long id;

    @Size(min = 3, max = 15)
    private String name;

    public Category() {
    }

    public Category(@Size(min = 3, max = 15) String name) {
        this.name = name;
    }
}
