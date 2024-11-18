package dev.dluks.dscommerce.services;

import dev.dluks.dscommerce.dtos.OrderDTO;
import dev.dluks.dscommerce.dtos.OrderItemDTO;
import dev.dluks.dscommerce.models.*;
import dev.dluks.dscommerce.repositories.OrderItemRepository;
import dev.dluks.dscommerce.repositories.OrderRepository;
import dev.dluks.dscommerce.repositories.ProductRepository;
import dev.dluks.dscommerce.services.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;
    private final UserService userService;
    private final AuthService authService;

    public OrderService(OrderRepository orderRepository, OrderItemRepository orderItemRepository, ProductRepository productRepository, UserService userService, AuthService authService) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.productRepository = productRepository;
        this.userService = userService;
        this.authService = authService;
    }

    @Transactional(readOnly = true)
    public OrderDTO findById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Id não encontrado")
        );

        authService.validateSelfOrAdmin(order.getClient().getId());

        return new OrderDTO(order);
    }

    @Transactional
    public OrderDTO insert(OrderDTO dto) {

        Order order = new Order();

        order.setMoment(Instant.now());
        order.setStatus(OrderStatus.WAITING_PAYMENT);

        User user = userService.authenticated();
        order.setClient(user);

        for (OrderItemDTO itemDTO : dto.getItems()) {
            Product product = productRepository.getReferenceById(itemDTO.getProductId());
            OrderItem orderItem = new OrderItem(order, product, itemDTO.getQuantity(), product.getPrice());

            order.getItems().add(orderItem);
        }

        order = orderRepository.save(order);
        orderItemRepository.saveAll(order.getItems());

        return new OrderDTO(order);

    }
}
