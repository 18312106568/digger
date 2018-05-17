package com.mrb.digger.configuration;

import com.google.gson.Gson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xiaozefeng
 */
@Configuration
public class GSONConfiguration {
    @Bean
    public Gson gson(){
        return new Gson();
    }
}
