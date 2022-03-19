package ir.mosi.persepolis.model.service;

import ir.mosi.persepolis.exception.ProductNotFoundException;
import ir.mosi.persepolis.model.entity.Product;

import java.util.List;

public interface ProductService {

    public List<Product> findAll();

    public Product findById(Long id) throws ProductNotFoundException;

    public Product create(Product product);

    public Product update(Long id, Product product) throws ProductNotFoundException;

    public void delete(Long id) throws ProductNotFoundException;

}
