/**
 *  Modified by Luo Jing
 *
 *  References:
 *      https://scalardb.scalar-labs.com/docs/latest/api-guide/#get-operation
 *      https://github.com/iamtatsuyamori/jjebank/tree/main/src/main/java/com/example/test/scalardb
 */

package com.example.test.scalardb;

import com.example.test.models.entities.AccountEntity;
import com.scalar.db.api.*;
import com.scalar.db.exception.storage.ExecutionException;
import com.scalar.db.exception.transaction.CrudException;
import com.scalar.db.exception.transaction.TransactionException;
import com.scalar.db.io.Key;
import com.scalar.db.service.TransactionFactory;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

public class PdLoadInitialData {
    public static void run(AccountEntity account) throws IOException, TransactionException, ExecutionException {
        Path configFilePath = Paths.get("src/main/resources/scalardb.properties");
        TransactionFactory transactionFactory = TransactionFactory.create(configFilePath);
        DistributedTransactionAdmin transactionAdmin = transactionFactory.getTransactionAdmin();
        // transactionAdmin.truncateTable("mysqlBank", "accounts");
        // transactionAdmin.truncateTable("postgreBank", "accounts");

        // create new transaction for new account
        DistributedTransactionManager transactionManager = transactionFactory.getTransactionManager();
        DistributedTransaction tx = null;
        try {
            tx = transactionManager.start();
            // new account
            putAccount(tx, "mysqlBank", "accounts", account.getAccountId(), account.getAccountName(), 1000);

            tx.commit();
        } catch (TransactionException e) {
            if (tx != null) {
                tx.abort();
            }
            throw e;
        }
    }

    public static void run() throws IOException, TransactionException, ExecutionException {
        Path configFilePath = Paths.get("src/main/resources/scalardb.properties");
        TransactionFactory transactionFactory = TransactionFactory.create(configFilePath);

        DistributedTransactionAdmin transactionAdmin = transactionFactory.getTransactionAdmin();
        transactionAdmin.truncateTable("mysqlBank", "accounts");
        transactionAdmin.truncateTable("postgreBank", "accounts");

        DistributedTransactionManager transactionManager = transactionFactory.getTransactionManager();
        DistributedTransaction tx = null;
        try {
            tx = transactionManager.start();
            putAccount(tx, "mysqlBank", "accounts", "m1", "watanabe", 1000);
            putAccount(tx, "mysqlBank", "accounts", "m2", "tanaka", 2000);
            putAccount(tx, "mysqlBank", "accounts", "m3", "nakamura", 3000);
            putAccount(tx, "postgreBank", "accounts", "p1", "john", 10000);
            putAccount(tx, "postgreBank", "accounts", "p2", "annie", 20000);
            putAccount(tx, "postgreBank", "accounts", "p3", "tom", 30000);
            tx.commit();
        } catch (TransactionException e) {
            if (tx != null) {
                tx.abort();
            }
            throw e;
        }
    }

    public static void putAccount(
            DistributedTransaction tx,
            String namespace,
            String table,
            String id,
            String name,
            int balance) throws CrudException {
        Optional<Result> account = tx.get(
                Get.newBuilder()
                        .namespace(namespace)
                        .table(table)
                        .partitionKey(Key.ofText("accountId", id))
                        .build());
        if (!account.isPresent()) {
            tx.put(
                    Put.newBuilder()
                            .namespace(namespace)
                            .table(table)
                            .partitionKey(Key.ofText("accountId", id))
                            .textValue("accountName", name) // TODO
                            .intValue("balance", balance)
                            .build());
        }
    }
}
