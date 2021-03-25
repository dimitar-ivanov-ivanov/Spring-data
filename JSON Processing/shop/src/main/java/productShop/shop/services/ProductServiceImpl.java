package productShop.shop.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import productShop.shop.models.Category;
import productShop.shop.models.Product;
import productShop.shop.models.User;
import productShop.shop.models.dto.binding.ProductDto;
import productShop.shop.models.dto.view.ProductNamePriceSellerNameDto;
import productShop.shop.repositories.ProductRepository;
import productShop.shop.services.interfaces.CategoryService;
import productShop.shop.services.interfaces.ProductService;
import productShop.shop.services.interfaces.UserService;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private ModelMapper modelMapper;
    private CategoryService categoryService;
    private UserService userService;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper, CategoryService categoryService, UserService userService) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        this.categoryService = categoryService;
        this.userService = userService;
    }

    @Override
    public void saveAll(ProductDto[] dtos) {
        Product[] products = modelMapper.map(dtos, Product[].class);
        productRepository.saveAll(Arrays.asList(products.clone()));

        //add random categories
        for (int i = 0; i < products.length; i++) {
            Category category = categoryService.getRandomEntity();
            User buyer = userService.getRandomEntity();
            User seller = userService.getRandomEntity();

            Product product = products[i];
            product.setBuyer(buyer);
            product.setSeller(seller);
            product.getCategories().add(category);
            productRepository.save(product);
        }
    }

    @Override
    public List<ProductNamePriceSellerNameDto> getProductByPriceBetween(BigDecimal lower, BigDecimal upper) {
        return this.productRepository
                .findAllByPriceBetweenOrderByPrice(lower, upper)
                .stream()
                .map(product -> {
                    System.out.println(product.getSeller().getFirstName());
                    System.out.println(product.getSeller().getLastName());
                    ProductNamePriceSellerNameDto dto = modelMapper.map(product, ProductNamePriceSellerNameDto.class);
                    return dto;
                })
                .collect(Collectors.toList());
    }
}
