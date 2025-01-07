package com.IstrateCristianAlexandru408.onlineshop.service;

import com.IstrateCristianAlexandru408.onlineshop.dto.Product;

import java.util.List;

public interface ProductService {

    public List<Product> getAllProducts();

    public Product getProductById(Long id);

    public Product createProduct(Product product);

    public Product updateProduct(Product product);

    public void deleteProduct(Long id);

}
