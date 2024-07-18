/*
 * By Luo Jing
 * 
 * Framework
 */

package com.example.test.dao;

import com.example.test.models.entities.AccountEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Mapper
public interface AccountDao {
    public AccountEntity getAccountEntityById(String accountId);

    public boolean addAccountEntity(AccountEntity account);

    public boolean updateAccountEntity(AccountEntity account);

    public boolean deleteAccountEntity(String accountId);
}
