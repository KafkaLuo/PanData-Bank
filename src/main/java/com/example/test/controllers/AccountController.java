/*
 * By Luo Jing
 * 
 * For local registration
 */

package com.example.test.controllers;

import com.example.test.models.entities.AccountEntity;
import com.example.test.models.services.AccountService;
import com.example.test.scalardb.PdLoadInitialData;
import com.scalar.db.exception.storage.ExecutionException;
import com.scalar.db.exception.transaction.TransactionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
public class AccountController {
    @Autowired
     AccountService accountService;

    @PostMapping("/account")
    public String createAccountEntity(AccountEntity account) throws TransactionException, IOException, ExecutionException {
        System.out.println("Register："+account);
        accountService.createAccountEntity(account);
        PdLoadInitialData.run(account);
        return "redirect:/login";
    }
}
