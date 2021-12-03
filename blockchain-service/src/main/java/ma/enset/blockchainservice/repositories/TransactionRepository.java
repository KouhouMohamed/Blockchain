package ma.enset.blockchainservice.repositories;

import ma.enset.blockchainservice.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction,String> {
}
