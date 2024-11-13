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

/**
 * Service class responsible for handling business logic related to messages.
 */
@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final AccountRepository accountRepository;

    /**
     * Constructs a new MessageService with the given repositories.
     *
     * @param messageRepository the repository for accessing message data
     * @param accountRepository the repository for accessing account data
     */
    @Autowired
    public MessageService(MessageRepository messageRepository, AccountRepository accountRepository) {
        this.messageRepository = messageRepository;
        this.accountRepository = accountRepository;
    }

    /**
     * Creates a new message after validating the account and message content.
     *
     * @param message the message to be created
     * @return the saved message
     * @throws ResourceNotFoundException if the account does not exist
     * @throws BadRequestException       if the message text is empty or exceeds 255
     *                                   characters
     */
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

    /**
     * Retrieves all messages from the repository.
     *
     * @return a list of all messages or an empty list if none exist
     */
    public List<Message> getAllMessages() {

        // Return empty list if no messages
        ArrayList<Message> result = new ArrayList<>();
        for (Message message : messageRepository.findAll()) {
            result.add(message);
        }
        return result;
    }

    /**
     * Retrieves a message by its ID.
     *
     * @param id the ID of the message to retrieve
     * @return the message with the specified ID or null if not found
     */
    public Message getMessageById(int id) {

        // Return null if no message
        Optional<Message> message = messageRepository.findById(id);
        if (message.isPresent()) {
            return message.get();
        } else {
            return null;
        }
    }

    /**
     * Deletes a message by its ID.
     *
     * @param id the ID of the message to delete
     * @return 1 if the message was deleted, or null if not found
     */
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

    /**
     * Updates a message's content by its ID.
     *
     * @param id      the ID of the message to update
     * @param request the new message content
     * @return 1 if the message was updated successfully
     * @throws BadRequestException if the message content is invalid or message not
     *                             found
     */
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

    /**
     * Retrieves all messages posted by a specific account ID.
     *
     * @param id the ID of the account
     * @return a list of messages posted by the specified account or an empty list
     *         if none exist
     */
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
