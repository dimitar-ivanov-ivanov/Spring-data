package bookshop.system.services;

import bookshop.system.models.Author;
import bookshop.system.models.Category;
import bookshop.system.repositories.CategoriesRepository;
import bookshop.system.services.interfaces.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoriesRepository categoriesRepository;

    @Autowired
    public CategoryServiceImpl(CategoriesRepository categoriesRepository) {
        this.categoriesRepository = categoriesRepository;
    }

    @Override
    public void registerCategory(Category category) {
        categoriesRepository.save(category);
    }

    @Override
    public Set<Category> getRandomCategories() {
        List<Category> categories = categoriesRepository.findAll();
        int random = (int) (Math.random() * (categories.size() + 1));
        return new HashSet<>(categories);
    }
}
