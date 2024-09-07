package com.pm.controller;

import com.pm.model.Product;
import com.pm.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/v1/products")
public class ProductController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<?> addProduct(@RequestBody Product product){
        product.setProductId(UUID.randomUUID().hashCode());
        Product savedResponse = productService.addProduct(product);
        if(Objects.nonNull(savedResponse)){
            logger.info("saved product {}",savedResponse.toString());
            return ResponseEntity.ok().body(savedResponse);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping
    public ResponseEntity<List<Product>> fetchAllProducts(){
        List<Product> products = productService.fetchAllProduct();
        logger.info("fetch all product {}",products);
        return ResponseEntity.ok().body(products);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<?> fetchProductById(@PathVariable("productId") int productId){
        Product savedProduct = productService.fetchProductById(productId);
        if(Objects.nonNull(savedProduct)){
            logger.info("fetched product based on id {}",savedProduct.toString());
            return ResponseEntity.ok().body(savedProduct);
        }
        return ResponseEntity.badRequest().build();
    }
}
