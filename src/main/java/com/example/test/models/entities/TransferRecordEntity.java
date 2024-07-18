/*
 * By Luo Jing
 */
package com.example.test.models.entities;

import java.io.Serializable;

public class TransferRecordEntity {
        
        private String toId;
        private String date;
        private int amount;
        private int flag;
        private String fromId;
        private String transactionId;
        private String fromTable;
        private String toTable;

        
        public TransferRecordEntity() {
            
        }

        
        public TransferRecordEntity(String toId, String date, int amount, int flag, String fromId,
                                   String transactionId, String fromTable, String toTable) {
            this.toId = toId;
            this.date = date;
            this.amount = amount;
            this.flag = flag;
            this.fromId = fromId;
            this.transactionId = transactionId;
            this.fromTable = fromTable;
            this.toTable = toTable;
        }

        // Getter and Setter
        public String getToId() {
            return toId;
        }

        public void setToId(String toId) {
            this.toId = toId;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public int getFlag() {
            return flag;
        }

        public void setFlag(int flag) {
            this.flag = flag;
        }

        public String getFromId() {
            return fromId;
        }

        public void setFromId(String fromId) {
            this.fromId = fromId;
        }

        public String getTransactionId() {
            return transactionId;
        }

        public void setTransactionId(String transactionId) {
            this.transactionId = transactionId;
        }

        public String getFromTable() {
            return fromTable;
        }

        public void setFromTable(String fromTable) {
            this.fromTable = fromTable;
        }

        public String getToTable() {
            return toTable;
        }

        public void setToTable(String toTable) {
            this.toTable = toTable;
        }


        @Override
        public String toString() {
            return "TransferRecordEntity{" +
                    "toId='" + toId + '\'' +
                    ", date='" + date + '\'' +
                    ", amount=" + amount +
                    ", flag=" + flag +
                    ", fromId='" + fromId + '\'' +
                    ", transactionId='" + transactionId + '\'' +
                    ", fromTable='" + fromTable + '\'' +
                    ", toTable='" + toTable + '\'' +
                    '}';
        }
    }

