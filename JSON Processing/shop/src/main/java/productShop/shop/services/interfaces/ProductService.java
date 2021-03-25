package productShop.shop.services.interfaces;

import org.springframework.stereotype.Service;
import productShop.shop.models.dto.ProductDto;

public interface ProductService {

    void saveAll(ProductDto[] dtos);
}
