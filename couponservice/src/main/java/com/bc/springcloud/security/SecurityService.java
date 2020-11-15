package com.bc.springcloud.security;

public interface SecurityService {

    boolean login(String userName, String password);
}
