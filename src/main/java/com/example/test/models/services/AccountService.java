/*
 * By Luo Jing
 */

package com.example.test.models.services;

import com.example.test.dao.AccountDao;
import com.example.test.models.entities.AccountEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService  implements UserDetailsService {
    private final  AccountDao accountDao;

    public AccountService( AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public AccountEntity getAccountEntityById(String accountId) {
        return accountDao.getAccountEntityById(accountId);
    }

    public boolean createAccountEntity(AccountEntity account) {
        return accountDao.addAccountEntity(account);
    }

    public boolean updateAccountEntity(AccountEntity account) {
        return accountDao.updateAccountEntity(account);
    }

    public boolean deleteAccountEntity(String accountId) {
        return accountDao.deleteAccountEntity(accountId);
    }


    @Override
    public UserDetails loadUserByUsername(String accountId) throws UsernameNotFoundException {
        //Query users by username
        AccountEntity account=accountDao.getAccountEntityById(accountId);
        if (account == null) {
            throw new RuntimeException("USER NOT FOUND");
        }
        //Add new account
        UserDetails user1 =  User.withDefaultPasswordEncoder()
                        .username(account.getAccountId())
                        .password(account.getAccountPassword())
                        .roles("USER")
                        .build();
        return user1;
    }
}
