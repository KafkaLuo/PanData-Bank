/*
 * By Luo Jing
 * Test
 */

package com.example.test;

import com.scalar.db.api.*;
import com.scalar.db.exception.transaction.TransactionException;
import com.scalar.db.io.Key;
import com.scalar.db.service.TransactionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;


@SpringBootTest
class TestApplicationTests {

    @Test
    void contextLoads() throws IOException, TransactionException {
            Path configFilePath = Paths.get("src/main/resources/scalardb.properties");
            TransactionFactory transactionFactory = TransactionFactory.create(configFilePath);
            DistributedTransactionManager transactionManager = transactionFactory.getTransactionManager();
            DistributedTransaction tx = transactionManager.start();

            try {
                Get poGet =
                        Get.newBuilder().namespace("mysqlBank").table("transactions")
                                .partitionKey(Key.ofText("transactionId", "aaaa/m1/202406301324"))
                                .build();
                Optional<Result> poResult = tx.get(poGet);
                System.out.println(poResult.toString());
            } catch (Exception e) {
                tx.abort();
                throw e;
            }
        }
    }
