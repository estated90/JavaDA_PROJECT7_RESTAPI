package com.nnk.springboot.configuration;

import java.util.Arrays;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.security.MyUserPrincipal;

@TestConfiguration
public class SpringSecurityWebAuxTestConfig {

    @Bean
    @Primary
    public UserDetailsService userDetailsService() {
        User basicUser = new User("Basic User", "Qwert1@", "BUser", "ADMIN");
        MyUserPrincipal basicActiveUser = new MyUserPrincipal(basicUser);
        return new InMemoryUserDetailsManager(Arrays.asList(basicActiveUser));
    }
	
}