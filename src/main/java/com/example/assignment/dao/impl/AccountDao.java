package com.example.assignment.dao.impl;

import com.example.assignment.dao.IAccountDao;
import com.example.assignment.mapper.AccountMapper;
import com.example.assignment.model.Account;

import java.util.List;

public class AccountDao extends AbstractDao<Account> implements IAccountDao {

    private AccountMapper accountMapper = new AccountMapper();

    @Override
    public List<Account> getAccountsByUserName(String userName){
        String sql = "select * from Account where account = ?";
        return query(sql, accountMapper, userName);
    }

    @Override
    public int addAccount(Account account){
        String sql = "Insert into Account (account, email, password, status, employee_id) values (?,?,?,?,?)";
        return insert(sql, account.getUsername(), account.getEmail(), account.getPassword(),
                account.getStatus(), account.getEmployeeId());
    }

    @Override
    public void deleteAccountByEmployeeId(Integer employeeId) {
        String sql = "Delete from Account where employee_id = ?";
        update(sql, employeeId);
    }

    @Override
    public void updateAccount(Account account) {
        StringBuilder sql = new StringBuilder();
        sql.append("update Account ");
        sql.append("set account = ?, email = ?, [password] = ?, [status] = ? ");
        sql.append("where account_id = ?");
        update(sql.toString(), account.getUsername(), account.getEmail(), account.getPassword(),
                account.getStatus() ? 1 : 0, account.getId());
    }

    @Override
    public List<Account> findByUsername(String username) {
        String sql = "Select * from Account where account = ?";
        return query(sql, accountMapper, username);
    }

    @Override
    public List<Account> findByEmail(String email) {
        String sql = "Select * from Account Where email = ?";
        return query(sql, accountMapper, email);
    }

}
