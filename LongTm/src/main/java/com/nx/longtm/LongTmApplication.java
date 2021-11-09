package com.nx.longtm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@MapperScan("com.nx.longtm.dao")
@SpringBootApplication
@EnableSwagger2
public class LongTmApplication {

    public static void main(String[] args) {
        SpringApplication.run(LongTmApplication.class, args);
    }

}
