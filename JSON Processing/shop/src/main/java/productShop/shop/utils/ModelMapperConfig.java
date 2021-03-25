package productShop.shop.utils;

import org.hibernate.collection.spi.PersistentCollection;
import org.modelmapper.Condition;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.data.mapping.context.MappingContext;
import productShop.shop.models.Product;
import productShop.shop.models.User;
import productShop.shop.models.dto.view.ProductNamePriceSellerNameDto;

public class ModelMapperConfig {

    private final ModelMapper modelMapper;

    public ModelMapperConfig(final ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.initialize();
    }

    private void initialize() {
        productNamePriceSellerName();
        this.modelMapper.validate();
    }

    private void productNamePriceSellerName() {
        this.modelMapper.addConverter((Converter<Product, ProductNamePriceSellerNameDto>) context -> {
            ProductNamePriceSellerNameDto dto = context.getDestination();
            final User seller = context.getSource().getSeller();
            if (seller.getFirstName() != null) {
                dto.setSellerFullName(String.format("%s %s", seller.getFirstName(), seller.getLastName()));
            } else {
                dto.setSellerFullName(seller.getLastName());
            }
            return dto;
        });

        this.modelMapper
                .createTypeMap(Product.class, ProductNamePriceSellerNameDto.class)
                .addMappings(mapping -> mapping.map(src ->
                                String.format("%s %s",
                                        src.getSeller().getFirstName() == null ? "" : src.getSeller().getFirstName(),
                                        src.getSeller().getLastName()).trim(),
                        ProductNamePriceSellerNameDto::setSellerFullName));


    }
}
