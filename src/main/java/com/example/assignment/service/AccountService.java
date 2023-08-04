package com.example.assignment.service;

import com.example.assignment.dao.IAccountDao;
import com.example.assignment.dao.impl.AccountDao;
import com.example.assignment.model.Account;
import com.example.assignment.utils.ValidationUtil;

import java.util.List;
import java.util.Optional;

public class AccountService {

    private static AccountService instance;
    private IAccountDao accountDao;
    public static AccountService getInstance(){
        if (instance == null){
            instance = new AccountService();
        }
        return instance;
    };

    private AccountService(){
        accountDao = new AccountDao();
    }

    private Account getAccountByUserName (String username){
        return accountDao.getAccountsByUserName(username).stream().findFirst().orElse(null);
    }

    public Account login (String username, String password){
        Account account = getAccountByUserName(username);
        if(account != null && password.equals(account.getPassword())){
            return account;
        }
        return null;
    }

    private void checkUsernameIsExist (Account account){
        List<Account> accounts = accountDao.findByUsername(account.getUsername());
        if(!accounts.isEmpty() && accounts.get(0).getId() != account.getId()){
            throw new IllegalArgumentException("User name is already existed!");
        }
    }

    private void checkEmailExist (Account account){
        List<Account> accounts = accountDao.findByEmail(account.getEmail());
        if(!accounts.isEmpty() && accounts.get(0).getId() != account.getId()){
            throw new IllegalArgumentException("Email is already existed!");
        }
    }

    private void validateAccount (Account account) {
        checkUsernameIsExist(account);
        checkEmailExist(account);
        ValidationUtil.validateEmpty(account.getUsername(), "Username can not be empty!");
        ValidationUtil.validateEmail(account.getEmail(), "Wrong email format!");
        ValidationUtil.validatePassword(account.getPassword(),
                "The password must have at least 6 characters, including uppercase, lowercase and number.");
    }

    public void addAccount(Account account){
        validateAccount(account);
        accountDao.addAccount(account);
    }

    public void updateAccount (Account account){
        validateAccount(account);
        accountDao.updateAccount(account);
    }

    public void deleteAccountByEmployeeId (Integer employeeId){
        accountDao.deleteAccountByEmployeeId(employeeId);
    }
}
