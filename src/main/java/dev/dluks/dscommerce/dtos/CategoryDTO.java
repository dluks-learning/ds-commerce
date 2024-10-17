package dev.dluks.dscommerce.dtos;

import dev.dluks.dscommerce.models.Category;

public class CategoryDTO {

    private final Long id;
    private final String name;

    public CategoryDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public CategoryDTO(Category entity) {
        id = entity.getId();
        name = entity.getName();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
