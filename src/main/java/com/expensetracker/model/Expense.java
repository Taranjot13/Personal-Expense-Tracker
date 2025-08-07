package com.expensetracker.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;

import jakarta.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Cloud-native Expense entity optimized for AWS DynamoDB
 * Includes validation, JSON serialization, and cloud-ready annotations
 */
@DynamoDbBean
public class Expense {
    
    private String userId;           // Partition key for multi-tenant support
    private String expenseId;        // Sort key for unique identification
    
    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.01", message = "Amount must be greater than 0")
    @DecimalMax(value = "999999.99", message = "Amount cannot exceed 999,999.99")
    private Double amount;
    
    @NotBlank(message = "Category is required")
    @Size(min = 2, max = 50, message = "Category must be between 2 and 50 characters")
    private String category;
    
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdDate;
    
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime updatedDate;
    
    @Size(max = 255, message = "Description cannot exceed 255 characters")
    private String description;
    
    @Pattern(regexp = "ACTIVE|DELETED", message = "Status must be ACTIVE or DELETED")
    private String status = "ACTIVE";
    
    // Default constructor required for DynamoDB
    public Expense() {
        this.expenseId = UUID.randomUUID().toString();
        this.createdDate = LocalDateTime.now();
        this.updatedDate = LocalDateTime.now();
    }
    
    // Constructor for creating new expenses
    public Expense(String userId, Double amount, String category, String description) {
        this();
        this.userId = userId;
        this.amount = amount;
        this.category = category;
        this.description = description;
    }
    
    // DynamoDB Partition Key
    @DynamoDbPartitionKey
    public String getUserId() {
        return userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    // DynamoDB Sort Key
    @DynamoDbSortKey
    public String getExpenseId() {
        return expenseId;
    }
    
    public void setExpenseId(String expenseId) {
        this.expenseId = expenseId;
    }
    
    public Double getAmount() {
        return amount;
    }
    
    public void setAmount(Double amount) {
        this.amount = amount;
        this.updatedDate = LocalDateTime.now();
    }
    
    public String getCategory() {
        return category;
    }
    
    public void setCategory(String category) {
        this.category = category;
        this.updatedDate = LocalDateTime.now();
    }
    
    public LocalDateTime getCreatedDate() {
        return createdDate;
    }
    
    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }
    
    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }
    
    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
        this.updatedDate = LocalDateTime.now();
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
        this.updatedDate = LocalDateTime.now();
    }
    
    @Override
    public String toString() {
        return "Expense{" +
                "userId='" + userId + '\'' +
                ", expenseId='" + expenseId + '\'' +
                ", amount=" + amount +
                ", category='" + category + '\'' +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
