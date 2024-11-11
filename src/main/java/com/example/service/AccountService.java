package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;
import com.example.exception.*;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account registerAccount(Account account) {

        // Account with that username does not already exist
        for (Account acc : accountRepository.findAll()) {
            if (account.getUsername().equals(acc.getUsername())) {
                throw new DuplicateUsernameException("An account with this username already exists.");
            }
        }

        // Password length at least 3 and username not blank
        if (account.getPassword().length() >= 4 &&
                account.getUsername() != "") {
            return accountRepository.save(account);
        } else {
            throw new BadRequestException(
                    "Username must not be blank and password must be at least 4 characters long.");
        }
    }

}
