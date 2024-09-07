package com.pm.service;

import com.pm.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private static List<Product> productList = new ArrayList<>();

    public Product addProduct(Product saveProduct){
        productList.add(saveProduct);
        return saveProduct;
    }

    public List<Product> fetchAllProduct(){
        return productList;
    }

    public Product fetchProductById(int productId){
        return productList.stream()
                .filter(product -> product.getProductId()==productId)
                .findFirst().orElse(null);
    }
}
