package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;
import com.example.exception.*;

/**
 * Service class responsible for handling business logic related to accounts.
 */
@Service
public class AccountService {

    private final AccountRepository accountRepository;

    /**
     * Constructs a new AccountService with the given repository.
     *
     * @param accountRepository the repository for accessing account data
     */
    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    /**
     * Registers a new account after validating the username and password.
     *
     * @param account the account to be registered
     * @return the saved account
     * @throws DuplicateUsernameException if an account with the same username already exists
     * @throws BadRequestException if the username is blank or the password is shorter than 4 characters
     */
    public Account registerAccount(Account account) {

        // Account with that username does not already exist
        for (Account existingAccount : accountRepository.findAll()) {
            if (account.getUsername().equals(existingAccount.getUsername())) {
                throw new DuplicateUsernameException("An account with this username already exists.");
            }
        }

        // Password length at least 4 and username not blank
        if (account.getPassword().length() >= 4 &&
                account.getUsername() != "") {
            return accountRepository.save(account);
        } else {
            throw new BadRequestException(
                    "Username must not be blank and password must be at least 4 characters long.");
        }
    }

    /**
     * Logs in an account by validating the username and password.
     *
     * @param account the account containing the username and password to log in
     * @return the logged-in account if the credentials are valid
     * @throws UnauthorizedAccessException if no matching account is found or credentials are incorrect
     */
    public Account login(Account account) {

        // Checking if another account exists with matching username and password
        for (Account existingAccount : accountRepository.findAll()) {
            if (account.getUsername().equals(existingAccount.getUsername()) &&
                    account.getPassword().equals(existingAccount.getPassword())) {
                return existingAccount;
            }
        }
        throw new UnauthorizedAccessException("Incorrect username or password");
    }
}
