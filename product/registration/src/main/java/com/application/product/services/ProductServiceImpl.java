package com.application.product.services;

import com.application.product.Configuration.FileStorageService;
import com.application.product.DTO.ProductDTO;
import com.application.product.exceptions.ProductNotFoundException;
import com.application.product.models.Product;
import com.application.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private FileStorageService fileStorageService;

    @Override
    public Product save(ProductDTO body) {

        //HTTP-SERVER to test image rendering
        String serverPath = "http://127.0.0.1:3001/";

        var imagepath = serverPath+body.product_img().getOriginalFilename();

        // UPLOAD IMAGE
        fileStorageService.save(body.product_img());

        var product = Product.builder().product_img(imagepath)
                .product_name(body.product_name())
                .price(body.price())
                .category_id(body.category_id())
                .quantity(body.quantity()).build();

        return productRepository.save(product);
    }

    @Override
    public Collection<Product> retrieveAll() {

        return productRepository.findAll();
    }

    @Override
    public Product retrieveProductById(Long id) {
        return productRepository.findById(id).get();

    }
}
