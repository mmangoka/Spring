package com.transaction.transaction.repositories;

import com.transaction.transaction.Model.CustomerTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerTransactionRepository extends JpaRepository<CustomerTransaction,Long>{

}
