package bookshop.system.services.interfaces;

import bookshop.system.models.Category;

import java.util.Set;

public interface CategoryService {

    void registerCategory(Category category);

    Set<Category> getRandomCategories();
}
