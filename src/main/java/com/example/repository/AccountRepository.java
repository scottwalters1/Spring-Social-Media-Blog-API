package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Account;

/**
 * Repository interface for {@link Account} entities.
 */
public interface AccountRepository extends JpaRepository<Account, Integer> {
}
