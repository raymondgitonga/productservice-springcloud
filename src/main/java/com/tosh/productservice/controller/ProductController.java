package com.tosh.productservice.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.tosh.productservice.model.Coupon;
import com.tosh.productservice.model.Product;
import com.tosh.productservice.repository.ProductRepo;
import com.tosh.productservice.restclient.CouponClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/productapi")
public class ProductController {

    @Autowired
    CouponClient couponClient;

    @Autowired
    private ProductRepo repo;

    @HystrixCommand(fallbackMethod = "sendErrorResponse")
    @PostMapping(value = "/products")
    private Product create(@RequestBody Product product){
        Coupon coupon = couponClient.getCoupon(product.getCouponCode());
        product.setPrice(product.getPrice().subtract(coupon.getDiscount()));
        return repo.save(product);
    }

    private Product sendErrorResponse(Product product){
        return product;
    }
}
