package com.tosh.productservice.controller;

import com.tosh.productservice.model.Product;
import com.tosh.productservice.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/productapi")
public class ProductController {
    @Autowired
    private ProductRepo repo;

    @PostMapping(value = "/products")
    private Product create(@RequestBody Product product){
        return repo.save(product);
    }
}
