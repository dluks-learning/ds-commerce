package dev.dluks.dscommerce.dtos;import dev.dluks.dscommerce.models.Product;public record ProductDTO(Long id,                         String name,                         String description,                         Double price,                         String imgUrl) {    public ProductDTO(Product product) {        this(product.getId(),                product.getName(),                product.getDescription(),                product.getPrice(),                product.getImgUrl());    }}