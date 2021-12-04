package ma.enset.blockchainservice.repositories;

import ma.enset.blockchainservice.entities.Block;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlockRepository extends JpaRepository<Block, String>{

    public List<Block> findAllByBlockchainId(String blockchain_id);
}
