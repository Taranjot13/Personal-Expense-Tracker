package com.expensetracker.dto;

import com.expensetracker.model.Expense;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

/**
 * Response DTO for expense data
 * Optimized for API responses and frontend consumption
 */
public class ExpenseResponse {
    
    private String expenseId;
    private Double amount;
    private String category;
    private String description;
    private String status;
    
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdDate;
    
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime updatedDate;
    
    // Default constructor
    public ExpenseResponse() {}
    
    // Constructor from Expense entity
    public ExpenseResponse(Expense expense) {
        this.expenseId = expense.getExpenseId();
        this.amount = expense.getAmount();
        this.category = expense.getCategory();
        this.description = expense.getDescription();
        this.status = expense.getStatus();
        this.createdDate = expense.getCreatedDate();
        this.updatedDate = expense.getUpdatedDate();
    }
    
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
    }
    
    public String getCategory() {
        return category;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
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
    
    @Override
    public String toString() {
        return "ExpenseResponse{" +
                "expenseId='" + expenseId + '\'' +
                ", amount=" + amount +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                '}';
    }
}
