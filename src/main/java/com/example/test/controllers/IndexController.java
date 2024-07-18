/*
 * Modified by Luo Jing
 * Append more user information
 * 
 * Reference: https://github.com/iamtatsuyamori/jjebank/blob/main/src/main/java/com/example/test/controllers/
 */

package com.example.test.controllers;

import java.io.IOException;

import com.example.test.models.services.AccountService;
import com.example.test.scalardb.PdBank;
import com.scalar.db.exception.transaction.TransactionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping()
public class IndexController {
    @Autowired
    AccountService accountService;
    @GetMapping()
    public String index(Authentication authentication,  Model model) throws IOException, TransactionException {
        String accountId = authentication.getName();
        String table = "";
        if (accountId.charAt(0) == 'p') {
            table = "postgres";
        } else {
            table = "mysql";
        }
        String accountName = PdBank.getAccountName(table, accountId);
        int balance = PdBank.getBalance(table, accountId);
        String accountEmail =accountService.getAccountEntityById(accountId).getAccountEmail();
        model.addAttribute("accountId", accountId);
        model.addAttribute("accountName", accountName);
        model.addAttribute("accountEmail", accountEmail);
        model.addAttribute("balance", balance);

        return "index";
    }
}
