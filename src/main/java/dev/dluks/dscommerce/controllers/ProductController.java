package dev.dluks.dscommerce.controllers;

import dev.dluks.dscommerce.dtos.ProductDTO;
import dev.dluks.dscommerce.services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    // sample query: http://localhost:8080/products?size=12&page=0&sort=name,desc
    @GetMapping({"", "/"})
    public Page<ProductDTO> findAll(Pageable pageable) {
        return productService.findAll(pageable);
    }
}
