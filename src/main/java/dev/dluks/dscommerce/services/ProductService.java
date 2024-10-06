package dev.dluks.dscommerce.services;

import dev.dluks.dscommerce.dtos.ProductDTO;
import dev.dluks.dscommerce.models.Product;
import dev.dluks.dscommerce.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        Optional<Product> result = productRepository.findById(id);

        if (result.isEmpty()) {
            throw new IllegalArgumentException("Product not found");
        }
        Product product = result.get();
        return new ProductDTO(product);
    }

}
