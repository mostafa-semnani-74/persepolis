package ir.mosi.persepolis.controller;

import ir.mosi.persepolis.exception.ProductNotFoundException;
import ir.mosi.persepolis.model.entity.Product;
import ir.mosi.persepolis.model.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/rest/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/")
    public List<Product> findAll() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable(value = "id") Long id) throws ProductNotFoundException {
        return ResponseEntity.ok().body(productService.findById(id));
    }

    @PostMapping("/")
    public ResponseEntity<Product> create(@Valid @RequestBody Product product) {
        return ResponseEntity.ok().body(productService.create(product));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable(value = "id") Long id, @Valid @RequestBody Product product)
            throws ProductNotFoundException {
        return ResponseEntity.ok().body(productService.update(id, product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable(value = "id") Long id) throws ProductNotFoundException {
        productService.delete(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

}
