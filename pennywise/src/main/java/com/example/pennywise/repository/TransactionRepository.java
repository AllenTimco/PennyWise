package com.example.pennywise.repository;

import com.example.pennywise.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.time.LocalDate;

public interface TransactionRepository extends JpaRepository<Transaction, Long> 
{

    List<Transaction> findAllByOrderByTransactionDateDesc();

    List<Transaction> findByTransactionDateBetween(LocalDate start, LocalDate end);

}