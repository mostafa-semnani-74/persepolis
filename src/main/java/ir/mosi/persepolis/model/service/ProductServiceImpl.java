package ir.mosi.persepolis.model.service;

import ir.mosi.persepolis.controller.ProductController;
import ir.mosi.persepolis.exception.ProductNotFoundException;
import ir.mosi.persepolis.model.entity.Product;
import ir.mosi.persepolis.model.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    Logger logger = LoggerFactory.getLogger(ProductController.class);
    private final ProductRepository productRepository;
    private final String prodNotFoundMsg = "Product not found with id : ";

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(Long id) throws ProductNotFoundException {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(prodNotFoundMsg + id));
    }

    @Override
    @Transactional
    public Product create(Product product) {
        return productRepository.save(product);
    }

    @Override
    @Transactional
    public Product update(Long id, Product productForUpdate) throws ProductNotFoundException {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(prodNotFoundMsg + id));

        product.setName(productForUpdate.getName());
        return productRepository.save(product);
    }

    @Override
    @Transactional
    public void delete(Long id) throws ProductNotFoundException {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(prodNotFoundMsg + id));

        productRepository.delete(product);
    }
}
