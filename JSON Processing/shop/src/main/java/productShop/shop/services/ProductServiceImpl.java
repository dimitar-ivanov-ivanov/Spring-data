package productShop.shop.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import productShop.shop.models.Category;
import productShop.shop.models.Product;
import productShop.shop.models.User;
import productShop.shop.models.dto.ProductDto;
import productShop.shop.repositories.CategoryRepository;
import productShop.shop.repositories.ProductRepository;
import productShop.shop.repositories.UserRepository;
import productShop.shop.services.interfaces.ProductService;
import java.util.Arrays;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private ModelMapper modelMapper;
    private CategoryRepository categoryRepository;
    private UserRepository userRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper, CategoryRepository categoryRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void saveAll(ProductDto[] dtos) {
        Product[] products = modelMapper.map(dtos, Product[].class);
        productRepository.saveAll(Arrays.asList(products.clone()));

        //add random categories
        for (int i = 0; i < products.length; i++) {
            Category category = categoryRepository.getRandomEntity();
            User buyer = userRepository.getRandomEntity();
            User seller = userRepository.getRandomEntity();

            Product product = products[i];
            product.setBuyer(buyer);
            product.setSeller(seller);
            product.getCategories().add(category);
            productRepository.save(product);
        }
    }
}
