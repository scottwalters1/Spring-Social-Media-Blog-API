package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Message;
import com.example.exception.BadRequestException;
import com.example.repository.MessageRepository;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Message createMessage(Message message) {

        if (message.getMessageText().length() <= 255 &&
            !message.getMessageText().equals("")) {
                return messageRepository.save(message);
            }
        throw new BadRequestException("Message must be between 0 and 255 characters.");

        
    }
}
