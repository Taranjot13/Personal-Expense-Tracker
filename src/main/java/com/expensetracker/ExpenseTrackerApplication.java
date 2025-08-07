package com.expensetracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * Cloud-Native Personal Expense Tracker
 * 
 * Features:
 * - REST API with Spring Boot
 * - AWS DynamoDB integration
 * - Containerized with Docker
 * - CI/CD ready with GitHub Actions
 * - Monitoring and logging
 */
@SpringBootApplication
@EnableCaching
public class ExpenseTrackerApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(ExpenseTrackerApplication.class, args);
    }
}
