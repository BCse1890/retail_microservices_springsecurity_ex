package com.bc.springcloud.security;

import com.bc.springcloud.model.User;
import com.bc.springcloud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

//    @Autowired
//    UserRepository userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findByEmail(username);
//        if(user==null) {
//            throw new UsernameNotFoundException("User not found for email" + username);
//        }
//        System.out.println("user" + user.getEmail() + "passwd" + user.getPassword() + "Role" + user.getRoles());
//        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), user.getRoles());
//    }

    @Autowired
    UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepo.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found for email" + username);
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                user.getRoles());
    }
}
