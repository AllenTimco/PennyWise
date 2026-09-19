package com.example.pennywise.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDate;


 
@Entity // this means treat Transaction as a database entity

@Table(name = "transactions") // stores the records in a database table named transactions


public class Transaction {

    @Id // marks id as the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // lets the databse automatically generate IDs
    private Long id; // store unique id for each new dataset

    @Column(nullable = false, precision = 19, scale = 2)
    /*  
        nullable = false means the amount cant be empty
        precision = 19 means the most amount of digits you can store is 19
        scale = amount of decimal points allowed in the digit
    */
    private BigDecimal amount; // BigDecimal is a imported data type to better represent money

    private String description; // this just stores a description of the purchase

    @Column(nullable = false)
    // again means the the value cant be left as null and needs an input
    private String category; // broader description of the type of purchase

    @Column(nullable = false)
    // again means the the value cant be left as null and needs an input
    private LocalDate transactionDate; // stores year, month, and day

    protected Transaction() 
    {
        // Jpa requires an empty constructor so it can create transaction objects when reading database rows
        // its protected because you should be using the other, more meaninful, constructor instead
        // JPA stands for Java Persistence API
        // It is a java standard for saving java objects in a database
        // otherwise you would have to manually write SQL
    }

    public Transaction(BigDecimal amount, String description, String category, LocalDate transactionDate) {
        this.amount = amount;
        this.description = description;
        this.category = category;
        this.transactionDate = transactionDate;
    }

    // Getters and Setters for all the data in the class

    public Long getId() {
        return id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }
}
