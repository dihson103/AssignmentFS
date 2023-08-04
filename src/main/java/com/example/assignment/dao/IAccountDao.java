package com.example.assignment.dao;

import com.example.assignment.model.Account;

import java.util.List;
import java.util.Optional;

public interface IAccountDao {

    List<Account> getAccountsByUserName(String username);

    int addAccount(Account account);

    void deleteAccountByEmployeeId (Integer employeeId);

    void updateAccount (Account account);

    List<Account> findByUsername (String username);

    List<Account> findByEmail (String email);
}
