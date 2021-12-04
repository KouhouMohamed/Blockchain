package ma.enset.blockchainservice.repositories;

import ma.enset.blockchainservice.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface TransactionRepository extends JpaRepository<Transaction,String> {
    public Collection<Transaction> findAllByBlock(String bock_id);
}
