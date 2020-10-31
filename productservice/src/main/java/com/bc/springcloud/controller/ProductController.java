package com.bc.springcloud.controller;

import com.bc.springcloud.dto.Coupon;
import com.bc.springcloud.model.Product;
import com.bc.springcloud.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/productApi")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private RestTemplate restTemplate;


    @Value("${couponService.url}")
    private String CouponServiceURL;

    @PostMapping("/products")
    public Product createProduct(@RequestBody Product product) {
        Coupon coupon = restTemplate.getForObject(CouponServiceURL + product.getCouponCode(), Coupon.class);
        product.setPrice(product.getPrice().subtract(coupon.getDiscount()));
        return productRepository.save(product);
    }

//    Get product by id
    @GetMapping("/product/{id}")
    public Product getProduct(@PathVariable Long id) {
        return productRepository.findById(id).get();
    }

//    Get all products in DB
    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product updateProduct(@RequestBody Product product) {
        return productRepository.save(product);

    }

    public void deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
    }

}
