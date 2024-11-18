package dev.dluks.dscommerce.repositories;

import dev.dluks.dscommerce.models.OrderItem;
import dev.dluks.dscommerce.models.OrderItemPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> {
}
