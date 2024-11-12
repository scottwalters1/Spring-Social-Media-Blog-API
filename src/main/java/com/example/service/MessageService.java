package com.example.service;

import java.util.ArrayList;
import java.util.List;
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
        Account acc = accountRepository.findById(message.getPostedBy())
                .orElseThrow(() -> new ResourceNotFoundException("Account not found"));

        if (message.getMessageText().length() <= 255 &&
                !message.getMessageText().equals("")) {
            return messageRepository.save(message);
        }
        throw new BadRequestException("Message must be between 1 and 255 characters.");

    }

    public List<Message> getAllMessages() {
        ArrayList<Message> result = new ArrayList<>();
        for (Message message : messageRepository.findAll()) {
            result.add(message);
        }
        return result;
    }

    public Message getMessageById(int id) {
        Optional<Message> message = messageRepository.findById(id);
        if (message.isPresent()) {
            return message.get();
        } else {
            return null;
        }
    }

    public Integer deleteMessage(int id) {
        Optional<Message> toDelete = messageRepository.findById(id);
        if (toDelete.isPresent()) { 
            messageRepository.deleteById(id);
            return 1;
        } else {
            return null;
        }
    }
}
