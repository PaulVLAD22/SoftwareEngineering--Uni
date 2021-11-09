package com.university.ilock.config;

import com.university.ilock.service.DeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@EnableWebMvc
@Configuration
@RequiredArgsConstructor
public class WebConfig  extends WebMvcConfigurerAdapter {
    private final DeviceService pinService;

    @Bean
    public CustomInterceptor customInterceptor(){
        return new CustomInterceptor(pinService);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(customInterceptor());
    }
}
