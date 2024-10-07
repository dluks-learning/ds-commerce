package dev.dluks.dscommerce.controllers;

import dev.dluks.dscommerce.dtos.ProductDTO;
import dev.dluks.dscommerce.services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id) {
        ProductDTO productDTO = productService.findById(id);
        return ResponseEntity.ok(productDTO);
    }

    // sample query: http://localhost:8080/products?size=12&page=0&sort=name,desc
    @GetMapping({"", "/"})
    public ResponseEntity<Page<ProductDTO>> findAll(Pageable pageable) {
        Page<ProductDTO> productDTOPage = productService.findAll(pageable);
        return ResponseEntity.ok(productDTOPage);
    }

    @PostMapping({"", "/"})
    public ResponseEntity<ProductDTO> insert(@RequestBody ProductDTO dto) {
        ProductDTO productDTO = productService.insert(dto);

        URI uri = URI.create("/products/" + productDTO.id());
        return ResponseEntity.created(uri).body(productDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> update(@PathVariable Long id,
                                             @RequestBody ProductDTO dto) {
        ProductDTO productDTO = productService.update(id, dto);
        return ResponseEntity.ok(productDTO);
    }
}
