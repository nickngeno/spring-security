package com.kimmy.springsecurity.controller;

import com.kimmy.springsecurity.model.Product;
import com.kimmy.springsecurity.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;


    @GetMapping("/v1")
    public String home () {
        return "Welcome home buddy!";
    }

    @GetMapping("/v1/products")
    public List<Product> productList () {
        return  productService.getProductList();
    }

     @GetMapping("/v1/products/{id}")
    public Product getProduct (@PathVariable int id) {
        return  productService.getProduct(id);
    }


}
