package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Message;

/**
 * Repository interface for {@link Message} entities.
 */
public interface MessageRepository extends JpaRepository<Message, Integer> {
}
