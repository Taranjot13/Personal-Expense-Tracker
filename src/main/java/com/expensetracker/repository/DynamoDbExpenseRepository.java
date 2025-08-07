package com.expensetracker.repository;

import com.expensetracker.model.Expense;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryEnhancedRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * DynamoDB implementation of ExpenseRepository
 * Handles all database operations with AWS DynamoDB
 */
@Repository
public class DynamoDbExpenseRepository implements ExpenseRepository {
    
    private final DynamoDbTable<Expense> expenseTable;
    
    @Autowired
    public DynamoDbExpenseRepository(DynamoDbEnhancedClient dynamoDbEnhancedClient) {
        this.expenseTable = dynamoDbEnhancedClient.table("expenses", TableSchema.fromBean(Expense.class));
    }
    
    @Override
    public Expense save(Expense expense) {
        try {
            expenseTable.putItem(expense);
            return expense;
        } catch (Exception e) {
            throw new RuntimeException("Failed to save expense", e);
        }
    }
    
    @Override
    public Expense findByUserIdAndExpenseId(String userId, String expenseId) {
        try {
            Key key = Key.builder()
                    .partitionValue(userId)
                    .sortValue(expenseId)
                    .build();
            
            return expenseTable.getItem(key);
        } catch (Exception e) {
            throw new RuntimeException("Failed to find expense", e);
        }
    }
    
    @Override
    public List<Expense> findByUserIdAndStatus(String userId, String status, int page, int size) {
        try {
            QueryConditional queryConditional = QueryConditional
                    .keyEqualTo(Key.builder().partitionValue(userId).build());
            
            QueryEnhancedRequest queryRequest = QueryEnhancedRequest.builder()
                    .queryConditional(queryConditional)
                    .limit(size)
                    .build();
            
            return expenseTable.query(queryRequest)
                    .items()
                    .stream()
                    .filter(expense -> status.equals(expense.getStatus()))
                    .skip((long) page * size)
                    .limit(size)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Failed to find expenses by status", e);
        }
    }
    
    @Override
    public List<Expense> findByUserIdAndCategoryAndStatus(String userId, String category, String status) {
        try {
            QueryConditional queryConditional = QueryConditional
                    .keyEqualTo(Key.builder().partitionValue(userId).build());
            
            return expenseTable.query(queryConditional)
                    .items()
                    .stream()
                    .filter(expense -> category.equals(expense.getCategory()) && status.equals(expense.getStatus()))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Failed to find expenses by category", e);
        }
    }
    
    @Override
    public List<Expense> findByUserIdAndDateRange(String userId, LocalDateTime startDate, LocalDateTime endDate) {
        try {
            QueryConditional queryConditional = QueryConditional
                    .keyEqualTo(Key.builder().partitionValue(userId).build());
            
            return expenseTable.query(queryConditional)
                    .items()
                    .stream()
                    .filter(expense -> {
                        LocalDateTime createdDate = expense.getCreatedDate();
                        return createdDate.isAfter(startDate) && createdDate.isBefore(endDate);
                    })
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Failed to find expenses by date range", e);
        }
    }
    
    @Override
    public boolean delete(String userId, String expenseId) {
        try {
            Key key = Key.builder()
                    .partitionValue(userId)
                    .sortValue(expenseId)
                    .build();
            
            expenseTable.deleteItem(key);
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete expense", e);
        }
    }
}
