package ma.enset.blockchainservice.repositories;

import ma.enset.blockchainservice.entities.Blockchain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlockchainRepository extends JpaRepository<Blockchain,String> {
    public Blockchain findByName(String name);
}
