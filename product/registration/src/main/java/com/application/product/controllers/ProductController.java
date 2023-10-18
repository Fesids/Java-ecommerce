package com.application.product.controllers;

import com.application.product.DTO.ProductDTO;
import com.application.product.models.Product;
import com.application.product.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

@RestController
//@RequestMapping("/api/v1/product")
@CrossOrigin("*")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/api/v1/product")
    public ResponseEntity<Product> createProduct(@RequestParam("product_name") String product_name,
                                                 @RequestParam("price") BigDecimal price,
                                                 @RequestParam("product_image") MultipartFile multipartFile,
                                                 @RequestParam("category") int category,
                                                 @RequestParam("quantity") int quantity){

        var requestBody = new ProductDTO(category, multipartFile, product_name, price,quantity);

        var resp = productService.save(requestBody);

        return ResponseEntity.ok().body(resp);
    }

    @GetMapping("/api/v1/product/all")
    public ResponseEntity<List<Product>> getAllProducts(){
        return ResponseEntity.ok().body(productService.retrieveAll().stream().toList());
    }

    @GetMapping("/api/v1/product/teste")
    public ResponseEntity<?> teste(){
        return ResponseEntity.ok().body("teste");
    }


    @GetMapping("/api/v1/product/{id}")
    public ResponseEntity<?> getProductById(@PathVariable("id") Long id){

        if (id == null){
            return ResponseEntity.badRequest().body("No ID provided");
        }

        var bodyResponse = productService.retrieveProductById(id);

        return ResponseEntity.ok().body(bodyResponse);


    }



}
