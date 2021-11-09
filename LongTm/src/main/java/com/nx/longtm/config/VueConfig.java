package com.nx.longtm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class VueConfig {
    //全局配置跨域
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                //针对某一网址 且请求路径是两级/../...
                //registry.addMapping("/*/*").allowedOrigins("http://10.252.96.120");
                //针对所有网址，且请求路径是多级/  or /../..  or /../../...
                registry.addMapping("/**").allowedOrigins("*");
                registry.addMapping("/**").allowCredentials(false);
            }
        };
    }

}
