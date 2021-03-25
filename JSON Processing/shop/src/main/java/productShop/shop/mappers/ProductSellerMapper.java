package productShop.shop.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import productShop.shop.models.Product;
import productShop.shop.models.dto.view.ProductNamePriceSellerNameDto;

@Mapper
public interface ProductSellerMapper {

    @Mapping(target = "name", source = "entity.name")
    @Mapping(target = "price", source = "entity.price")
    @Mapping(target = "sellerFullName",
            expression = "java(product.getSeller().getFirstName() + product.getSeller().getLastName())")
    ProductNamePriceSellerNameDto productToPriceNameSellerDto(Product product);

}
