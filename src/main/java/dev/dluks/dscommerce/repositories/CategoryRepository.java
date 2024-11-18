package dev.dluks.dscommerce.repositories;

import dev.dluks.dscommerce.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
