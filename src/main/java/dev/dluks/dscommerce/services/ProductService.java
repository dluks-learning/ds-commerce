package dev.dluks.dscommerce.services;

import dev.dluks.dscommerce.dtos.ProductDTO;
import dev.dluks.dscommerce.models.Product;
import dev.dluks.dscommerce.repositories.ProductRepository;
import dev.dluks.dscommerce.services.exceptions.DatabaseException;
import dev.dluks.dscommerce.services.exceptions.ResourceNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Id não encontrado")
        );
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
        copyDTOToProduct(dto, toInsert);
        toInsert = productRepository.save(toInsert);
        return new ProductDTO(toInsert);
    }

    @Transactional
    public ProductDTO update(Long id, ProductDTO dto) {
        productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Id não encontrado")
        );
        Product toUpdate = productRepository.getReferenceById(id);
        copyDTOToProduct(dto, toUpdate);
        toUpdate = productRepository.save(toUpdate);
        return new ProductDTO(toUpdate);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
        try {
            productRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial");
        }
    }

    private void copyDTOToProduct(ProductDTO dto, Product toUpdate) {
        toUpdate.setName(dto.name());
        toUpdate.setDescription(dto.description());
        toUpdate.setPrice(dto.price());
        toUpdate.setImgUrl(dto.imgUrl());
    }
}
