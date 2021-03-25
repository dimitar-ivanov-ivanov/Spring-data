package productShop.shop.mappers;

import productShop.shop.models.Product;
import productShop.shop.models.dto.view.ProductNamePriceSellerNameDto;

public class ProductSellerMapperImpl implements ProductSellerMapper {



    /*
    @Override
    public ProductNamePriceSellerNameDto productToPriceNameSellerDto
            (Product product) {

        if (product == null) {
            return null;
        }

        ProductNamePriceSellerNameDto dto = new ProductNamePriceSellerNameDto();
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());

        if (product.getSeller().getFirstName() != null) {
            dto.setSellerFullName(String.format("%s %s",
                    product.getSeller().getFirstName().trim(),
                    product.getSeller().getLastName().trim()));
        } else {
            dto.setSellerFullName(product.getSeller().getLastName());
        }

        return dto;
    }*/
}
