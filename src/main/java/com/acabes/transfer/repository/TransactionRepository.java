package com.acabes.transfer.repository;

import com.acabes.transfer.entity.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TransactionRepository extends MongoRepository<Transaction,String> {

    List<Transaction> findBySenderAccountOrReceiverAccount(String senderAccount, String receiverAccount);

}
