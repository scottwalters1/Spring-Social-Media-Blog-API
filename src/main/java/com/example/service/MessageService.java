package com.example.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.exception.BadRequestException;
import com.example.exception.ResourceNotFoundException;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;

@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository, AccountRepository accountRepository) {
        this.messageRepository = messageRepository;
        this.accountRepository = accountRepository;
    }

    public Message createMessage(Message message) {

        // throws ResourceNotFoundException
        Account acc = accountRepository.findById(message.getPostedBy()).orElseThrow(() -> new ResourceNotFoundException("Account not found"));

        if (message.getMessageText().length() <= 255 &&
                !message.getMessageText().equals("")) {
            return messageRepository.save(message);
        }
        throw new BadRequestException("Message must be between 1 and 255 characters.");

    }
}
