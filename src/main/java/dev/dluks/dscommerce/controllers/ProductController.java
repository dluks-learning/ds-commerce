package dev.dluks.dscommerce.controllers;

import dev.dluks.dscommerce.dtos.ProductDTO;
import dev.dluks.dscommerce.models.Product;
import dev.dluks.dscommerce.repositories.ProductRepository;
import dev.dluks.dscommerce.services.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ProductDTO findById(@PathVariable Long id) {
        return productService.findById(id);
    }

}
