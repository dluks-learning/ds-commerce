package dev.dluks.dscommerce.services;

import dev.dluks.dscommerce.dtos.ProductDTO;
import dev.dluks.dscommerce.models.Product;
import dev.dluks.dscommerce.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(Pageable pageable) {
        Page<Product> result = productRepository.findAll(pageable);
        return result.map(ProductDTO::new);
    }

    @Transactional
    public ProductDTO insert(ProductDTO dto) {
        Product toInsert = new Product();
        copyDToToProduct(dto, toInsert);
        toInsert = productRepository.save(toInsert);
        return new ProductDTO(toInsert);
    }

    @Transactional
    public ProductDTO update(Long id, ProductDTO dto) {
        Product toUpdate = productRepository.getReferenceById(id);
        copyDToToProduct(dto, toUpdate);
        toUpdate = productRepository.save(toUpdate);
        return new ProductDTO(toUpdate);
    }

    private void copyDToToProduct(ProductDTO dto, Product toUpdate) {
        toUpdate.setName(dto.name());
        toUpdate.setDescription(dto.description());
        toUpdate.setPrice(dto.price());
        toUpdate.setImgUrl(dto.imgUrl());
    }
}
