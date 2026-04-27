package com.amanna.billingmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.amanna.billingmanagement.infrastructure")
public class BillingmanagementApplication {
    public static void main(String[] args) {
        SpringApplication.run(BillingmanagementApplication.class, args);
    }
}
