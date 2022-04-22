package com.eneba.enebaback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//TODO remove exclude setting once db is set up
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class EnebaBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(EnebaBackApplication.class, args);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

}
