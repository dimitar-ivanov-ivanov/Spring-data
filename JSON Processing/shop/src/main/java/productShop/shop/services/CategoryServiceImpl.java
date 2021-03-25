package productShop.shop.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import productShop.shop.models.Category;
import productShop.shop.models.dto.binding.CategoryDto;
import productShop.shop.repositories.CategoryRepository;
import productShop.shop.services.interfaces.CategoryService;

import java.util.Arrays;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;
    private ModelMapper modelMapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void saveAll(CategoryDto[] dtos) {
        Category[] categories = modelMapper.map(dtos, Category[].class);
        categoryRepository.saveAll(Arrays.asList(categories));

    }

    @Override
    public Category getRandomEntity() {
        return categoryRepository.getRandomEntity();
    }
}
