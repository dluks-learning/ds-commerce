package dev.dluks.dscommerce.dtos;

import dev.dluks.dscommerce.models.Product;

public class ProductMinDTO {

    private final Long id;
    private final String name;
    private final Double price;
    private final String imgUrl;

    public ProductMinDTO(Product product) {
        id = product.getId();
        name = product.getName();
        price = product.getPrice();
        imgUrl = product.getImgUrl();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public String getImgUrl() {
        return imgUrl;
    }
}
