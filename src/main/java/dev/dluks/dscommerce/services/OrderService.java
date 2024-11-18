package dev.dluks.dscommerce.services;

import dev.dluks.dscommerce.dtos.OrderDTO;
import dev.dluks.dscommerce.models.Order;
import dev.dluks.dscommerce.repositories.OrderRepository;
import dev.dluks.dscommerce.services.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Transactional(readOnly = true)
    public OrderDTO findById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Id não encontrado")
        );
        return new OrderDTO(order);
    }
}
