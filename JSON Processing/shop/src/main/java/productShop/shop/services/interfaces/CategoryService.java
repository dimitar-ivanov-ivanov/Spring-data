package productShop.shop.services.interfaces;

import org.springframework.stereotype.Service;
import productShop.shop.models.dto.CategoryDto;

public interface CategoryService {

    void saveAll(CategoryDto[] dtos);
}
