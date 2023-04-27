package com.kimmy.springsecurity.service;

import com.kimmy.springsecurity.model.Product;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.stream.IntStream;

@Service
public class ProductService {

    private List<Product> productList;

    public  void generateProducts (){
        this.productList = IntStream.rangeClosed(1, 100)
                .mapToObj((i) -> Product
                        .builder()
                        .id(i)
                        .name("product" + i)
                        .qty(new Random().nextInt(500))
                        .price(Double.parseDouble(new DecimalFormat("0.00").format(new Random().nextDouble(200))))
                        .build())
                .toList();
    }

    public List<Product> getProductList(){
        this.generateProducts();
        return this.productList;
    }
    public Product getProduct(int id){
        return this.productList
                .stream()
                .filter(product -> id == product.getId())
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Product with  id :" + id + "not found!"));
    }
}
