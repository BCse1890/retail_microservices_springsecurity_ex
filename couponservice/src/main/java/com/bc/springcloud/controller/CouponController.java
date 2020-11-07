package com.bc.springcloud.controller;

import com.bc.springcloud.model.Coupon;
import com.bc.springcloud.repository.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/couponApi")
@RestController
public class CouponController {

    @Autowired
    CouponRepository couponRepository;

    @GetMapping("/coupons/{code}")
    public Coupon getCoupon(@PathVariable("code") String code) {
        return couponRepository.findByCode(code);
    }

    @PostMapping("/coupons")
    public Coupon createCoupon(@RequestBody Coupon coupon) {
        return couponRepository.save(coupon);
    }

}
