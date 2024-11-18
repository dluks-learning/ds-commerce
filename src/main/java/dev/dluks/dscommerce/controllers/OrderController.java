package dev.dluks.dscommerce.controllers;

import dev.dluks.dscommerce.dtos.OrderDTO;
import dev.dluks.dscommerce.services.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'CLIENT')")
    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> findById(
            @PathVariable Long id) {

        OrderDTO orderDTO = orderService.findById(id);
        return ResponseEntity.ok(orderDTO);
    }

    @PreAuthorize("hasAnyRole('CLIENT')")
    @PostMapping()
    public ResponseEntity<OrderDTO> createOrder(
            @Valid @RequestBody OrderDTO dto) {

        OrderDTO orderDTO = orderService.insert(dto);

        URI uri = URI.create("/orders/" + orderDTO.getId());
        return ResponseEntity.created(uri).body(orderDTO);
    }

}
