package ir.mosi.persepolis.service;

import ir.mosi.persepolis.exception.ProductNotFoundException;
import ir.mosi.persepolis.model.entity.Product;
import ir.mosi.persepolis.model.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Test
    @Order(1)
    public void findAll_ReturnsProductList() {
        List<Product> productList = productService.findAll();
        Assertions.assertEquals(2, productList.size());
    }

    @Test
    @Order(2)
    public void findById_WithExistingProduct_ReturnsProduct() throws ProductNotFoundException {
        Product product = productService.findById(3L);
        Assertions.assertNotNull(product);
        Assertions.assertEquals(product.getName(), "Supra");
    }

    @Test
    @Order(3)
    @Transactional
    public void createProduct_ReturnsProduct() {
        productService.create(Product.builder()
                .name("ae86")
                .build());

        List<Product> productList = productService.findAll();
        Assertions.assertEquals(3, productList.size());
    }

    @Test
    @Order(4)
    @Transactional
    public void updateProduct_ReturnsProduct() throws ProductNotFoundException {
        Product updatedProduct = productService.update(2L, Product.builder()
                .name("Lancer")
                .build());

        Product product = productService.findById(2L);
        Assertions.assertNotNull(product);
        Assertions.assertEquals(product, updatedProduct);
    }

    @Test
    @Order(5)
    @Transactional
    public void deleteProduct_ReturnsAccepted() throws ProductNotFoundException {
        productService.delete(2L);
        List<Product> all = productService.findAll();
        Assertions.assertEquals(1, all.size());
        Assertions.assertEquals(all.get(0).getId(), 3L);
    }

}
