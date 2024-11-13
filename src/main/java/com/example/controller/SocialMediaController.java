package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.MessageUpdateRequest;
import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;

/**
 * TODO: You will need to write your own endpoints and handlers for your
 * controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use
 * the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations.
 * You should
 * refer to prior mini-project labs and lecture materials for guidance on how a
 * controller may be built.
 */

@RestController
public class SocialMediaController {

    private final AccountService accountService;
    private final MessageService messageService;

    /**
     * Constructor for dependency injection of services.
     * 
     * @param accountService the service handling account-related operations
     * @param messageService the service handling message-related operations
     */
    @Autowired
    public SocialMediaController(AccountService accountService, MessageService messageService) {
        this.accountService = accountService;
        this.messageService = messageService;
    }

    /**
     * Registers a new account.
     *
     * @param account the account details to register
     * @return the created account with HTTP status 200 (OK)
     */
    @PostMapping("/register")
    public ResponseEntity<Account> registerAccount(@RequestBody Account account) {
        Account newAccount = null;
        newAccount = accountService.registerAccount(account);
        return ResponseEntity.ok().body(newAccount);
    }

    /**
     * Logs in an existing account.
     *
     * @param account the account details for login
     * @return the logged-in account with HTTP status 200 (OK)
     */
    @PostMapping("/login")
    public ResponseEntity<Account> login(@RequestBody Account account) {
        Account loggedIn = accountService.login(account);
        return ResponseEntity.ok().body(loggedIn);
    }

    /**
     * Creates a new message.
     *
     * @param message the message to be created
     * @return the created message with HTTP status 200 (OK)
     */
    @PostMapping("/messages")
    public ResponseEntity<Message> createMessage(@RequestBody Message message) {
        Message createdMessage = messageService.createMessage(message);
        return ResponseEntity.ok().body(createdMessage);
    }

    /**
     * Retrieves all messages.
     *
     * @return a list of all messages with HTTP status 200 (OK)
     */
    @GetMapping("/messages")
    public ResponseEntity<List<Message>> getAllMessages() {
        List<Message> messages = messageService.getAllMessages();
        return ResponseEntity.ok().body(messages);
    }

    /**
     * Retrieves a message by its ID.
     *
     * @param id the ID of the message to retrieve
     * @return the message with the specified ID and HTTP status 200 (OK)
     */
    @GetMapping("/messages/{id}")
    public ResponseEntity<Message> getMessageById(@PathVariable int id) {
        Message message = messageService.getMessageById(id);
        return ResponseEntity.ok().body(message);
    }

    /**
     * Deletes a message by its ID.
     *
     * @param id the ID of the message to delete
     * @return the number of rows affected (1 if successful) with HTTP status 200
     *         (OK)
     */
    @DeleteMapping("/messages/{id}")
    public ResponseEntity<Integer> deleteMessage(@PathVariable int id) {
        Integer rows = messageService.deleteMessage(id);
        return ResponseEntity.ok().body(rows);
    }

    /**
     * Updates a message by its ID.
     *
     * @param id      the ID of the message to update
     * @param request the details to update in the message
     * @return the number of rows affected (1 if successful) with HTTP status 200
     *         (OK)
     */
    @PatchMapping("/messages/{id}")
    public ResponseEntity<Integer> updateMessage(@PathVariable int id, @RequestBody MessageUpdateRequest request) {
        Integer rows = messageService.updateMessage(id, request);
        return ResponseEntity.ok().body(rows);
    }

    /**
     * Retrieves all messages by a specific account ID.
     *
     * @param id the ID of the account
     * @return a list of messages associated with the specified account and HTTP
     *         status 200 (OK)
     */
    @GetMapping("/accounts/{id}/messages")
    public ResponseEntity<List<Message>> getAllMessagesById(@PathVariable int id) {
        List<Message> messages = messageService.getAllMessagesById(id);
        return ResponseEntity.ok().body(messages);
    }
}
