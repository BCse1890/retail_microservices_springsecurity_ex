package com.bc.springcloud.security.config;

import com.bc.springcloud.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

//    @Autowired
//    UserDetailsServiceImpl userDetailsService;
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService);
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.formLogin();
////        http.authorizeRequests().anyRequest().permitAll();
//        http.authorizeRequests().mvcMatchers(HttpMethod.GET, "couponApi/coupons/{code:^[A-Z]*$}", "/", "/index")
//                .hasAnyRole("USER", "ADMIN")
//                .mvcMatchers(HttpMethod.POST, "couponApi/coupons")
//                .hasRole("ADMIN").anyRequest().denyAll().and().csrf().disable();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .mvcMatchers(HttpMethod.GET, "/couponApi/coupons/{code:^[A-Z]*$}", "/index",  "/showGetCoupon", "/getCoupon", "/couponDetails")
                .hasAnyRole("USER", "ADMIN")
                .mvcMatchers(HttpMethod.GET, "/showCreateCoupon", "/createCoupon", "/createResponse").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.POST, "/getCoupon").hasAnyRole("USER", "ADMIN")
                .mvcMatchers(HttpMethod.POST, "/couponApi/coupons", "saveCoupon", "/getCoupon").hasRole("ADMIN")
                .mvcMatchers("/", "/login").permitAll()
                .anyRequest().denyAll().and().csrf().disable().logout().logoutSuccessUrl("/").deleteCookies();

    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
