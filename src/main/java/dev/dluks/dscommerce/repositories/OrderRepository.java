package dev.dluks.dscommerce.repositories;

import dev.dluks.dscommerce.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
