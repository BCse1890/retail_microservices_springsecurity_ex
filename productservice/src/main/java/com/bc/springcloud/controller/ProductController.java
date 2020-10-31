package com.bc.springcloud.controller;

import com.bc.springcloud.dto.Coupon;
import com.bc.springcloud.model.Product;
import com.bc.springcloud.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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

}
