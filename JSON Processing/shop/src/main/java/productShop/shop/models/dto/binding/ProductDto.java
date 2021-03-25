package productShop.shop.models.dto.binding;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ProductDto {

    @Size(min = 3)
    private String name;

    private BigDecimal price;
}
