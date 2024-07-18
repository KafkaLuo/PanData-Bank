/*
 * By Luo Jing
 * 
 * Add more functions (withdraw, deposit, transfer, query)
 */

package com.example.test.controllers;

import com.example.test.models.entities.TransactionEntity;
import com.example.test.models.entities.TransferRecordEntity;
import com.example.test.scalardb.PdBank;
import com.scalar.db.exception.transaction.TransactionException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.test.models.services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

@Controller
@RequiredArgsConstructor

public class TransactionController {
    private final TransactionService transactionService;

    @GetMapping("/transaction")
    public String showForm(Model model) {
        return "transaction_form";
    }

    @PostMapping("/transaction")
    public String showResult(
            @RequestParam("toAccountId") String toId,
            @RequestParam("amount") int amount,
            @ModelAttribute TransactionEntity transactionEntity,
            Authentication authentication,
            Model model) throws TransactionException, IOException {
        String fromId = authentication.getName();

        String fromTable = "";
        if (fromId.charAt(0) == 'p') {
            fromTable = "postgres";
        } else {
            fromTable = "mysql";
        }

        String toTable = "";
        if (toId.charAt(0) == 'p') {
            toTable = "postgres";
        } else {
            toTable = "mysql";

        }
        String toName = PdBank.getAccountName(toTable, toId);

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
        String date = now.format(formatter);

        String transactionId = PdBank.transfer(fromTable, fromId, toTable, toId, amount, date);

        model.addAttribute("transactionId", transactionId);
        model.addAttribute("toId", toId);
        model.addAttribute("toName", toName);
        model.addAttribute("amount", amount);
        model.addAttribute("date", date);

        return "transaction_result";
    }

    @GetMapping("/transferrecord")
    public String transferRecord(Model model) {
        // model.addAttribute("transactionList", new TransactionEntity());
        return "transferrecord_form";
    }

    @PostMapping("/transferrecord")
    public String showtransferRecord(
            @RequestParam("transactionId") String transactionId,
            Model model) throws TransactionException, IOException {
        TransferRecordEntity transferRecordEntity = PdBank.select(transactionId);
        if (transferRecordEntity.getFlag() == 1) {
            model.addAttribute("transferrecordId", transferRecordEntity.getTransactionId());
            model.addAttribute("toId", transferRecordEntity.getToId());
            model.addAttribute("amount", transferRecordEntity.getAmount());
            model.addAttribute("date", transferRecordEntity.getDate());
        } else {
            model.addAttribute("transferrecordId", "Already Deleted");
            model.addAttribute("toId", null);
            model.addAttribute("amount", null);
            model.addAttribute("date", null);
        }
        return "transferrecord_result";
    }

    @GetMapping("/bankDeposit")
    public String showbankDepositForm(Authentication authentication, Model model) {
        String accountId = authentication.getName();
        model.addAttribute("accountId", accountId);

        return "bankDeposit_form";
    }

    @PostMapping("/bankDeposit")
    public String showbankDepositResult(
            @RequestParam("toAccountId") String toId,
            @RequestParam("amount") int amount,
            @ModelAttribute TransactionEntity transactionEntity,
            Authentication authentication,
            Model model) throws TransactionException, IOException {
        String fromId = authentication.getName();

        String fromTable = "";
        if (fromId.charAt(0) == 'p') {
            fromTable = "postgres";
        } else {
            fromTable = "mysql";
        }

        String toTable = "";
        if (toId.charAt(0) == 'p') {
            toTable = "postgres";
        } else {
            toTable = "mysql";

        }
        String toName = PdBank.getAccountName(toTable, toId);

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
        String date = now.format(formatter);

        String transactionId = PdBank.bankDeposit(fromTable, fromId, toTable, toId, amount, date);

        model.addAttribute("transactionId", transactionId);
        model.addAttribute("toId", toId);
        model.addAttribute("toName", toName);
        model.addAttribute("amount", amount);
        model.addAttribute("date", date);

        return "bankDeposit_result";
    }

    @GetMapping("/bankWithdraw")
    public String showbankWithdrawForm(Authentication authentication, Model model) {
        String accountId = authentication.getName();
        model.addAttribute("accountId", accountId);
        return "bankWithdraw_form";
    }

    @PostMapping("/bankWithdraw")
    public String showbankWithdrawResult(
            @RequestParam("toAccountId") String toId,
            @RequestParam("amount") int amount,
            @ModelAttribute TransactionEntity transactionEntity,
            Authentication authentication,
            Model model) throws TransactionException, IOException {
        String fromId = authentication.getName();

        String fromTable = "";
        if (fromId.charAt(0) == 'p') {
            fromTable = "postgres";
        } else {
            fromTable = "mysql";
        }

        String toTable = "";
        if (toId.charAt(0) == 'p') {
            toTable = "postgres";
        } else {
            toTable = "mysql";

        }
        String toName = PdBank.getAccountName(toTable, toId);

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
        String date = now.format(formatter);

        String transactionId = PdBank.bankWithdraw(fromTable, fromId, toTable, toId, amount, date);

        model.addAttribute("transactionId", transactionId);
        model.addAttribute("toId", toId);
        model.addAttribute("toName", toName);
        model.addAttribute("amount", amount);
        model.addAttribute("date", date);

        return "bankWithdraw_result";
    }

}
