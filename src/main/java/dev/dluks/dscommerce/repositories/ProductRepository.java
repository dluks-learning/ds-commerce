package dev.dluks.dscommerce.repositories;

import dev.dluks.dscommerce.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
