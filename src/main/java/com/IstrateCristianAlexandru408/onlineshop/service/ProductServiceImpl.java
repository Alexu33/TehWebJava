package com.IstrateCristianAlexandru408.onlineshop.service;

import com.IstrateCristianAlexandru408.onlineshop.dto.Product;
import com.IstrateCristianAlexandru408.onlineshop.entity.ProductEntity;
import com.IstrateCristianAlexandru408.onlineshop.exception.ProductNotFoundException;
import com.IstrateCristianAlexandru408.onlineshop.mapper.ProductMapper;
import com.IstrateCristianAlexandru408.onlineshop.repository.ProductRepository;
import com.IstrateCristianAlexandru408.onlineshop.util.ErrorMessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;


    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(ProductMapper.getInstance()::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Product getProductById(Long id) {
        ProductEntity productEntity = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(String.format(ErrorMessageUtils.PRODUCT_NOT_FOUND, id)));
        return ProductMapper.getInstance().toDto(productEntity);
    }

    @Override
    public Product createProduct(Product product) {
        ProductEntity productEntity = productRepository.save(ProductMapper.getInstance().toEntity(product));
        return ProductMapper.getInstance().toDto(productEntity);
    }

    @Override
    public Product updateProduct(Product product) {
        productRepository.findById(product.getId())
                .orElseThrow(() -> new ProductNotFoundException(String.format(ErrorMessageUtils.PRODUCT_NOT_FOUND, product.getId())));
        ProductEntity productEntity = productRepository.save(ProductMapper.getInstance().toEntity(product));
        return ProductMapper.getInstance().toDto(productEntity);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(String.format(ErrorMessageUtils.PRODUCT_NOT_FOUND, id)));
        productRepository.deleteById(id);

    }
}