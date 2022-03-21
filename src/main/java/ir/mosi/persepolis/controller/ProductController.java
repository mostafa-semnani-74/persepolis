package ir.mosi.persepolis.controller;

import ir.mosi.persepolis.exception.ProductNotFoundException;
import ir.mosi.persepolis.model.entity.Product;
import ir.mosi.persepolis.model.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/rest/api/product")
@RequiredArgsConstructor
public class ProductController {
    Logger logger = LoggerFactory.getLogger(ProductController.class);
    private final ProductService productService;

    @GetMapping("/")
    public List<Product> findAll() {
        List<Product> productList = productService.findAll();
        logger.info("productlist with size " + productList.size() + " found succesfully");
        return productList;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable(value = "id") Long id) throws ProductNotFoundException {
        Product productFoundById = productService.findById(id);
        logger.info("Product with Id " + id + " found succesfully");
        return ResponseEntity.ok().body(productFoundById);
    }

    @PostMapping("/")
    public ResponseEntity<Product> create(@Valid @RequestBody Product product) {
        Product createdProduct = productService.create(product);
        logger.info("Product with id " + createdProduct.getId() + " and with name " + createdProduct.getName()
                + " created succesfully");
        return ResponseEntity.ok().body(createdProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable(value = "id") Long id, @Valid @RequestBody Product product)
            throws ProductNotFoundException {
        Product updatedProduct = productService.update(id, product);
        logger.info("Product with id " + updatedProduct.getId() + " and with new name " + updatedProduct.getName()
                + " updated succesfully");
        return ResponseEntity.ok().body(updatedProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable(value = "id") Long id) throws ProductNotFoundException {
        productService.delete(id);
        logger.info("Product with id " + id + " deleted succesfully");
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

}
