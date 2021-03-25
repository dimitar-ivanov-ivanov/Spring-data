package productShop.shop.services.interfaces;

import productShop.shop.models.dto.binding.ProductDto;
import productShop.shop.models.dto.view.ProductNamePriceSellerNameDto;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {

    void saveAll(ProductDto[] dtos);

    List<ProductNamePriceSellerNameDto> getProductByPriceBetween(BigDecimal lower, BigDecimal upper);

}
