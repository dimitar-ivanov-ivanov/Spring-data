package productShop.shop.services.interfaces;

import productShop.shop.models.Category;
import productShop.shop.models.dto.binding.CategoryDto;

public interface CategoryService {

    void saveAll(CategoryDto[] dtos);

    Category getRandomEntity();
}
