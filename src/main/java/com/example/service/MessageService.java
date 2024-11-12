package com.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.MessageUpdateRequest;
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

        // postedBy must refer to a real, existing user
        accountRepository.findById(message.getPostedBy())
                .orElseThrow(() -> new ResourceNotFoundException("Account not found"));

        // messageText is not blank, is not over 255 characters
        if (message.getMessageText().length() <= 255 &&
                !message.getMessageText().equals("")) {
            return messageRepository.save(message);
        }
        throw new BadRequestException("Message must be between 1 and 255 characters");
    }

    public List<Message> getAllMessages() {

        // Return empty list if no messages
        ArrayList<Message> result = new ArrayList<>();
        for (Message message : messageRepository.findAll()) {
            result.add(message);
        }
        return result;
    }

    public Message getMessageById(int id) {

        // Return null if no message
        Optional<Message> message = messageRepository.findById(id);
        if (message.isPresent()) {
            return message.get();
        } else {
            return null;
        }
    }

    public Integer deleteMessage(int id) {

        // Return 1 if deleted, else null
        Optional<Message> toDelete = messageRepository.findById(id);
        if (toDelete.isPresent()) {
            messageRepository.deleteById(id);
            return 1;
        } else {
            return null;
        }
    }

    public Integer updateMessage(int id, MessageUpdateRequest request) {

        String messageText = request.getMessageText();

        // messageText is not blank and is not over 255 characters
        if (messageText.equals("") || messageText.length() > 255) {
            throw new BadRequestException("Message must be between 1 and 255 characters");
        }

        Optional<Message> toUpdate = messageRepository.findById(id);

        // Update and return 1 if found, else exception
        if (toUpdate.isPresent()) {
            Message message = toUpdate.get();
            message.setMessageText(messageText);
            messageRepository.save(message);
            return 1;
        } else {
            throw new BadRequestException("Message not found");
        }
    }

    public List<Message> getAllMessagesById(int id) {

        // Return empty list if no messages
        List<Message> result = new ArrayList<>();
        for (Message message : messageRepository.findAll()) {
            if (message.getPostedBy() == id) {
                result.add(message);
            }
        }
        return result;
    }
}
