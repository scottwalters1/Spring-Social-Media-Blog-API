package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Account;
import com.example.service.AccountService;
import com.example.exception.*;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */

@RestController
public class SocialMediaController {

    private final AccountService accountService;

    @Autowired
    public SocialMediaController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/register")
    public ResponseEntity createAccount(@RequestBody Account account) {

        Account newAccount = null;
        try {
            newAccount = accountService.registerAccount(account);
        } catch (DuplicateUsernameException e) {
            // not sure about body args
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username is already taken.");
        } catch (BadRequestException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid account registration data.");
        } 
        return ResponseEntity.status(200).body(newAccount);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody Account account) {
        
        Account loggedIn = null;
        try {
            loggedIn = accountService.login(account);
        } catch (BadRequestException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        }
        return ResponseEntity.status(HttpStatus.OK).body(loggedIn);
    }
}
