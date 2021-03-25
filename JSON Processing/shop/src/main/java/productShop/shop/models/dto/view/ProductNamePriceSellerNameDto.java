package productShop.shop.models.dto.view;

import com.google.gson.annotations.SerializedName;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Builder
public class ProductNamePriceSellerNameDto implements Serializable {

    @NotNull
    @Size(min = 3)
    private String name;

    @NotNull
    private BigDecimal price;

    @NotNull
    @SerializedName("seller")
    private String sellerFullName;

}
