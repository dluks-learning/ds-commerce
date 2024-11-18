package dev.dluks.dscommerce.services;

import dev.dluks.dscommerce.dtos.CategoryDTO;
import dev.dluks.dscommerce.dtos.ProductDTO;
import dev.dluks.dscommerce.dtos.ProductMinDTO;
import dev.dluks.dscommerce.models.Category;
import dev.dluks.dscommerce.models.Product;
import dev.dluks.dscommerce.repositories.CategoryRepository;
import dev.dluks.dscommerce.repositories.ProductRepository;
import dev.dluks.dscommerce.services.exceptions.DatabaseException;
import dev.dluks.dscommerce.services.exceptions.ResourceNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Transactional(readOnly = true)
    public List<CategoryDTO> findAll() {
        List<Category> result = categoryRepository.findAll();
        return result.stream().map(CategoryDTO::new).toList();
    }

}
